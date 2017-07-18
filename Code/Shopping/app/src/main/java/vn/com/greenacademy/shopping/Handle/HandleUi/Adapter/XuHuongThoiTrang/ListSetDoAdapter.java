package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.XuHuongThoiTrang;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder.SetDoHolder;
import vn.com.greenacademy.shopping.Model.SetDo;
import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class ListSetDoAdapter extends RecyclerView.Adapter<SetDoHolder> {
    private Context context;
    private ArrayList<SetDo> listSetDo = null;

    public ListSetDoAdapter(Context context, ArrayList<SetDo> listSetDo){
        this.context = context;
        this.listSetDo = listSetDo;
    }

    @Override
    public SetDoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.item_set_do, parent, false);
        return new SetDoHolder(root);
    }

    @Override
    public void onBindViewHolder(SetDoHolder holder, int position) {
        //Description set đồ
        if (!listSetDo.get(position).getDescriptionSetDo().isEmpty()){
            holder.tvDescription.setText(listSetDo.get(position).getDescriptionSetDo());
        } else {
            holder.tvDescription.setVisibility(View.GONE);
        }

        //hình set đồ
    }

    @Override
    public int getItemCount() {
        return listSetDo.size();
    }
}
