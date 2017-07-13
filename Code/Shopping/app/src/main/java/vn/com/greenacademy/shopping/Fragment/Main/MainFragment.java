package vn.com.greenacademy.shopping.Fragment.Main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.greenacademy.shopping.Handle.HandleUi.Dialog.Adapter.BannerXuHuongThoiTrangAdapter;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment{
    private RecyclerView listXuHuongThoiTrang;

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        listXuHuongThoiTrang = (RecyclerView) root.findViewById(R.id.list_xu_huong_thoi_trang);

        listXuHuongThoiTrang.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        listXuHuongThoiTrang.setAdapter(new BannerXuHuongThoiTrangAdapter(getActivity(), getActivity().getSupportFragmentManager()));
        return root;
    }
}
