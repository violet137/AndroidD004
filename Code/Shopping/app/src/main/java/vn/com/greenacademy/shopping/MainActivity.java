package vn.com.greenacademy.shopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.common.SignInButton;

import vn.com.greenacademy.shopping.Control.ControlGoogleSignIn;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SignInButton signInButton;
    ControlGoogleSignIn controlGoogleSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(this);

        controlGoogleSignIn = new ControlGoogleSignIn(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_button:
                controlGoogleSignIn.googleSignIn();
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        controlGoogleSignIn.activityResult(requestCode, resultCode, data);
    }
}
