package vn.com.greenacademy.shopping.Fragment.MyShopping;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Magazine.MagazineFragment;
import vn.com.greenacademy.shopping.Fragment.MyShopping.TaiKhoan.DangKyFragment;
import vn.com.greenacademy.shopping.Fragment.MyShopping.TaiKhoan.DangNhapFragment;
import vn.com.greenacademy.shopping.Handle.HandleData.GioHang.GioHangHandler;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.GioHang.ParseGioHang;
import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.GioHang.SanPhamGioHangAdapter;
import vn.com.greenacademy.shopping.Handle.HandleUi.Model.Support;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Interface.ItemClickCallBack;
import vn.com.greenacademy.shopping.Interface.ItemLongClickCallBack;
import vn.com.greenacademy.shopping.MainActivity;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPhamGioHang;
import vn.com.greenacademy.shopping.Network.AsynTask.DataServerAsyncTask;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SharePreference.MySharedPreferences;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class GioHangFragment extends Fragment implements View.OnClickListener, DataCallBack, ItemLongClickCallBack {
    private View root;
    private TextView tvTenTaiKhoan;
    private TextView tvSoLuong;
    private TextView tvTongTienTruocGiamGia;
    private TextView tvTongGiaGiam;
    private TextView tvTongTienSauGiamGia;
    private TextView tvTongTien;
    private TextView tvTongTienBottomBar;
    private RecyclerView vListSanPham;

    private MySharedPreferences mySharedPref;
    private BaseFragment baseFragment;
    private ArrayList<SanPhamGioHang> mListSanPham = new ArrayList<>();
    private GioHangHandler gioHangHandler;

    public GioHangFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySharedPref = new MySharedPreferences(getActivity(), SupportKeyList.SHAREDPREF_TEN_FILE);
        baseFragment = new BaseFragment(getActivity(), getActivity().getSupportFragmentManager());
        if (mySharedPref.getDaDangNhap()) {
            gioHangHandler = new GioHangHandler(getActivity(), this);
            gioHangHandler.getGioHangTuServer();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mySharedPref.getGioHang() != null) {
            root = inflater.inflate(R.layout.fragment_gio_hang_co_san_pham, container, false);
            tvTenTaiKhoan = (TextView) root.findViewById(R.id.tvTenTaiKhoan_FragmentGioHang);
            tvSoLuong = (TextView) root.findViewById(R.id.tong_so_luong_san_pham_gio_hang);
            tvTongTienTruocGiamGia = (TextView) root.findViewById(R.id.tong_tien_ban_dau_gio_hang);
            tvTongGiaGiam = (TextView) root.findViewById(R.id.tong_gia_giam_gio_hang);
            tvTongTienSauGiamGia = (TextView) root.findViewById(R.id.tong_tien_sau_giam_gia_gio_hang);
            tvTongTien = (TextView) root.findViewById(R.id.tong_tien_gio_hang);
            tvTongTienBottomBar = (TextView) root.findViewById(R.id.bottom_bar_tong_tien_gio_hang);
            vListSanPham = (RecyclerView) root.findViewById(R.id.recycler_view_list_san_pham_gio_hang);

            root.findViewById(R.id.btn_xac_nhan_gio_hang).setOnClickListener(this);
        }
        else {
            root = inflater.inflate(R.layout.fragment_gio_hang, container, false);
            setUpUiKhongSanPham();
        }

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();
        MainActivity.tvTenMuc.setVisibility(View.GONE);
        return root;
    }

    private void setUpUiCoSanPham() {
        tvTenTaiKhoan.setText(getString(R.string.title_message) + " " + mySharedPref.getTenTaiKhoan());

        //List sản phẩm
        vListSanPham.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        vListSanPham.setAdapter(new SanPhamGioHangAdapter(getActivity(), mListSanPham, this));
        vListSanPham.setNestedScrollingEnabled(false);

        //Hiển thị số lượng
        int countSoLuong = 0;
        for (int i = 0; i < mListSanPham.size(); i++) {
            countSoLuong += mListSanPham.get(i).getSoLuong();
        }
        tvSoLuong.setText(String.valueOf(countSoLuong));

        //Hiển thị giá tổng cộng
        long sumGiaTruocKhiGiam = 0;
        long sumGiaKhuyenMai = 0;
        long sumTong = 0;
        for (int i = 0; i < mListSanPham.size(); i++) {
            if (mListSanPham.get(i).getGiaGiam() != 0)
                sumGiaKhuyenMai += mListSanPham.get(i).getGiaGiam();
            sumGiaTruocKhiGiam += mListSanPham.get(i).getGiaGoc();
        }
        tvTongTienTruocGiamGia.setText(SanPhamHandler.chuyenGia(sumGiaTruocKhiGiam));
        if (sumGiaKhuyenMai != 0) {
            String tongGiamGia = "-" + SanPhamHandler.chuyenGia(sumGiaKhuyenMai);
            SpannableString spanString = new SpannableString(tongGiamGia);
            //Xử lý hiển thị thông tin trên text
            spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getActivity(), android.R.color.holo_red_dark)), 0, tongGiamGia.length(), 0);
            tvTongGiaGiam.setText(spanString);
        }
        else
            root.findViewById(R.id.thong_tin_khuyen_mai).setVisibility(View.GONE);

        sumTong = sumGiaTruocKhiGiam - sumGiaKhuyenMai;
        tvTongTienSauGiamGia.setText(SanPhamHandler.chuyenGia(sumTong));
        tvTongTien.setText(SanPhamHandler.chuyenGia(sumTong));
        tvTongTienBottomBar.setText("Tổng:" + "\n" + SanPhamHandler.chuyenGia(sumTong));
    }

    private void setUpUiKhongSanPham() {
        TextView nutInspiration = (TextView) root.findViewById(R.id.nut_inspiration_gio_hang);
        TextView nutShopping = (TextView) root.findViewById(R.id.nut_shopping_gio_hang);

        TextView tvTenTaiKhoan = (TextView) root.findViewById(R.id.tvTenTaiKhoan_FragmentGioHang);

        root.findViewById(R.id.btn_dang_ky_fragment_gio_hang).setOnClickListener(this);
        root.findViewById(R.id.btn_dang_nhap_fragment_gio_hang).setOnClickListener(this);
        nutInspiration.setOnClickListener(this);
        nutShopping.setOnClickListener(this);

        //Check đăng nhập
        if(mySharedPref.getDaDangNhap()) {
            tvTenTaiKhoan.setText(getString(R.string.title_message) + " " + mySharedPref.getTenTaiKhoan());
            root.findViewById(R.id.dang_nhap_gio_hang).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dang_ky_fragment_gio_hang:
                baseFragment.ChuyenFragment(new DangKyFragment(), SupportKeyList.TAG_FRAGMENT_DANG_KY, true);
                break;
            case R.id.btn_dang_nhap_fragment_gio_hang:
                baseFragment.ChuyenFragment(new DangNhapFragment(), SupportKeyList.TAG_FRAGMENT_DANG_NHAP, true);
                break;
            case R.id.nut_inspiration_gio_hang:
                baseFragment.ChuyenFragment(new MagazineFragment(), SupportKeyList.TAG_FRAGMENT_MAGAZINE, true);
                break;
            case R.id.nut_shopping_gio_hang:
                baseFragment.XoaFragment();
                break;
            case R.id.btn_xac_nhan_gio_hang:
                Toast.makeText(getActivity(), "Xác nhận giỏ hàng", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void KetQua(String result, @Nullable Bundle bundle) {
        switch (result){
            case SupportKeyList.LOI_DATA_SERVER:
                Toast.makeText(getActivity(), getString(R.string.toast_loi_data_server), Toast.LENGTH_SHORT).show();
                break;
            case SupportKeyList.LOI_DATA:
                Toast.makeText(getActivity(), getString(R.string.toast_loi_data), Toast.LENGTH_SHORT).show();
                break;
            case SupportKeyList.LAY_DATA_THANH_CONG:
                gioHangHandler.luuGioHangTuServer(bundle);
                mListSanPham = gioHangHandler.getGioHang();
                if (mListSanPham != null)
                    setUpUiCoSanPham();
                break;
            case SupportKeyList.CAP_NHAT_THANH_CONG:
                Toast.makeText(getActivity(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                if (mListSanPham.size() != 0)
                    setUpUiCoSanPham();
                else
                    baseFragment.ChuyenFragment(new GioHangFragment(), SupportKeyList.TAG_FRAGMENT_GIO_HANG, false);
                break;
            case SupportKeyList.CAP_NHAT_THAT_BAI:
                Toast.makeText(getActivity(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void longClickItem(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Xóa sản phẩm khỏi giỏ hàng ?");
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gioHangHandler.XoaSanPhamGioHang(mListSanPham.get(position).getIdSanPham());
                mListSanPham.remove(position);
            }
        });
        builder.create().show();
    }
}

