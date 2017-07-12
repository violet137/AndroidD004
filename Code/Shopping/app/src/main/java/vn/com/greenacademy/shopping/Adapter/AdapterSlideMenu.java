package vn.com.greenacademy.shopping.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.ModeSlideMenu;
import vn.com.greenacademy.shopping.R;

/**
 * Created by GIT on 3/11/2017.
 */

public class AdapterSlideMenu extends ArrayAdapter {

    Activity activity;
    int layoutItem;
    ArrayList<ModeSlideMenu> arrayList;

    public AdapterSlideMenu(Activity activity, int resource, ArrayList<ModeSlideMenu> objects){
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
        final ModeSlideMenu modeSlideMenu = arrayList.get(position);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName_item_slide_menu);
        ImageView ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon_item_slide_menu);


        tvName.setText(modeSlideMenu.getTen());
        ivIcon.setImageResource(modeSlideMenu.getIcon());

        return convertView;
    }
}
