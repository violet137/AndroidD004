package vn.com.greenacademy.shopping.Fragment.Support;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Handle.HandleData.Support.SupportHandler;
import vn.com.greenacademy.shopping.Interface.ObjectCallBack;
import vn.com.greenacademy.shopping.MainActivity;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupportFragment extends Fragment {

    public static ObjectCallBack objectCallBack;

    public SupportFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.tvTenMuc.setVisibility(View.VISIBLE);
        MainActivity.tvTenMuc.setText("Hổ Trợ");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_support, container, false);

        final ListView lvSupport = (ListView) view.findViewById(R.id.lvSupport_SuportFragment);

        final SupportHandler supportHandler = new SupportHandler(getActivity());

        supportHandler.Cick(lvSupport);

        objectCallBack = new ObjectCallBack() {
            @Override
            public void callBack(Object object) {
                lvSupport.setAdapter(supportHandler.getAdapter(object));
            }
        };

        supportHandler.loadData();

        return view;
    }

}