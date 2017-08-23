package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Search;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.Handle.HandleData.Search.SearchHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale.AdapterRecyclerColorProduct;
import vn.com.greenacademy.shopping.Handle.HandleUi.ImageLoad;
import vn.com.greenacademy.shopping.Model.DanhMuc.MucSanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 8/23/2017.
 */

public class AdapterTopTuKhoaTim  extends RecyclerView.Adapter<TopTKYimHolder>{
    Activity activity;
    LayoutInflater mLayoutInflater;
    ArrayList<MucSanPham> mucSanPhamArrayList;
    ImageLoad imageLoad;

    public AdapterTopTuKhoaTim(Activity activity, ArrayList<MucSanPham> mucSanPhamArrayList) {
        this.activity = activity;
        mLayoutInflater = LayoutInflater.from(activity);
        this.mucSanPhamArrayList=mucSanPhamArrayList;
    }

    @Override
    public TopTKYimHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mLayoutInflater.inflate(R.layout.item_muc_san_pham, parent, false);
        return new TopTKYimHolder(itemview);
    }

    @Override
    public void onBindViewHolder(TopTKYimHolder holder, int position) {
        holder.tvName.setText(mucSanPhamArrayList.get(position).getTenDanhMuc());
        holder.llLayout.setTag(mucSanPhamArrayList.get(position));
        holder.llLayout.setOnClickListener(SearchHandler.onClickListenerTopTuKhoa);
    }


    @Override
    public int getItemCount() {
        return mucSanPhamArrayList.size();

    }

}
class TopTKYimHolder extends RecyclerView.ViewHolder {
    TextView tvName;
    LinearLayout llLayout;

    public TopTKYimHolder(View itemView) {
        super(itemView);

        tvName = (TextView) itemView.findViewById(R.id.tvName_mucSanPham);

        llLayout = (LinearLayout) itemView.findViewById(R.id.llLayout_mucSanPham);
    }

}
