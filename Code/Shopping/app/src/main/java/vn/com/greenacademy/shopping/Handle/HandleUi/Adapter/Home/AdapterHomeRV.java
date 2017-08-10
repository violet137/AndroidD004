package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Home;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.Home.ClickListenerHomeItem;
import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Model.Home.ItemHome;
import vn.com.greenacademy.shopping.Model.Home.MenuMain;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 8/10/2017.
 */

public class AdapterHomeRV  extends RecyclerView.Adapter<ItemRVHomeHolder>{
    Activity activity;
    LayoutInflater mLayoutInflater;
    ArrayList<MenuMain> menuMainArrayList;
    int firstPositionMagazine = -1;

    public AdapterHomeRV(Activity activity , ArrayList<MenuMain> menuMainArrayList) {
        this.activity = activity;
        mLayoutInflater = LayoutInflater.from(activity);
        this.menuMainArrayList=menuMainArrayList;

    }


    @Override
    public ItemRVHomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mLayoutInflater.inflate(R.layout.item_menu_home_recycler_view, parent, false);
        return new ItemRVHomeHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ItemRVHomeHolder holder, int position) {
        ImageLoad imageLoad = new ImageLoad(activity);
        MenuMain item = menuMainArrayList.get(position);
        ItemHome itemHome;

        switch (item.getFlagItem()){
            case SupportKeyList.ClickHome_Advertise:

                holder.itemAdvertise.setVisibility(View.VISIBLE);

                holder.itemProdutcs.setVisibility(View.GONE);
                holder.itemNewProduct.setVisibility(View.GONE);
                holder.itemFashion.setVisibility(View.GONE);
                holder.itemMagazine.setVisibility(View.GONE);
                holder.llName_Magazine.setVisibility(View.GONE);

                for (int i = 0; i < item.getAdvertiseMenuMains().size() ; i++) {
                    // chu y neu ko tai hinh dc thi kiem tra lai mang
                    ImageView image = new ImageView(activity);

                    itemHome = new ItemHome();
                    itemHome.setFlagItemHome(SupportKeyList.ClickHome_Advertise);
                    itemHome.setId(String.valueOf(item.getAdvertiseMenuMains().get(i).getId()));

                    image.setTag(itemHome);
                    image.setOnClickListener(ClickListenerHomeItem.onClickListener);

                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);

                    // dùng hàm load ảnh của imageLoad để tải anh theo link web và set vào imageView
                    imageLoad.load(item.getAdvertiseMenuMains().get(i).getHinhDaiDien(), image);
                    holder.vfAdvertise.addView(image);
                }
                // bắt đầu chuyển imageView
                holder.vfAdvertise.startFlipping();

                break;
            case SupportKeyList.ClickHome_Products:

                holder.itemProdutcs.setVisibility(View.VISIBLE);

                holder.itemAdvertise.setVisibility(View.GONE);
                holder.itemNewProduct.setVisibility(View.GONE);
                holder.itemFashion.setVisibility(View.GONE);
                holder.itemMagazine.setVisibility(View.GONE);
                holder.llName_Magazine.setVisibility(View.GONE);

                itemHome = new ItemHome();
                itemHome.setFlagItemHome(SupportKeyList.ClickHome_Products);
                itemHome.setId(String.valueOf(item.getId()));

                holder.itemProdutcs.setTag(itemHome);
                holder.itemProdutcs.setOnClickListener(ClickListenerHomeItem.onClickListener);

                holder.tvName.setText(item.getName());

                imageLoad.load(item.getUrl(), holder.ivProduct);

                break;
            case SupportKeyList.ClickHome_NewProduct:

                holder.itemNewProduct.setVisibility(View.VISIBLE);

                holder.itemProdutcs.setVisibility(View.GONE);
                holder.itemAdvertise.setVisibility(View.GONE);
                holder.itemFashion.setVisibility(View.GONE);
                holder.itemMagazine.setVisibility(View.GONE);
                holder.llName_Magazine.setVisibility(View.GONE);

                AdapterNewProductVP adapterNewProductVP =
                        new AdapterNewProductVP(((AppCompatActivity) activity).getSupportFragmentManager(),
                                activity, item.getSanPhamArrayList());

                holder.vpNewProduct.setAdapter(adapterNewProductVP
                );

                break;
            case SupportKeyList.ClickHome_Fashion:

               holder.itemFashion.setVisibility(View.VISIBLE);

                holder.itemProdutcs.setVisibility(View.GONE);
                holder.itemNewProduct.setVisibility(View.GONE);
                holder.itemAdvertise.setVisibility(View.GONE);
                holder.itemMagazine.setVisibility(View.GONE);
                holder.llName_Magazine.setVisibility(View.GONE);

                itemHome = new ItemHome();
                itemHome.setFlagItemHome(SupportKeyList.ClickHome_Fashion);
                itemHome.setId(String.valueOf(item.getId()));

                holder.ivFashion.setTag(itemHome);
                holder.ivFashion.setOnClickListener(ClickListenerHomeItem.onClickListener);

                imageLoad.load(item.getUrl(), holder.ivFashion);

                break;
            case SupportKeyList.ClickHome_Magazine:

                holder.itemMagazine.setVisibility(View.VISIBLE);

                holder.itemProdutcs.setVisibility(View.GONE);
                holder.itemNewProduct.setVisibility(View.GONE);
                holder.itemFashion.setVisibility(View.GONE);
                holder.itemAdvertise.setVisibility(View.GONE);
                holder.llName_Magazine.setVisibility(View.GONE);

                if (firstPositionMagazine == -1){
                    firstPositionMagazine = position;
                }

                if (position == firstPositionMagazine){
                    holder.llName_Magazine.setVisibility(View.VISIBLE);

                    itemHome = new ItemHome();
                    itemHome.setFlagItemHome(SupportKeyList.ClickHome_Button_Magazine);
                    itemHome.setId("Magazine");

                    holder.btnMagazine.setTag(itemHome);
                    holder.btnMagazine.setOnClickListener(ClickListenerHomeItem.onClickListener);
                }

                itemHome = new ItemHome();
                itemHome.setFlagItemHome(SupportKeyList.ClickHome_Magazine);
                itemHome.setId(String.valueOf(item.getId()));

                holder.ivMagazine.setTag(itemHome);
                holder.ivMagazine.setOnClickListener(ClickListenerHomeItem.onClickListener);

                holder.tvMagazineType.setText(item.getMagazineType());
                holder.tvMagazineName.setText(item.getName());

                imageLoad.load(item.getUrl(), holder.ivMagazine);

                break;
        }
    }

    @Override
    public int getItemCount() {
        return menuMainArrayList.size();

    }
}

