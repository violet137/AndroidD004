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
//        int posCard1 = -1, posCard2 =-1, posCard3=-1;
//        switch (positionViewPager){
//            case 0:
//                posCard1 = 0;
//                posCard2 = 1;
//                if (sanPhamArrayList.size()<5){
//
//                }else {
//                    posCard3 = 3;
//                }
//                break;
//            case 1:
//                posCard1 = 0;
//                posCard2 = 1;
//                if (sanPhamArrayList.size()<8){
//
//                }else {
//                    posCard3 = 3;
//                }
//                break;
//            case 2:
//                posCard1 = 0;
//                posCard2 = 1;
//                break;
//        }
        // the 1
        ImageView imageView1 = (ImageView) view.findViewById(R.id.ivAnh1_item_SaleProduct);
        ImageLoad imageLoad = new ImageLoad(getActivity());
        imageLoad.ImageLoad(sanPhamArrayList.get(positionViewPager+1).getHinhDaiDien(), imageView1);

        TextView textView = (TextView) view.findViewById(R.id.tvGia1_item_SaleProduct);
        textView.setText(String.valueOf(sanPhamArrayList.get(positionViewPager+1).getGiamGia()));

        // the 2
        ImageView imageView2 = (ImageView) view.findViewById(R.id.ivAnh2_item_SaleProduct);
        ImageLoad imageLoad2 = new ImageLoad(getActivity());
        imageLoad2.ImageLoad(sanPhamArrayList.get(positionViewPager+2).getHinhDaiDien(), imageView2);

        TextView textView2 = (TextView) view.findViewById(R.id.tvGia2_item_SaleProduct);
        textView2.setText(String.valueOf( sanPhamArrayList.get(positionViewPager+2).getGiamGia()));

        // the 3
//        if (sanPhamArrayList.size()<5){
//
//        }else if (sanPhamArrayList.size()<8){
//            ImageView imageView3 = (ImageView) view.findViewById(R.id.ivAnh1_item_SaleProduct);
//            ImageLoad imageLoad3 = new ImageLoad(getActivity());
//            imageLoad3.ImageLoad(sanPhamArrayList.get(posCard3).getHinhDaiDien(), imageView3);
//
//            TextView textView3 = (TextView) view.findViewById(R.id.tvGia1_item_SaleProduct);
//            textView3.setText((int) sanPhamArrayList.get(posCard3).getGiamGia());
//        }else {
//            ImageView imageView3 = (ImageView) view.findViewById(R.id.ivAnh1_item_SaleProduct);
//            ImageLoad imageLoad3 = new ImageLoad(getActivity());
//            imageLoad3.ImageLoad(sanPhamArrayList.get(posCard3).getHinhDaiDien(), imageView3);
//
//            TextView textView3 = (TextView) view.findViewById(R.id.tvGia1_item_SaleProduct);
//            textView3.setText((int) sanPhamArrayList.get(posCard3).getGiamGia());
//        }

        return view;
    }

}
