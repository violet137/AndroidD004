package vn.com.greenacademy.shopping.Fragment.Main.SanPham;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder.SanPhamHolder;
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

        imageLoad.load(listSanPham.get(position).getHinhSanPham().get(0).getLinkHinh()[0], vHinhSanPham);
        if (listSanPham.get(position).getGiamGia() != 0){
            String giaSanPham = SanPhamHandler.chuyenGia(listSanPham.get(position).getGiamGia()) + "\n" + SanPhamHandler.chuyenGia(listSanPham.get(position).getGiaSanPham());
            SpannableString spanString = new SpannableString(giaSanPham);
            //Xử lý hiển thị thông tin trên text
            spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getActivity(), android.R.color.holo_red_dark)), 0, SanPhamHandler.chuyenGia(listSanPham.get(position).getGiamGia()).length(), 0);
            spanString.setSpan(new StrikethroughSpan(), SanPhamHandler.chuyenGia(listSanPham.get(position).getGiamGia()).length(), giaSanPham.length(), 0);
            tvGia.setText(spanString);
        } else {
            tvGia.setText(SanPhamHandler.chuyenGia(listSanPham.get(position).getGiaSanPham()));
            tvGia.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorTextDark));
        }
        return root;
    }

}
