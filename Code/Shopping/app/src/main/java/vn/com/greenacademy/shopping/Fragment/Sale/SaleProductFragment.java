package vn.com.greenacademy.shopping.Fragment.Sale;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Model.Sale;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleProductFragment extends Fragment {

    ArrayList<SanPham> sanPhamArrayList;
    int positionViewPager;
    public SaleProductFragment(ArrayList<SanPham> sanPhamArrayList, int positionViewPager) {
        // Required empty public constructor
        this.positionViewPager = positionViewPager;
        this.sanPhamArrayList = sanPhamArrayList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale_product, container, false);

        return view;
    }

}
