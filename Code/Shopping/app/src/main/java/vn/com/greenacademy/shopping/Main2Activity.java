package vn.com.greenacademy.shopping;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Adapter.AdapterMenuMain;
import vn.com.greenacademy.shopping.Adapter.AdapterSlideMenu;
import vn.com.greenacademy.shopping.Fragment.MenuMainFragment;
import vn.com.greenacademy.shopping.Model.ModeMenuMain;
import vn.com.greenacademy.shopping.Model.ModeSlideMenu;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new MenuMainFragment()).commit();
    }
}