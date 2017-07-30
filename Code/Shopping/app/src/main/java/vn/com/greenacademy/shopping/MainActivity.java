package vn.com.greenacademy.shopping;

import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan.DangNhapFragment;
import vn.com.greenacademy.shopping.Fragment.SplashScreenFragment;
import vn.com.greenacademy.shopping.Handle.HandleData.DataHandler;
import vn.com.greenacademy.shopping.Handle.HandleData.GoogleHandler;
import vn.com.greenacademy.shopping.Handle.HandleData.SlideMenuHandler;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Interface.FindStoreListenerCallBack;
import vn.com.greenacademy.shopping.Util.SharePreference.MySharedPreferences;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

public class MainActivity extends AppCompatActivity implements DataCallBack, FindStoreListenerCallBack {
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

    TextView textViewMain;
    SlideMenuHandler slideMenuHandler;
    boolean trangThaiListFindStore = false;
    ListView listViewFindStore;

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

        View navigationView = findViewById(R.id.nav_menu_main);
        //Xử lý sự kiện
//        navigationView.findViewById(R.id.ivUser_nav_hear).setOnClickListener(this);
//        navigationView.findViewById(R.id.tvName_nav_hear).setOnClickListener(this);
//        navigationView.findViewById(R.id.tvEmail_nav_hear).setOnClickListener(this);

        slideMenuHandler = new SlideMenuHandler(this);

        textViewMain = (TextView) findViewById(R.id.tvName_contnt_main);

        lv_item_slide_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //modeSlideMenu = arrayModeSlideMenus.get(position);
                slideMenuHandler.itemClickListener(position, baseFragment, textViewMain,MainActivity.this);
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
        slideMenuHandler.loadData();
        slideMenuHandler.displayListview(lv_item_slide_menu);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Chạy màn hình splash
        baseFragment.ChuyenFragment(new SplashScreenFragment(getSupportActionBar(), drawerLayout), null, false);
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
                    // [start] group item find store
                    menu.setGroupVisible(R.id.group_find_store,false);
                    // [end]

                    // [start] group 1
                    menu.setGroupVisible(R.id.group1,false);
                    // [end]
                    break;
                case SupportKeyList.TAG_FRAGMENT_MY_SHOPPING:
                    // [start] group item find store
                    menu.setGroupVisible(R.id.group_find_store,false);
                    // [end]
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
                case SupportKeyList.TAG_FRAGMENT_FINDSTORE:
                    menu.setGroupVisible(R.id.group1,false);
                    menu.findItem(R.id.show_list_dia_chi_store).setVisible(true);
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
        menu.findItem(R.id.dang_nhap_toolbar).setVisible(false);
        menu.findItem(R.id.dang_xuat_toolbar).setVisible(false);
        // [start] group item find store
        menu.setGroupVisible(R.id.group_find_store,false);
        // [end]
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
                        GoogleHandler googleHandle = new GoogleHandler(this, this);
                        googleHandle.signOut();
                        break;
                    case SupportKeyList.ACCOUNT_FACEBOOK:
                        break;
                }
                break;
            case R.id.show_list_dia_chi_store:
                if (trangThaiListFindStore){
                    trangThaiListFindStore = false;
                    listViewFindStore.setVisibility(View.GONE);
                    textViewMain.setVisibility(View.GONE);
                } else {
                    trangThaiListFindStore = true;
                    listViewFindStore.setVisibility(View.VISIBLE);
                    textViewMain.setText("Danh sách các cửa hàng");
                    textViewMain.setVisibility(View.VISIBLE);
                }
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
    public void listViewCallBack(ListView listView) {
        listViewFindStore = listView;
    }
}
