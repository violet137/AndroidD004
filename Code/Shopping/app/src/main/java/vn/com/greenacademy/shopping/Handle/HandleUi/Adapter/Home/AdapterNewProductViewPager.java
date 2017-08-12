package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Home;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Home.NewProductFragmentViewPager;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

/**
 * Created by ADMIN on 8/8/2017.
 */

public class AdapterNewProductViewPager extends FragmentStatePagerAdapter {
    Activity activity;
    ArrayList<SanPham> sanPhamArrayList;

    public AdapterNewProductViewPager(FragmentManager fm, Activity activity,
                                ArrayList<SanPham> sanPhamArrayList) {
        super(fm);
        this.activity = activity;
        this.sanPhamArrayList = sanPhamArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return new NewProductFragmentViewPager(sanPhamArrayList,position);
    }

    @Override
    public int getCount() {
        // so luong viewPager tarr về dc cố định bằng 3 để đẹp layout
        return 3;
    }
}