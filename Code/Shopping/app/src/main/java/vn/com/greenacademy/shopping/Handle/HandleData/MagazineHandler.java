package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine.AdapterMagazineRecyclerView;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine.AdapterMagazineViewPager;
import vn.com.greenacademy.shopping.Interface.MagazineCallBack;
import vn.com.greenacademy.shopping.Interface.MagazineTypeCallBack;
import vn.com.greenacademy.shopping.Model.Item_recyclerView_magazine;
import vn.com.greenacademy.shopping.Model.Magazine;
import vn.com.greenacademy.shopping.Model.MagazineType;
import vn.com.greenacademy.shopping.Network.AsynTask.GetMagazine;
import vn.com.greenacademy.shopping.Network.AsynTask.GetMagazineType;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 7/23/2017.
 */

public class MagazineHandler implements MagazineCallBack, MagazineTypeCallBack {

    Activity activity;
    View.OnClickListener onClickListener;
    ViewPager viewPager;
    TabLayout tabLayout;
    RecyclerView recyclerView;
    int positionViewPagger;
    ArrayList<MagazineType> magazineTypeArrayList;

    public MagazineHandler(Activity activity) {
        this.activity = activity;
    }

    public void clickItem(RecyclerView recyclerView){
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, String.valueOf(((Item_recyclerView_magazine)v.getTag()).getId())
                        + " " + ((Item_recyclerView_magazine)v.getTag()).getName() ,Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void setLayoutRecyclerView(RecyclerView recyclerView, int positionViewPagger, ArrayList<MagazineType> magazineTypeArrayList) {
//        this.recyclerView = recyclerView;
//        this.positionViewPagger = positionViewPagger;
//        this.magazineTypeArrayList = magazineTypeArrayList;
//
//        GetMagazine getMagazine = new GetMagazine(this);
//        getMagazine.execute(ServerUrl.UrlDanhSachMagazine+magazineTypeArrayList.get(positionViewPagger).getLoaiTapChi());

        AdapterMagazineRecyclerView adapterMagazineRecyclerView = new AdapterMagazineRecyclerView
                (activity,onClickListener,positionViewPagger, magazineTypeArrayList);
        recyclerView.setAdapter(adapterMagazineRecyclerView);
    }

    @Override
    public void magazineCallBack(ArrayList<Magazine> magazineArrayList) {
        //        AdapterMagazineRecyclerView adapterMagazineRecyclerView = new AdapterMagazineRecyclerView
//                (activity,onClickListener,magazineArrayList);
//        recyclerView.setAdapter(adapterMagazineRecyclerView);
    }

    @Override
    public void magazineTypeCallBack(ArrayList<MagazineType> magazineTypes) {
        AdapterMagazineViewPager adapter = new AdapterMagazineViewPager(
                ((AppCompatActivity)activity).getSupportFragmentManager(),activity,magazineTypes.get(0).getTen(), magazineTypes);
        viewPager.setAdapter(adapter);

        for (int i = 0; i < adapter.getCount(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(adapter.getTitle(i)));
        }
    }


    public void getMagazineType() {
        GetMagazineType getMagazineType = new GetMagazineType(this);
        getMagazineType.execute(ServerUrl.UrlDanhSachMagazineType);
    }

    public void setLayoutMagazineFragment(final ViewPager viewPager, TabLayout tabLayout) {
        this.viewPager = viewPager;
        this.tabLayout = tabLayout;

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // set su kien scroll cho tabLayout
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        // set su kien click tablayout thay doi viewPagger
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // thay doi view pager khi click vao tab layout
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
