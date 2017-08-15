package vn.com.greenacademy.shopping.Fragment.MyShopping;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.com.greenacademy.shopping.Fragment.Magazine.MagazineFragment;
import vn.com.greenacademy.shopping.Fragment.MyShopping.TaiKhoan.DangKyFragment;
import vn.com.greenacademy.shopping.Fragment.MyShopping.TaiKhoan.DangNhapFragment;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SharePreference.MySharedPreferences;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class GioHangFragment extends Fragment implements View.OnClickListener {
    private MySharedPreferences mySharedPref;
    private BaseFragment baseFragment;

    public GioHangFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySharedPref = new MySharedPreferences(getActivity(), SupportKeyList.SHAREDPREF_TEN_FILE);
        baseFragment = new BaseFragment(getActivity(), getActivity().getSupportFragmentManager());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentTextView tvTenTaiKhoan = (TextView) root.findViewById(R.id.tvTenTaiKhoan_FragmentGioHang);[]\
        View root = inflater.inflate(R.layout.fragment_gio_hang, container, false);
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

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();
        return root;
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
}

