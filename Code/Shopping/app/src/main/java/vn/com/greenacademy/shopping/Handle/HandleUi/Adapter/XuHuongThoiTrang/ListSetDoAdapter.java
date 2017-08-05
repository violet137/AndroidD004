package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.XuHuongThoiTrang;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder.SetDoHolder;
import vn.com.greenacademy.shopping.Interface.SetDoCallBack;
import vn.com.greenacademy.shopping.Model.SetDo;
import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class ListSetDoAdapter extends RecyclerView.Adapter<SetDoHolder> {
    private Context context;
    private SetDoCallBack setDoCallBack;
    private ImageLoad imageLoad;
    private ArrayList<SetDo> listSetDo =  new ArrayList<>();

    public ListSetDoAdapter(Context context, SetDoCallBack setDoCallBack, ArrayList<SetDo> listSetDo, ImageLoad imageLoad){
        this.context = context;
        this.listSetDo = listSetDo;
        this.setDoCallBack = setDoCallBack;
        this.imageLoad = imageLoad;
    }

    @Override
    public SetDoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.item_set_do, parent, false);
        return new SetDoHolder(root);
    }

    @Override
    public void onBindViewHolder(final SetDoHolder holder, int position) {
        if (!listSetDo.get(position).isVideo())
            imageLoad.load(listSetDo.get(position).getHinhDaiDien(), holder.imgSetDo);
        else {

        }
        //Description set đồ
        if (!listSetDo.get(position).getDescriptionSetDo().isEmpty()){
            holder.tvDescription.setText(listSetDo.get(position).getDescriptionSetDo());
        } else {
            holder.tvDescription.setVisibility(View.GONE);
        }

        holder.imgSetDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDoCallBack.clickSetDo(holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSetDo.size();
    }
}
