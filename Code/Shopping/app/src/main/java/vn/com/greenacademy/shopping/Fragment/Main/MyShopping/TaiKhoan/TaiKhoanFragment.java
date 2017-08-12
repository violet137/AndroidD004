package vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Util.SharePreference.MySharedPreferences;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaiKhoanFragment extends Fragment implements View.OnClickListener {
    private MySharedPreferences mySharedPref;
    private BaseFragment baseFragment;

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
        baseFragment = new BaseFragment(getActivity(), getActivity().getSupportFragmentManager());
        tvUsername.setText(mySharedPref.getTenTaiKhoan());

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvDoiMatKhau_FragmentTaiKhoan:
                Toast.makeText(getActivity(), "Đổi mật khẩu", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnEdit_FragmentTaiKhoan:
                baseFragment.ChuyenFragment(new ThongTinCaNhanFragment(), SupportKeyList.TAG_FRAGMENT_THONG_TIN_CA_NHAN, true);
                break;
        }
    }
}
