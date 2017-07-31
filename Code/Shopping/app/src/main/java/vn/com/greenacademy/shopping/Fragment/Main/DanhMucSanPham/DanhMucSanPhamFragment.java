package vn.com.greenacademy.shopping.Fragment.Main.DanhMucSanPham;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import vn.com.greenacademy.shopping.Network.AsynTask.DanhMucSanPhamAsyncTask;
import vn.com.greenacademy.shopping.R;

/**
 * Created by Administrator on 29/07/2017.
 */

public class DanhMucSanPhamFragment extends Fragment {

    DanhMucSanPhamAsyncTask asyncTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danhmucsanpham,container,false);
        TextView tv_danhmucsp= (TextView) view.findViewById(R.id.tv_danhmucsp);
        ListView lv_danhmucsp = (ListView) view.findViewById(R.id.lview_danhmucsp);
        ImageView image_danhmucsp = (ImageView) view.findViewById(R.id.img_danhmucsp);
        asyncTask = new DanhMucSanPhamAsyncTask(getContext(),tv_danhmucsp,lv_danhmucsp,image_danhmucsp);
        asyncTask.execute("nam");


        return view;
    }
}
