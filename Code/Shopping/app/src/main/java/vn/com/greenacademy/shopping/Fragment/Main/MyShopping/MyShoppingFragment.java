package vn.com.greenacademy.shopping.Fragment.Main.MyShopping;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.com.greenacademy.shopping.Data.MySharedPreferences;
import vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan.DangNhapFragment;
import vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan.TaiKhoanFragment;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyShoppingFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private MySharedPreferences mySharedPref;

    private static final String TAG_TAI_KHOAN = "TaiKhoan";
    private static final String TAG_DANG_NHAP = "DangNhap";

    public MyShoppingFragment(Context context) {
        this.context = context;
        mySharedPref = new MySharedPreferences(context, SupportKeyList.SHAREDPREF_TEN_FILE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_shopping, container, false);
        root.findViewById(R.id.tvTaiKhoan_MyShoppingFragment).setOnClickListener(this);
        TextView tvTenTaiKhoan = (TextView) root.findViewById(R.id.tvTenTaiKhoan_FragmentMyShopping);

        //Hiện tên tài khoản tren title bar
        if(mySharedPref.getDA_DANG_NHAP())
            tvTenTaiKhoan.setText(mySharedPref.getTEN_TAI_KHOAN());


        getActivity().invalidateOptionsMenu();
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvTaiKhoan_MyShoppingFragment:
                //Kiểm tra người dùng đã đăng nhập trước đó chưa
                if(mySharedPref.getDA_DANG_NHAP())
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new TaiKhoanFragment(context)).addToBackStack(TAG_TAI_KHOAN).commit();
                else
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new DangNhapFragment(context)).addToBackStack(TAG_DANG_NHAP).commit();
                break;
        }
    }
}
