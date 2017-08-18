package vn.com.greenacademy.shopping.Fragment.MyShopping;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Magazine.MagazineFragment;
import vn.com.greenacademy.shopping.Fragment.MyShopping.TaiKhoan.DangKyFragment;
import vn.com.greenacademy.shopping.Fragment.MyShopping.TaiKhoan.DangNhapFragment;
import vn.com.greenacademy.shopping.Handle.HandleData.GioHang.GioHangHandler;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.GioHang.ParseGioHang;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.GioHang.SanPhamGioHangAdapter;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
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
public class GioHangFragment extends Fragment implements View.OnClickListener, DataCallBack {
    private View root;
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
        if (mySharedPref.getGioHang() != null)
            root = inflater.inflate(R.layout.fragment_gio_hang_co_san_pham, container, false);
        else {
            root = inflater.inflate(R.layout.fragment_gio_hang, container, false);
            setUpUiKhongSanPham(root);
        }

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();
        return root;
    }

    private void setUpUiCoSanPham() {
        RecyclerView vListSanPham = (RecyclerView) root.findViewById(R.id.recycler_view_list_san_pham_gio_hang);

        vListSanPham.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        vListSanPham.setAdapter(new SanPhamGioHangAdapter(getActivity(), mListSanPham));
        vListSanPham.setNestedScrollingEnabled(false);
    }

    private void setUpUiKhongSanPham(View root) {
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
                gioHangHandler.luuGioHang(bundle);
                mListSanPham = gioHangHandler.getGioHang();
                if (mListSanPham != null)
                    setUpUiCoSanPham();
                break;
        }
    }
}

