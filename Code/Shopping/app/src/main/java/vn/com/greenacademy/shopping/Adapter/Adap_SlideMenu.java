//package vn.com.greenacademy.shopping.Adapter;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.support.annotation.LayoutRes;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.Display;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//import vn.com.greenacademy.shopping.Model.Mod_Child_SlideMenu;
//import vn.com.greenacademy.shopping.R;
//
///**
// * Created by ADMIN on 7/8/2017.
// */
//
//public class Adap_SlideMenu extends ArrayAdapter {
//    Activity activity;
//    int layoutItem;
//    ArrayList<Mod_Child_SlideMenu> arrayList;
//    int index;
//
//
//    public Adap_SlideMenu(Activity activity, int layoutItem, ArrayList<Mod_Child_SlideMenu> arrayList) {
//        super(activity, layoutItem, arrayList);
//        this.activity = activity;
//        this.layoutItem = layoutItem;
//        this.arrayList = arrayList;
//    }
//
//    @Override
//    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = activity.getLayoutInflater();
//        convertView = inflater.inflate(layoutItem, null);
//        final Mod_Child_SlideMenu child_slideMenu = arrayList.get(position);
//        TextView tvName = (TextView) convertView.findViewById(R.id.tvName_icon_nav_mani2);
//        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_icon_nav_mani2);
//        index = position;
//
//        tvName.setText(child_slideMenu.getTen());
//        imageView.setImageResource(child_slideMenu.getAnh());
//
//        return convertView;
//    }
//}
//
