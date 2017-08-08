package vn.com.greenacademy.shopping.Handle.HandleData.Magazine;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Magazine.MagazineDetailFragment;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine.AdapterMagazineRecyclerView;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine.AdapterMagazineViewPager;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Magazine.ParseMagazine;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Magazine.ParseMagazineType;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Model.Item_recyclerView_magazine;
import vn.com.greenacademy.shopping.Model.Magazine;
import vn.com.greenacademy.shopping.Model.MagazineType;
import vn.com.greenacademy.shopping.Network.AsynTask.GetServerData;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by ADMIN on 7/23/2017.
 */

public class MagazineHandler implements ServerCallBack {

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

    public void clickItem(){
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseFragment baseFragment = new BaseFragment(((AppCompatActivity)activity).getSupportFragmentManager());
                baseFragment.ChuyenFragment(new MagazineDetailFragment(String.valueOf(((Item_recyclerView_magazine)v.getTag())
                        .getId())), SupportKeyList.TAG_FRAGMENT_MAGAZINE_DETAIL, true);

//                Toast.makeText(activity, String.valueOf(((Item_recyclerView_magazine)v.getTag()).getId())
//                        + " " + ((Item_recyclerView_magazine)v.getTag()).getName() ,Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void setLayoutRecyclerView(RecyclerView recyclerView, int positionViewPagger, ArrayList<MagazineType> magazineTypeArrayList) {
        this.recyclerView = recyclerView;
        this.positionViewPagger = positionViewPagger;
        this.magazineTypeArrayList = magazineTypeArrayList;

//        GetMagazine getMagazine = new GetMagazine(this);
//        getMagazine.execute(ServerUrl.UrlDanhSachMagazine+magazineTypeArrayList.get(positionViewPagger).getLoaiTapChi());

        GetServerData getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhSachMagazine+magazineTypeArrayList.get(positionViewPagger).getLoaiTapChi(),SupportKeyList.Magazine);


//        AdapterHomeRecyclerView adapterMagazineRecyclerView = new AdapterHomeRecyclerView
//                (activity,onClickListener,positionViewPagger, magazineTypeArrayList);
//        recyclerView.setAdapter(adapterMagazineRecyclerView);
    }

//    @Override
//    public void magazineCallBack(ArrayList<Magazine> magazineArrayList) {
//
//    }

//    @Override
//    public void magazineTypeCallBack(ArrayList<MagazineType> magazineTypes) {
//
//    }


    public void getMagazineType() {
//        GetMagazineType getMagazineType = new GetMagazineType(this);
//        getMagazineType.execute(ServerUrl.UrlDanhSachMagazineType);

        GetServerData getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhSachMagazineType, SupportKeyList.MagazineType);
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

    @Override
    public void serverCallBack(String dataServer) {
    }


    @Override
    public void serverCallBack(String dataServer, String key) {
        switch (key){
            case SupportKeyList.Magazine:
                ParseMagazine parseMagazine = new ParseMagazine(dataServer);
                containerMagazine(parseMagazine.parData());
                break;
            case SupportKeyList.MagazineType:
                ParseMagazineType parseMagazineType = new ParseMagazineType(dataServer);
                containerMagazineType(parseMagazineType.parData());
                break;
        }
    }

    private void containerMagazine(ArrayList<Magazine> magazines) {
        AdapterMagazineRecyclerView adapterMagazineRecyclerView = new AdapterMagazineRecyclerView
                (activity,onClickListener,magazines);
        recyclerView.setAdapter(adapterMagazineRecyclerView);
    }

    private void containerMagazineType(ArrayList<MagazineType> magazineTypeArrayList) {
        AdapterMagazineViewPager adapter = new AdapterMagazineViewPager(
                ((AppCompatActivity)activity).getSupportFragmentManager(),
                activity,magazineTypeArrayList.get(0).getTen(), magazineTypeArrayList);

        viewPager.setAdapter(adapter);

        for (int i = 0; i < adapter.getCount(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(adapter.getTitle(i)));
        }
    }
}
