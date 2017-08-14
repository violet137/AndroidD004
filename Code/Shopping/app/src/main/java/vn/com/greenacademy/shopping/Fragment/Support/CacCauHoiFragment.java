package vn.com.greenacademy.shopping.Fragment.Support;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import vn.com.greenacademy.shopping.Handle.HandleData.Support.SupportHandler;
import vn.com.greenacademy.shopping.Interface.ObjectCallBack;
import vn.com.greenacademy.shopping.MainActivity;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CacCauHoiFragment extends Fragment {

    public static ObjectCallBack objectCallBack;

    public CacCauHoiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.tvTenMuc.setVisibility(View.VISIBLE);
        MainActivity.tvTenMuc.setText("Các Câu Hỏi Thường Gặp");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cac_cau_hoi, container, false);

        final ListView lvCauHoi = (ListView) view.findViewById(R.id.lvCauHoi_CacCauHoiFragment);

        final SupportHandler supportHandler = new SupportHandler(getActivity());

        supportHandler.ClickCauHoi(lvCauHoi);

        objectCallBack = new ObjectCallBack() {
            @Override
            public void callBack(Object object, int flag) {
                lvCauHoi.setAdapter(supportHandler.getAdapterCacCauHoi(object));
            }
        };

        supportHandler.loadDataCacCauHoiTG();

        return view;
    }

}
