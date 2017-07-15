package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.XuHuongThoiTrang;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder.SetDoHolder;
import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class ListSetDoAdapter extends RecyclerView.Adapter<SetDoHolder> {
    private Context context;
    private String[] listDataDescription;

    public ListSetDoAdapter(Context context){
        this.context = context;

        //Data test
        loadDataTest();
    }
    @Override
    public SetDoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.item_set_do, parent, false);
        return new SetDoHolder(root);
    }

    @Override
    public void onBindViewHolder(SetDoHolder holder, int position) {
        if (!listDataDescription[position].isEmpty()){
            holder.tvDescription.setText(listDataDescription[position]);
        } else {
            holder.tvDescription.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listDataDescription.length;
    }

    public void loadDataTest(){
        listDataDescription = context.getResources().getStringArray(R.array.arr_description);
    }
}
