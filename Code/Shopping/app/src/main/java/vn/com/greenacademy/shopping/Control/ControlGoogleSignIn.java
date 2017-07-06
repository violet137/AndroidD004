package vn.com.greenacademy.shopping.Control;

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

import vn.com.greenacademy.shopping.AsynTalk.AsynTalkGGAInformationPush;

/**
 * Created by ADMIN on 7/4/2017.
 */

public class ControlGoogleSignIn extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener {
    Activity mActivity;

    public ControlGoogleSignIn(Activity activity) {
        this.mActivity = activity;

    }

    public void googleSignIn() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(mActivity)
                .enableAutoManage((FragmentActivity) mActivity /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        mActivity.startActivityForResult(signInIntent, 200);

    }

    public void activityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int RC_SIGN_IN = 200;
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            AsynTalkGGAInformationPush asynTalkGGAInformationPush = new AsynTalkGGAInformationPush();
//            asynTalkGGAInformationPush.execute(result.getSignInAccount().getEmail(),result.getSignInAccount().getDisplayName());
            Toast.makeText(mActivity, result.getSignInAccount().getAccount().toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(mActivity, "Không Thể Kết Nối", Toast.LENGTH_SHORT).show();
    }
}
