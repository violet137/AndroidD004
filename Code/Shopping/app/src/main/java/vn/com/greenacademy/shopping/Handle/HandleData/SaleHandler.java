package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Sale.SaleFragment;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale.AdapterSale;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Sale.ParseSale;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Model.Sale;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Network.AsynTask.GetServerData;
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

    public AdapterSale getAdapter(FragmentManager fm,ArrayList<Sale> saleArrayList){

        AdapterSale adapterSale = new AdapterSale(fm,activity,this,saleArrayList);

        return adapterSale;
    }
}

class LoadDataSaleHandler implements ServerCallBack{

    Activity activity;

    public LoadDataSaleHandler(Activity activity) {
        this.activity = activity;
    }

    public void getDataServer() {
        GetServerData getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlKhuyenMai);


    }

    @Override
    public void serverCallBack(String dataServer) {
        ParseSale parseSale = new ParseSale(dataServer);
        containerData(parseSale.parData());
    }

    private void containerData(ArrayList<Sale> saleArrayList) {
        SaleFragment.saleCallBack.saleCallBack(saleArrayList);
    }

    @Override
    public void serverCallBack(String dataServer, String key) {

    }
}
