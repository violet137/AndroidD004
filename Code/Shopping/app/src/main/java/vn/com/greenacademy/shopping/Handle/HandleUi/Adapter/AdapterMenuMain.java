package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Model.AdvertisePhoto;
import vn.com.greenacademy.shopping.Model.MenuMain;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by GIT on 3/11/2017.
 */

public class AdapterMenuMain extends ArrayAdapter {

    Activity activity;
    int layoutItem;
    ArrayList<MenuMain> arrayList;
    View.OnClickListener onClickListener;

    public AdapterMenuMain(Activity activity, int resource, ArrayList<MenuMain> objects, View.OnClickListener onClickListener){
        super(activity,resource,objects);
        this.activity = activity;
        layoutItem=resource;
        arrayList=objects;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //dinh nghia thanh phan giao dien cho tung item cua listview
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(layoutItem,null);
        ImageLoad imageLoad = new ImageLoad(activity);

        final MenuMain menuMain = arrayList.get(position);
        switch (position){
            case SupportKeyList.Advertise:
                ViewFlipper viewFlipper = (ViewFlipper) convertView.findViewById(R.id.vf_menu_main);
                viewFlipper.setVisibility(View.VISIBLE);
                String[] arraylink_Advertise = activity.getResources().getStringArray(R.array.link_Advertise);
                for (int i = 0; i < arraylink_Advertise.length; i++) {
                    // chu y neu ko tai hinh dc thi kiem t(ra lai mang
                    ImageView image = new ImageView(activity);
                    AdvertisePhoto advertisePhoto = new AdvertisePhoto();
                    advertisePhoto.setId(i);
                    image.setTag(advertisePhoto);
                    image.setOnClickListener(onClickListener);
                    image.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageLoad.ImageLoad(arraylink_Advertise[i], image);
                    viewFlipper.addView(image);
                }
                viewFlipper.startFlipping();
                break;
            case SupportKeyList.Ladies:
            case SupportKeyList.Men:
            case SupportKeyList.Kids:
            case SupportKeyList.Home:
            case SupportKeyList.Magazine:
                convertView.findViewById(R.id.constrainLayout_menu_main).setVisibility(View.VISIBLE);

                ImageView imageView = (ImageView) convertView.findViewById(R.id.ivMenuType_menu_main);
                TextView textView = (TextView) convertView.findViewById(R.id.tvName_menu_main);

                String[] arraylink_MenunType = activity.getResources().getStringArray(R.array.link_MenuPhotos);
                imageLoad.ImageLoad(arraylink_MenunType[position-1], imageView);
                textView.setText(menuMain.getName());
                break;
            default:
                LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.linear_ivFashion_menu_main);
                linearLayout.setVisibility(View.VISIBLE);
                if(position == 6){
                    convertView.findViewById(R.id.diviver_ivFashion_menu_main).setVisibility(View.VISIBLE);
                }
                ImageView imageViewFashion = (ImageView) convertView.findViewById(R.id.ivFashion_menu_main);
                String[] arraylink_Fashion = activity.getResources().getStringArray(R.array.link_MenuPhotos);
                imageLoad.ImageLoad(arraylink_Fashion[position-1], imageViewFashion);
                break;
        }

        return convertView;
    }
}
