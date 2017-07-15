package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder.XuHuongThoiTrangHolder;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private String listName;
    private BaseFragment baseFragment;

    public RecycleViewAdapter(Context context, String listName, BaseFragment baseFragment) {
        this.context = context;
        this.listName = listName;
        this.baseFragment = baseFragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = null;
        switch (listName){
            case "ListBannerXuHuongThoiTrang":
                root = inflater.inflate(R.layout.item_banner_xu_huong_thoi_trang, parent, false);
                return new XuHuongThoiTrangHolder(root, baseFragment);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
