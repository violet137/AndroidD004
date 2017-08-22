package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Main.SanPham.ThongTinSanPhamFragment;
import vn.com.greenacademy.shopping.Handle.HandleUi.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale.AdapterRecyclerColorProduct;
import vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder.SanPhamHolder;
import vn.com.greenacademy.shopping.Interface.SanPhamCallBack;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class ListSanPhamAdapter extends RecyclerView.Adapter<SanPhamHolder> {
    private Context context;
    private ArrayList<SanPham> listSanPham;
    private SanPham sanPham = null;
    private BaseFragment baseFragment;
    private ImageLoad imageLoad;
    private SanPhamCallBack sanPhamCallBack;
    private boolean inDialog; //Kiểm tra nếu list trong dialog thì tùy chỉnh cho phù hợp

    public ListSanPhamAdapter(Context context, ArrayList<SanPham> listSanPham, BaseFragment baseFragment, SanPhamCallBack sanPhamCallBack, ImageLoad imageLoad){
        this.context = context;
        this.listSanPham = listSanPham;
        this.baseFragment = baseFragment;
        this.sanPhamCallBack = sanPhamCallBack;
        this.imageLoad = imageLoad;
    }

    public ListSanPhamAdapter(Context context, boolean inDialog, ArrayList<SanPham> listSanPham, BaseFragment baseFragment, SanPhamCallBack sanPhamCallBack, ImageLoad imageLoad){
        this.context = context;
        this.listSanPham = listSanPham;
        this.baseFragment = baseFragment;
        this.sanPhamCallBack = sanPhamCallBack;
        this.imageLoad = imageLoad;
        this.inDialog = inDialog;
    }

    @Override
    public SanPhamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.item_san_pham, parent, false);
        if (inDialog)
            root.setLayoutParams(new ViewGroup.LayoutParams((int)context.getResources().getDimension(R.dimen.item_list_san_pham_dialog_image_width), ViewGroup.LayoutParams.MATCH_PARENT));
        return new SanPhamHolder(root);
    }

    @Override
    public void onBindViewHolder(final SanPhamHolder holder, final int position) {
        sanPham = listSanPham.get(position);

        if (inDialog){
            holder.tvTenSanPham.setVisibility(View.GONE);
            holder.recyclerViewListMau.setVisibility(View.GONE);
        }

        // Hình sản phẩm
        imageLoad.load(sanPham.getHinhSanPham().get(0).getLinkHinh()[0], holder.imgSanPham);

        // Tên
        holder.tvTenSanPham.setText(sanPham.getTenSanPham());

        //Giá
        if (sanPham.getGiamGia() != 0){
            holder.tvGia.setText(SanPhamHandler.chuyenGia(sanPham.getGiamGia()));
            holder.tvGia.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
        } else{
            holder.tvGia.setText(SanPhamHandler.chuyenGia(sanPham.getGiaSanPham()));
        }

        // List màu
        holder.recyclerViewListMau.setLayoutManager(new GridLayoutManager(context, listSanPham.get(position).getMauSanPham().length > SupportKeyList.SoMauHienThiToiDa ? SupportKeyList.SoMauHienThiToiDa + 1:listSanPham.get(position).getMauSanPham().length));
        holder.recyclerViewListMau.setAdapter(new AdapterRecyclerColorProduct(context, listSanPham.get(position).getMauSanPham()));

        //Set sự kiện
        holder.imgSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseFragment.ChuyenFragment(ThongTinSanPhamFragment.newInstance(holder.getLayoutPosition(), listSanPham), SupportKeyList.TAG_THONG_TIN_SAN_PHAM, true);
                if (sanPhamCallBack != null)
                    sanPhamCallBack.clickSanPham(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSanPham.size();
    }
}
