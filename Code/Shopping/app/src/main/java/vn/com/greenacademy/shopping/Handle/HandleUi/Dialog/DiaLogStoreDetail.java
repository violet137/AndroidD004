package vn.com.greenacademy.shopping.Handle.HandleUi.Dialog;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Model.Store;
import vn.com.greenacademy.shopping.R;

/**
 * Created by ADMIN on 7/29/2017.
 */

public class DiaLogStoreDetail extends DialogFragment implements View.OnClickListener {
    Store store;
    double myLat;
    double myLng;

    public DiaLogStoreDetail(Store store, double myLat, double myLng) {
        this.store = store;
        this.myLat = myLat;
        this.myLng = myLng;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_store_detail);

        ImageView ivPhoto = (ImageView) dialog.findViewById(R.id.ivPhoto_dialog_StoreDeatil);

        TextView tvName = (TextView) dialog.findViewById(R.id.tvName_dialog_StoreDetail);
        TextView tvDiaChi = (TextView) dialog.findViewById(R.id.tvDiaChi_dialog_StoreDetail);
        TextView tvOpenHours = (TextView) dialog.findViewById(R.id.tvOpeningHours_dialog_StoreDetail);
        TextView tvType = (TextView) dialog.findViewById(R.id.tvProductsType_dialog_StoreDetail);

        Button btnDirection = (Button) dialog.findViewById(R.id.btnDirection_dialog_StoreDetail);
        Button btnPhone = (Button) dialog.findViewById(R.id.btnPhone_dialog_StoreDetail);

        btnDirection.setOnClickListener(this);
        btnPhone.setOnClickListener(this);

        ImageLoad imageLoad = new ImageLoad(getActivity());
        imageLoad.load(store.getLinkAnh(), ivPhoto);

        btnPhone.setText(store.getSoDienThoai());

        tvName.setText(store.getTenCuaHang()!=null? store.getTenCuaHang():"");
        tvDiaChi.setText(store.getDiaChi() !=null? store.getDiaChi():"");
        tvOpenHours.setText(store.getGioMoCua() !=null? store.getGioMoCua():"");
        if (store.getLoaiThoiTrang() == null){
            dialog.findViewById(R.id.linear_Type_dialog_StoreDetail).setVisibility(View.GONE);
        }else {
            String productsType = "";
            for (int i = 0; i < store.getLoaiThoiTrang().size(); i++) {
                if (i==0){
                    productsType = store.getLoaiThoiTrang().get(i).getTen();
                }else {
                    productsType = productsType + " - " + store.getLoaiThoiTrang().get(i).getTen();
                }
            }
            tvType.setText(productsType);
        }


        return dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDirection_dialog_StoreDetail:
                /// su dung app google map chi duong
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=" + myLat + "," + myLng + "&daddr=" + store.getLat() + "," + store.getLng()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_LAUNCHER );
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
                break;
            case R.id.btnPhone_dialog_StoreDetail:

                break;
        }
    }
}
