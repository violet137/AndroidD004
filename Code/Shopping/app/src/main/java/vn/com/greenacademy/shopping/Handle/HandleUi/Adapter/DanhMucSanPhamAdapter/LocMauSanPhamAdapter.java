package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.DanhMucSanPhamAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.internal.zzop;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder.LocMauHolder;
import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 8/13/2017.
 */

public class LocMauSanPhamAdapter extends RecyclerView.Adapter<LocMauHolder> {
    private Context context;
    private ArrayList<String> mListMau = new ArrayList<>();
    private SanPhamHandler sanPhamHandler;

    public LocMauSanPhamAdapter(Context context, ArrayList<String> mListMau) {
        this.context = context;
        this.mListMau = mListMau;
        sanPhamHandler = new SanPhamHandler((Activity) context);
    }

    @Override
    public LocMauHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.item_loc_mau_san_pham, parent, false);
        return new LocMauHolder(root);
    }

    @Override
    public void onBindViewHolder(LocMauHolder holder, final int position) {
        holder.btnHinhMau.setBackgroundResource(sanPhamHandler.doiMaMau(mListMau.get(position)));
        holder.tvTenMau.setText(SanPhamHandler.chuyenTenMau(mListMau.get(position)));

        holder.vItemMau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, SanPhamHandler.chuyenTenMau(mListMau.get(position)), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListMau.size();
    }
}
