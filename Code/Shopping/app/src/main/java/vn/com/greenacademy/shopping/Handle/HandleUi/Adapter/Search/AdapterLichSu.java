package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Search;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.Search.SearchHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.ImageLoad;
import vn.com.greenacademy.shopping.Model.DanhMuc.MucSanPham;
import vn.com.greenacademy.shopping.R;

/**
 * Created by ADMIN on 8/23/2017.
 */

public class AdapterLichSu extends RecyclerView.Adapter<LichSuHolder>{
    Activity activity;
    LayoutInflater mLayoutInflater;
    ArrayList<MucSanPham> mucSanPhamArrayList;

    public AdapterLichSu(Activity activity, ArrayList<MucSanPham> mucSanPhamArrayList) {
        this.activity = activity;
        mLayoutInflater = LayoutInflater.from(activity);
        this.mucSanPhamArrayList=mucSanPhamArrayList;
    }

    @Override
    public LichSuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mLayoutInflater.inflate(R.layout.item_lich_su_search, parent, false);
        return new LichSuHolder(itemview);
    }

    @Override
    public void onBindViewHolder(LichSuHolder holder, int position) {
        if (mucSanPhamArrayList.get(position).getId() == -1){
            holder.llLayout.setVisibility(View.INVISIBLE);
        }else {
            holder.tvName.setText(mucSanPhamArrayList.get(position).getTenDanhMuc());
            holder.llLayout.setTag(mucSanPhamArrayList.get(position));
            holder.llLayout.setOnClickListener(SearchHandler.onClickListenerTopTuKhoa);
        }
    }


    @Override
    public int getItemCount() {
        return mucSanPhamArrayList.size();

    }

}
class LichSuHolder extends RecyclerView.ViewHolder {
    TextView tvName;
    LinearLayout llLayout;

    public LichSuHolder(View itemView) {
        super(itemView);

        tvName = (TextView) itemView.findViewById(R.id.tvName_lichSu_search);

        llLayout = (LinearLayout) itemView.findViewById(R.id.llLayout_lichSu_search);
    }

}
