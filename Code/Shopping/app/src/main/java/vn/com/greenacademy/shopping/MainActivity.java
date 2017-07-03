package vn.com.greenacademy.shopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.com.greenacademy.shopping.Fragment.TaiKhoan.DangNhapFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Load màn hình flash
        Thread threadFirstScreen = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //Sau 5s chuyển sang màn hình đăng nhập
                    Thread.sleep(5000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getSupportFragmentManager().beginTransaction().replace(R.id.content_Main, new DangNhapFragment()).commit();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadFirstScreen.start();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() <= 0)
            super.onBackPressed();
        else
            getSupportFragmentManager().popBackStack();
    }
}
