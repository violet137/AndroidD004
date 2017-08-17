package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Home;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.Home.ClickListenerHomeItem;
import vn.com.greenacademy.shopping.Handle.HandleUi.ImageLoad;
import vn.com.greenacademy.shopping.Model.Home.ItemHome;
import vn.com.greenacademy.shopping.Model.Home.MenuMain;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 8/9/2017.
 */

public class AdapterHomeRVProducts extends RecyclerView.Adapter<ItemRVProductsHolder>{
    Activity activity;
    LayoutInflater mLayoutInflater;
    ArrayList<MenuMain> menuMainArrayList;


    public AdapterHomeRVProducts(Activity activity , ArrayList<MenuMain> menuMainArrayList) {
        this.activity = activity;
        mLayoutInflater = LayoutInflater.from(activity);
        this.menuMainArrayList=menuMainArrayList;

    }


    @Override
    public ItemRVProductsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mLayoutInflater.inflate(R.layout.item_main_menu_product, parent, false);
        return new ItemRVProductsHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ItemRVProductsHolder holder, int position) {

        ImageLoad imageLoad = new ImageLoad(activity);
        ItemHome itemHome = new ItemHome();
        itemHome.setFlagItemHome(SupportKeyList.ClickHome_Products);
        itemHome.setId(String.valueOf(menuMainArrayList.get(position).getId()));

        holder.clProduct_item_main_menu.setTag(itemHome);
        holder.clProduct_item_main_menu.setOnClickListener(ClickListenerHomeItem.onClickListener);
        holder.ivProduct.setTag(itemHome);
        holder.ivProduct.setOnClickListener(ClickListenerHomeItem.onClickListener);

        holder.tvName.setText(menuMainArrayList.get(position).getName());

        imageLoad.load(menuMainArrayList.get(position).getUrl(), holder.ivProduct);

    }


    @Override
    public int getItemCount() {
        return menuMainArrayList.size();

    }
}
class ItemRVProductsHolder extends RecyclerView.ViewHolder {
    ImageView ivProduct;
    TextView tvName;
    ConstraintLayout clProduct_item_main_menu;
    public ItemRVProductsHolder(View itemView) {
        super(itemView);
        clProduct_item_main_menu = (ConstraintLayout) itemView.findViewById(R.id.clProduct_item_main_menu);
        ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct_item_main_menu);
        tvName = (TextView) itemView.findViewById(R.id.tvNameProduct_item_main_menu);
    }
}