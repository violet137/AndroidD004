package vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import vn.com.greenacademy.shopping.Handle.HandleData.DangKyTkHandler;
import vn.com.greenacademy.shopping.Interface.ErrorCallBack;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DangKyFragment extends Fragment implements ErrorCallBack {


    public DangKyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dang_ky, container, false);
        TextView tvEmail = (TextView) view.findViewById(R.id.tvEmail_fragmentDangKy);
        TextView tvPassword = (TextView) view.findViewById(R.id.tvPassword_fragmentDangKy);
        TextView tvRePassword = (TextView) view.findViewById(R.id.tvRePassword_fragmentDangKy);

        final EditText etEmail = (EditText) view.findViewById(R.id.etEmail_fragmentDangKy);
        final EditText etPassword = (EditText) view.findViewById(R.id.etPassword_fragmentDangKy);
        final EditText etRePassword = (EditText) view.findViewById(R.id.etRePassword_fragmentDangKy);

        view.findViewById(R.id.btnRegister_fragmentDangKy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKyTkHandler dangKyTkHandler = new DangKyTkHandler(getActivity(), DangKyFragment.this);
                if (dangKyTkHandler.createAccount(etEmail.getText().toString(),
                        etPassword.getText().toString(),etRePassword.getText().toString())){


                }
            }
        });

        return view;
    }

    @Override
    public void errorCallBack(String error) {

    }
}
