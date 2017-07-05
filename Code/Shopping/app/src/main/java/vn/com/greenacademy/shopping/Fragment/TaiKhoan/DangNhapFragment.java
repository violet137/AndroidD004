package vn.com.greenacademy.shopping.Fragment.TaiKhoan;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Asynctask.GoiAPIServerAsyncTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class DangNhapFragment extends Fragment implements View.OnClickListener {
    private EditText etTenDangNhap;
    private EditText etPassword;
    private TextView tvDangKy;
    private Button btnDangNhap;

    private Context context;
    private static final String URL_DANG_NHAP = "http://tamod.vn:9045/TaiKhoan/DangNhap";

    public DangNhapFragment(Context context) {
        this.context = context;
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
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

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
                if (!etTenDangNhap.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty())
                    new GoiAPIServerAsyncTask(context, getActivity().getSupportFragmentManager()).execute(SupportKeyList.API_DANG_NHAP, URL_DANG_NHAP, etTenDangNhap.getText().toString(), etPassword.getText().toString());
                else
                    Toast.makeText(getContext(), R.string.toast_nhap_thieu, Toast.LENGTH_SHORT).show();
                break;

            case R.id.tvDangKy_FragmentDangNhap:
                //Chuyền sang màn hình đăng ký
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_Main, new DangKyFragment()).addToBackStack("dang_ky_fragment").commit();
                break;
        }
    }
}
