package vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Handle.HandleData.DataHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Dialog.LoadingDialog;
import vn.com.greenacademy.shopping.Handle.HandleData.GoogleHandle;
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

    private LoadingDialog  loadingDialog;
    private GoogleHandle googleHandle;
    private DataHandler dataHandler;
    private BaseFragment baseFragment;
    
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

        root.findViewById(R.id.dang_nhap_button_fragment_dang_nhap).setOnClickListener(this);
        root.findViewById(R.id.dang_ky_textview_fragment_dang_nhap).setOnClickListener(this);
        root.findViewById(R.id.sign_in_button).setOnClickListener(this);

        dataHandler = new DataHandler(getActivity(), this);
        baseFragment = new BaseFragment(getActivity().getSupportFragmentManager());
        loadingDialog = new LoadingDialog(getActivity());

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
                //Chuyền sang màn hình đăng ký
                baseFragment.ChuyenFragment(new DangNhapFragment(), SupportKeyList.TAG_FRAGMENT_DANG_KY, true);
                break;

            case R.id.sign_in_button:
                googleHandle = new GoogleHandle(getActivity(), this , this);
                googleHandle.connectBuild();
                googleHandle.signIn();
                break;
        }
    }

    //Xử lý kết quả trả về
    @Override
    public void KetQua(int result) {
        switch (result){
            case SupportKeyList.LOI_KET_NOI:
                Toast.makeText(getActivity(), getString(R.string.toast_loi_ket_noi), Toast.LENGTH_LONG).show();
                break;
            case SupportKeyList.DANG_NHAP_THANH_CONG:
                Toast.makeText(getActivity(), getString(R.string.toast_dang_nhap_thanh_cong) + " " + etTenDangNhap.getText().toString() , Toast.LENGTH_SHORT).show();
                dataHandler.setTrangThaiDangNhap(SupportKeyList.ACCOUNT_THUONG, etTenDangNhap.getText().toString(), etTenDangNhap.getText().toString(), cbLuuDangNhap.isChecked());
                baseFragment.ChuyenFragment(new TaiKhoanFragment(), SupportKeyList.TAG_FRAGMENT_TAI_KHOAN, false);
                break;
            case SupportKeyList.DANG_NHAP_GOOGLE_THANH_CONG:
                Toast.makeText(getActivity(), getString(R.string.toast_dang_nhap_thanh_cong) + " " + googleHandle.getUsername() , Toast.LENGTH_SHORT).show();
                dataHandler.setTrangThaiDangNhap(SupportKeyList.ACCOUNT_GOOGLE, googleHandle.getEmail(), googleHandle.getUsername(), cbLuuDangNhap.isChecked());
                baseFragment.ChuyenFragment(new TaiKhoanFragment(), SupportKeyList.TAG_FRAGMENT_TAI_KHOAN, false);
                break;
            case SupportKeyList.DANG_NHAP_THAT_BAI:
                Toast.makeText(getActivity(), R.string.toast_dang_nhap_that_bai, Toast.LENGTH_SHORT).show();
                break;
            case SupportKeyList.DANG_NHAP_GOOGLE_THAT_BAI:
                Toast.makeText(getActivity(), R.string.toast_loi_ket_noi_server, Toast.LENGTH_SHORT).show();
                googleHandle.signOut();
                break;
            case SupportKeyList.DANG_NHAP_FACEBOOK_THAT_BAI:
                Toast.makeText(getActivity(), R.string.toast_loi_ket_noi_server, Toast.LENGTH_SHORT).show();
                break;
            case SupportKeyList.DANG_XUAT_THANH_CONG:
                break;
        }
        loadingDialog.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        googleHandle.activityResult(requestCode, resultCode, data);
    }
}
