package vn.com.greenacademy.shopping.Fragment.MyShopping;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Magazine.MagazineFragment;
import vn.com.greenacademy.shopping.Fragment.MyShopping.TaiKhoan.DangKyFragment;
import vn.com.greenacademy.shopping.Fragment.MyShopping.TaiKhoan.DangNhapFragment;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
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
    private MySharedPreferences mySharedPref;
    private BaseFragment baseFragment;
    private ArrayList<SanPham> mListSanPham = new ArrayList<>();

    public GioHangFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySharedPref = new MySharedPreferences(getActivity(), SupportKeyList.SHAREDPREF_TEN_FILE);
        baseFragment = new BaseFragment(getActivity(), getActivity().getSupportFragmentManager());

        DataServerAsyncTask dataServerAsyncTask = new DataServerAsyncTask(this);
        dataServerAsyncTask.execute(SupportKeyList.API_GET_GIO_HANG, ServerUrl.UrlGetGioHang + mySharedPref.getEmail(), "GET");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root;
        if (mySharedPref.getDaDangNhap() && mySharedPref.getGioHang() != null){
            root = inflater.inflate(R.layout.fragment_gio_hang_co_san_pham, container, false);
            setUpUiCoSanPham(root);
        }
        else {
            root = inflater.inflate(R.layout.fragment_gio_hang, container, false);
            setUpUiKhongSanPham(root);
        }

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();
        return root;
    }

    private void setUpUiCoSanPham(View root) {
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

        }
    }
}

