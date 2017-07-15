package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.XuHuongThoiTrang;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder.SanPhamHolder;
import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class ListSanPhamAdapter extends RecyclerView.Adapter<SanPhamHolder> {
    private Context context;

    public ListSanPhamAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SanPhamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.item_san_pham, parent, false);
        return new SanPhamHolder(root);
    }

    @Override
    public void onBindViewHolder(SanPhamHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
