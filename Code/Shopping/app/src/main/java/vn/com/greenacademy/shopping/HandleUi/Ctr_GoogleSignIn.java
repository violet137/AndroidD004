package vn.com.greenacademy.shopping.HandleUi;

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

import vn.com.greenacademy.shopping.Network.AsynTask.GooglePlusAsyncTask;

/**
 * Created by ADMIN on 7/4/2017.
 */

public class Ctr_GoogleSignIn extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener {
    Activity mActivity;
    int RC_SIGN_IN = 200;

    public Ctr_GoogleSignIn(Activity activity) {
        this.mActivity = activity;

    }
    public void signOut() {
        if (requestCode == RC_SIGN_IN){
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            requestCode = 0;
                            Toast.makeText(mActivity, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                        }
                    });
        }else {
            Toast.makeText(mActivity, "Chưa Đăng Nhập", Toast.LENGTH_SHORT).show();
        }
    }
    GoogleApiClient mGoogleApiClient;
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
        Toast.makeText(mActivity, "Build hoàn tất", Toast.LENGTH_SHORT).show();
    }
    public void signIn() {
        if (requestCode == 0){
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            mActivity.startActivityForResult(signInIntent, 200);
            Toast.makeText(mActivity, "Kết nối", Toast.LENGTH_SHORT).show();
        }else if (requestCode == 200){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            GooglePlusAsyncTask asynTalkGGAInformationPush = new GooglePlusAsyncTask();
            Toast.makeText(mActivity, "Đã kết nối với ID: " + result.getSignInAccount().getEmail(), Toast.LENGTH_SHORT).show();
        }
    }
    public void getEmail(){
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            GooglePlusAsyncTask asynTalkGGAInformationPush = new GooglePlusAsyncTask();
//            asynTalkGGAInformationPush.execute(result.getSignInAccount().getEmail(),result.getSignInAccount().getDisplayName());
            Toast.makeText(mActivity, result.getSignInAccount().getEmail(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mActivity, "Chưa Đăng Nhập", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(mActivity, "Không Thể Kết Nối", Toast.LENGTH_SHORT).show();
    }

    int requestCode;
    int resultCode;
    Intent data;
    public void activityResult(int requestCode, int resultCode, Intent data) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data=data;
    }
}
