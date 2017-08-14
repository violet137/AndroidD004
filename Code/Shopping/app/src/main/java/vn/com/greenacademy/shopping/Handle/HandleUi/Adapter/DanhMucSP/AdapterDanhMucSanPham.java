package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.DanhMucSP;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.DanhMucSanPham.ClickListenerDanhMucSanPham;
import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale.AdapterViewPagerSale;
import vn.com.greenacademy.shopping.Model.MucSanPham;
import vn.com.greenacademy.shopping.Model.Sale;
import vn.com.greenacademy.shopping.R;

/**
 * Created by ADMIN on 8/13/2017.
 */

public class AdapterDanhMucSanPham extends RecyclerView.Adapter<MenuDanhMucHolder>{

    Context context;
    LayoutInflater mLayoutInflater;
    ArrayList<MucSanPham> mucSanPhamArrayList;

    public AdapterDanhMucSanPham (Context context, ArrayList<MucSanPham> mucSanPhamArrayList) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.mucSanPhamArrayList = mucSanPhamArrayList;
    }

    @Override
    public MenuDanhMucHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mLayoutInflater.inflate(R.layout.item_menu_danh_muc, parent, false);
        return new MenuDanhMucHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MenuDanhMucHolder holder, int position) {

        holder.itemMenuDanhMuc.setTag(mucSanPhamArrayList.get(position));
        holder.itemMenuDanhMuc.setOnClickListener(ClickListenerDanhMucSanPham.onClickListener);

        holder.tvNameDanhMuc.setText(mucSanPhamArrayList.get(position).getTenDanhMuc());
    }

    @Override
    public int getItemCount() {
        return mucSanPhamArrayList.size();

    }
}
class MenuDanhMucHolder extends RecyclerView.ViewHolder {

    LinearLayout itemMenuDanhMuc;
    TextView tvNameDanhMuc;

    public MenuDanhMucHolder(View itemView) {
        super(itemView);

        itemMenuDanhMuc = (LinearLayout) itemView.findViewById(R.id.item_Menu_DanhMuc);
        tvNameDanhMuc = (TextView) itemView.findViewById(R.id.tvName_item_MenuDanhMuc);

    }

}
