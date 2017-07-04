package vn.com.greenacademy.shopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.com.greenacademy.shopping.Fragment.TaiKhoan.DangNhapFragment;
import vn.com.greenacademy.shopping.Fragment.TaiKhoan.FirstScreenFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_Main, new FirstScreenFragment(this)).commit();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() <= 0)
            super.onBackPressed();
        else
            getSupportFragmentManager().popBackStack();
    }
}
