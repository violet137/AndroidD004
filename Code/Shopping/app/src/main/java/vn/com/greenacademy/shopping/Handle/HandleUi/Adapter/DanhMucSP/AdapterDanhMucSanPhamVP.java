package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.DanhMucSP;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.DanhMuc.MucSanPham;

/**
 * Created by ADMIN on 8/13/2017.
 */

public class AdapterDanhMucSanPhamVP extends FragmentStatePagerAdapter {
    Activity activity;
    ArrayList<MucSanPham> mucSanPhamArrayList;

    public AdapterDanhMucSanPhamVP(FragmentManager fm, Activity activity,
                                      ArrayList<MucSanPham> mucSanPhamArrayList) {
        super(fm);
        this.activity = activity;
        this.mucSanPhamArrayList = mucSanPhamArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return new Fragment();
//        return new HotProductViewPagerFragment();
    }

    @Override
    public int getCount() {
        // so luong viewPager tarr về dc cố định bằng 3 để đẹp layout
        return 3;
    }
}