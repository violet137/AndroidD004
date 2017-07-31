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
import vn.com.greenacademy.shopping.Model.DanhMucSP;
import vn.com.greenacademy.shopping.Model.MenuMain;
import vn.com.greenacademy.shopping.Model.MucSanPham;
import vn.com.greenacademy.shopping.Model.ProductsPhoto;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 7/31/2017.
 */

public class AdapterDanhMucSP extends ArrayAdapter {

    Activity activity;
    int layoutItem;
    ArrayList<MucSanPham> mucSanPhams;
    View.OnClickListener onClickListener;

    public AdapterDanhMucSP(Activity activity, int resource, ArrayList<MucSanPham> mucSanPhams, View.OnClickListener onClickListener){
        super(activity,resource, (List) mucSanPhams);
        this.activity = activity;
        layoutItem=resource;
        this.mucSanPhams=mucSanPhams;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //dinh nghia thanh phan giao dien cho tung item cua listview
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(layoutItem,null);
        ImageLoad imageLoad = new ImageLoad(activity);

        switch (position){
            case SupportKeyList.ivDanhMucSP:
                ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_itemDanhMucSP);
                convertView.findViewById(R.id.tvName_itemDanhMucSP).setVisibility(View.GONE);

                imageView.setTag(mucSanPhams.get(position));
                imageView.setOnClickListener(onClickListener);

                imageView.setVisibility(View.VISIBLE);
                imageLoad.ImageLoad(mucSanPhams.get(position).getLinkAnh(), imageView);
                break;

            default:
                TextView textView = (TextView) convertView.findViewById(R.id.tvName_itemDanhMucSP);

                textView.setTag(mucSanPhams.get(position));
                textView.setOnClickListener(onClickListener);

                textView.setText(mucSanPhams.get(position).getTenDanhMuc());
                break;
        }
        return convertView;
    }
}
