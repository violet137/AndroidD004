package vn.com.greenacademy.shopping.Fragment.Main.MyShopping;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.com.greenacademy.shopping.Data.MySharedPreferences;
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

    private static final String TAG_TAI_KHOAN = "TaiKhoan";
    private static final String TAG_DANG_NHAP = "DangNhap";
    private static final String TAG_DANG_NHAP_KHONG_LUU = "DangNhapKhongLuu";

    public MyShoppingFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_shopping, container, false);
        root.findViewById(R.id.tvTaiKhoan_MyShoppingFragment).setOnClickListener(this);
        TextView tvTenTaiKhoan = (TextView) root.findViewById(R.id.tvTenTaiKhoan_FragmentMyShopping);

        mySharedPref = new MySharedPreferences(getActivity(), SupportKeyList.SHAREDPREF_TEN_FILE);
        //Hiện tên tài khoản tren title bar
        if(mySharedPref.getDA_DANG_NHAP())
            tvTenTaiKhoan.setText(getString(R.string.title_message) + " " + mySharedPref.getTEN_TAI_KHOAN());


        getActivity().supportInvalidateOptionsMenu();
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvTaiKhoan_MyShoppingFragment:
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //Kiểm tra người dùng đã đăng nhập trước đó chưa
                if(mySharedPref.getDA_DANG_NHAP()) {
                    if (!mySharedPref.getLUU_DANG_NHAP())
                        transaction.replace(R.id.content_main, new DangNhapKhongLuuFragment()).addToBackStack(TAG_DANG_NHAP_KHONG_LUU).commit();
                    else
                        transaction.replace(R.id.content_main, new TaiKhoanFragment()).addToBackStack(TAG_TAI_KHOAN).commit();
                } else
                    transaction.replace(R.id.content_main, new DangNhapFragment()).addToBackStack(TAG_DANG_NHAP).commit();
                break;
        }
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.search_toolbar).setVisible(false);
        menu.findItem(R.id.dang_nhap_toolbar).setVisible(true);
    }
}
