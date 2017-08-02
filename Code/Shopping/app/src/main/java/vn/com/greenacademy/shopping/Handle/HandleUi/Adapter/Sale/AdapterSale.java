package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Sale.SaleProductFragment;
import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham.SanPhamPagerAdapter;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Model.Sale;
import vn.com.greenacademy.shopping.R;

import static android.R.attr.fragment;

/**
 * Created by ADMIN on 8/1/2017.
 */

public class AdapterSale extends RecyclerView.Adapter<SaleHolder>{
    Context context;
    LayoutInflater mLayoutInflater;
    View.OnClickListener onClickListener;
    ArrayList<Sale> saleArrayList;

    public AdapterSale (Context context, View.OnClickListener onClickListener,
                                       ArrayList<Sale> saleArrayList) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.onClickListener=onClickListener;
        this.saleArrayList=saleArrayList;
    }

    @Override
    public SaleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mLayoutInflater.inflate(R.layout.item_sale, parent, false);
        return new SaleHolder(itemview);
    }

    @Override
    public void onBindViewHolder(SaleHolder holder, int position) {

        Sale sale = new Sale();

        sale.setId(saleArrayList.get(position).getId());

        setData(holder, position);
    }


    @Override
    public int getItemCount() {
        return saleArrayList.size();

    }

    public void setData(SaleHolder holder, int position){
        holder.itemView.setTag(saleArrayList.get(position));
        holder.itemView.setOnClickListener(onClickListener);

        ImageLoad imageLoad = new ImageLoad((Activity)context);
        imageLoad.ImageLoad(saleArrayList.get(position).getHinhDaiDien(),holder.imageView);

        holder.tvName.setText(saleArrayList.get(position).getTen());
        holder.tvMoTa.setText("  "+saleArrayList.get(position).getMota());
        
//        AdapterViewPagerSale adapterViewPagerSale = new AdapterViewPagerSale(
//                ((AppCompatActivity)context).getSupportFragmentManager(),(Activity) context);
//
//        holder.viewPager.setAdapter(adapterViewPagerSale);

    }
}
class SaleHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    ViewPager viewPager;
    ImageView ivIcon;
    TextView tvName;
    TextView tvMoTa;
    DataCallBack dataCallBack;

    public SaleHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.iv_itemSale);
        viewPager = (ViewPager) itemView.findViewById(R.id.vp_itemSale);
        ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon__itemSale);
        tvName = (TextView) itemView.findViewById(R.id.tvName_itemSale);
        tvMoTa = (TextView) itemView.findViewById(R.id.tvMoTa_itemSale);

    }

}
