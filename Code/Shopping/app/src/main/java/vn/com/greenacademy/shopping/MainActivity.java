package vn.com.greenacademy.shopping;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
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
import android.widget.TextView;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Fragment.Home.MainFragment;
import vn.com.greenacademy.shopping.Fragment.Main.MyShopping.GioHangFragment;
import vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan.DangNhapFragment;
import vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan.DangNhapKhongLuuFragment;
import vn.com.greenacademy.shopping.Fragment.SplashScreenFragment;
import vn.com.greenacademy.shopping.Fragment.Store.FindStoreFragment;
import vn.com.greenacademy.shopping.Handle.HandleData.DataHandler;
import vn.com.greenacademy.shopping.Handle.HandleData.TaiKhoan.GoogleHandler;
import vn.com.greenacademy.shopping.Handle.HandleData.SlideMenuHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Dialog.SplashScreenDialog;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Util.SharePreference.MySharedPreferences;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

public class MainActivity extends AppCompatActivity implements DataCallBack {
    ListView lv_item_slide_menu;

//    ArrayList<SlideMenu> arrayModeSlideMenus;
//    int[] arrIcon;
//    String[] arrName;
//    SlideMenu modeSlideMenu;
//    AdapterSlideMenu adapterSlideMenu;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    private BaseFragment baseFragment;
    private DataHandler dataHandler;
    private MySharedPreferences mySharedPref;

    public static TextView tvTenMuc;
    SlideMenuHandler slideMenuHandler;
    boolean trangThaiListFindStore = false;

    public static final int MY_PERMISSIONS_REQUEST_CODE = 1;

    @Override()
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Config custom appbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        lv_item_slide_menu = (ListView) findViewById(R.id.lv_item_slide_menu);
        tvTenMuc = (TextView) findViewById(R.id.text_ten_muc_content_main);
        tvTenMuc.setMovementMethod(new ScrollingMovementMethod());

//        View navigationView = findViewById(R.id.nav_menu_main);
//        //Xử lý sự kiện
//        navigationView.findViewById(R.id.ivUser_nav_hear).setOnClickListener(this);
//        navigationView.findViewById(R.id.tvName_nav_hear).setOnClickListener(this);
//        navigationView.findViewById(R.id.tvEmail_nav_hear).setOnClickListener(this);

        slideMenuHandler = new SlideMenuHandler(this);
        slideMenuHandler.loadData();
        lv_item_slide_menu.setAdapter(slideMenuHandler.displayListview());

        lv_item_slide_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //modeSlideMenu = arrayModeSlideMenus.get(position);
                slideMenuHandler.itemClickListener(position, baseFragment);
                // dong slide menu
                if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });

        //Khởi tạo, thiết lập giá trị
        mySharedPref = new MySharedPreferences(this, SupportKeyList.SHAREDPREF_TEN_FILE);
        dataHandler = new DataHandler(this);
        baseFragment = new BaseFragment(getSupportFragmentManager());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(Color.parseColor("#ffcc0000"));
        toggle.syncState();

        //Chạy màn hình splash
