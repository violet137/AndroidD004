package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Model.AdvertisePhoto;
import vn.com.greenacademy.shopping.Model.BannerPhoto;
import vn.com.greenacademy.shopping.Model.MenuMain;
import vn.com.greenacademy.shopping.Model.ProductsPhoto;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by GIT on 3/11/2017.
 */

public class AdapterMenuMain extends ArrayAdapter {

    Activity activity;
    int layoutItem;
    ArrayList<MenuMain> menuMainArrayList;
    View.OnClickListener onClickListenerAdvertise;
    View.OnClickListener onClickListenerProducts;
    View.OnClickListener onClickListenerBanner;

    // bien dem so luong banner products
    int countProduct =0;
    // bien dem so luong banner trước banner magazine
    int countBeforeMagazine =0;
    public AdapterMenuMain(Activity activity, int resource, ArrayList<MenuMain> objects,
                           View.OnClickListener onClickListenerAdvertise, View.OnClickListener onClickListenerProducts,
                           View.OnClickListener onClickListenerBanner){
        super(activity,resource, (List) objects);
        this.activity = activity;
        layoutItem=resource;
        menuMainArrayList=objects;
        this.onClickListenerAdvertise = onClickListenerAdvertise;
        this.onClickListenerProducts = onClickListenerProducts;
        this.onClickListenerBanner = onClickListenerBanner;

    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //dinh nghia thanh phan giao dien cho tung item cua listview
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(layoutItem,null);

        // imageLoad đùng để tải ảnh sủ dụng đường link ưeb
        ImageLoad imageLoad = new ImageLoad(activity);

        switch (position){
            case SupportKeyList.Advertise:
                convertView.findViewById(R.id.linear_vf_menu_main).setVisibility(View.VISIBLE);

                // viewFlipper dung để view anh theo thời gian
                ViewFlipper viewFlipper = (ViewFlipper) convertView.findViewById(R.id.vf_menu_main);

                for (int i = 0; i < menuMainArrayList.get(position).getAdvertiseMenuMains().size() ; i++) {
                    // chu y neu ko tai hinh dc thi kiem tra lai mang
                    ImageView image = new ImageView(activity);
                    AdvertisePhoto advertisePhoto = new AdvertisePhoto();

                    advertisePhoto.setId(menuMainArrayList.get(position).getAdvertiseMenuMains().get(i).getId());
                    image.setTag(advertisePhoto);

                    // set su kien click cho từng tấm ảnh
                    image.setOnClickListener(onClickListenerAdvertise);
                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);

                    // dùng hàm load ảnh của imageLoad để tải anh theo link web và set vào imageView
                    imageLoad.load(menuMainArrayList.get(position).getAdvertiseMenuMains().get(i).getHinhDaiDien(), image);
                    viewFlipper.addView(image);
                }
                // bắt đầu chuyển imageView
                viewFlipper.startFlipping();
                break;

            default:
                switch (menuMainArrayList.get(position).getFlag()){
                    // set du lieu cho loại item san pham
                    case SupportKeyList.Products:
                        convertView.findViewById(R.id.constrainLayout_menu_main).setVisibility(View.VISIBLE);

                        ImageView imageViewProducts = (ImageView) convertView.findViewById(R.id.ivMenuType_menu_main);
                        TextView textView = (TextView) convertView.findViewById(R.id.tvName_menu_main);

                        ProductsPhoto productsPhoto = new ProductsPhoto();
                        productsPhoto.setId(menuMainArrayList.get(position).getId());
                        imageViewProducts.setTag( productsPhoto);

                        // set su kien click cho từng tấm ảnh
                        imageViewProducts.setOnClickListener(onClickListenerProducts);

                        textView.setText(menuMainArrayList.get(position).getName());

                        imageLoad.load(menuMainArrayList.get(position).getUrl(), imageViewProducts);
                        if (countProduct <position){
                            countProduct = position;
                        }
                        break;

                    // set du liệu cho item xu hướng + ... + ...
                    case SupportKeyList.Banner:
                        if ( position == countProduct +1){
                            convertView.findViewById(R.id.diviver_ivFashion_menu_main).setVisibility(View.VISIBLE);
                        }

                        if (menuMainArrayList.get(position).getType().equals("XuHuongThoiTrang")){
                            if (position == countBeforeMagazine +1){
                                convertView.findViewById(R.id.btnMagazine_menu_main).setVisibility(View.VISIBLE);
                            }
                            convertView.findViewById(R.id.linear_ivMagazine_menu_main).setVisibility(View.VISIBLE);

                            ImageView imageViewBanner = (ImageView) convertView.findViewById(R.id.ivMagazine_menu_main);

                            BannerPhoto bannerPhoto = new BannerPhoto();
                            bannerPhoto.setLoaiBanner(menuMainArrayList.get(position).getType());
                            bannerPhoto.setId(Long.parseLong(menuMainArrayList.get(position).getId()));
                            imageViewBanner.setTag( bannerPhoto);

                            // set su kien click cho từng tấm ảnh
                            imageViewBanner.setOnClickListener(onClickListenerBanner);

                            imageLoad.load(menuMainArrayList.get(position).getUrl(), imageViewBanner);
                        } else {
                            convertView.findViewById(R.id.linear_ivFashion_menu_main).setVisibility(View.VISIBLE);

                            ImageView imageViewBanner = (ImageView) convertView.findViewById(R.id.ivFashion_menu_main);

                            BannerPhoto bannerPhoto = new BannerPhoto();
                            bannerPhoto.setLoaiBanner(menuMainArrayList.get(position).getType());
                            bannerPhoto.setId(Long.parseLong(menuMainArrayList.get(position).getId()));
                            imageViewBanner.setTag( bannerPhoto);

                            // set su kien click cho từng tấm ảnh
                            imageViewBanner.setOnClickListener(onClickListenerBanner);

                            imageLoad.load(menuMainArrayList.get(position).getUrl(), imageViewBanner);

                            if (countBeforeMagazine <position){
                                countBeforeMagazine = position;
                            }
                        }
                        break;

                    default:
                        break;
                }
                break;
        }
        return convertView;
    }
}
