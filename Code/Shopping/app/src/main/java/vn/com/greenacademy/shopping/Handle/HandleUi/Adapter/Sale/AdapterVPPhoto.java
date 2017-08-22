package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Sale.SalePhotoFragment;
import vn.com.greenacademy.shopping.Fragment.Sale.SaleProductFragmentViewPager;
import vn.com.greenacademy.shopping.Model.Sale;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

/**
 * Created by ADMIN on 8/23/2017.
 */

public class AdapterVPPhoto extends FragmentStatePagerAdapter {
    Activity activity;
    ArrayList<Sale> saleArrayList;

    public AdapterVPPhoto(FragmentManager fm, Activity activity,
                                ArrayList<Sale> saleArrayList) {
        super(fm);
        this.activity = activity;
        this.saleArrayList = saleArrayList;
    }

    @Override
    public Fragment getItem(int position) {

        return new SalePhotoFragment(saleArrayList.get(position));
    }

    @Override
    public int getCount() {
       return saleArrayList.size();
    }
}