//        baseFragment.ChuyenFragment(new SplashScreenFragment(getSupportActionBar(), drawerLayout), null, false);
        baseFragment.ChuyenFragment(new MainFragment(), null, false);
        SplashScreenDialog splashScreenDialog = new SplashScreenDialog(MainActivity.this, R.style.Theme_Dialog_Light_NoActionBar, drawerLayout);
        splashScreenDialog.show();

    }

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

    //Menu của tool bar
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
                    menu.findItem(R.id.my_bag_toolbar).setVisible(true);
                    menu.findItem(R.id.find_store_toolbar).setVisible(false);
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
                    menu.findItem(R.id.find_store_toolbar).setVisible(false);
                    menu.findItem(R.id.my_bag_toolbar).setVisible(false);
                    break;

                case SupportKeyList.TAG_XU_HUONG_THOI_TRANG:
                case SupportKeyList.TAG_THONG_TIN_SAN_PHAM:
                    menu.findItem(R.id.my_bag_toolbar).setVisible(true);
                    menu.findItem(R.id.find_store_toolbar).setVisible(false);
                    menu.findItem(R.id.search_toolbar).setVisible(false);
                    menu.findItem(R.id.dang_nhap_toolbar).setVisible(false);
                    menu.findItem(R.id.dang_xuat_toolbar).setVisible(false);
                    break;

                case SupportKeyList.TAG_FRAGMENT_FINDSTORE:
                    menu.findItem(R.id.find_store_toolbar).setVisible(true);
                    menu.findItem(R.id.search_toolbar).setVisible(false);
                    menu.findItem(R.id.dang_nhap_toolbar).setVisible(false);
                    menu.findItem(R.id.dang_xuat_toolbar).setVisible(false);
                    menu.findItem(R.id.my_bag_toolbar).setVisible(false);
                    break;

                case SupportKeyList.TAG_FRAGMENT_MAGAZINE:
                    menu.close();

                default:
                    menu.clear();
                    break;
            }
            return true;
        }
        menu.findItem(R.id.search_toolbar).setVisible(true);
        menu.findItem(R.id.my_bag_toolbar).setVisible(true);
        menu.findItem(R.id.find_store_toolbar).setVisible(false);
        menu.findItem(R.id.dang_nhap_toolbar).setVisible(false);
        menu.findItem(R.id.dang_xuat_toolbar).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.dang_nhap_toolbar:
                if (mySharedPref.getLuuDangNhap())
                    baseFragment.ChuyenFragment(new DangNhapFragment(), SupportKeyList.TAG_FRAGMENT_DANG_NHAP, true);
                else
                    baseFragment.ChuyenFragment(new DangNhapKhongLuuFragment(), SupportKeyList.TAG_FRAGMENT_DANG_NHAP_KHONG_LUU, true);
                break;
            case R.id.dang_xuat_toolbar:
                switch (mySharedPref.getLoaiTaiKhoan()){
                    case SupportKeyList.ACCOUNT_THUONG:
                        dataHandler.DangXuat();
                        baseFragment.ChuyenFragment(new DangNhapFragment(), SupportKeyList.TAG_FRAGMENT_DANG_NHAP, false);
                        break;
                    case SupportKeyList.ACCOUNT_GOOGLE:
                        GoogleHandler googleHandle = new GoogleHandler(this, this);
                        googleHandle.signOut();
                        break;
                    case SupportKeyList.ACCOUNT_FACEBOOK:
                        break;
                }
                break;

            case R.id.find_store_toolbar:
                if (trangThaiListFindStore){
                    trangThaiListFindStore = false;
                    FindStoreFragment.listView.setVisibility(View.GONE);
                    tvTenMuc.setVisibility(View.GONE);
                } else {
                    trangThaiListFindStore = true;
                    FindStoreFragment.listView.setVisibility(View.VISIBLE);
                    tvTenMuc.setText("Danh sách các cửa hàng");
                    tvTenMuc.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.my_bag_toolbar:
                baseFragment.ChuyenFragment(new GioHangFragment(), SupportKeyList.TAG_FRAGMENT_GIO_HANG, true);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    //Xử lý kết quả
    @Override
    public void KetQua(String result, Bundle bundle) {
        switch (result){
            case SupportKeyList.DANG_XUAT_THANH_CONG:
                dataHandler.DangXuat();
                Toast.makeText(this, "dang xuat thanh cong", Toast.LENGTH_SHORT).show();
                baseFragment.ChuyenFragment(new DangNhapFragment(), SupportKeyList.TAG_FRAGMENT_DANG_NHAP, false);
                break;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    baseFragment.ChuyenFragment(new MainFragment(), SupportKeyList.TAG_FRAGMENT_MAGAZINE, false);
                } else{
                    finish();
                }
                return;
        }
    }

}
