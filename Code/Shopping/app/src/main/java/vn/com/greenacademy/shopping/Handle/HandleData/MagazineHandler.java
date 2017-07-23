package vn.com.greenacademy.shopping.Handle.HandleData;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine.AdapterMagazineRecyclerView;
import vn.com.greenacademy.shopping.Model.Item_recyclerView_magazine;

/**
 * Created by ADMIN on 7/23/2017.
 */

public class MagazineHandler {

    Context context;
    View.OnClickListener onClickListener;

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

    public void displayView(RecyclerView recyclerView, int positionViewPagger) {
        AdapterMagazineRecyclerView adapterMagazineRecyclerView = new AdapterMagazineRecyclerView(context,onClickListener,positionViewPagger);
        recyclerView.setAdapter(adapterMagazineRecyclerView);
    }
}
