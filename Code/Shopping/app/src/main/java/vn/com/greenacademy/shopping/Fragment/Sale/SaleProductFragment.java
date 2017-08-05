package vn.com.greenacademy.shopping.Fragment.Sale;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
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
        if (positionViewPager == 0){
            this.positionViewPager = positionViewPager;
        }else{
            this.positionViewPager = positionViewPager + (int) Math.pow(2,positionViewPager);
        }
        this.sanPhamArrayList = sanPhamArrayList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale_product, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv_Sale_product_fragment);
        TextView tvGia = (TextView) view.findViewById(R.id.tvGia_Sale_product_fragment);
        TextView tvSanPhamMoi = (TextView) view.findViewById(R.id.tvNewProduct_Sale_product_fragment);
        RecyclerView rvMau = (RecyclerView) view.findViewById(R.id.rvMau_Sale_product_fragment);
        setData(imageView, tvGia, tvSanPhamMoi,rvMau, positionViewPager);

        ImageView imageView2 = (ImageView) view.findViewById(R.id.iv2_Sale_product_fragment);
        TextView tvGia2 = (TextView) view.findViewById(R.id.tvGia2_Sale_product_fragment);
        TextView tvSanPhamMoi2 = (TextView) view.findViewById(R.id.tvNewProduct2_Sale_product_fragment);
        RecyclerView rvMau2 = (RecyclerView) view.findViewById(R.id.rvMau2_Sale_product_fragment);
        setData(imageView2, tvGia2, tvSanPhamMoi2,rvMau2, positionViewPager+1);

        ImageView imageView3 = (ImageView) view.findViewById(R.id.iv3_Sale_product_fragment);
        TextView tvGia3 = (TextView) view.findViewById(R.id.tvGia3_Sale_product_fragment);
        TextView tvSanPhamMoi3 = (TextView) view.findViewById(R.id.tvNewProduct3_Sale_product_fragment);
        RecyclerView rvMau3 = (RecyclerView) view.findViewById(R.id.rvMau3_Sale_product_fragment);
        setData(imageView3, tvGia3, tvSanPhamMoi3,rvMau3, positionViewPager+2);


        return view;
    }

    void setData(ImageView imageView, TextView textView, TextView textView2, RecyclerView recyclerView,int position){
        ImageLoad imageLoad = new ImageLoad(getActivity());
        imageLoad.load(sanPhamArrayList.get(position).getHinhDaiDien(), imageView);

        textView.setText(String.valueOf(sanPhamArrayList.get(position).getGiamGia()));

    }

}
