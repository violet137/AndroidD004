package vn.com.greenacademy.shopping.Fragment.Main.MyShopping;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;
import vn.com.greenacademy.shopping.Util.SharePreference.MySharedPreferences;
import vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan.DangNhapFragment;
import vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan.DangNhapKhongLuuFragment;
import vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan.TaiKhoanFragment;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyShoppingFragment extends Fragment implements View.OnClickListener {
    private MySharedPreferences mySharedPref;
    private BaseFragment baseFragment;

    public MyShoppingFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_shopping, container, false);
        root.findViewById(R.id.tvTaiKhoan_MyShoppingFragment).setOnClickListener(this);
        TextView tvTenTaiKhoan = (TextView) root.findViewById(R.id.tvTenTaiKhoan_FragmentMyShopping);

        baseFragment = new BaseFragment(getActivity(), getActivity().getSupportFragmentManager());
        mySharedPref = new MySharedPreferences(getActivity(), SupportKeyList.SHAREDPREF_TEN_FILE);

        //Hiện tên tài khoản tren title bar
        if(mySharedPref.getDaDangNhap())
            tvTenTaiKhoan.setText(getString(R.string.title_message) + " " + mySharedPref.getTenTaiKhoan());

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvTaiKhoan_MyShoppingFragment:
                //Kiểm tra người dùng đã đăng nhập trước đó chưa
                if(mySharedPref.getDaDangNhap()) {
                    if (!mySharedPref.getLuuDangNhap())
                        baseFragment.ChuyenFragment(new DangNhapKhongLuuFragment(), SupportKeyList.TAG_FRAGMENT_DANG_NHAP_KHONG_LUU, true);
                    else
                        baseFragment.ChuyenFragment(new TaiKhoanFragment(), SupportKeyList.TAG_FRAGMENT_TAI_KHOAN, true);
                } else
                    baseFragment.ChuyenFragment(new DangNhapFragment(), SupportKeyList.TAG_FRAGMENT_DANG_NHAP, true);
                break;
        }
    }
}
