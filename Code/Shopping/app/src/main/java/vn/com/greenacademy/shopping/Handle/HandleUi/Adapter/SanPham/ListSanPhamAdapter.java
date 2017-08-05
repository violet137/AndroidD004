package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Main.SanPham.ThongTinSanPhamFragment;
import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
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

    public ListSanPhamAdapter(Context context, ArrayList<SanPham> listSanPham, BaseFragment baseFragment, SanPhamCallBack sanPhamCallBack, ImageLoad imageLoad){
        this.context = context;
        this.listSanPham = listSanPham;
        this.baseFragment = baseFragment;
        this.sanPhamCallBack = sanPhamCallBack;
        this.imageLoad = imageLoad;
    }

    @Override
    public SanPhamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.item_san_pham, parent, false);
        return new SanPhamHolder(root);
    }

    @Override
    public void onBindViewHolder(final SanPhamHolder holder, final int position) {
        sanPham = listSanPham.get(position);
        imageLoad.ImageLoad(sanPham.getHinhSanPham().get(0).getLinkHinh()[0], holder.imgSanPham);
        holder.tvTenSanPham.setText(sanPham.getTenSanPham());
        if (sanPham.getGiamGia() != 0){
            holder.tvGia.setText(String.valueOf(sanPham.getGiamGia()));
            holder.tvGia.setTextColor(Color.RED);
        } else{
            holder.tvGia.setText(String.valueOf(sanPham.getGiaSanPham()));
        }

        holder.imgSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseFragment.ChuyenFragment(new ThongTinSanPhamFragment(holder.getLayoutPosition(), listSanPham), SupportKeyList.TAG_THONG_TIN_SAN_PHAM, true);
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
