package vn.com.greenacademy.shopping.Fragment.Main.DanhMucSanPham;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.DanhMucSPHandler;
import vn.com.greenacademy.shopping.Model.MucSanPham;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DanhMucSanPhamFragment extends Fragment {

    boolean listenerBack = false;

    DanhMucSPHandler danhMucSPHandler;

    ArrayList<MucSanPham> mucSanPhamArrayList;

    ImageView ivPhoto;
    ViewPager vpHotProduct;
    RecyclerView rvMenu;

    public DanhMucSanPhamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        danhMucSPHandler = new DanhMucSPHandler(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_danh_muc_san_pham, container, false);

        ivPhoto = (ImageView) view.findViewById(R.id.ivFashion_menu_main);

        vpHotProduct = (ViewPager) view.findViewById(R.id.vpSanPham_vp_sp_hot);

        rvMenu = (RecyclerView) view.findViewById(R.id.rvMenu_DanhMucSanPham_Fragment);



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listenerBack = true;
    }
}
