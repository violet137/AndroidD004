package vn.com.greenacademy.shopping;

import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Adapter.AdapterSlideMenu;
import vn.com.greenacademy.shopping.Model.ModeSlideMenu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView lv_item_slide_menu;

    ArrayList<ModeSlideMenu> arrayModeSlideMenus;
    int[] arrIcon;
    String[] arrName;
    ModeSlideMenu modeSlideMenu;
    AdapterSlideMenu adapterSlideMenu;

    @Override()
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View viewNav = findViewById(R.id.nav_view);
        viewNav.findViewById(R.id.ivUser_nav_hear).setOnClickListener(this);
        viewNav.findViewById(R.id.tvName_nav_hear).setOnClickListener(this);
        viewNav.findViewById(R.id.tvEmail_nav_hear).setOnClickListener(this);

        lv_item_slide_menu = (ListView) findViewById(R.id.lv_item_slide_menu);

        lv_item_slide_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                modeSlideMenu = arrayModeSlideMenus.get(position);
                itemClickListener(position);

            }
        });

        //load data
        LoadData();
        displayListview();
    }

    private void itemClickListener(int position) {
        String temp ;
        switch (position){
            case 0:
                temp = "Products";
                break;
            case 1:
                temp = "Ladies";
                break;
            case 2:
                temp = "Men";
                break;
            case 3:
                temp = "Kids";
                break;
            case 4:
                temp = "Home";
                break;
            case 5:
                temp = "Magazine";
                break;
            case 6:
                temp = "Wish list";
                break;
            case 7:
                temp = "My Shopping";
                break;
            case 8:
                temp = "Support";
                break;
            case 9:
                temp = "Find a store";
                break;
            case 10:
                temp = "Newsletter";
                break;
            default:
                temp = "null";
                break;
        }
        Toast.makeText(this, temp, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void displayListview() {
        adapterSlideMenu = new AdapterSlideMenu(this, R.layout.item_slide_menu, arrayModeSlideMenus);
        lv_item_slide_menu.setAdapter(adapterSlideMenu);
    }

    private void LoadData() {
        arrName = getResources().getStringArray(R.array.name_slide_menu);
        TypedArray listAnh = getResources().obtainTypedArray(R.array.icon_slide_menu);
        arrIcon = new int[arrName.length];
        for(int i=0; i< arrName.length;i++){
            arrIcon[i]=listAnh.getResourceId(i,-1);
        }
        // danh sach bai hat
        arrayModeSlideMenus = new ArrayList<ModeSlideMenu>();
        for(int i = 0; i< arrName.length; i++){
            ModeSlideMenu modeSlideMenu = new ModeSlideMenu();
            modeSlideMenu.setTen(arrName[i]);
            modeSlideMenu.setIcon(arrIcon[i]);
            arrayModeSlideMenus.add(modeSlideMenu);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivUser_nav_hear:
                Toast.makeText(this, "Photo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvName_nav_hear:
                Toast.makeText(this, "Name", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvEmail_nav_hear:
                Toast.makeText(this, "Email", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
