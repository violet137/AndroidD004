package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Sale.SaleProductFragmentViewPager;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

/**
 * Created by ADMIN on 8/2/2017.
 */

public class AdapterViewPagerSale extends FragmentPagerAdapter {
    Activity activity;
    ArrayList<SanPham> sanPhamArrayList;

    public AdapterViewPagerSale(FragmentManager fm, Activity activity,
                                ArrayList<SanPham> sanPhamArrayList) {
        super(fm);
        this.activity = activity;
        this.sanPhamArrayList = sanPhamArrayList;
    }

//    public AdapterViewPagerSale(FragmentManager fm, Activity activity) {
//        super(fm);
//        this.activity = activity;
//    }

    @Override
    public Fragment getItem(int position) {
        return new SaleProductFragmentViewPager(sanPhamArrayList, position);
//        return new MagazineRecyclerViewFragment(position, sanPhamArrayList);
    }

    @Override
    public int getCount() {
        if (sanPhamArrayList.size() < 5){
            return 1;
        } else  if (sanPhamArrayList.size() < 8){
            return 2;
        }else{
            return 3;
        }
    }

}