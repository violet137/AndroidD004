package vn.com.greenacademy.shopping.Fragment.TaiKhoan;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.greenacademy.shopping.Data.MySharedPreferences;
import vn.com.greenacademy.shopping.Fragment.Main.MainFragment;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SPlashScreenFragment extends Fragment {
    private MySharedPreferences mySharedPreferences;
    private Context context;

    private static final int DELAY_TIME = 2000;

    public SPlashScreenFragment(Context context) {
        this.context = context;
        mySharedPreferences = new MySharedPreferences(context, SupportKeyList.SHAREDPREF_TEN_FILE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        //Load màn hình flash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                //Kiểm tra user đã đăng nhập trước đó chưa
                if (!mySharedPreferences.getLUU_DANG_NHAP())
                    transaction.replace(R.id.content_Main, new DangNhapFragment(context)).commit();
                else
                    transaction.replace(R.id.content_Main, new MainFragment(context)).commit();
            }
        }, DELAY_TIME);
        return root;
    }

}
