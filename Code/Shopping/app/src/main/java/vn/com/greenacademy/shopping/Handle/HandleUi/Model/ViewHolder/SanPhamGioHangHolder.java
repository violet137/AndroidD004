package vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 8/18/2017.
 */

public class SanPhamGioHangHolder extends RecyclerView.ViewHolder {
    public TextView tvTenSanPham;
    public TextView tvGiaSanPham;
    public TextView tvGiaGiam;
    public TextView tvSoLuong;
    public TextView tvMau;
    public TextView tvSize;
    public ImageView imgSanPham;
    public FrameLayout itemGioHang;

    public SanPhamGioHangHolder(View itemView) {
        super(itemView);
        imgSanPham = (ImageView) itemView.findViewById(R.id.hinh_san_pham_gio_hang);
        tvTenSanPham = (TextView) itemView.findViewById(R.id.ten_san_pham_gio_hang);
        tvGiaSanPham = (TextView) itemView.findViewById(R.id.gia_san_pham_gio_hang);
        tvGiaGiam = (TextView) itemView.findViewById(R.id.gia_giam_san_pham_gio_hang);
        tvSoLuong = (TextView) itemView.findViewById(R.id.so_luong_san_pham_gio_hang);
        tvMau = (TextView) itemView.findViewById(R.id.mau_san_pham_gio_hang);
        tvSize = (TextView) itemView.findViewById(R.id.size_san_pham_gio_hang);
        itemGioHang = (FrameLayout) itemView.findViewById(R.id.item_san_pham_gio_hang);
    }
}
