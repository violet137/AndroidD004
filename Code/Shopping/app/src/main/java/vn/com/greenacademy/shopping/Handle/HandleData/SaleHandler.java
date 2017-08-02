package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Sale.SaleFragment;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.AdapterSale;
import vn.com.greenacademy.shopping.Interface.SaleCallBack;
import vn.com.greenacademy.shopping.Model.Sale;
import vn.com.greenacademy.shopping.Network.AsynTask.GetSale;
import vn.com.greenacademy.shopping.Util.ServerUrl;

/**
 * Created by ADMIN on 8/1/2017.
 */

public class SaleHandler extends LoadDataSaleHandler implements View.OnClickListener{
    Activity activity;

    public SaleHandler(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Sale sale = (Sale) v.getTag();
        Toast.makeText(activity,String.valueOf(sale.getId()),Toast.LENGTH_SHORT).show();
    }

    public AdapterSale getAdapter(ArrayList<Sale> saleArrayList){
        AdapterSale adapterSale = new AdapterSale(activity,this,saleArrayList);
        return adapterSale;
    }
}

class LoadDataSaleHandler implements SaleCallBack{

    Activity activity;

    public LoadDataSaleHandler(Activity activity) {
        this.activity = activity;
    }

    public void getDataServer() {
        GetSale getSale = new GetSale(this);
        getSale.execute(ServerUrl.UrlKhuyenMai);
    }

    @Override
    public void saleCallBack(ArrayList<Sale> saleArrayList) {
        SaleFragment.saleCallBack.saleCallBack(saleArrayList);
    }
}
