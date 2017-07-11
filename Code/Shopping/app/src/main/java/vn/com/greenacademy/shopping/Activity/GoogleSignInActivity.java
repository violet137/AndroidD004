package vn.com.greenacademy.shopping.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.SignInButton;

import vn.com.greenacademy.shopping.HandleUi.Ctr_GoogleSignIn;
import vn.com.greenacademy.shopping.R;

public class GoogleSignInActivity extends AppCompatActivity implements View.OnClickListener {

    SignInButton signInButton;
    Ctr_GoogleSignIn ctr_googleSignIn;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_signin);
        getSupportActionBar().hide();

        button2 = (Button) findViewById(R.id.button2);
        findViewById(R.id.button3).setOnClickListener(this);

        button2.setOnClickListener(this);

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(this);

        ctr_googleSignIn = new Ctr_GoogleSignIn(this);
        ctr_googleSignIn.connectBuild();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_button:
                ctr_googleSignIn.signIn();
                break;
            case R.id.button2:
                ctr_googleSignIn.getEmail();
                break;
            case R.id.button3:
                ctr_googleSignIn.signOut();
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        ctr_googleSignIn.activityResult(requestCode, resultCode, data);
    }


}
