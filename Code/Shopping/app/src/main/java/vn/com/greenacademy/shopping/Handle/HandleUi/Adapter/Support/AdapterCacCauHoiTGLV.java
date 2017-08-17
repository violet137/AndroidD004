package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Support;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.Support.CauHoiTG;
import vn.com.greenacademy.shopping.R;

/**
 * Created by ADMIN on 8/12/2017.
 */

public class AdapterCacCauHoiTGLV extends ArrayAdapter {

    Activity activity;
    int layoutItem;
    ArrayList<CauHoiTG> arrayList;

    public AdapterCacCauHoiTGLV(Activity activity, int layoutItem, ArrayList<CauHoiTG> objects){
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
        final CauHoiTG cauHoiTG = arrayList.get(position);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName_item_CacCAuHoiTG);

        tvName.setText(cauHoiTG.getNoiDungCauHoi());

        return convertView;
    }
}
