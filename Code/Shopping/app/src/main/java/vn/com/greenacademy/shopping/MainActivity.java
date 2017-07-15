package vn.com.greenacademy.shopping;

import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import vn.com.greenacademy.shopping.Fragment.Main.MyShopping.MyShoppingFragment;
import vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan.DangNhapFragment;
import vn.com.greenacademy.shopping.Fragment.SplashScreenFragment;
import vn.com.greenacademy.shopping.Handle.HandleData.DataHandler;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Model.ModeSlideMenu;
import vn.com.greenacademy.shopping.Util.SharePreference.MySharedPreferences;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener, DataCallBack {
    ListView lv_item_slide_menu;

    ArrayList<ModeSlideMenu> arrayModeSlideMenus;
    int[] arrIcon;
    String[] arrName;
    ModeSlideMenu modeSlideMenu;
    AdapterSlideMenu adapterSlideMenu;
    /////////
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    private BaseFragment baseFragment;
    private DataHandler dataHandler;
    private MySharedPreferences mySharedPref;

    @Override()
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Config custom appbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_menu_main);

        navigationView.setNavigationItemSelectedListener(this);

        mySharedPref = new MySharedPreferences(this, SupportKeyList.SHAREDPREF_TEN_FILE);
        dataHandler = new DataHandler(this);
        baseFragment = new BaseFragment(getSupportFragmentManager());

        //Hiện icon menu va đồng bộ actionbar với menu
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Chạy màn hình splash
        baseFragment.ChuyenFragment(new SplashScreenFragment(getSupportActionBar(), drawerLayout), null, false);






        //////////
        View viewNav = findViewById(R.id.nav_menu_main);
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


//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }


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


    ////////////
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() <= 0)
                super.onBackPressed();
            else {
                baseFragment.XoaFragment();
                supportInvalidateOptionsMenu();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tool_bar, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_main);
        if(fragment != null && fragment.getTag() != null) {
            switch (fragment.getTag()){
                case SupportKeyList.TAG_FRAGMENT_MAIN:
                    menu.findItem(R.id.search_toolbar).setVisible(true);
                    menu.findItem(R.id.dang_nhap_toolbar).setVisible(false);
                    menu.findItem(R.id.dang_xuat_toolbar).setVisible(false);
                    break;
                case SupportKeyList.TAG_FRAGMENT_MY_SHOPPING:
                    if (mySharedPref.getDaDangNhap()) {
                        if(!mySharedPref.getLuuDangNhap()) {
                            menu.findItem(R.id.dang_nhap_toolbar).setVisible(true);
                            menu.findItem(R.id.dang_xuat_toolbar).setVisible(false);
                        } else {
                            menu.findItem(R.id.dang_xuat_toolbar).setVisible(true);
                            menu.findItem(R.id.dang_nhap_toolbar).setVisible(false);
                        }
                    } else {
                        menu.findItem(R.id.dang_nhap_toolbar).setVisible(true);
                        menu.findItem(R.id.dang_xuat_toolbar).setVisible(false);
                    }
                    menu.findItem(R.id.search_toolbar).setVisible(false);
                    break;
                default:
                    menu.clear();
                    break;
            }
            return true;
        }
        menu.findItem(R.id.search_toolbar).setVisible(true);
        menu.findItem(R.id.dang_nhap_toolbar).setVisible(false);
        menu.findItem(R.id.dang_xuat_toolbar).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.dang_nhap_toolbar:
                baseFragment.ChuyenFragment(new DangNhapFragment(), SupportKeyList.TAG_FRAGMENT_DANG_NHAP, true);
                break;
            case R.id.dang_xuat_toolbar:
                switch (mySharedPref.getLoaiTaiKhoan()){
                    case SupportKeyList.ACCOUNT_THUONG:
                        dataHandler.DangXuat();
                        baseFragment.ChuyenFragment(new DangNhapFragment(), SupportKeyList.TAG_FRAGMENT_DANG_NHAP, false);
                        break;
                    case SupportKeyList.ACCOUNT_GOOGLE:
                        //googleHandle = new GoogleHandle(this, this);
                        //googleHandle.signOut();
                        break;
                    case SupportKeyList.ACCOUNT_FACEBOOK:
                        break;
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_my_shopping:
                int count = getSupportFragmentManager().getBackStackEntryCount();
                while(count > 0){
                    baseFragment.XoaFragment();
                    count--;
                }
                baseFragment.ChuyenFragment(new MyShoppingFragment(), SupportKeyList.TAG_FRAGMENT_MY_SHOPPING, true);
                break;
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void KetQua(String result) {
        switch (result){
            case SupportKeyList.DANG_XUAT_THANH_CONG:
                dataHandler.DangXuat();
                baseFragment.ChuyenFragment(new DangNhapFragment(), SupportKeyList.TAG_FRAGMENT_DANG_NHAP, false);
                break;
        }
    }
}
