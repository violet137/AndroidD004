package vn.com.greenacademy.shopping.Handle.HandleData.Magazine;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Magazine.MagazineDetailFragment;
import vn.com.greenacademy.shopping.Fragment.Magazine.MagazineFragment;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine.AdapterMagazineRecyclerView;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine.AdapterMagazineViewPager;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Magazine.ParseMagazine;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Magazine.ParseMagazineType;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Model.Magazine.Item_recyclerView_magazine;
import vn.com.greenacademy.shopping.Model.Magazine.Magazine;
import vn.com.greenacademy.shopping.Model.Magazine.MagazineType;
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
//    ViewPager viewPager;
//    TabLayout tabLayout;
    RecyclerView recyclerView;
    int positionViewPagger;


    public MagazineHandler(Activity activity) {
        this.activity = activity;
    }

    public void clickItem(){
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseFragment baseFragment = new BaseFragment(((AppCompatActivity)activity), ((AppCompatActivity)activity).getSupportFragmentManager());
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

        GetServerData getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhSachMagazine+magazineTypeArrayList.get(positionViewPagger).getLoaiTapChi(),SupportKeyList.Magazine);


    }

    public void getMagazineType() {
        GetServerData getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhSachMagazineType, SupportKeyList.MagazineType);
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

        MagazineFragment.objectCallBack.callBack(magazineTypeArrayList,0);

    }

    public AdapterMagazineViewPager getAdapterMagazineType(FragmentManager fm,ArrayList<MagazineType> magazineTypeArrayList){

        AdapterMagazineViewPager adapter = new AdapterMagazineViewPager
                (fm, activity,magazineTypeArrayList.get(0).getTen(), magazineTypeArrayList);

        MagazineFragment.objectCallBack.callBack(adapter,1);

        return adapter;
    }

}
