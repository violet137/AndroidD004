package vn.com.greenacademy.shopping.Fragment.TaiKhoan;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.com.greenacademy.shopping.Data.MySharedPreferences;
import vn.com.greenacademy.shopping.Fragment.Main.MainFragment;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstScreenFragment extends Fragment {
    private MySharedPreferences mySharedPreferences;
    private Context context;

    public FirstScreenFragment(Context context) {
        this.context = context;
        mySharedPreferences = new MySharedPreferences(context, SupportKeyList.PREFERENCES_TEN_FILE);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_first_screen, container, false);

        //Load màn hình flash
        Thread threadFirstScreen = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //Sau 3s chuyển sang màn hình đăng nhập
                    Thread.sleep(3000);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                            //Kiểm tra user đã đăng nhập trước đó chưa
                            if (!mySharedPreferences.getSHAREDPREF_LUU_DANG_NHAP())
                                transaction.replace(R.id.content_Main, new DangNhapFragment(context)).commit();
                            else
                                transaction.replace(R.id.content_Main, new MainFragment(context)).commit();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadFirstScreen.start();
        return root;
    }

}
