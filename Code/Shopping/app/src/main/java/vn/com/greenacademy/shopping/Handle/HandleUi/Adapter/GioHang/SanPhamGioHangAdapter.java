package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.GioHang;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.GioHang.GioHangHandler;
import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder.SanPhamGioHangHolder;
import vn.com.greenacademy.shopping.Interface.ItemClickCallBack;
import vn.com.greenacademy.shopping.Interface.ItemLongClickCallBack;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPhamGioHang;
import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 8/18/2017.
 */

public class SanPhamGioHangAdapter extends RecyclerView.Adapter<SanPhamGioHangHolder> {
    private Context context;
    private GioHangHandler gioHangHandler;
    private ArrayList<SanPhamGioHang> mListSanPham = new ArrayList<>();
    private SanPhamGioHang sanPhamGioHang;
    private ImageLoad imageLoad;
    private ItemClickCallBack itemClickCallBack;
    private ItemLongClickCallBack itemLongClickCallBack;

    public SanPhamGioHangAdapter(Context context, ArrayList<SanPhamGioHang> listSanPham, ItemLongClickCallBack itemLongClickCallBack, ItemClickCallBack itemClickCallBack) {
        this.context = context;
        mListSanPham = listSanPham;
        sanPhamGioHang = new SanPhamGioHang();
        imageLoad = new ImageLoad((Activity)context);
        gioHangHandler = new GioHangHandler(context, null);
        this.itemClickCallBack = itemClickCallBack;
        this.itemLongClickCallBack = itemLongClickCallBack;
    }

    @Override
    public SanPhamGioHangHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.item_san_pham_gio_hang, parent, false);
        return new SanPhamGioHangHolder(root);
    }

    @Override
    public void onBindViewHolder(final SanPhamGioHangHolder holder, int position) {
        sanPhamGioHang = mListSanPham.get(position);

        holder.itemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickCallBack.clickItem(holder.getLayoutPosition());
            }
        });
        holder.itemGioHang.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                itemLongClickCallBack.longClickItem(holder.getLayoutPosition());
                return false;
            }
        });


        imageLoad.load(sanPhamGioHang.getLinkHinh(), holder.imgSanPham);
        holder.tvTenSanPham.setText(sanPhamGioHang.getTenSanPham());

        //Xử lý hiển thị thông tin trên text
        //Số lượng
        SpannableString spanStringSoLuong = new SpannableString(holder.tvSoLuong.getText() + String.valueOf(sanPhamGioHang.getSoLuong()));
        spanStringSoLuong.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorTextDark)), holder.tvSoLuong.getText().length(), holder.tvSoLuong.getText().length() + String.valueOf(sanPhamGioHang.getSoLuong()).length(), 0);
        holder.tvSoLuong.setText(spanStringSoLuong);
        //Màu
        SpannableString spanStringMau = new SpannableString(holder.tvMau.getText() + SanPhamHandler.chuyenTenMau(sanPhamGioHang.getMauSanPham()));
        spanStringMau.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorTextDark)), holder.tvMau.getText().length(), holder.tvMau.getText().length() + SanPhamHandler.chuyenTenMau(sanPhamGioHang.getMauSanPham()).length(), 0);
        holder.tvMau.setText(spanStringMau);
        //Size
        SpannableString spanStringSize = new SpannableString(holder.tvSize.getText() + String.valueOf(sanPhamGioHang.getSize()));
        spanStringSize.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorTextDark)), holder.tvSize.getText().length(), holder.tvSize.getText().length() + String.valueOf(sanPhamGioHang.getSize()).length(), 0);
        holder.tvSize.setText(spanStringSize);

        //Tiền
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
