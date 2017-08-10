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
import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Model.Home.ItemHome;
import vn.com.greenacademy.shopping.Model.Home.MenuMain;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 8/9/2017.
 */

public class AdapterHomeRVFashion extends RecyclerView.Adapter<ItemRVFashionHolder>{
    Activity activity;
    LayoutInflater mLayoutInflater;
    ArrayList<MenuMain> menuMainArrayList;


    public AdapterHomeRVFashion(Activity activity , ArrayList<MenuMain> menuMainArrayList) {
        this.activity = activity;
        mLayoutInflater = LayoutInflater.from(activity);
        this.menuMainArrayList=menuMainArrayList;

    }


    @Override
    public ItemRVFashionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mLayoutInflater.inflate(R.layout.item_main_menu_fashion, parent, false);
        return new ItemRVFashionHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ItemRVFashionHolder holder, int position) {
        ImageLoad imageLoad = new ImageLoad(activity);

        ItemHome itemHome = new ItemHome();
        itemHome.setFlagItemHome(SupportKeyList.ClickHome_Fashion);
        itemHome.setId(String.valueOf(menuMainArrayList.get(position).getId()));

        holder.ivFashion.setTag(itemHome);
        holder.ivFashion.setOnClickListener(ClickListenerHomeItem.onClickListener);

        imageLoad.load(menuMainArrayList.get(position).getUrl(), holder.ivFashion);


    }

    @Override
    public int getItemCount() {
        return menuMainArrayList.size();

    }
}
class ItemRVFashionHolder extends RecyclerView.ViewHolder {
    ImageView ivFashion;
    public ItemRVFashionHolder(View itemView) {
        super(itemView);
        ivFashion = (ImageView) itemView.findViewById(R.id.ivFashion_item_main_menu);
    }
}