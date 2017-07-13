package vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class XuHuongThoiTrangFragment extends Fragment {
    private int position;

    //Data test
    private int[] listBanner;

    public XuHuongThoiTrangFragment(int pos) {
       position = pos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_xu_huong_thoi_trang, container, false);
        ImageView imageView = (ImageView) root.findViewById(R.id.head_image_fragment_xu_huong_thoi_trang);

        //Data test
        loadDataTest();

        imageView.setImageResource(listBanner[position]);
        return root;
    }

    public void loadDataTest(){
        TypedArray tempListBanner = getActivity().getResources().obtainTypedArray(R.array.arr_hinh);
        listBanner = new int[3];
        for (int i = 0; i < 3; i++) {
            listBanner[i] = tempListBanner.getResourceId(i, -1);
        }
    }
}
