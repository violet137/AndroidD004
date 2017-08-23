package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Search;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.com.greenacademy.shopping.Handle.HandleData.DanhMucSanPham.ClickListenerDanhMucSanPham;
import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.Handle.HandleData.Search.SearchHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale.AdapterRecyclerColorProduct;
import vn.com.greenacademy.shopping.Handle.HandleUi.ImageLoad;
import vn.com.greenacademy.shopping.Model.DanhMuc.MucSanPham;
import vn.com.greenacademy.shopping.Model.Magazine.Item_recyclerView_magazine;
import vn.com.greenacademy.shopping.Model.Magazine.Magazine;
import vn.com.greenacademy.shopping.Model.Magazine.MagazineType;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 8/23/2017.
 */

public class AdapterTopSanPhamXem  extends RecyclerView.Adapter<TopSPXemHolder>{
    Activity activity;
    LayoutInflater mLayoutInflater;
    ArrayList<SanPham> sanPhamArrayList;
    ImageLoad imageLoad;

    public AdapterTopSanPhamXem(Activity activity, ArrayList<SanPham> sanPhamArrayList) {
        this.activity = activity;
        mLayoutInflater = LayoutInflater.from(activity);
        this.sanPhamArrayList=sanPhamArrayList;
    }

    @Override
    public TopSPXemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mLayoutInflater.inflate(R.layout.item_product, parent, false);
        return new TopSPXemHolder(itemview);
    }

    @Override
    public void onBindViewHolder(TopSPXemHolder holder, int position) {
        setDataLayout(holder.llLayout, holder.ivProduct, holder.tvName, holder.tvGia, holder.rvMau, position);
    }

    private void setDataLayout(LinearLayout llNewProduct, ImageView ivNewProduct, TextView tvNameProduct,
                               TextView tvGiaProduct, RecyclerView rvMauProduct, int positionItem) {
        SanPham item = sanPhamArrayList.get(positionItem);
        imageLoad = new ImageLoad(activity);

        imageLoad.load(item.getHinhSanPham().get(0).getLinkHinh()[0],ivNewProduct);
        tvNameProduct.setText(item.getTenSanPham());
        tvGiaProduct.setText(SanPhamHandler.chuyenGia(item.getGiaSanPham()));

        AdapterRecyclerColorProduct adapterRecyclerColorProduct = new AdapterRecyclerColorProduct(activity, item.getMauSanPham());
        rvMauProduct.setLayoutManager(new GridLayoutManager(activity,
                item.getMauSanPham().length> SupportKeyList.SoMauHienThiToiDa ?
                        SupportKeyList.SoMauHienThiToiDa + 1 : item.getMauSanPham().length));
        rvMauProduct.setAdapter(adapterRecyclerColorProduct);

        llNewProduct.setTag(item);
        llNewProduct.setOnClickListener(SearchHandler.onClickListener);

    }

    @Override
    public int getItemCount() {
        return sanPhamArrayList.size();

    }

}
class TopSPXemHolder extends RecyclerView.ViewHolder {
    LinearLayout llLayout;
    ImageView ivProduct;
    TextView tvName;
    TextView tvGia;
    RecyclerView rvMau;
    public TopSPXemHolder(View itemView) {
        super(itemView);
        llLayout = (LinearLayout) itemView.findViewById(R.id.llLayout_itemProduct);

        ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct_itemProduct);

        tvGia = (TextView) itemView.findViewById(R.id.tvGia_itemProduct);

        tvName = (TextView) itemView.findViewById(R.id.tvName_itemProduct);

        rvMau = (RecyclerView) itemView.findViewById(R.id.rvMau_itemProduct);

    }

}
