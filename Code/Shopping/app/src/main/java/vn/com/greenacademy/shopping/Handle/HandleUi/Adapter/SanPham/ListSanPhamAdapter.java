package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder.SanPhamHolder;
import vn.com.greenacademy.shopping.Model.SanPham;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class ListSanPhamAdapter extends RecyclerView.Adapter<SanPhamHolder> {
    private Context context;
    private ArrayList<SanPham> listSanPham;
    private BaseFragment baseFragment;

    public ListSanPhamAdapter(Context context, ArrayList<SanPham> listSanPham, BaseFragment baseFragment){
        this.context = context;
        this.listSanPham = listSanPham;
        this.baseFragment = baseFragment;
    }

    @Override
    public SanPhamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.item_san_pham, parent, false);
        return new SanPhamHolder(root, baseFragment, listSanPham);
    }

    @Override
    public void onBindViewHolder(SanPhamHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listSanPham.size();
    }
}
