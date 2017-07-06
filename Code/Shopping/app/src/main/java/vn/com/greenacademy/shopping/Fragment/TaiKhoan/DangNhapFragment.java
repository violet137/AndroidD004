package vn.com.greenacademy.shopping.Fragment.TaiKhoan;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView tvDangKy;
    private Button btnDangNhap;

    private Context context;
    private MySharedPreferences mySharedPref;
    private ProgressDialog loadingDialog;

    private static final String URL_DANG_NHAP = "http://tamod.vn:9045/TaiKhoan/DangNhap";

    public DangNhapFragment(Context context) {
        this.context = context;
        mySharedPref = new MySharedPreferences(context, SupportKeyList.SHAREDPREF_TEN_FILE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_dang_nhap, container, false);
        etTenDangNhap = (EditText) root.findViewById(R.id.etTenDangNhap_FragmentDangNhap);
        etPassword = (EditText) root.findViewById(R.id.etMatKhau_FragmentDangNhap);
        tvDangKy = (TextView) root.findViewById(R.id.tvDangKy_FragmentDangNhap);
        btnDangNhap = (Button) root.findViewById(R.id.btnDangNhap_FragmentDangNhap);

        //Thay đổi chữ đăng ký
        ((TextView) root.findViewById(R.id.tvDangKy_FragmentDangNhap)).setText(R.string.dang_ky);

        btnDangNhap.setOnClickListener(this);
        tvDangKy.setOnClickListener(this);

        //Ẩn hiện password
        etPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_RIGHT = 2;

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if(motionEvent.getRawX() >= (etPassword.getRight() - etPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        etPassword.setTransformationMethod(null);
                        return true;
                    }
                }
                etPassword.setTransformationMethod(new PasswordTransformationMethod());
                return false;
            }
        });
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDangNhap_FragmentDangNhap:
                //Kiểm tra thông tin và tạo kết nối tới server để đăng nhập
                if (!etTenDangNhap.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()) {
                    //Hiện dialog loading chờ xử lý kết quả
                    loadingDialog = new ProgressDialog(context);
                    loadingDialog.setMessage(getString(R.string.dialog_loading));
                    loadingDialog.setCancelable(false);
                    loadingDialog.setInverseBackgroundForced(false);
                    loadingDialog.show();
                    //Gọi API server
                    new GoiAPIServerAsyncTask(this).execute(SupportKeyList.API_DANG_NHAP, URL_DANG_NHAP, etTenDangNhap.getText().toString(), etPassword.getText().toString());
                }
                else
                    Toast.makeText(getContext(), R.string.toast_nhap_thieu, Toast.LENGTH_SHORT).show();
                break;

            case R.id.tvDangKy_FragmentDangNhap:
                //Chuyền sang màn hình đăng ký
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_Main, new DangKyFragment()).addToBackStack("dang_ky_fragment").commit();
                break;
        }
    }

    //Xử lý kết quả trả về
    @Override
    public void KetQua(int result) {
        switch (result){
            case SupportKeyList.LOI_KET_NOI:
                Toast.makeText(context, getString(R.string.toast_loi_ket_noi), Toast.LENGTH_LONG).show();
                loadingDialog.dismiss();
                break;
            case SupportKeyList.DANG_NHAP_THANH_CONG:
                loadingDialog.dismiss();
                Toast.makeText(context, getString(R.string.toast_dang_nhap_thanh_cong) + " " + etTenDangNhap.getText().toString() , Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_Main, new MainFragment(context)).commit();
                mySharedPref.setLUU_DANG_NHAP(true);
                mySharedPref.setLOAI_TAI_KHOAN(SupportKeyList.ACCOUNT_THUONG);
                break;
            case SupportKeyList.DANG_NHAP_THAT_BAI:
                loadingDialog.dismiss();
                Toast.makeText(context, R.string.toast_dang_nhap_that_bai, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
