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
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.Home.ClickListenerHomeItem;
import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Model.Home.ItemHome;
import vn.com.greenacademy.shopping.Model.Home.MenuMain;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 7/22/2017.
 */

// demo dang recycler view muiti types nhung ổn dịnh cua recycler view ko cao co hien tuong giật lag
public class AdapterHomeRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Activity activity;
    ArrayList<MenuMain> menuMainArrayList;

    // lay vi tri item dau tiên cuar magazine
    int firstPositionMagazine = -1;

    public AdapterHomeRecyclerView(Activity activity, ArrayList<MenuMain> menuMainArrayList) {
        this.activity = activity;
        this.menuMainArrayList=menuMainArrayList;
    }
    @Override
    public int getItemViewType(int position) {
        MenuMain item = menuMainArrayList.get(position);

        switch (item.getFlag()){
            case SupportKeyList.Advertise_Url:
                return SupportKeyList.Advertise_Item;
            case SupportKeyList.Products_Url:
                return SupportKeyList.Products_Item;
            case SupportKeyList.NewProduct_Url:
                return SupportKeyList.NewProduct_Item;
            case SupportKeyList.Banner_Url:
                if (item.getType().equals("TapChi")){
                    return SupportKeyList.Magazine_Item;
                }else {
                    return SupportKeyList.Fashion_Item;
                }
            default:
                return -1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case SupportKeyList.Advertise_Item:
                view = inflate.inflate(R.layout.item_main_menu_advertise, parent, false);
                holder = new ItemAdvertiseHolder(view);
                break;
            case SupportKeyList.Products_Item:
                view = inflate.inflate(R.layout.item_main_menu_product, parent, false);
                holder = new ItemProductsHolder(view);
                break;
            case SupportKeyList.NewProduct_Item:
                view = inflate.inflate(R.layout.item_main_menu_new_product, parent, false);
                holder = new ItemNewProductHolder(view);
                break;
            case SupportKeyList.Fashion_Item:
                view = inflate.inflate(R.layout.item_main_menu_fashion, parent, false);
                holder = new ItemFashionHolder(view);
                break;
            case SupportKeyList.Magazine_Item:
                view = inflate.inflate(R.layout.item_main_menu_magazine, parent, false);
                holder = new ItemMagazineHolder(view);
                break;
            default:
                Toast.makeText(activity, "lỗi case onCreateViewHolder", Toast.LENGTH_SHORT).show();
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder genHolder, int position) {
        // item view theo vi tri
        MenuMain item = menuMainArrayList.get(position);

        // imageLoad đùng để tải ảnh sủ dụng đường link ưeb
        ImageLoad imageLoad = new ImageLoad(activity);

        if (genHolder instanceof ItemAdvertiseHolder){
            ItemAdvertiseHolder holder = (ItemAdvertiseHolder) genHolder;

            for (int i = 0; i < menuMainArrayList.get(position).getAdvertiseMenuMains().size() ; i++) {
                // chu y neu ko tai hinh dc thi kiem tra lai mang
                ImageView image = new ImageView(activity);

                ItemHome itemHome = new ItemHome();
                itemHome.setFlagItemHome(SupportKeyList.ClickHome_Advertise);
                itemHome.setId(String.valueOf(menuMainArrayList.get(position).getAdvertiseMenuMains().get(i).getId()));

                image.setTag(itemHome);
                image.setOnClickListener(ClickListenerHomeItem.onClickListener);

                image.setScaleType(ImageView.ScaleType.CENTER_CROP);

                // dùng hàm load ảnh của imageLoad để tải anh theo link web và set vào imageView
                imageLoad.load(menuMainArrayList.get(position).getAdvertiseMenuMains().get(i).getHinhDaiDien(), image);
                holder.vfAdvertise.addView(image);
            }
            // bắt đầu chuyển imageView
            holder.vfAdvertise.startFlipping();



        }else if (genHolder instanceof ItemProductsHolder){
            ItemProductsHolder holder = (ItemProductsHolder) genHolder;

//            ProductsPhoto productsPhoto = new ProductsPhoto();
//            productsPhoto.setId(menuMainArrayList.get(position).getId());
//            imageViewProducts.setTag( productsPhoto);
//
//            // set su kien click cho từng tấm ảnh
//            imageViewProducts.setOnClickListener(onClickListenerProducts);


            ItemHome itemHome = new ItemHome();
            itemHome.setFlagItemHome(SupportKeyList.ClickHome_Products);
            itemHome.setId(String.valueOf(menuMainArrayList.get(position).getId()));

            holder.clProduct_item_main_menu.setTag(itemHome);
            holder.clProduct_item_main_menu.setOnClickListener(ClickListenerHomeItem.onClickListener);

            holder.tvName.setText(menuMainArrayList.get(position).getName());

            imageLoad.load(menuMainArrayList.get(position).getUrl(), holder.ivProduct);

        }else if (genHolder instanceof ItemNewProductHolder){
            ItemNewProductHolder holder = (ItemNewProductHolder) genHolder;

            AdapterNewProductViewPager adapterNewProductViewPager =
                    new AdapterNewProductViewPager(((AppCompatActivity) activity).getSupportFragmentManager(),
                            activity, menuMainArrayList.get(position).getSanPhamArrayList());

            holder.vpNewProduct.setAdapter(adapterNewProductViewPager);

        }else if (genHolder instanceof ItemFashionHolder){
            ItemFashionHolder holder = (ItemFashionHolder) genHolder;

            ItemHome itemHome = new ItemHome();
            itemHome.setFlagItemHome(SupportKeyList.ClickHome_Fashion);
            itemHome.setId(String.valueOf(menuMainArrayList.get(position).getId()));

            holder.ivFashion.setTag(itemHome);
            holder.ivFashion.setOnClickListener(ClickListenerHomeItem.onClickListener);

            imageLoad.load(menuMainArrayList.get(position).getUrl(), holder.ivFashion);



        }else if (genHolder instanceof ItemMagazineHolder){
            ItemMagazineHolder holder = (ItemMagazineHolder) genHolder;

            if (firstPositionMagazine == -1){
                firstPositionMagazine = position;
            }

            if (position == firstPositionMagazine){
                holder.llMagazine.setVisibility(View.VISIBLE);

                ItemHome itemHome = new ItemHome();
                itemHome.setFlagItemHome(SupportKeyList.ClickHome_Button_Magazine);
                itemHome.setId("Magazine");

                holder.btnMagazine.setTag(itemHome);
                holder.btnMagazine.setOnClickListener(ClickListenerHomeItem.onClickListener);
            }

//            holder.llMagazine;
//            holder.btnMagazine;

//            BannerPhoto bannerPhoto = new BannerPhoto();
//            bannerPhoto.setLoaiBanner(menuMainArrayList.get(position).getType());
//            bannerPhoto.setId(Long.parseLong(menuMainArrayList.get(position).getId()));
//            imageViewBanner.setTag( bannerPhoto);
//
//            // set su kien click cho từng tấm ảnh
//            imageViewBanner.setOnClickListener(onClickListenerBanner);

            ItemHome itemHome = new ItemHome();
            itemHome.setFlagItemHome(SupportKeyList.ClickHome_Magazine);
            itemHome.setId(String.valueOf(menuMainArrayList.get(position).getId()));

            holder.ivMagazine.setTag(itemHome);
            holder.ivMagazine.setOnClickListener(ClickListenerHomeItem.onClickListener);

            holder.tvMagazineType.setText(menuMainArrayList.get(position).getMagazineType());
            holder.tvMagazineName.setText(menuMainArrayList.get(position).getName());

            imageLoad.load(menuMainArrayList.get(position).getUrl(), holder.ivMagazine);
        } else {
            Toast.makeText(activity, "lỗi case onBindViewHolder", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public int getItemCount() {
        if (menuMainArrayList != null){
            return menuMainArrayList.size();
        }
        return 0;
    }

}

class ItemAdvertiseHolder extends RecyclerView.ViewHolder {
    ViewFlipper vfAdvertise;
    public ItemAdvertiseHolder(View itemView) {
        super(itemView);
        vfAdvertise = (ViewFlipper) itemView.findViewById(R.id.vfAdvertise_item_main_menu);
    }
}
class ItemProductsHolder extends RecyclerView.ViewHolder {
    ImageView ivProduct;
    TextView tvName;
    ConstraintLayout clProduct_item_main_menu;
    public ItemProductsHolder(View itemView) {
        super(itemView);
        clProduct_item_main_menu = (ConstraintLayout) itemView.findViewById(R.id.clProduct_item_main_menu);
        ivProduct = (ImageView) itemView.findViewById(R.id.ivProduct_item_main_menu);
        tvName = (TextView) itemView.findViewById(R.id.tvNameProduct_item_main_menu);
    }
}
class ItemNewProductHolder extends RecyclerView.ViewHolder {
    ViewPager vpNewProduct;
    public ItemNewProductHolder(View itemView) {
        super(itemView);
        vpNewProduct = (ViewPager) itemView.findViewById(R.id.vpNewProduct_item_main_menu);
    }
}
class ItemFashionHolder extends RecyclerView.ViewHolder {
    ImageView ivFashion;
    public ItemFashionHolder(View itemView) {
        super(itemView);
        ivFashion = (ImageView) itemView.findViewById(R.id.ivFashion_item_main_menu);
    }
}
class ItemMagazineHolder extends RecyclerView.ViewHolder {
    LinearLayout llMagazine;
    Button btnMagazine;

    ImageView ivMagazine;
    TextView tvMagazineType;
    TextView tvMagazineName;

    public ItemMagazineHolder(View itemView) {
        super(itemView);
        llMagazine = (LinearLayout) itemView.findViewById(R.id.llMagazine_item_main_menu);
        btnMagazine = (Button) itemView.findViewById(R.id.btnMagazine_item_main_menu);

        ivMagazine = (ImageView) itemView.findViewById(R.id.ivMagazine_item_main_menu);
        tvMagazineType = (TextView) itemView.findViewById(R.id.tvTypeMagazine_item_main_menu);
        tvMagazineName = (TextView) itemView.findViewById(R.id.tvNameMagzine_item_main_menu);

    }
}
