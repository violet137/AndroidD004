package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Main.DanhMucSanPham.DanhMucSPFragment;
import vn.com.greenacademy.shopping.Fragment.Main.DanhMucSanPham.ChiTietDanhMucFragment;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.DanhMucSP.AdapterDanhMucSP;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main.ParseDanhMucSP;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Model.DanhMucSP;
import vn.com.greenacademy.shopping.Model.MucSanPham;
import vn.com.greenacademy.shopping.Network.AsynTask.GetServerData;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by ADMIN on 7/31/2017.
 */

public class DanhMucSPHandler extends LoadDataDanhMucSPHandler implements View.OnClickListener{
    private Activity activity;

    public DanhMucSPHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        BaseFragment baseFragment = new BaseFragment(activity,((AppCompatActivity)activity).getSupportFragmentManager());
        MucSanPham mucSanPham = (MucSanPham) v.getTag();
        Toast.makeText(activity, String.valueOf(mucSanPham.getId()), Toast.LENGTH_SHORT).show();
        baseFragment.ChuyenFragment(ChiTietDanhMucFragment.newInstance(mucSanPham.getId(), mucSanPham.getTenDanhMuc()), SupportKeyList.TAG_CHI_TIET_DANH_MUC_SAN_PHAM, true);
    }

    public AdapterDanhMucSP displayListView(ArrayList<MucSanPham> arrayList){
        AdapterDanhMucSP adapterDanhMucSP = new AdapterDanhMucSP(activity, R.layout.item_danh_muc_san_pham, arrayList,this);
        return adapterDanhMucSP;
    }


}

class LoadDataDanhMucSPHandler implements ServerCallBack{

    ArrayList<MucSanPham> arrayList;

    public void getDataServer (String loaiSP){

        GetServerData getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhMucSP+loaiSP);
    }

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