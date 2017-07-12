package vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Util.SharePreference.MySharedPreferences;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongTinCaNhanFragment extends Fragment implements View.OnClickListener {
    private MySharedPreferences mySharedPref;
    private BaseFragment baseFragment;
    private ProgressDialog progressDialog;

    public ThongTinCaNhanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_thong_tin_ca_nhan, container, false);
        EditText etUsername = (EditText) root.findViewById(R.id.etUsername_FragmentThongTinCaNhan);

        root.findViewById(R.id.btnLuu_FragmentThongTinCaNhan).setOnClickListener(this);
        root.findViewById(R.id.btnHuy_FragmentThongTinCaNhan).setOnClickListener(this);

        mySharedPref = new MySharedPreferences(getActivity(), SupportKeyList.SHAREDPREF_TEN_FILE);
        baseFragment = new BaseFragment(getActivity().getSupportFragmentManager());
        etUsername.setText(mySharedPref.getTenTaiKhoan());

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLuu_FragmentThongTinCaNhan:
                Toast.makeText(getActivity(), "Lưu", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnHuy_FragmentThongTinCaNhan:
                break;
        }
        baseFragment.ChuyenFragment(new TaiKhoanFragment(), SupportKeyList.TAG_FRAGMENT_THONG_TIN_CA_NHAN, false);
    }
}
