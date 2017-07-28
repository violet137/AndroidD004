package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.SlideMenu;
import vn.com.greenacademy.shopping.R;

/**
 * Created by GIT on 3/11/2017.
 */

public class AdapterSlideMenu extends ArrayAdapter {

    Activity activity;
    int layoutItem;
    ArrayList<SlideMenu> arrayList;

    public AdapterSlideMenu(Activity activity, int layoutItem, ArrayList<SlideMenu> objects){
        super(activity,layoutItem,objects);
        this.activity = activity;
        this.layoutItem=layoutItem;
        arrayList=objects;
    }



    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //dinh nghia thanh phan giao dien cho tung item cua listview
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(layoutItem,null);
        final SlideMenu slideMenu = arrayList.get(position);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName_item_slide_menu);

        ImageView ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon_item_slide_menu);

        if (position>0 && position<5) {
            ivIcon.setVisibility(View.INVISIBLE);
        }

        if (position == 4){
            convertView.findViewById(R.id.divider_item_slide_menu).setVisibility(View.VISIBLE);
        }

        tvName.setText(slideMenu.getTen());
        ivIcon.setImageResource(slideMenu.getIcon());

        return convertView;
    }
}
