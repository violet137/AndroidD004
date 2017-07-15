package vn.com.greenacademy.shopping.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.ModeMenuMain;
import vn.com.greenacademy.shopping.Model.ModeSlideMenu;
import vn.com.greenacademy.shopping.R;

/**
 * Created by GIT on 3/11/2017.
 */

public class AdapterMenuMain extends ArrayAdapter {

    Activity activity;
    int layoutItem;
    ArrayList<ModeMenuMain> arrayList;

    public AdapterMenuMain(Activity activity, int resource, ArrayList<ModeMenuMain> objects){
        super(activity,resource,objects);
        this.activity = activity;
        layoutItem=resource;
        arrayList=objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //dinh nghia thanh phan giao dien cho tung item cua listview
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(layoutItem,null);
        final ModeMenuMain modeMenuMain = arrayList.get(position);

        switch (position){
            case 0:
                ViewFlipper viewFlipper = (ViewFlipper) convertView.findViewById(R.id.vf_menu_main);
                viewFlipper.setVisibility(View.VISIBLE);
                String[] arrayLinkPhoto = activity.getResources().getStringArray(R.array.link_viewFlipper);
                for (int i = 0; i < arrayLinkPhoto.length; i++) {
                    // chu y neu ko tai hinh dc thi kiem t(ra lai mang
                    ImageView image = new ImageView(activity);
                    image.setScaleType(ImageView.ScaleType.FIT_XY);
                    Picasso.with(activity).load(arrayLinkPhoto[i]).into(image);
                    viewFlipper.addView(image);
                }
                viewFlipper.startFlipping();
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                convertView.findViewById(R.id.constrainLayout_menu_main).setVisibility(View.VISIBLE);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.ivLayout_menu_main);
                TextView textView = (TextView) convertView.findViewById(R.id.tvName_menu_main);

                imageView.setImageResource(modeMenuMain.getPhoto());
                textView.setText(modeMenuMain.getName());
                break;
            default:
                ImageView imageView2 = (ImageView) convertView.findViewById(R.id.iv_menu_main);
                imageView2.setVisibility(View.VISIBLE);
                imageView2.setImageResource(modeMenuMain.getPhoto());
                break;
        }

        return convertView;
    }
}
