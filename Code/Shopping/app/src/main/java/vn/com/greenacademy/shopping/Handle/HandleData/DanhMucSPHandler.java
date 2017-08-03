package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Main.DanhMucSanPham.DanhMucSPFragment;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.AdapterDanhMucSP;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main.ParseDanhMucSP;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Model.DanhMucSP;
import vn.com.greenacademy.shopping.Model.MucSanPham;
import vn.com.greenacademy.shopping.Network.AsynTask.GetServerData;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;

/**
 * Created by ADMIN on 7/31/2017.
 */

public class DanhMucSPHandler extends LoadDataDanhMucSPHandler implements View.OnClickListener{
    Activity activity;

    public DanhMucSPHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        MucSanPham mucSanPham = (MucSanPham) v.getTag();
        Toast.makeText(activity, String.valueOf(mucSanPham.getId()), Toast.LENGTH_SHORT).show();
    }

    public AdapterDanhMucSP displayListView(ArrayList<MucSanPham> arrayList){
        AdapterDanhMucSP adapterDanhMucSP = new AdapterDanhMucSP(activity, R.layout.item_danh_muc_san_pham, arrayList,this);
        return adapterDanhMucSP;
    }


}

class LoadDataDanhMucSPHandler implements ServerCallBack{

    ArrayList<MucSanPham> arrayList;

    public void getDataServer (String loaiSP){
//        GetDanhMucSP getDanhMucSP = new GetDanhMucSP(this);
//        getDanhMucSP.execute(ServerUrl.UrlDanhMucSP+loaiSP);

        GetServerData getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhMucSP+loaiSP);
    }

//    @Override
//    public void danhMucCallBack(DanhMucSP danhMucSP) {
//
//    }

    private void finishGetData(boolean flag) {
        if (flag){
            DanhMucSPFragment.mucSPCallBack.callBack(arrayList);
        }
    }

    @Override
    public void serverCallBack(String dataServer) {
        ParseDanhMucSP parseDanhMucSP = new ParseDanhMucSP(dataServer);
        containerData(parseDanhMucSP.parData());
    }

    private void containerData(DanhMucSP danhMucSP) {
        arrayList = new ArrayList<>();

        MucSanPham newMucSanPham = new MucSanPham();
        newMucSanPham.setLinkAnh(danhMucSP.getXuHuongTtrangLink());
        newMucSanPham.setId(danhMucSP.getXuHuongTtrangId());
        newMucSanPham.setLoaiThoiTrang(danhMucSP.getLoaiThoiTrang());
        arrayList.add(newMucSanPham);

        for (int i = 0; i < danhMucSP.getMucSanPhamArrayList().size(); i++) {
            arrayList.add(danhMucSP.getMucSanPhamArrayList().get(i));
        }

        finishGetData(true);
    }


    @Override
    public void serverCallBack(String dataServer, String key) {

    }
}