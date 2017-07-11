package vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import vn.com.greenacademy.shopping.Data.MySharedPreferences;
import vn.com.greenacademy.shopping.Fragment.Main.MainFragment;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Asynctask.GoiAPIServerAsyncTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class DangNhapFragment extends Fragment implements View.OnClickListener, DataCallBack {
    private EditText etTenDangNhap;
    private EditText etPassword;
    private CheckBox cbLuuDangNhap;
    private CallbackManager callbackManager;
    private Button btn_face_login;

    private MySharedPreferences mySharedPref;
    private ProgressDialog loadingDialog;

    public DangNhapFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_dang_nhap, container, false);
        etTenDangNhap = (EditText) root.findViewById(R.id.etTenDangNhap_FragmentDangNhap);
        etPassword = (EditText) root.findViewById(R.id.etMatKhau_FragmentDangNhap);
        cbLuuDangNhap = (CheckBox) root.findViewById(R.id.cbLuuDangNhap_FragmentDangNhap);
        btn_face_login = (Button) root.findViewById(R.id.btn_face_login);
        printKeyHash(getContext());

        root.findViewById(R.id.btnDangNhap_FragmentDangNhap).setOnClickListener(this);
        root.findViewById(R.id.tvDangKy_FragmentDangNhap).setOnClickListener(this);
        btn_face_login.setOnClickListener(this);


        mySharedPref = new MySharedPreferences(getActivity(), SupportKeyList.SHAREDPREF_TEN_FILE);

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDangNhap_FragmentDangNhap:
                //Kiểm tra thông tin và tạo kết nối tới server để đăng nhập
                if (!etTenDangNhap.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()) {
                    //Hiện dialog loading chờ xử lý kết quả
                    loadingDialog = new ProgressDialog(getActivity());
                    loadingDialog.setMessage(getString(R.string.dialog_loading));
                    loadingDialog.setCancelable(false);
                    loadingDialog.setInverseBackgroundForced(false);
                    loadingDialog.show();
                    //Gọi API server
                    new GoiAPIServerAsyncTask(this).execute(SupportKeyList.API_DANG_NHAP, SupportKeyList.URL_DANG_NHAP, etTenDangNhap.getText().toString(), etPassword.getText().toString());
                }
                else
                    Toast.makeText(getContext(), R.string.toast_nhap_thieu, Toast.LENGTH_SHORT).show();
                break;

            case R.id.tvDangKy_FragmentDangNhap:
                //Chuyền sang màn hình đăng ký
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new DangKyFragment()).addToBackStack("dang_ky_fragment").commit();
                break;
            case R.id.btn_face_login:
                loginface();
                LoginManager.getInstance().logInWithPublishPermissions(getParentFragment(), Collections.<String>emptyList());
                break;
        }
    }

    private void loginface() {
        
        callbackManager = CallbackManager.Factory.create();
        FacebookSdk.sdkInitialize(this.getActivity());
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                if (profile!=null) {
                    String name = profile.getName();
                    Toast.makeText(getActivity(),name, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(),"Dang nhap bi huy", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getActivity(),"Dang nhap that bai", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Xử lý kết quả trả về
    @Override
    public void KetQua(int result) {
        switch (result){
            case SupportKeyList.LOI_KET_NOI:
                Toast.makeText(getActivity(), getString(R.string.toast_loi_ket_noi), Toast.LENGTH_LONG).show();
                loadingDialog.dismiss();
                break;
            case SupportKeyList.DANG_NHAP_THANH_CONG:
                loadingDialog.dismiss();
                Toast.makeText(getActivity(), getString(R.string.toast_dang_nhap_thanh_cong) + " " + etTenDangNhap.getText().toString() , Toast.LENGTH_SHORT).show();
                mySharedPref.setTEN_TAI_KHOAN(etTenDangNhap.getText().toString());
                mySharedPref.setDA_DANG_NHAP(true);
                mySharedPref.setLUU_DANG_NHAP(cbLuuDangNhap.isChecked());
                mySharedPref.setLOAI_TAI_KHOAN(SupportKeyList.ACCOUNT_THUONG);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new TaiKhoanFragment()).commit();
                break;
            case SupportKeyList.DANG_NHAP_THAT_BAI:
                loadingDialog.dismiss();
                Toast.makeText(getActivity(), R.string.toast_dang_nhap_that_bai, Toast.LENGTH_SHORT).show();
                break;
        }
    }
    // Hien thi Key Hash cho facebook
    public static String printKeyHash(Context context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (android.content.pm.Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
