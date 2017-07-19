package vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Handle.HandleData.DangKyTkHandler;
import vn.com.greenacademy.shopping.Interface.ErrorCallBack;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DangKyFragment extends Fragment implements ErrorCallBack {


    public DangKyFragment() {
        // Required empty public constructor
    }

    TextView tvEmail ;
    TextView tvPassword ;
    TextView tvRePassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dang_ky, container, false);
        tvEmail = (TextView) view.findViewById(R.id.tvEmail_fragmentDangKy);
        tvPassword = (TextView) view.findViewById(R.id.tvPassword_fragmentDangKy);
        tvRePassword = (TextView) view.findViewById(R.id.tvRePassword_fragmentDangKy);

        final EditText etEmail = (EditText) view.findViewById(R.id.etEmail_fragmentDangKy);
        final EditText etPassword = (EditText) view.findViewById(R.id.etPassword_fragmentDangKy);
        final EditText etRePassword = (EditText) view.findViewById(R.id.etRePassword_fragmentDangKy);

        view.findViewById(R.id.btnRegister_fragmentDangKy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKyTkHandler dangKyTkHandler = new DangKyTkHandler(getActivity(), DangKyFragment.this);
                if (dangKyTkHandler.createAccount(etEmail.getText().toString(),
                        etPassword.getText().toString(),etRePassword.getText().toString())){
                    Toast.makeText(getActivity(), "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Không thể tạo tài khoản", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void errorCallBack(String error, int errorPosition) {
        switch (errorPosition){
            case SupportKeyList.Email_Error:
                tvEmail.setTextColor(Color.RED);
                tvEmail.setText("Email(username):*  Tài Khoản đã được sử dung");
                break;
            case SupportKeyList.Password_Error:
                tvPassword.setTextColor(Color.RED);
                tvPassword.setText("Password:*   Độ bảo mật kém");
                break;
            case SupportKeyList.Re_type_Password_Error:
                tvPassword.setTextColor(Color.RED);
                tvPassword.setText("Re-type password*   password không trùng khớp");
                break;
            case SupportKeyList.Connect_Error:
                Toast.makeText(getActivity(), "Không thể kết nối", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
