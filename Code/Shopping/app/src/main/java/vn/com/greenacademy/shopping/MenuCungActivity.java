package vn.com.greenacademy.shopping;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuCungActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    ImageView ivPersonalPhotos;
    TextView tvName;
    TextView tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cung);
        getSupportActionBar().hide();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();

        ivPersonalPhotos = (ImageView) findViewById(R.id.ivPersonalPhotos_activity_menu_cung);
//        ivPersonalPhotos.setOnClickListener(this);
        tvEmail = (TextView) findViewById(R.id.tvEmail_activity_menu_cung);
//        tvEmail.setOnClickListener(this);
        tvName = (TextView) findViewById(R.id.tvName_activity_menu_cung);
//        tvName.setOnClickListener(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_cung, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){
            case R.id.nav_Products:
                Toast.makeText(this, "Products", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Ladies:
                Toast.makeText(this, "Ladies", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Men:
                Toast.makeText(this, "Men", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Kids:
                Toast.makeText(this, "Kids", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Magazine:
                Toast.makeText(this, "Magazine", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Wish_List:
                Toast.makeText(this, "Wish_List", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_My_Shopping:
                Toast.makeText(this, "My_Shopping", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Support:
                Toast.makeText(this, "Support", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Find_a_store:
                Toast.makeText(this, "Find_a_store", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_Newsletter:
                Toast.makeText(this, "Newsletter", Toast.LENGTH_SHORT).show();
                break;
        }
//
//        int id = item.getItemId();
//
//        if (id == R.id.nav_Products) {
//            // Handle the camera action
//        } else if (id == R.id.nav_Ladies) {
//
//        } else if (id == R.id.nav_Men) {
//
//        } else if (id == R.id.nav_Kids) {
//
//        } else if (id == R.id.nav_Home) {
//
//        } else if (id == R.id.nav_Magazine) {
//
//        } else if (id == R.id.nav_Wish_List) {
//
//        } else if (id == R.id.nav_My_Shopping) {
//
//        } else if (id == R.id.nav_Support) {
//
//        } else if (id == R.id.nav_Find_a_store) {
//
//        } else if (id == R.id.nav_Newsletter) {
//
//        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivPersonalPhotos_activity_menu_cung:
                Toast.makeText(this, "Personal Photos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvName_activity_menu_cung:
                Toast.makeText(this, "Name", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvEmail_activity_menu_cung:
                Toast.makeText(this, "Email", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
