package vn.com.greenacademy.shopping.Fragment.DanhMucSanPham;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.DanhMuc.MucSanPham;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotProductViewPagerFragment extends Fragment {

    ArrayList<MucSanPham> mucSanPhamArrayList;
    int positionViewPager;

    public HotProductViewPagerFragment(ArrayList<MucSanPham> mucSanPhamArrayList, int positionViewPager) {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hot_product_view_pager, container, false);
    }

}
