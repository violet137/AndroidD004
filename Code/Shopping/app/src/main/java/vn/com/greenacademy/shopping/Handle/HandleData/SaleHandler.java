package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.AdapterSale;
import vn.com.greenacademy.shopping.Interface.SaleCallBack;
import vn.com.greenacademy.shopping.Model.Sale;
import vn.com.greenacademy.shopping.Network.AsynTask.GetSale;
import vn.com.greenacademy.shopping.Util.ServerUrl;

/**
 * Created by ADMIN on 8/1/2017.
 */

public class SaleHandler implements SaleCallBack, View.OnClickListener{
    Activity activity;
    RecyclerView recyclerView;

    public SaleHandler(Activity activity) {
        this.activity = activity;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;

        GetSale getSale = new GetSale(this);
        getSale.execute(ServerUrl.UrlKhuyenMai);

    }

    @Override
    public void saleCallBack(ArrayList<Sale> saleArrayList) {
        AdapterSale adapterSale = new AdapterSale(activity,this,saleArrayList);

        recyclerView.setLayoutManager(new GridLayoutManager(activity, 1));

        recyclerView.setAdapter(adapterSale);
    }

    @Override
    public void onClick(View v) {
        Sale sale = (Sale) v.getTag();
        Toast.makeText(activity,String.valueOf(sale.getId()),Toast.LENGTH_SHORT).show();
    }
}
