package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;

import vn.com.greenacademy.shopping.Interface.ErrorCallBack;

/**
 * Created by ADMIN on 7/19/2017.
 */

public class DangKyTkHandler {
    Activity activity;
    ErrorCallBack errorCallBack;

    public DangKyTkHandler(Activity activity, ErrorCallBack errorCallBack) {
        this.activity = activity;
        this.errorCallBack = errorCallBack;
    }

    public boolean createAccount(String email, String password, String rePassword) {

        return true;
    }
}
