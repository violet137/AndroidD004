package vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Asynctask.GoiAPIServerAsyncTask;
import vn.com.greenacademy.shopping.Data.MySharedPreferences;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DangNhapKhongLuuFragment extends Fragment implements View.OnClickListener, DataCallBack {
    EditText etTenDangNhap;
    EditText etPassword;
    CheckBox cbLuuDangNhap;

    private MySharedPreferences mySharedPref;
    private ProgressDialog loadingDialog;

    public DangNhapKhongLuuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_dang_nhap_khong_luu, container, false);
        TextView tvTaiKhoanKhac = (TextView) root.findViewById(R.id.tvTaiKhoanKhac_FragmentDangNhapKhongLuu);
        TextView tvUsername = (TextView) root.findViewById(R.id.tvTenTaiKhoan_FragmentDangNhapKhongLuu);
        etPassword = (EditText) root.findViewById(R.id.etMatKhau_FragmentDangNhapKhongLuu);
        cbLuuDangNhap = (CheckBox) root.findViewById(R.id.cbLuuDangNhap_FragmentDangNhapKhongLuu);

        root.findViewById(R.id.btnDangNhap_FragmentDangNhapKhongLuu).setOnClickListener(this);
        tvTaiKhoanKhac.setOnClickListener(this);

        mySharedPref = new MySharedPreferences(getActivity(), SupportKeyList.SHAREDPREF_TEN_FILE);
        tvUsername.setText(tvUsername.getText().toString() + " " + mySharedPref.getTEN_TAI_KHOAN());
        tvTaiKhoanKhac.setText(R.string.tai_khoan_khac);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvTaiKhoanKhac_FragmentDangNhapKhongLuu:
                //Đăng xuất
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new DangNhapFragment()).commit();
                mySharedPref.setTEN_TAI_KHOAN("");
                mySharedPref.setDA_DANG_NHAP(false);
                mySharedPref.setLUU_DANG_NHAP(false);
                mySharedPref.setLOAI_TAI_KHOAN(-1);
                break;

            case R.id.btnDangNhap_FragmentDangNhapKhongLuu:
                //Kiểm tra thông tin và tạo kết nối tới server để đăng nhập
                if (!etPassword.getText().toString().isEmpty()) {
                    //Hiện dialog loading chờ xử lý kết quả
                    loadingDialog = new ProgressDialog(getActivity());
                    loadingDialog.setMessage(getString(R.string.dialog_loading));
                    loadingDialog.setCancelable(false);
                    loadingDialog.setInverseBackgroundForced(false);
                    loadingDialog.show();
                    //Gọi API server
                    new GoiAPIServerAsyncTask(this).execute(SupportKeyList.API_DANG_NHAP, SupportKeyList.URL_DANG_NHAP, mySharedPref.getTEN_TAI_KHOAN(), etPassword.getText().toString());
                }
                else
                    Toast.makeText(getContext(), R.string.toast_nhap_thieu, Toast.LENGTH_SHORT).show();
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
                mySharedPref.setLUU_DANG_NHAP(cbLuuDangNhap.isChecked());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new TaiKhoanFragment()).commit();
                break;
            case SupportKeyList.DANG_NHAP_THAT_BAI:
                loadingDialog.dismiss();
                Toast.makeText(getActivity(), R.string.toast_dang_nhap_that_bai, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
