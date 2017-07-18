package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Main.SanPham.SanPhamPagerFragment;
import vn.com.greenacademy.shopping.Model.SanPham;

/**
 * Created by zzzzz on 7/17/2017.
 */

public class SanPhamPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<SanPham> listSanPham;

    public SanPhamPagerAdapter(FragmentManager fm, ArrayList<SanPham> listSanPham) {
        super(fm);
        this.listSanPham = listSanPham;
    }

    @Override
    public Fragment getItem(int position) {
        return new SanPhamPagerFragment(position, listSanPham);
    }

    @Override
    public int getCount() {
        return listSanPham.size();
    }
}
