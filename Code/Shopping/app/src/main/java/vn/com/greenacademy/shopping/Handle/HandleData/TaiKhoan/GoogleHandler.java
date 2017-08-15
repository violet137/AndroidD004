package vn.com.greenacademy.shopping.Handle.HandleData.TaiKhoan;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import vn.com.greenacademy.shopping.Fragment.MyShopping.TaiKhoan.DangNhapFragment;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Network.AsynTask.TaiKhoanServerAsyncTask;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 7/4/2017.
 */

public class GoogleHandler extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener {
    private Activity mActivity;
    private TaiKhoanServerAsyncTask taiKhoanServerAsyncTask;
    private GoogleApiClient mGoogleApiClient;
    private DataCallBack dataCallBack;
    private DangNhapFragment fragment;
    private int requestCode;
    private int resultCode;
    private Intent data;

    private final int RC_SIGN_IN = 200;

    public GoogleHandler(Activity activity, DangNhapFragment fragment, DataCallBack dataCallBack) {
        this.mActivity = activity;
        this.fragment = fragment;
        this.dataCallBack = dataCallBack;
        taiKhoanServerAsyncTask = new TaiKhoanServerAsyncTask(dataCallBack);
    }

    public GoogleHandler(Activity activity, DataCallBack dataCallBack) {
        this.mActivity = activity;
        this.dataCallBack = dataCallBack;
        taiKhoanServerAsyncTask = new TaiKhoanServerAsyncTask(dataCallBack);
    }

    public void connectBuild (){
        if (mGoogleApiClient == null){
            // Configure sign-in to request the user's ID, email address, and basic
            // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            // Build a GoogleApiClient with access to the Google Sign-In API and the
            // options specified by gso.
            mGoogleApiClient = new GoogleApiClient.Builder(mActivity)
                    .enableAutoManage((FragmentActivity) mActivity /* FragmentActivity */, this /* OnConnectionFailedListener */)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        }

    }

    public void signIn() {
        if (requestCode == 0){
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            fragment.startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    }

    //Lấy email
    public String getEmail(){
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        if (requestCode == RC_SIGN_IN) {
            return result.getSignInAccount().getEmail();
        }else {
            Toast.makeText(mActivity, "Chưa Đăng Nhập", Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    //Lấy hình
    public String getUserPhoto(){
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        if (requestCode == RC_SIGN_IN) {
            return result.getSignInAccount().getPhotoUrl().toString();
        }else {
            Toast.makeText(mActivity, "Chưa Đăng Nhập", Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    //Lấy tên
    public String getUsername(){
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        if (requestCode == RC_SIGN_IN) {
            return result.getSignInAccount().getGivenName();
        }else {
            Toast.makeText(mActivity, "Chưa Đăng Nhập", Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    public void signOut() {
        if (requestCode == RC_SIGN_IN){
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            requestCode = 0;
                            dataCallBack.KetQua(SupportKeyList.DANG_XUAT_THANH_CONG, null);
                        }
                    });
        }else {
            Toast.makeText(mActivity, "Chưa Đăng Nhập", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        dataCallBack.KetQua(SupportKeyList.LOI_KET_NOI, null);
    }

    public void activityResult(int requestCode, int resultCode, Intent data) {
        //check requestCode
        if(requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Kiểm tra đăng nhập thành công
            if (result.isSuccess()) {
//                String mail = result.getSignInAccount().getEmail();
//                Toast.makeText(mActivity, mail, Toast.LENGTH_SHORT).show();
//                String username = result.getSignInAccount().getGivenName();
//                Toast.makeText(mActivity, username, Toast.LENGTH_SHORT).show();
                //Kiểm tra hình
                if (result.getSignInAccount().getPhotoUrl() == null){
                    Toast.makeText(mActivity, "tài khoản chưa có ảnh", Toast.LENGTH_SHORT).show();
                } else {
                    String hinh = result.getSignInAccount().getPhotoUrl().toString();
                    Toast.makeText(mActivity, hinh, Toast.LENGTH_SHORT).show();
                }
                taiKhoanServerAsyncTask.execute(SupportKeyList.API_DANG_NHAP, ServerUrl.DangNhapUrl, SupportKeyList.ACCOUNT_GOOGLE, result.getSignInAccount().getId());
            }
            else {
                requestCode = 0;
                Toast.makeText(mActivity, "Lỗi đăng nhập", Toast.LENGTH_LONG).show();
            }
        }

        // chuyen du lieu ra ngoai
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data=data;
    }
}
