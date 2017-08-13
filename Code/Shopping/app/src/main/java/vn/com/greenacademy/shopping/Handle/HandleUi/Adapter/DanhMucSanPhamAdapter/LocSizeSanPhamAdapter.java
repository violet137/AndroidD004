package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.DanhMucSanPhamAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder.LocSizeHolder;
import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 8/13/2017.
 */

public class LocSizeSanPhamAdapter extends RecyclerView.Adapter<LocSizeHolder> {
    private Context context;
    private ArrayList<String> mListSize = new ArrayList<>();

    public LocSizeSanPhamAdapter(Context context, ArrayList<String> mListSize) {
        this.context = context;
        this.mListSize = mListSize;
    }

    @Override
    public LocSizeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.item_loc_size_san_pham, parent, false);
        return new LocSizeHolder(root);
    }

    @Override
    public void onBindViewHolder(LocSizeHolder holder, int position) {
        holder.btnSize.setText(mListSize.get(position));

        holder.btnSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mListSize.size();
    }
}
