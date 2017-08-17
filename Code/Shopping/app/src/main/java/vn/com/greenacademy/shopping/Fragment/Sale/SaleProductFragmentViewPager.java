package vn.com.greenacademy.shopping.Fragment.Sale;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleUi.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale.AdapterRecyclerColorProduct;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleProductFragmentViewPager extends Fragment implements View.OnClickListener {

    ArrayList<SanPham> sanPhamArrayList;
    int positionViewPager;
    public SaleProductFragmentViewPager(ArrayList<SanPham> sanPhamArrayList, int positionViewPager) {
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
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll1_SaleProduct_fragment);
        linearLayout.setOnClickListener(this);
        setData(imageView, tvGia, tvSanPhamMoi,rvMau ,linearLayout,positionViewPager);

        ImageView imageView2 = (ImageView) view.findViewById(R.id.iv2_Sale_product_fragment);
        TextView tvGia2 = (TextView) view.findViewById(R.id.tvGia2_Sale_product_fragment);
        TextView tvSanPhamMoi2 = (TextView) view.findViewById(R.id.tvNewProduct2_Sale_product_fragment);
        RecyclerView rvMau2 = (RecyclerView) view.findViewById(R.id.rvMau2_Sale_product_fragment);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.ll2_SaleProduct_fragment);
        linearLayout2.setOnClickListener(this);
        setData(imageView2, tvGia2, tvSanPhamMoi2,rvMau2, linearLayout2,positionViewPager+1);

        int soLuongViewPager = 0;

        if (sanPhamArrayList.size() < 5){
            soLuongViewPager = 6;
        } else  if (sanPhamArrayList.size() < 8){
            soLuongViewPager = 3;
        }else{
            soLuongViewPager = 0;
        }

        if (positionViewPager+2 == 8-soLuongViewPager){
            LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.ll4_SaleProduct_fragment);
            LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.ll3_SaleProduct_fragment);
            linearLayout3.setVisibility(View.GONE);
            linearLayout4.setVisibility(View.VISIBLE);
            SanPham sanPham = new SanPham();
            sanPham.setIdSanPham(-1);
            linearLayout4.setTag(sanPham);
            linearLayout4.setOnClickListener(this);
        }else {
            ImageView imageView3 = (ImageView) view.findViewById(R.id.iv3_Sale_product_fragment);
            TextView tvGia3 = (TextView) view.findViewById(R.id.tvGia3_Sale_product_fragment);
            TextView tvSanPhamMoi3 = (TextView) view.findViewById(R.id.tvNewProduct3_Sale_product_fragment);
            RecyclerView rvMau3 = (RecyclerView) view.findViewById(R.id.rvMau3_Sale_product_fragment);
            LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.ll3_SaleProduct_fragment);
            linearLayout3.setOnClickListener(this);
            setData(imageView3, tvGia3, tvSanPhamMoi3,rvMau3,linearLayout3, positionViewPager+2);
        }

        return view;
    }

    void setData(ImageView imageView, TextView textView, TextView textView2, RecyclerView recyclerView,LinearLayout linearLayout,int position){
        ImageLoad imageLoad = new ImageLoad(getActivity());
        imageLoad.load(sanPhamArrayList.get(position).getHinhDaiDien(), imageView);

        textView.setText(SanPhamHandler.chuyenGia(sanPhamArrayList.get(position).getGiamGia()));

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),
                sanPhamArrayList.get(position).getMauSanPham().length> SupportKeyList.SoMauHienThiToiDa ? SupportKeyList.SoMauHienThiToiDa + 1 : sanPhamArrayList.get(position).getMauSanPham().length));

        AdapterRecyclerColorProduct adapterRecyclerColorProduct = new AdapterRecyclerColorProduct(
                getContext(), sanPhamArrayList.get(position).getMauSanPham());

        recyclerView.setAdapter(adapterRecyclerColorProduct);

        linearLayout.setTag(sanPhamArrayList.get(position));
    }

    @Override
    public void onClick(View v) {
        SanPham sanPham = (SanPham) v.getTag();
        Toast.makeText(getContext(),String.valueOf(sanPham.getIdSanPham()),Toast.LENGTH_SHORT).show();
    }
}
