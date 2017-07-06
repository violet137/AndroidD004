package vn.com.greenacademy.shopping.Fragment.Main;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vn.com.greenacademy.shopping.Data.MySharedPreferences;
import vn.com.greenacademy.shopping.Fragment.TaiKhoan.DangNhapFragment;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    Button btnDangXuat;

    private MySharedPreferences mySharedPreferences;
    public MainFragment(Context context) {
        mySharedPreferences = new MySharedPreferences(context, SupportKeyList.SHAREDPREF_TEN_FILE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        btnDangXuat = (Button) root.findViewById(R.id.btnDangXuat_FragmentMain);

        btnDangXuat.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDangXuat_FragmentMain:
                //Chuyển về màn hình đăng nhập, lưu lại trạng thái
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_Main, new DangNhapFragment(getContext())).commit();
                mySharedPreferences.setLUU_DANG_NHAP(false);
                break;
        }
    }
}
