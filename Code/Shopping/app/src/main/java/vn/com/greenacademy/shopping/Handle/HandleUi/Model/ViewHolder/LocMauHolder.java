package vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 8/13/2017.
 */

public class LocMauHolder extends RecyclerView.ViewHolder {
    public Button btnHinhMau;
    public TextView tvTenMau;

    public LocMauHolder(View itemView) {
        super(itemView);
        btnHinhMau = (Button) itemView.findViewById(R.id.hinh_mau_san_pham);
        tvTenMau = (TextView) itemView.findViewById(R.id.ten_mau_san_pham);
    }
}
