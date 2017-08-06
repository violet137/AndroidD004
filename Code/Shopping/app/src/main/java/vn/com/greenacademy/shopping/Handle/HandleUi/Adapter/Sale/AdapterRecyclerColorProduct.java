package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale;

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

import vn.com.greenacademy.shopping.MainActivity;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 8/5/2017.
 */

public class AdapterRecyclerColorProduct extends RecyclerView.Adapter<ColorProductHolder>{

    Context context;
    LayoutInflater mLayoutInflater;
    String[] mauSanPham;


    public AdapterRecyclerColorProduct(Context context, String[] mauSanPham) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.mauSanPham = mauSanPham;

    }

    @Override
    public ColorProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mLayoutInflater.inflate(R.layout.item_recycler_color_product, parent, false);
        return new ColorProductHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ColorProductHolder holder, int position) {
        holder.imageView.setImageResource(MainActivity.doiMaMau(mauSanPham[position]));
    }

    @Override
    public int getItemCount() {
        return mauSanPham.length;
    }
}

class ColorProductHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    public ColorProductHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.ivMau_item_recycleView_color_product);

    }

}
