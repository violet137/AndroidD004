package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Magazine.MagazineRecyclerViewFragment;
import vn.com.greenacademy.shopping.Fragment.Sale.SaleProductFragment;
import vn.com.greenacademy.shopping.Model.MagazineType;
import vn.com.greenacademy.shopping.Model.SanPham;

/**
 * Created by ADMIN on 8/2/2017.
 */

public class AdapterViewPagerSale extends FragmentPagerAdapter {
    Activity activity;
    ArrayList<SanPham> sanPhamArrayList;

//    public AdapterViewPagerSale(FragmentManager fm, Activity activity,
//                                ArrayList<SanPham> sanPhamArrayList) {
//        super(fm);
//        this.activity = activity;
//        this.sanPhamArrayList = sanPhamArrayList;
//    }

    public AdapterViewPagerSale(FragmentManager fm, Activity activity) {
        super(fm);
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        return new SaleProductFragment();
//        return new MagazineRecyclerViewFragment(position, sanPhamArrayList);
    }

    @Override
    public int getCount() {
        return 3;
    }

}