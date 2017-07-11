package vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;

import vn.com.greenacademy.shopping.HandleUi.GoogleHandle;
import vn.com.greenacademy.shopping.Util.SharePreference.MySharedPreferences;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Network.AsynTask.GoiAPIServerAsyncTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class DangNhapFragment extends Fragment implements View.OnClickListener, DataCallBack {
    private EditText etTenDangNhap;
    private EditText etPassword;
    private CheckBox cbLuuDangNhap;

    private MySharedPreferences mySharedPref;
    private ProgressDialog loadingDialog;
    private GoogleHandle googleHandle;
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

        mySharedPref = new MySharedPreferences(getActivity(), SupportKeyList.SHAREDPREF_TEN_FILE);
        googleHandle = new GoogleHandle(getActivity());
        googleHandle.connectBuild();

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

            case R.id.sign_in_button:
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

}