class ItemRVHomeHolder extends RecyclerView.ViewHolder {
    ViewFlipper vfAdvertise;
    LinearLayout itemAdvertise;

    ImageView ivProduct;
    TextView tvName;
    ConstraintLayout itemProdutcs;

    ViewPager vpNewProduct;
    LinearLayout itemNewProduct;

    ImageView ivFashion;
    LinearLayout itemFashion;

    Button btnMagazine;
    LinearLayout llName_Magazine;

    ImageView ivMagazine;
    TextView tvMagazineType;
    TextView tvMagazineName;
    LinearLayout itemMagazine;

    public ItemRVHomeHolder(View itemView) {
        super(itemView);

        vfAdvertise = (ViewFlipper) itemView.findViewById(R.id.vfAdvertise_menu_home_rv);
        itemAdvertise = (LinearLayout) itemView.findViewById(R.id.itemAdvertise_menu_home_rv);

        ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct_menu_home_rv);
        tvName = (TextView) itemView.findViewById(R.id.tvNameProduct_menu_home_rv);
        itemProdutcs = (ConstraintLayout) itemView.findViewById(R.id.itemProducts_menu_home_rv);

        vpNewProduct = (ViewPager) itemView.findViewById(R.id.vpNewProduct_menu_home_rv);
        itemNewProduct = (LinearLayout) itemView.findViewById(R.id.itemNewProduct_menu_home_rv);

        ivFashion = (ImageView) itemView.findViewById(R.id.ivFashion_menu_home_rv);
        itemFashion = (LinearLayout) itemView.findViewById(R.id.itemFashion_menu_home_rv);

        btnMagazine = (Button) itemView.findViewById(R.id.btnMagazine_menu_home_rv);
        llName_Magazine = (LinearLayout) itemView.findViewById(R.id.llName_Magazine_menu_home_rv);

        tvMagazineType = (TextView) itemView.findViewById(R.id.tvTypeMagazine_menu_home_rv);
        tvMagazineName = (TextView) itemView.findViewById(R.id.tvNameMagzine_menu_home_rv);
        itemMagazine = (LinearLayout) itemView.findViewById(R.id.itemMagazine_menu_home_rv);
        ivMagazine = (ImageView) itemView.findViewById(R.id.ivMagazine_menu_home_rv);

    }
}
