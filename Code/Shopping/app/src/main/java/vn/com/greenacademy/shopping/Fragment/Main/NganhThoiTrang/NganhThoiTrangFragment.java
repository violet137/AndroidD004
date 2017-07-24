package vn.com.greenacademy.shopping.Fragment.Main.NganhThoiTrang;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NganhThoiTrangFragment extends Fragment {


    public NganhThoiTrangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_nganh_thoi_trang, container, false);
        TextView tvTitle = (TextView) root.findViewById(R.id.text_loai_nganh_thoi_trang);
        TextView tvSoLuong = (TextView) root.findViewById(R.id.text_so_luong_nganh_thoi_trang);
        ListView listPhanLoai = (ListView) root.findViewById(R.id.list_phan_loai);
        RecyclerView listSanPham = (RecyclerView) root.findViewById(R.id.list_san_pham_nganh_thoi_trang);

        return root;
    }

}
