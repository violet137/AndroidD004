package vn.com.greenacademy.shopping.Fragment.Main.SanPham;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SanPhamPagerFragment extends Fragment {
    private ArrayList<SanPham> listSanPham = new ArrayList<>();
    private ImageLoad imageLoad;
    private int position;

    public SanPhamPagerFragment(int position, ArrayList<SanPham> listSanPham){
        this.listSanPham = listSanPham;
        this.position = position;
        imageLoad = new ImageLoad(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_san_pham_page, container, false);
        ImageView vHinhSanPham = (ImageView) root.findViewById(R.id.hinh_san_pham);
        TextView tvGia = (TextView) root.findViewById(R.id.gia_fragment_page_san_pham);

        imageLoad.ImageLoad(listSanPham.get(position).getHinhSanPham().get(0).getLinkHinh()[0], vHinhSanPham);
        tvGia.setText(String.valueOf(listSanPham.get(position).getGiaSanPham()));
        return root;
    }

}
