package vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.XuHuongThoiTrang.ListSanPhamAdapter;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.XuHuongThoiTrang.ListSetDoAdapter;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class XuHuongThoiTrangFragment extends Fragment {
    private int position;

    //Data test
    private int[] listDataBanner;

    public XuHuongThoiTrangFragment(int pos) {
       position = pos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_xu_huong_thoi_trang, container, false);
        ImageView imageView = (ImageView) root.findViewById(R.id.head_image_fragment_xu_huong_thoi_trang);
        TextView tvDescription = (TextView) root.findViewById(R.id.description_textview_item_set_do);
        RecyclerView listSetDo = (RecyclerView) root.findViewById(R.id.list_set_do_fragment_xu_huong_thoi_trang);
        RecyclerView listSanPham = (RecyclerView) root.findViewById(R.id.list_san_pham_fragment_xu_huong_thoi_trang);

        //Data test
        loadDataTest();

        //Set Ui
        imageView.setImageResource(listDataBanner[position]);

        listSetDo.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        listSetDo.setAdapter(new ListSetDoAdapter(getActivity()));
        listSanPham.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        listSanPham.setAdapter(new ListSanPhamAdapter(getActivity()));
        return root;
    }

    public void loadDataTest(){
        TypedArray tempListBanner = getActivity().getResources().obtainTypedArray(R.array.arr_hinh);
        listDataBanner = new int[3];
        for (int i = 0; i < 3; i++) {
            listDataBanner[i] = tempListBanner.getResourceId(i, -1);
        }
    }
}
