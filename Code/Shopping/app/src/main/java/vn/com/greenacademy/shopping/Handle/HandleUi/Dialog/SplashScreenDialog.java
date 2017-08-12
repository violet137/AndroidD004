package vn.com.greenacademy.shopping.Handle.HandleUi.Dialog;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Fragment.Home.MainFragment;
import vn.com.greenacademy.shopping.Network.NetworkChangeReceiver;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

import static vn.com.greenacademy.shopping.MainActivity.MY_PERMISSIONS_REQUEST_CODE;

/**
 * Created by zzzzz on 7/12/2017.
 */

public class SplashScreenDialog extends ProgressDialog {
    private View root;
    private Button btnRetryConnect;
    private ProgressBar progressBar;

    private DrawerLayout drawerLayout;
    private Context context;

    private static final int DELAY_TIME = 3000;

    public SplashScreenDialog(Context context, DrawerLayout drawerLayout) {
        super(context);
        if (context instanceof Activity){
            setOwnerActivity((Activity)context);
        }
        this.drawerLayout = drawerLayout;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(context);
        root = inflater.inflate(R.layout.fragment_splash_screen, null);
        btnRetryConnect = (Button) root.findViewById(R.id.button_retry_connect_splash_screen);
        progressBar = (ProgressBar) root.findViewById(R.id.progress_bar_splash_screen);

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        setCancelable(false);
        setInverseBackgroundForced(false);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            TiepTuc();
        return super.onTouchEvent(event);
    }

    @Override
    public void show() {
        super.show();
        setContentView(root);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.getWindow().setDimAmount(0);

        //Xin quyền
        if(Build.VERSION.SDK_INT >= 23) {
            if (CheckPermission())
                TiepTuc();
        }
        else TiepTuc();
    }

    private void TiepTuc() {
        //Kiểm tra internet trước khi vào app
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            progressBar.findViewById(R.id.progress_bar_splash_screen).setVisibility(View.VISIBLE);
            btnRetryConnect.findViewById(R.id.button_retry_connect_splash_screen).setVisibility(View.GONE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                    dismiss();
                }
            }, DELAY_TIME);
        } else {
            Toast.makeText(context, "Kiểm tra kết nối và thử lại", Toast.LENGTH_SHORT).show();
            btnRetryConnect.findViewById(R.id.button_retry_connect_splash_screen).setVisibility(View.VISIBLE);
            progressBar.findViewById(R.id.progress_bar_splash_screen).setVisibility(View.GONE);
        }
    }

    private boolean CheckPermission() {
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            Activity activity = getOwnerActivity();
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                //Hiện giải thích về quyền và xin quyền
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_CODE);
            }
        } else {
            return true;
        }
        return false;
    }
}
