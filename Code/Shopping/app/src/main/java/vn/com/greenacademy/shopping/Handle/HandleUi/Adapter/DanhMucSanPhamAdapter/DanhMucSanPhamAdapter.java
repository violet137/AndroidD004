package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.DanhMucSanPhamAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.LinkedList;
import java.util.List;

import vn.com.greenacademy.shopping.Model.DanhMucSanPhamModel.DanhMucSanPhamModel;
import vn.com.greenacademy.shopping.R;

/**
 * Created by Administrator on 31/07/2017.
 */

public class DanhMucSanPhamAdapter extends BaseAdapter {
    List<String> listTenDanhMuc;
    Context context;

    public DanhMucSanPhamAdapter(List<String> listTenDanhMuc, Context context) {
        this.listTenDanhMuc = listTenDanhMuc;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listTenDanhMuc.size();
    }

    @Override
    public Object getItem(int position) {
        return listTenDanhMuc.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_danh_muc_sp,null);
        TextView tv_item_danhmucsp = (TextView) convertView.findViewById(R.id.tv_itemDanhMuc);

        tv_item_danhmucsp.setText(listTenDanhMuc.get(position));


        return convertView;
    }
}
