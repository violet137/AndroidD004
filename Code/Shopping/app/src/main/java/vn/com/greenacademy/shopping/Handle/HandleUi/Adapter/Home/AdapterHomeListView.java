package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Home;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import vn.com.greenacademy.shopping.Handle.HandleData.Home.ClickListenerHomeItem;
import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Model.Home.ItemHome;
import vn.com.greenacademy.shopping.Model.Home.MenuMain;
import vn.com.greenacademy.shopping.Model.MucSanPham;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 8/9/2017.
 */

public class AdapterHomeListView extends ArrayAdapter {

    Activity activity;
    int layoutItem;
    ArrayList<MenuMain> menuMainArrayList;
    int firstPositionMagazine = -1;

    public AdapterHomeListView(Activity activity, int resource, ArrayList<MenuMain> menuMainArrayList){
        super(activity,resource, menuMainArrayList);
        this.activity = activity;
        layoutItem=resource;
        this.menuMainArrayList=menuMainArrayList;

    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //dinh nghia thanh phan giao dien cho tung item cua listview
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(layoutItem,null);
        ImageLoad imageLoad = new ImageLoad(activity);
        MenuMain item = menuMainArrayList.get(position);
        ItemHome itemHome;

        switch (item.getFlagItem()){
            case SupportKeyList.ClickHome_Advertise:

                final ViewFlipper vfAdvertise = (ViewFlipper) convertView.findViewById(R.id.vfAdvertise_item_menu);
                vfAdvertise.setVisibility(View.VISIBLE);

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
                    vfAdvertise.addView(image);
                }
                // bắt đầu chuyển imageView
                vfAdvertise.startFlipping();

                break;
            case SupportKeyList.ClickHome_Products:
                convertView.findViewById(R.id.item_menu_product).setVisibility(View.VISIBLE);
//                ConstraintLayout clProduct_item_main_menu = (ConstraintLayout) convertView.findViewById(R.id.clProduct_item_main_menu);
                final ImageView ivProduct = (ImageView) convertView.findViewById(R.id.ivProduct_item_main_menu);
                final TextView tvName = (TextView) convertView.findViewById(R.id.tvNameProduct_item_main_menu);

                itemHome = new ItemHome();
                itemHome.setFlagItemHome(SupportKeyList.ClickHome_Products);
                itemHome.setId(String.valueOf(item.getId()));

                ivProduct.setTag(itemHome);
                ivProduct.setOnClickListener(ClickListenerHomeItem.onClickListener);

                tvName.setText(item.getName());

                imageLoad.load(item.getUrl(), ivProduct);

                break;
            case SupportKeyList.ClickHome_NewProduct:
                convertView.findViewById(R.id.item_menu_new_product).setVisibility(View.VISIBLE);
                final ViewPager vpNewProduct = (ViewPager) convertView.findViewById(R.id.vpNewProduct_item_main_menu);

                AdapterNewProductViewPager adapterNewProductViewPager =
                        new AdapterNewProductViewPager(((AppCompatActivity) activity).getSupportFragmentManager(),
                                activity, item.getSanPhamArrayList());

                vpNewProduct.setAdapter(adapterNewProductViewPager);

                break;
            case SupportKeyList.ClickHome_Fashion:
                convertView.findViewById(R.id.item_menu_fashion).setVisibility(View.VISIBLE);
                final ImageView ivFashion = (ImageView) convertView.findViewById(R.id.ivFashion_item_main_menu);

                itemHome = new ItemHome();
                itemHome.setFlagItemHome(SupportKeyList.ClickHome_Fashion);
                itemHome.setId(String.valueOf(item.getId()));

                ivFashion.setTag(itemHome);
                ivFashion.setOnClickListener(ClickListenerHomeItem.onClickListener);

                imageLoad.load(item.getUrl(), ivFashion);

                break;
            case SupportKeyList.ClickHome_Magazine:
                convertView.findViewById(R.id.item_menu_magazine).setVisibility(View.VISIBLE);

                final LinearLayout llMagazine = (LinearLayout) convertView.findViewById(R.id.llMagazine_item_main_menu);
                final Button btnMagazine = (Button) convertView.findViewById(R.id.btnMagazine_item_main_menu);

                ImageView ivMagazine = (ImageView) convertView.findViewById(R.id.ivMagazine_item_main_menu);
                TextView tvMagazineType = (TextView) convertView.findViewById(R.id.tvTypeMagazine_item_main_menu);
                TextView tvMagazineName = (TextView) convertView.findViewById(R.id.tvNameMagzine_item_main_menu);

                if (firstPositionMagazine == -1){
                    firstPositionMagazine = position;
                }

                if (position == firstPositionMagazine){
                    llMagazine.setVisibility(View.VISIBLE);

                    itemHome = new ItemHome();
                    itemHome.setFlagItemHome(SupportKeyList.ClickHome_Button_Magazine);
                    itemHome.setId("Magazine");

                    btnMagazine.setTag(itemHome);
                    btnMagazine.setOnClickListener(ClickListenerHomeItem.onClickListener);
                }

                itemHome = new ItemHome();
                itemHome.setFlagItemHome(SupportKeyList.ClickHome_Magazine);
                itemHome.setId(String.valueOf(item.getId()));

                ivMagazine.setTag(itemHome);
                ivMagazine.setOnClickListener(ClickListenerHomeItem.onClickListener);

                tvMagazineType.setText(item.getMagazineType());
                tvMagazineName.setText(item.getName());

                imageLoad.load(item.getUrl(), ivMagazine);

                break;
        }

        return convertView;
    }
}
