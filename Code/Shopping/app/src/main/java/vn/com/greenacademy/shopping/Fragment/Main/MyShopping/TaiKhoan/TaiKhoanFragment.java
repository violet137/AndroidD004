package vn.com.greenacademy.shopping.Fragment.Main.MyShopping.TaiKhoan;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import vn.com.greenacademy.shopping.Data.MySharedPreferences;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaiKhoanFragment extends Fragment {
    private MySharedPreferences mySharedPref;

    public TaiKhoanFragment(Context context) {
        mySharedPref = new MySharedPreferences(context, SupportKeyList.SHAREDPREF_TEN_FILE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        TextView tvUsername = (TextView) root.findViewById(R.id.tvUsername_FragmentTaiKhoan);

        tvUsername.setText(mySharedPref.getTEN_TAI_KHOAN());
        return root;
    }

}
