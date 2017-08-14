package vn.com.greenacademy.shopping.Handle.HandleData.DanhMucSanPham;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Main.DanhMucSanPham.ChiTietDanhMucFragment;
import vn.com.greenacademy.shopping.Fragment.Main.DanhMucSanPham.DanhMucSanPhamFragment;
import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.DanhMucSP.AdapterDanhMucSP;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main.ParseDanhMucSP;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.DanhMucSP.AdapterDanhMucSanPham;
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
    ImageLoad imageLoad;

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

    public void setPhoto(MucSanPham mucSanPham, ImageView ivPhoto) {
        imageLoad = new ImageLoad(activity);
        if (mucSanPham.getLinkAnh().equals("")){
            ivPhoto.setOnClickListener(ClickListenerDanhMucSanPham.onClickListener);

            imageLoad.load("https://lmt.com.vn/media/k2/items/cache/4251dec72b18ac89643edfb7a8300016_XL.jpg", ivPhoto);
        }else {
            ivPhoto.setTag(-1);
            ivPhoto.setOnClickListener(ClickListenerDanhMucSanPham.onClickListener);

            imageLoad.load(mucSanPham.getLinkAnh(), ivPhoto);
        }
    }

    public RecyclerView.Adapter getAdapterListDM(ArrayList<MucSanPham> object) {
        AdapterDanhMucSanPham adapter = new AdapterDanhMucSanPham(activity, object);
        return adapter;
    }
}

class LoadDataDanhMucSPHandler implements ServerCallBack{

    public void getDataServer (String loaiSP){
        GetServerData getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhMucSP+loaiSP, SupportKeyList.ListDanhMucSP);
    }

    public void getDataNewProductServer (String loaiSP){
        GetServerData getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhMucSP+loaiSP, SupportKeyList.ListDanhMucSP);
    }

    @Override
    public void serverCallBack(String dataServer) {
//        ParseDanhMucSP parseDanhMucSP = new ParseDanhMucSP(dataServer);
//        containerData(parseDanhMucSP.parData());
    }

    private void containerData(DanhMucSP danhMucSP) {
        MucSanPham newMucSanPham = new MucSanPham();
        newMucSanPham.setLinkAnh(danhMucSP.getXuHuongTtrangLink());
        newMucSanPham.setId(danhMucSP.getXuHuongTtrangId());
        newMucSanPham.setLoaiThoiTrang(danhMucSP.getLoaiThoiTrang());

        DanhMucSanPhamFragment.objectCallBack.callBack(
                newMucSanPham,SupportKeyList.ClickDanhMuc_Photo);

        DanhMucSanPhamFragment.objectCallBack.callBack(
                danhMucSP.getMucSanPhamArrayList(),SupportKeyList.ClickDanhMuc_Menu);
    }


    @Override
    public void serverCallBack(String dataServer, String key) {
        switch (key){
            case SupportKeyList.ListDanhMucSP:
                ParseDanhMucSP parseDanhMucSP = new ParseDanhMucSP(dataServer);
                containerData(parseDanhMucSP.parData());
                break;
            case SupportKeyList.ViewPagerDMSPMoi:
                break;
            default:
                Log.d("case severCallBack DanhMucSPHandler","lôi");
                break;
        }
    }
}