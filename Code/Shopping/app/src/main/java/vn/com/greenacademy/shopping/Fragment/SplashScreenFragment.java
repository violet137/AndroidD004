package vn.com.greenacademy.shopping.Fragment;


import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.Manifest;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;
import vn.com.greenacademy.shopping.Fragment.Home.MainFragment;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

import static vn.com.greenacademy.shopping.MainActivity.MY_PERMISSIONS_REQUEST_CODE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashScreenFragment extends Fragment {
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private BaseFragment baseFragment;

    private static final int DELAY_TIME = 3000;

    public SplashScreenFragment(ActionBar actionBar, DrawerLayout drawerLayout) {
        this.actionBar = actionBar;
        this.drawerLayout = drawerLayout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        actionBar.hide();
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        View root = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        baseFragment = new BaseFragment(getActivity().getSupportFragmentManager());

        //Xin quyền
        if(Build.VERSION.SDK_INT >= 23) {
            if (CheckPermission())
                TiepTuc();
        }
        else TiepTuc();

        return root;
    }

    private void TiepTuc() {
        //Load màn hình splash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                actionBar.show();
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                baseFragment.ChuyenFragment(new MainFragment(), SupportKeyList.TAG_FRAGMENT_MAIN, false);
            }
        }, DELAY_TIME);
    }

    private boolean CheckPermission() {
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                //Hiện giải thích về quyền và xin quyền
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_CODE);
            }
        } else {
            return true;
        }
        return false;
    }

}
