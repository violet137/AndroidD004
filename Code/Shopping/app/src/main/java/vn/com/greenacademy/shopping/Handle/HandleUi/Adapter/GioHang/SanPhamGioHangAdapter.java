package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.GioHang;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder.SanPhamGioHangHolder;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPhamGioHang;
import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 8/18/2017.
 */

public class SanPhamGioHangAdapter extends RecyclerView.Adapter<SanPhamGioHangHolder> {
    private Context context;
    private ArrayList<SanPhamGioHang> mListSanPham = new ArrayList<>();
    private SanPhamGioHang sanPhamGioHang;
    private ImageLoad imageLoad;

    public SanPhamGioHangAdapter(Context context, ArrayList<SanPhamGioHang> listSanPham) {
        this.context = context;
        mListSanPham = listSanPham;
        sanPhamGioHang = new SanPhamGioHang();
        imageLoad = new ImageLoad((Activity)context);
    }

    @Override
    public SanPhamGioHangHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.item_san_pham_gio_hang, parent, false);
        return new SanPhamGioHangHolder(root);
    }

    @Override
    public void onBindViewHolder(SanPhamGioHangHolder holder, int position) {
        sanPhamGioHang = mListSanPham.get(position);

        imageLoad.load(sanPhamGioHang.getLinkHinh(), holder.imgSanPham);
        holder.tvTenSanPham.setText(sanPhamGioHang.getTenSanPham());
        holder.tvSoLuong.setText(holder.tvSoLuong.getText() + String.valueOf(sanPhamGioHang.getSoLuong()));
        holder.tvMau.setText(holder.tvMau.getText() + SanPhamHandler.chuyenTenMau(sanPhamGioHang.getMauSanPham()));
        holder.tvSize.setText(holder.tvSize.getText() + sanPhamGioHang.getSize());

        if (sanPhamGioHang.getGiaGiam() != 0){
            holder.tvGiaSanPham.setText(String.valueOf(SanPhamHandler.chuyenGia(sanPhamGioHang.getGiaGiam())));
            String giaSanPham = SanPhamHandler.chuyenGia(sanPhamGioHang.getGiaGiam()) + " " + SanPhamHandler.chuyenGia(sanPhamGioHang.getGiaGoc());
            SpannableString spanString = new SpannableString(giaSanPham);
            //Xử lý hiển thị thông tin trên text
            spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, android.R.color.holo_red_dark)), 0, SanPhamHandler.chuyenGia(sanPhamGioHang.getGiaGiam()).length(), 0);
            spanString.setSpan(new StrikethroughSpan(), SanPhamHandler.chuyenGia(sanPhamGioHang.getGiaGiam()).length() + 1, giaSanPham.length(), 0);
            holder.tvGiaGiam.setText(spanString);
        } else {
            holder.tvGiaSanPham.setText(String.valueOf(SanPhamHandler.chuyenGia(sanPhamGioHang.getGiaGoc())));
            holder.tvGiaGiam.setText(SanPhamHandler.chuyenGia(sanPhamGioHang.getGiaGoc()));
            holder.tvGiaGiam.setTextColor(ContextCompat.getColor(context, R.color.colorTextDark));
        }
    }

    @Override
    public int getItemCount() {
        return mListSanPham.size();
    }
}
