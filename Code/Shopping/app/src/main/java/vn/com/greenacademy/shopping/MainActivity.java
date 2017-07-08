package vn.com.greenacademy.shopping;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import vn.com.greenacademy.shopping.Fragment.Main.MyShopping.MyShoppingFragment;
import vn.com.greenacademy.shopping.Fragment.SplashScreenFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    private static final String TAG = "MyShopping";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_menu_main);

        //Hiện icon menu va đồng bộ actionbar với menu
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Sự kiện click item trong menu
        navigationView.setNavigationItemSelectedListener(this);

        //Chạy màn hình splash
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new SplashScreenFragment(this, getSupportActionBar())).commit();


    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() <= 0)
                super.onBackPressed();
            else
                getSupportFragmentManager().popBackStack();
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
        menu.findItem(R.id.toolbar_item_dang_nhap).setVisible(false);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_my_shopping:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new MyShoppingFragment(this)).addToBackStack(TAG).commit();
                break;
            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}
