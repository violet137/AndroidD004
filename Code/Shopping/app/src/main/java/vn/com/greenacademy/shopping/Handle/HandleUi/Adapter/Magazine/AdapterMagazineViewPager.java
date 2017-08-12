package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Magazine.MagazineRecyclerViewFragment;
import vn.com.greenacademy.shopping.Model.MagazineType;

/**
 * Created by ADMIN on 7/22/2017.
 */

public class AdapterMagazineViewPager extends FragmentStatePagerAdapter {
    String magazineType;
    Activity activity;
    ArrayList<MagazineType> magazineTypeArrayList;

    public AdapterMagazineViewPager(FragmentManager fm, Activity activity,
                                    String magazineType, ArrayList<MagazineType> magazineTypeArrayList) {
        super(fm);
        this.magazineType = magazineType;
        this.activity = activity;
        this.magazineTypeArrayList = magazineTypeArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return new MagazineRecyclerViewFragment(position, magazineTypeArrayList);
    }


    @Override
    public int getCount() {
        return magazineTypeArrayList.size();
    }

    public String getTitle (int position){
        return magazineTypeArrayList.get(position).getTen();
    }
}