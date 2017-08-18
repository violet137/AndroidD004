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
import android.support.annotation.Nullable;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Home.MainFragment;
import vn.com.greenacademy.shopping.Handle.HandleData.GioHang.GioHangHandler;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPhamGioHang;
import vn.com.greenacademy.shopping.Network.AsynTask.DataServerAsyncTask;
import vn.com.greenacademy.shopping.Network.NetworkChangeReceiver;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SharePreference.MySharedPreferences;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

import static vn.com.greenacademy.shopping.MainActivity.MY_PERMISSIONS_REQUEST_CODE;

/**
 * Created by zzzzz on 7/12/2017.
 */

public class SplashScreenDialog extends ProgressDialog implements DataCallBack {
    private View root;
    private Button btnRetryConnect;
    private ProgressBar progressBar;
    private DrawerLayout drawerLayout;

    private Context context;
    private MySharedPreferences mySharedPreferences;
    private GioHangHandler gioHangHandler;

    private static final int DELAY_TIME = 3000;

    public SplashScreenDialog(Context context, DrawerLayout drawerLayout) {
        super(context);
        if (context instanceof Activity){
            setOwnerActivity((Activity)context);
        }
        this.drawerLayout = drawerLayout;
        this.context = context;
        mySharedPreferences = new MySharedPreferences(getOwnerActivity(), SupportKeyList.SHAREDPREF_TEN_FILE);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getOwnerActivity().finish();
    }

    private void TiepTuc() {
        //Kiểm tra internet trước khi vào app
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            progressBar.findViewById(R.id.progress_bar_splash_screen).setVisibility(View.VISIBLE);
            btnRetryConnect.findViewById(R.id.button_retry_connect_splash_screen).setVisibility(View.GONE);
            gioHangHandler = new GioHangHandler(getOwnerActivity(), this);
            gioHangHandler.getGioHangTuServer();
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

    //Xử lý kết quả giỏ hàng
    @Override
    public void KetQua(String result, @Nullable Bundle bundle) {
        switch (result){
//            case SupportKeyList.LOI_DATA_SERVER:
//                Toast.makeText(getOwnerActivity(), getOwnerActivity().getString(R.string.toast_loi_data_server), Toast.LENGTH_SHORT).show();
//                break;
//            case SupportKeyList.LOI_DATA:
//                Toast.makeText(getOwnerActivity(), getOwnerActivity().getString(R.string.toast_loi_data), Toast.LENGTH_SHORT).show();
//                break;
            case SupportKeyList.LAY_DATA_THANH_CONG:
                gioHangHandler.luuGioHang(bundle);
                break;
        }
    }
}
