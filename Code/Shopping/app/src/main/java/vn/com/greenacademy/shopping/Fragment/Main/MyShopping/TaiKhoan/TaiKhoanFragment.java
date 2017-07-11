package vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import vn.com.greenacademy.shopping.Data.MySharedPreferences;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaiKhoanFragment extends Fragment implements View.OnClickListener {
    private MySharedPreferences mySharedPref;

    public TaiKhoanFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        TextView tvUsername = (TextView) root.findViewById(R.id.tvUsername_FragmentTaiKhoan);

        root.findViewById(R.id.tvDoiMatKhau_FragmentTaiKhoan).setOnClickListener(this);
        root.findViewById(R.id.btnEdit_FragmentTaiKhoan).setOnClickListener(this);

        mySharedPref = new MySharedPreferences(getActivity(), SupportKeyList.SHAREDPREF_TEN_FILE);
        tvUsername.setText(mySharedPref.getTEN_TAI_KHOAN());

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();
        return root;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.tvDoiMatKhau_FragmentTaiKhoan:
                Toast.makeText(getActivity(), "Đổi mật khẩu", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnEdit_FragmentTaiKhoan:
                transaction.replace(R.id.content_main, new ThongTinCaNhanFragment()).commit();
                break;
        }
    }
}
