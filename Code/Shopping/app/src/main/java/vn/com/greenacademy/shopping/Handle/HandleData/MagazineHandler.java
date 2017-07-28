package vn.com.greenacademy.shopping.Handle.HandleData;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine.AdapterMagazineRecyclerView;
import vn.com.greenacademy.shopping.Interface.MagazineCallBack;
import vn.com.greenacademy.shopping.Model.Item_recyclerView_magazine;
import vn.com.greenacademy.shopping.Model.Magazine;
import vn.com.greenacademy.shopping.Model.MagazineType;
import vn.com.greenacademy.shopping.Network.AsynTask.GetMagazine;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 7/23/2017.
 */

public class MagazineHandler implements MagazineCallBack {

    Context context;
    View.OnClickListener onClickListener;
    RecyclerView recyclerView;
    int positionViewPagger;
    ArrayList<MagazineType> magazineTypeArrayList;

    public MagazineHandler(Context context) {
        this.context = context;
    }

    public void clickItem(RecyclerView recyclerView){
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, String.valueOf(((Item_recyclerView_magazine)v.getTag()).getId())
                        + " " + ((Item_recyclerView_magazine)v.getTag()).getName() ,Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void setLayout(RecyclerView recyclerView, int positionViewPagger, ArrayList<MagazineType> magazineTypeArrayList) {
//        this.recyclerView = recyclerView;
//        this.positionViewPagger = positionViewPagger;
//        this.magazineTypeArrayList = magazineTypeArrayList;
//
//        GetMagazine getMagazine = new GetMagazine(this);
//        getMagazine.execute(ServerUrl.UrlDanhSachMagazine);

        AdapterMagazineRecyclerView adapterMagazineRecyclerView = new AdapterMagazineRecyclerView
                (context,onClickListener,positionViewPagger, magazineTypeArrayList);
        recyclerView.setAdapter(adapterMagazineRecyclerView);
    }

    @Override
    public void magazineCallBack(ArrayList<Magazine> magazineArrayList) {
        AdapterMagazineRecyclerView adapterMagazineRecyclerView = new AdapterMagazineRecyclerView
                (context,onClickListener,positionViewPagger,magazineArrayList, magazineTypeArrayList);
        recyclerView.setAdapter(adapterMagazineRecyclerView);
    }
}
