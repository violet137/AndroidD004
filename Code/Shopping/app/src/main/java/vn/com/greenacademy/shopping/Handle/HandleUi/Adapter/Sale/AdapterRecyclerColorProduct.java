package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.MainActivity;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 8/5/2017.
 */

public class AdapterRecyclerColorProduct extends RecyclerView.Adapter<ColorProductHolder>{
    public static Context context;

    LayoutInflater mLayoutInflater;
    String[] mListMauSanPham;

    public AdapterRecyclerColorProduct(Context context, String[] mListMauSanPham) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);

        //Thiết lập số lượng màu hiển thị
        if (mListMauSanPham.length > SupportKeyList.SoMauHienThiToiDa) {
            this.mListMauSanPham = new String[SupportKeyList.SoMauHienThiToiDa + 1];
            for (int i = 0; i < this.mListMauSanPham.length; i++) {
                if (i == this.mListMauSanPham.length -1)
                    this.mListMauSanPham[i] = "more";
                else
                    this.mListMauSanPham[i] = mListMauSanPham[i];
            }
        } else
            this.mListMauSanPham = mListMauSanPham;
    }

    @Override
    public ColorProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mLayoutInflater.inflate(R.layout.item_recycler_color_product, parent, false);
        return new ColorProductHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ColorProductHolder holder, int position) {
        holder.imageView.setImageResource(holder.sanPhamHandler.doiMaMau(mListMauSanPham[position]));
    }

    @Override
    public int getItemCount() {
        return mListMauSanPham.length;
    }
}

class ColorProductHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    SanPhamHandler sanPhamHandler;

    public ColorProductHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.ivMau_item_recycleView_color_product);

        sanPhamHandler = new SanPhamHandler((Activity)AdapterRecyclerColorProduct.context);
    }

}
