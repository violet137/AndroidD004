package vn.com.greenacademy.shopping.Fragment.Support;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.Support.SupportHandler;
import vn.com.greenacademy.shopping.Interface.ObjectCallBack;
import vn.com.greenacademy.shopping.MainActivity;
import vn.com.greenacademy.shopping.Model.Support.CauHoiTG;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CacCauHoiFragment extends Fragment {

    boolean listenBack = false;

    public static ObjectCallBack objectCallBack;

    public static ArrayList<CauHoiTG> cauHoiTGArrayList;

    ListView lvCauHoi;

    SupportHandler supportHandler;

    public CacCauHoiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportHandler = new SupportHandler(getActivity());
        supportHandler.loadDataCacCauHoiTG();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.tvTenMuc.setVisibility(View.VISIBLE);
        MainActivity.tvTenMuc.setText("Các Câu Hỏi Thường Gặp");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cac_cau_hoi, container, false);

        lvCauHoi = (ListView) view.findViewById(R.id.lvCauHoi_CacCauHoiFragment);


        supportHandler.ClickCauHoi(lvCauHoi);

        objectCallBack = new ObjectCallBack() {
            @Override
            public void callBack(Object object, int flag) {
                cauHoiTGArrayList = (ArrayList<CauHoiTG>) object;
                lvCauHoi.setAdapter(supportHandler.getAdapterCacCauHoi(cauHoiTGArrayList));
            }
        };
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listenBack){
            lvCauHoi.setAdapter(supportHandler.getAdapterCacCauHoi(cauHoiTGArrayList));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listenBack = true;
    }
}
