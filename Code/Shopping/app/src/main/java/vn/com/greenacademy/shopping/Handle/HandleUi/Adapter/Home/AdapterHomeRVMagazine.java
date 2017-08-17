package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Home;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class AdapterHomeRVMagazine  extends RecyclerView.Adapter<ItemRVMagazineHolder>{
    Activity activity;
    LayoutInflater mLayoutInflater;
    ArrayList<MenuMain> menuMainArrayList;


    public AdapterHomeRVMagazine(Activity activity , ArrayList<MenuMain> menuMainArrayList) {
        this.activity = activity;
        mLayoutInflater = LayoutInflater.from(activity);
        this.menuMainArrayList=menuMainArrayList;

    }


    @Override
    public ItemRVMagazineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mLayoutInflater.inflate(R.layout.item_main_menu_magazine, parent, false);
        return new ItemRVMagazineHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ItemRVMagazineHolder holder, int position) {
        ImageLoad imageLoad = new ImageLoad(activity);

        if (position == 0){
            holder.llMagazine.setVisibility(View.VISIBLE);

            ItemHome itemHome = new ItemHome();
            itemHome.setFlagItemHome(SupportKeyList.ClickHome_Button_Magazine);
            itemHome.setId("Magazine");

            holder.btnMagazine.setTag(itemHome);
            holder.btnMagazine.setOnClickListener(ClickListenerHomeItem.onClickListener);
        }

        ItemHome itemHome = new ItemHome();
        itemHome.setFlagItemHome(SupportKeyList.ClickHome_Magazine);
        itemHome.setId(String.valueOf(menuMainArrayList.get(position).getId()));

        holder.ivMagazine.setTag(itemHome);
        holder.ivMagazine.setOnClickListener(ClickListenerHomeItem.onClickListener);

        holder.tvMagazineType.setText(menuMainArrayList.get(position).getMagazineType());
        holder.tvMagazineName.setText(menuMainArrayList.get(position).getName());

        imageLoad.load(menuMainArrayList.get(position).getUrl(), holder.ivMagazine);
    }


    @Override
    public int getItemCount() {
        return menuMainArrayList.size();

    }
}
class ItemRVMagazineHolder extends RecyclerView.ViewHolder {
    LinearLayout llMagazine;
    Button btnMagazine;

    ImageView ivMagazine;
    TextView tvMagazineType;
    TextView tvMagazineName;

    public ItemRVMagazineHolder(View itemView) {
        super(itemView);
        llMagazine = (LinearLayout) itemView.findViewById(R.id.llMagazine_item_main_menu);
        btnMagazine = (Button) itemView.findViewById(R.id.btnMagazine_item_main_menu);

        ivMagazine = (ImageView) itemView.findViewById(R.id.ivMagazine_item_main_menu);
        tvMagazineType = (TextView) itemView.findViewById(R.id.tvTypeMagazine_item_main_menu);
        tvMagazineName = (TextView) itemView.findViewById(R.id.tvNameMagzine_item_main_menu);

    }
}
