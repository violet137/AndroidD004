package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.FindStore;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.SlideMenu;
import vn.com.greenacademy.shopping.Model.Store;
import vn.com.greenacademy.shopping.R;

/**
 * Created by ADMIN on 7/28/2017.
 */

public class AdapterFindStore extends ArrayAdapter {

    Activity activity;
    int layoutItem;
    ArrayList<Store> arrayList;
    double myLat;
    double myLng;

    public AdapterFindStore(Activity activity, int layoutItem, ArrayList<Store> objects,
                            double myLat, double myLng){
        super(activity,layoutItem,objects);
        this.activity = activity;
        this.layoutItem=layoutItem;
        arrayList=objects;
        this.myLat = myLat;
        this.myLng = myLng;
    }



    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //dinh nghia thanh phan giao dien cho tung item cua listview
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(layoutItem,null);
        final Store store = arrayList.get(position);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName_find_store);
        TextView tvKhoangCach = (TextView) convertView.findViewById(R.id.tvKhoanCach_find_store);
        TextView tvDiaChi = (TextView) convertView.findViewById(R.id.tvDiaChi_find_store);
        TextView tvLoai = (TextView) convertView.findViewById(R.id.tvLoai_find_store);



        tvName.setText(store.getTenCuaHang());
        tvKhoangCach.setText(tinhDuongChimBay(store.getLat(), store.getLng()) + " Km");
        tvDiaChi.setText(store.getDiaChi());
        String loaiThoiTrang = "";
        for (int i = 0; i < store.getLoaiThoiTrang().size(); i++) {
            if (i==0){
                loaiThoiTrang += store.getLoaiThoiTrang().get(i).getTen();
            }else {
                loaiThoiTrang = loaiThoiTrang + " - " + store.getLoaiThoiTrang().get(i).getTen();
            }
        }
        tvLoai.setText(loaiThoiTrang);



        return convertView;
    }

    private String tinhDuongChimBay(double lat, double lng) {
        final int banKinhTraiDat = 6317;
        double dLat = (lat-myLat)*(Math.PI/180);
        double dLng = (lng-myLng)*(Math.PI/180);

        double latToRad = lat*(Math.PI/180);
        double mylatToRad = myLat*(Math.PI/180);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(latToRad) * Math.cos(mylatToRad) * Math.sin(dLng / 2) * Math.sin(dLng / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        float result = (float) (banKinhTraiDat * c);

        // format lại số
        DecimalFormat decimalFormat = new DecimalFormat("###.##");

        return decimalFormat.format(result);
    }
}
