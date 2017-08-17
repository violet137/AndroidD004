package vn.com.greenacademy.shopping.Fragment.Home;


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

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.Home.ClickListenerHomeItem;
import vn.com.greenacademy.shopping.Handle.HandleUi.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale.AdapterRecyclerColorProduct;
import vn.com.greenacademy.shopping.Model.Home.ItemHome;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewProductFragmentViewPager extends Fragment {

    ArrayList<SanPham> sanPhamArrayList;
    int position;
    ImageLoad imageLoad;

    public NewProductFragmentViewPager(ArrayList<SanPham> sanPhamArrayList, int positionViewPager) {
        // Required empty public constructor
        this.sanPhamArrayList=sanPhamArrayList;
        if (positionViewPager == 0) {
            position = positionViewPager;
        }else {
            position = (int) Math.pow(2, positionViewPager+1);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_product_fragment_view_pager, container, false);
        // the 1
        LinearLayout llNewProduct = (LinearLayout) view.findViewById(R.id.llNewProduct_1_NewProductFragment);
        ImageView ivNewProduct = (ImageView) view.findViewById(R.id.ivNewProduct_1_NewProductFragment);
        TextView tvNameProduct = (TextView) view.findViewById(R.id.tvNameProduct_1_NewProductFragment);
        TextView tvGiaProduct = (TextView) view.findViewById(R.id.tvGiaProduct_1_NewProductFragment);
        RecyclerView rvMauProduct = (RecyclerView) view.findViewById(R.id.rvMauProduct_1_NewProductFragment);
        setDataLayout(llNewProduct, ivNewProduct, tvNameProduct, tvGiaProduct, rvMauProduct, position);

        // the 2
        LinearLayout llNewProduct2 = (LinearLayout) view.findViewById(R.id.llNewProduct_2_NewProductFragment);
        ImageView ivNewProduct2 = (ImageView) view.findViewById(R.id.ivNewProduct_2_NewProductFragment);
        TextView tvNameProduct2 = (TextView) view.findViewById(R.id.tvNameProduct_2_NewProductFragment);
        TextView tvGiaProduct2 = (TextView) view.findViewById(R.id.tvGiaProduct_2_NewProductFragment);
        RecyclerView rvMauProduct2 = (RecyclerView) view.findViewById(R.id.rvMauProduct_2_NewProductFragment);
        setDataLayout(llNewProduct2, ivNewProduct2, tvNameProduct2, tvGiaProduct2, rvMauProduct2, position+1);

        // the 3
        LinearLayout llNewProduct3 = (LinearLayout) view.findViewById(R.id.llNewProduct_3_NewProductFragment);
        ImageView ivNewProduct3 = (ImageView) view.findViewById(R.id.ivNewProduct_3_NewProductFragment);
        TextView tvNameProduct3 = (TextView) view.findViewById(R.id.tvNameProduct_3_NewProductFragment);
        TextView tvGiaProduct3 = (TextView) view.findViewById(R.id.tvGiaProduct_3_NewProductFragment);
        RecyclerView rvMauProduct3 = (RecyclerView) view.findViewById(R.id.rvMauProduct_3_NewProductFragment);
        setDataLayout(llNewProduct3, ivNewProduct3, tvNameProduct3, tvGiaProduct3, rvMauProduct3, position+2);

        if (position+3 == 11){

            view.findViewById(R.id.llNewProduct_4_NewProductFragment).setVisibility(View.GONE);

            LinearLayout llNewProduct5 = (LinearLayout) view.findViewById(R.id.llNewProduct_5_NewProductFragment);
            llNewProduct5.setVisibility(View.VISIBLE);

            ItemHome itemHome = new ItemHome();
            itemHome.setFlagItemHome(SupportKeyList.ClickHome_NewProduct);
            itemHome.setId("ALL");

            llNewProduct5.setTag(itemHome);
            llNewProduct5.setOnClickListener(ClickListenerHomeItem.onClickListener);

        }else {
            // the 4
            LinearLayout llNewProduct4 = (LinearLayout) view.findViewById(R.id.llNewProduct_4_NewProductFragment);
            ImageView ivNewProduct4 = (ImageView) view.findViewById(R.id.ivNewProduct_4_NewProductFragment);
            TextView tvNameProduct4 = (TextView) view.findViewById(R.id.tvNameProduct_4_NewProductFragment);
            TextView tvGiaProduct4 = (TextView) view.findViewById(R.id.tvGiaProduct_4_NewProductFragment);
            RecyclerView rvMauProduct4 = (RecyclerView) view.findViewById(R.id.rvMauProduct_4_NewProductFragment);
            setDataLayout(llNewProduct4, ivNewProduct4, tvNameProduct4, tvGiaProduct4, rvMauProduct4, position+3);
        }

        return view;
    }

    private void setDataLayout(LinearLayout llNewProduct, ImageView ivNewProduct, TextView tvNameProduct,
                               TextView tvGiaProduct, RecyclerView rvMauProduct, int positionItem) {
        SanPham item = sanPhamArrayList.get(positionItem);
        imageLoad = new ImageLoad(getActivity());

        imageLoad.load(item.getHinhSanPham().get(0).getLinkHinh()[0],ivNewProduct);
        tvNameProduct.setText(item.getTenSanPham());
        tvGiaProduct.setText(SanPhamHandler.chuyenGia(item.getGiaSanPham()));

        AdapterRecyclerColorProduct adapterRecyclerColorProduct = new AdapterRecyclerColorProduct(getContext(), item.getMauSanPham());
        rvMauProduct.setLayoutManager(new GridLayoutManager(getActivity(),
                item.getMauSanPham().length> SupportKeyList.SoMauHienThiToiDa ?
                        SupportKeyList.SoMauHienThiToiDa + 1 : item.getMauSanPham().length));
        rvMauProduct.setAdapter(adapterRecyclerColorProduct);

        ItemHome itemHome = new ItemHome();
        itemHome.setFlagItemHome(SupportKeyList.ClickHome_NewProduct);
        itemHome.setId(String.valueOf(item.getIdSanPham()));

        llNewProduct.setTag(itemHome);
        llNewProduct.setOnClickListener(ClickListenerHomeItem.onClickListener);

    }

}
