package vn.com.greenacademy.shopping.Handle.HandleData.TaiKhoan;

import android.app.Activity;

import vn.com.greenacademy.shopping.Interface.ErrorCallBack;
import vn.com.greenacademy.shopping.Network.AsynTask.PostIDNew;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

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

    public void createAccount(String email, String password, String rePassword, String name) {
        if (kiemTraPassHopLe(password)){
            // so sanh rePassword co trung khop voi password ko
            if (password.equals(rePassword)){
                postIDtoServer(email,password, name);
            }else {
                errorCallBack.errorCallBack("password không trùng khớp", SupportKeyList.Re_type_Password_Error);
            }
        }else {
            errorCallBack.errorCallBack("Độ bảo mật kém", SupportKeyList.Password_Error);
//            errorCallBack.errorCallBack("Tài Khoản đã được sử dung", SupportKeyList.Email_Error);
        }
    }

    private void postIDtoServer(String email, String password, String name) {
        PostIDNew postIDNew = new PostIDNew(errorCallBack);
        postIDNew.execute(ServerUrl.DangKyUrl,email, password, name);
    }

    private boolean kiemTraPassHopLe(String password) {
        // password phai co tren 6 ky tu
        if (password.toCharArray().length >= 6){
            return true;
        }else {
            return false;
        }
    }
}
