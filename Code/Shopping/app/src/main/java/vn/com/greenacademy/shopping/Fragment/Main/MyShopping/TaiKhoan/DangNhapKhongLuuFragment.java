package vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Handle.HandleData.DataHandler;
import vn.com.greenacademy.shopping.Util.SharePreference.MySharedPreferences;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DangNhapKhongLuuFragment extends Fragment implements View.OnClickListener, DataCallBack {
    EditText etTenDangNhap;
    EditText etPassword;
    CheckBox cbLuuDangNhap;
    private ProgressDialog loadingDialog;

    private MySharedPreferences mySharedPref;
    private DataHandler dataHandler;
    private BaseFragment baseFragment;
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

        dataHandler = new DataHandler(getActivity(), this);
        baseFragment = new BaseFragment(getActivity().getSupportFragmentManager());
        mySharedPref = new MySharedPreferences(getActivity(), SupportKeyList.SHAREDPREF_TEN_FILE);
        tvUsername.setText(tvUsername.getText().toString() + " " + mySharedPref.getTenTaiKhoan());
        tvTaiKhoanKhac.setText(R.string.tai_khoan_khac);

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvTaiKhoanKhac_FragmentDangNhapKhongLuu:
                //Đăng xuất
                dataHandler.DangXuat();
                baseFragment.ChuyenFragment(new DangNhapFragment(), SupportKeyList.TAG_FRAGMENT_DANG_NHAP, false);
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
                    dataHandler.DangNhap(SupportKeyList.ACCOUNT_THUONG, mySharedPref.getTenTaiKhoan(), etPassword.getText().toString());
                }
                else
                    Toast.makeText(getContext(), R.string.toast_nhap_thieu, Toast.LENGTH_SHORT).show();
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
                mySharedPref.setLuuDangNhap(cbLuuDangNhap.isChecked());
                baseFragment.ChuyenFragment(new TaiKhoanFragment(), SupportKeyList.TAG_FRAGMENT_TAI_KHOAN, false);
                break;
            case SupportKeyList.DANG_NHAP_THAT_BAI:
                Toast.makeText(getActivity(), R.string.toast_dang_nhap_that_bai, Toast.LENGTH_SHORT).show();
                break;
        }
        loadingDialog.dismiss();
    }
}
