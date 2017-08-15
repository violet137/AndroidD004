package vn.com.greenacademy.shopping.Fragment.Main.DanhMucSanPham;


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

import vn.com.greenacademy.shopping.Handle.HandleData.DanhMucSanPham.ClickListenerDanhMucSanPham;
import vn.com.greenacademy.shopping.Handle.HandleData.Home.ClickListenerHomeItem;
import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale.AdapterRecyclerColorProduct;
import vn.com.greenacademy.shopping.Model.Home.ItemHome;
import vn.com.greenacademy.shopping.Model.ListObject;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotProductDMFragment extends Fragment {

    ArrayList<SanPham> sanPhamArrayList;
    int position;
    ImageLoad imageLoad;

    public HotProductDMFragment(ArrayList<SanPham> sanPhamArrayList, int positionViewPager) {
        // Required empty public constructor
        this.sanPhamArrayList=sanPhamArrayList;
        if (positionViewPager == 0) {
            position = positionViewPager;
        }else {
            position = positionViewPager * 3;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ClickListenerDanhMucSanPham.objectCallBack.callBack(sanPhamArrayList,0);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hot_product_dm, container, false);

        LinearLayout llNewProduct = (LinearLayout) view.findViewById(R.id.llNewProduct_1_HotProductFragment);
        ImageView ivNewProduct = (ImageView) view.findViewById(R.id.ivNewProduct_1_HotProductFragment);
        TextView tvNameProduct = (TextView) view.findViewById(R.id.tvNameProduct_1_HotProductFragment);
        TextView tvGiaProduct = (TextView) view.findViewById(R.id.tvGiaProduct_1_HotProductFragment);
        RecyclerView rvMauProduct = (RecyclerView) view.findViewById(R.id.rvMauProduct_1_HotProductFragment);
        setDataLayout(llNewProduct, ivNewProduct, tvNameProduct, tvGiaProduct, rvMauProduct, position);

        // the 2
        LinearLayout llNewProduct2 = (LinearLayout) view.findViewById(R.id.llNewProduct_2_HotProductFragment);
        ImageView ivNewProduct2 = (ImageView) view.findViewById(R.id.ivNewProduct_2_HotProductFragment);
        TextView tvNameProduct2 = (TextView) view.findViewById(R.id.tvNameProduct_2_HotProductFragment);
        TextView tvGiaProduct2 = (TextView) view.findViewById(R.id.tvGiaProduct_2_HotProductFragment);
        RecyclerView rvMauProduct2 = (RecyclerView) view.findViewById(R.id.rvMauProduct_2_HotProductFragment);
        setDataLayout(llNewProduct2, ivNewProduct2, tvNameProduct2, tvGiaProduct2, rvMauProduct2, position+1);

        // the 3
        LinearLayout llNewProduct3 = (LinearLayout) view.findViewById(R.id.llNewProduct_3_HotProductFragment);
        ImageView ivNewProduct3 = (ImageView) view.findViewById(R.id.ivNewProduct_3_HotProductFragment);
        TextView tvNameProduct3 = (TextView) view.findViewById(R.id.tvNameProduct_3_HotProductFragment);
        TextView tvGiaProduct3 = (TextView) view.findViewById(R.id.tvGiaProduct_3_HotProductFragment);
        RecyclerView rvMauProduct3 = (RecyclerView) view.findViewById(R.id.rvMauProduct_3_HotProductFragment);
        setDataLayout(llNewProduct3, ivNewProduct3, tvNameProduct3, tvGiaProduct3, rvMauProduct3, position+2);


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

        llNewProduct.setTag(item);
        llNewProduct.setOnClickListener(ClickListenerDanhMucSanPham.onClickListenerHotProduct);

    }

}
