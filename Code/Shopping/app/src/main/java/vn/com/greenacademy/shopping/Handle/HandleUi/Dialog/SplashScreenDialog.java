package vn.com.greenacademy.shopping.Handle.HandleUi.Dialog;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;

import vn.com.greenacademy.shopping.Fragment.Home.MainFragment;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

import static vn.com.greenacademy.shopping.MainActivity.MY_PERMISSIONS_REQUEST_CODE;

/**
 * Created by zzzzz on 7/12/2017.
 */

public class SplashScreenDialog extends ProgressDialog {
    private DrawerLayout drawerLayout;

    private static final int DELAY_TIME = 3000;

    public SplashScreenDialog(Context context, int theme, DrawerLayout drawerLayout) {
        super(context, theme);
        this.drawerLayout = drawerLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        //Xin quyền
        if(Build.VERSION.SDK_INT >= 23) {
            if (CheckPermission())
                TiepTuc();
        }
        else TiepTuc();

        setCancelable(false);
        setInverseBackgroundForced(false);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void show() {
        super.show();
        setContentView(R.layout.fragment_splash_screen);
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        this.getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels;
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.getWindow().setDimAmount(0);
    }
    private void TiepTuc() {
        //Load màn hình splash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                dismiss();
            }
        }, DELAY_TIME);
    }

    private boolean CheckPermission() {
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                //Hiện giải thích về quyền và xin quyền
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions((Activity)getContext(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_CODE);
            }
        } else {
            return true;
        }
        return false;
    }
}
