package vn.com.greenacademy.shopping.Fragment.Main.SanPham;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham.SanPhamPagerAdapter;
import vn.com.greenacademy.shopping.Model.SanPham;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

import static vn.com.greenacademy.shopping.Util.SupportKeyList.TAG_DETAILS_SAN_PHAM;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongTinSanPhamFragment extends Fragment implements View.OnClickListener {
    private BaseFragment baseFragment;
    private ArrayList<SanPham> listSanPham = new ArrayList<>();
    private int position;

    public ThongTinSanPhamFragment(int position, ArrayList<SanPham> listSanPham) {
        this.listSanPham = listSanPham;
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_san_pham, container, false);
        final TextView tvTenVaMau = (TextView) root.findViewById(R.id.ten_va_mau_fragment_san_pham);
        final TextView tvSoLuong = (TextView) root.findViewById(R.id.so_luong_fragment_san_pham);
        ViewPager pagerSanPham = (ViewPager) root.findViewById(R.id.pager_fragment_san_pham);

        root.findViewById(R.id.button_info_san_pham).setOnClickListener(this);

        baseFragment = new BaseFragment(getActivity().getSupportFragmentManager());
        pagerSanPham.setAdapter(new SanPhamPagerAdapter(getActivity().getSupportFragmentManager(),listSanPham));
        pagerSanPham.setCurrentItem(position);
        pagerSanPham.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvTenVaMau.setText(listSanPham.get(position).getTenSanPham() + " - Khaki Green");
                tvSoLuong.setText(String.valueOf(position + 1) + "/" + String.valueOf(listSanPham.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tvTenVaMau.setText(listSanPham.get(position).getTenSanPham() + " - Khaki Green");
        tvSoLuong.setText(String.valueOf(position + 1) + "/" + String.valueOf(listSanPham.size()));
        return root;
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.button_info_san_pham)
//            new DetailsSanPhamFragment(getActivity()).show();
    }
}
