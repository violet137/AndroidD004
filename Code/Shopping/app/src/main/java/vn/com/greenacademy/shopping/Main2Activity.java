package vn.com.greenacademy.shopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.com.greenacademy.shopping.Fragment.MenuMain;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new MenuMain()).commit();
    }
}
