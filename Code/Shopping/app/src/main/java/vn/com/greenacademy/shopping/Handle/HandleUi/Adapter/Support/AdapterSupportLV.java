package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Support;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.DanhMucSanPham.ClickListenerDanhMucSanPham;
import vn.com.greenacademy.shopping.Handle.HandleUi.Model.Support;
import vn.com.greenacademy.shopping.Model.SlideMenu;
import vn.com.greenacademy.shopping.R;

/**
 * Created by ADMIN on 8/12/2017.
 */

public class AdapterSupportLV extends ArrayAdapter {

    Activity activity;
    int layoutItem;
    ArrayList<Support> arrayList;

    public AdapterSupportLV(Activity activity, int layoutItem, ArrayList<Support> objects){
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
        final Support support = arrayList.get(position);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName_item_slide_menu);

        ImageView ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon_item_slide_menu);

        tvName.setText(support.getName());
        ivIcon.setImageResource(support.getIcon());

        LinearLayout itemMenu = (LinearLayout) convertView.findViewById(R.id.item_menu_SlideMenu);
        itemMenu.setTag(position);
        itemMenu.setOnClickListener(ClickListenerDanhMucSanPham.onClickListener);

        return convertView;
    }
}
