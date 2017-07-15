package vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class SanPhamHolder extends RecyclerView.ViewHolder {

    public SanPhamHolder(View itemView) {
        super(itemView);
        ImageView imgSanPham = (ImageView) itemView.findViewById(R.id.san_pham_image);
        TextView tvTenSanPham = (TextView) itemView.findViewById(R.id.ten_san_pham_text_view);
        TextView tvGia = (TextView) itemView.findViewById(R.id.gia_text_view);
    }
}
