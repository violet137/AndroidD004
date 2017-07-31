package vn.com.greenacademy.shopping.Fragment.Main.DanhMucSanPham;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import vn.com.greenacademy.shopping.Handle.HandleData.DanhMucSPHandler;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DanhMucSPFragment extends Fragment {

    String loaiSP;
    public DanhMucSPFragment(String loaiSP) {
        // Required empty public constructor
        this.loaiSP = loaiSP;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_danh_muc_s, container, false);

        ListView listView = (ListView) view.findViewById(R.id.lvDanhMuc_DanhMucSP_Fragment);

        DanhMucSPHandler danhMucSPHandler = new DanhMucSPHandler(getActivity());

        danhMucSPHandler.setListView(listView, loaiSP);

        return view;
    }

}
