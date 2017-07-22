package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Main.Magazine.MagazineFragment;
import vn.com.greenacademy.shopping.Fragment.Main.Magazine.MagazineRecyclerViewFragment;

/**
 * Created by ADMIN on 7/22/2017.
 */

public class AdapterMagazineViewPager extends FragmentPagerAdapter {
    String magazineType;
    Context context;
    String[] title = {"ALL","FASHION","BEAUTY","CULTURE","VIDEO"};
    public AdapterMagazineViewPager(FragmentManager fm, Context context, String magazineType) {
        super(fm);
        this.magazineType = magazineType;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return new MagazineRecyclerViewFragment();
    }

    @Override
    public int getCount() {
        return 5;
    }

    public String getTitle (int position){
        return title[position];
    }
}