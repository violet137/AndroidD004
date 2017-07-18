package vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import vn.com.greenacademy.shopping.Handle.HandleData.DataHandler;
import vn.com.greenacademy.shopping.Handle.HandleData.GoogleHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Dialog.LoadingDialog;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DangNhapFragment extends Fragment implements View.OnClickListener, DataCallBack {
    private EditText etTenDangNhap;
    private EditText etPassword;
    private CheckBox cbLuuDangNhap;
    private LoginButton button_face_login;

    private LoadingDialog  loadingDialog;
    private DataHandler dataHandler;
    private GoogleHandler googleHandler;
    private BaseFragment baseFragment;
    private CallbackManager callbackManager;

    public DangNhapFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_dang_nhap, container, false);
        etTenDangNhap = (EditText) root.findViewById(R.id.ten_dang_nhap_edittext_fragment_dang_nhap);
        etPassword = (EditText) root.findViewById(R.id.mat_khau_edittext_fragment_dang_nhap);
        cbLuuDangNhap = (CheckBox) root.findViewById(R.id.luu_dang_nhap_checkbox);
        button_face_login = (LoginButton) root.findViewById(R.id.btn_face_login);

        root.findViewById(R.id.dang_nhap_button_fragment_dang_nhap).setOnClickListener(this);
        root.findViewById(R.id.dang_ky_textview_fragment_dang_nhap).setOnClickListener(this);
        root.findViewById(R.id.sign_in_button).setOnClickListener(this);
        button_face_login.setOnClickListener(this);

        dataHandler = new DataHandler(getActivity(), this);
        baseFragment = new BaseFragment(getActivity().getSupportFragmentManager());
        loadingDialog = new LoadingDialog(getActivity());
        callbackManager = CallbackManager.Factory.create();
        button_face_login.setFragment(this);
        printKeyHash(getActivity());

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dang_nhap_button_fragment_dang_nhap:
                //Kiểm tra thông tin và tạo kết nối tới server để đăng nhập
                if (!etTenDangNhap.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()) {
                    //Hiện dialog loading chờ xử lý kết quả
                    loadingDialog.show();
                    dataHandler.DangNhap(SupportKeyList.ACCOUNT_THUONG, etTenDangNhap.getText().toString(), etPassword.getText().toString());
                }
                else
                    Toast.makeText(getContext(), R.string.toast_nhap_thieu, Toast.LENGTH_SHORT).show();
                break;

            case R.id.dang_ky_textview_fragment_dang_nhap:
                baseFragment.ChuyenFragment(new DangNhapFragment(), SupportKeyList.TAG_FRAGMENT_DANG_KY, true);
                break;

            case R.id.sign_in_button:
                googleHandler = new GoogleHandler(getActivity(), this , this);
                googleHandler.connectBuild();
                googleHandler.signIn();
                break;

            case R.id.btn_face_login:
                button_face_login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Profile profile = Profile.getCurrentProfile();
                        String name = null;
                        if (profile!=null){
                            name = profile.getName();
                            Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getActivity(), "dang nhap bi huy", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(getActivity(), "dang nhap bi loi", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    //Xử lý kết quả trả về
    @Override
    public void KetQua(String result, Bundle bundle) {
        switch (result){
            case SupportKeyList.LOI_KET_NOI:
                Toast.makeText(getActivity(), getString(R.string.toast_loi_ket_noi), Toast.LENGTH_LONG).show();
                break;

            case SupportKeyList.DANG_NHAP_THANH_CONG:
                Toast.makeText(getActivity(), getString(R.string.toast_dang_nhap_thanh_cong) + " " + etTenDangNhap.getText().toString() , Toast.LENGTH_SHORT).show();
                dataHandler.setTrangThaiDangNhap(bundle.getString("Token"), SupportKeyList.ACCOUNT_THUONG, etTenDangNhap.getText().toString(), etTenDangNhap.getText().toString(), cbLuuDangNhap.isChecked());
                baseFragment.ChuyenFragment(new TaiKhoanFragment(), SupportKeyList.TAG_FRAGMENT_TAI_KHOAN, false);
                break;

            case SupportKeyList.DANG_NHAP_GOOGLE_THANH_CONG:
                Toast.makeText(getActivity(), getString(R.string.toast_dang_nhap_thanh_cong) + " " + googleHandler.getUsername() , Toast.LENGTH_SHORT).show();
                dataHandler.setTrangThaiDangNhap(bundle.getString("Token"),SupportKeyList.ACCOUNT_GOOGLE, googleHandler.getEmail(), googleHandler.getUsername(), cbLuuDangNhap.isChecked());
                baseFragment.ChuyenFragment(new TaiKhoanFragment(), SupportKeyList.TAG_FRAGMENT_TAI_KHOAN, false);
                break;

            case SupportKeyList.DANG_NHAP_THAT_BAI:
                Toast.makeText(getActivity(), R.string.toast_dang_nhap_that_bai, Toast.LENGTH_SHORT).show();
                break;

            case SupportKeyList.DANG_NHAP_GOOGLE_THAT_BAI:
                Toast.makeText(getActivity(), R.string.toast_loi_ket_noi_server, Toast.LENGTH_SHORT).show();
                googleHandler.signOut();
                break;

            case SupportKeyList.DANG_NHAP_FACEBOOK_THAT_BAI:
                Toast.makeText(getActivity(), R.string.toast_loi_ket_noi_server, Toast.LENGTH_SHORT).show();
                break;

            case SupportKeyList.DANG_XUAT_THANH_CONG:
                break;
        }
        loadingDialog.dismiss();
    }

    //Xử lý kết quả của Google
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()){
            callbackManager.onActivityResult(requestCode,resultCode,data);
        }else {
            googleHandler.activityResult(requestCode, resultCode, data);
        }
    }

    // Hien thi Key Hash cho facebook
    public static String printKeyHash(Activity context) {
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
}
