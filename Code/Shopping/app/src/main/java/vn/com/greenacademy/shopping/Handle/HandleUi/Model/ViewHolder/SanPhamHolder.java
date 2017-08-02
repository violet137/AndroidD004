package vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class SanPhamHolder extends RecyclerView.ViewHolder {
    public ImageView imgSanPham;
    public TextView tvTenSanPham;
    public  TextView tvGia;
    public LinearLayout itemSanPham;

    public SanPhamHolder(View itemView) {
        super(itemView);
        imgSanPham = (ImageView) itemView.findViewById(R.id.san_pham_image);
        tvTenSanPham = (TextView) itemView.findViewById(R.id.ten_san_pham_text_view);
        tvGia = (TextView) itemView.findViewById(R.id.gia_text_view);
        itemSanPham = (LinearLayout) itemView.findViewById(R.id.layout_item_san_pham);
    }
}
