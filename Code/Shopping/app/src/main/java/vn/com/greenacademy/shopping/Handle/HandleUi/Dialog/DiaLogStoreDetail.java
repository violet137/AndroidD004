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

import vn.com.greenacademy.shopping.Handle.HandleUi.ImageLoad;
import vn.com.greenacademy.shopping.Model.Store;
import vn.com.greenacademy.shopping.R;

/**
 * Created by ADMIN on 7/29/2017.
 */

public class DiaLogStoreDetail extends DialogFragment implements View.OnClickListener {
    Store store;
    double myLat;
    double myLng;

    Button btnPhone;

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

        dialog.findViewById(R.id.llDirection_dialog_StoreDetail).setOnClickListener(this);
        dialog.findViewById(R.id.llCall_dialog_StoreDetail).setOnClickListener(this);

        btnPhone = (Button) dialog.findViewById(R.id.btnPhone_dialog_StoreDetail);

        btnPhone.setOnClickListener(this);

        ImageLoad imageLoad = new ImageLoad(getActivity());
        imageLoad.load(store.getLinkAnh(), ivPhoto);

        // format lại số dt
        String phoneNumber = "";

        char mangSoDT[] = store.getSoDienThoai().toCharArray();

        for (int i = 0; i < mangSoDT.length; i++) {
            // khoan tu 48 den 57 la cac so tu 0,1,2,3,4,5,6,7,8,9
            if (mangSoDT[i]>47 && mangSoDT[i]<58){
                phoneNumber += mangSoDT[i];
            }
        }

        btnPhone.setText("  "+phoneNumber);

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
            case R.id.llDirection_dialog_StoreDetail:
                /// su dung app google map chi duong
                Intent intentMap = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=" + myLat + "," + myLng + "&daddr=" + store.getLat() + "," + store.getLng()));
                intentMap.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intentMap.addCategory(Intent.CATEGORY_LAUNCHER );
                intentMap.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intentMap);
                break;
            case R.id.llCall_dialog_StoreDetail:
                // chuyen sang mang hinh goi cua dt
                Intent intentPhone = new Intent(Intent.ACTION_DIAL);
                intentPhone.setData(Uri.parse("tel:" + btnPhone.getText().toString()));
                startActivity(intentPhone);
                break;
        }
    }
}
