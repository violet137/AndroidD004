package vn.com.greenacademy.shopping.Handle.HandleData.Search;

import android.app.Activity;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.DanhMucSanPham.DanhMucSanPhamFragment;
import vn.com.greenacademy.shopping.Fragment.Search.SearchFragment;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main.ParseDanhMucSP;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Product.ParseNewProductList;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Search.AdapterLichSu;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Search.AdapterTopSanPhamXem;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Search.AdapterTopTuKhoaTim;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Model.DanhMuc.DanhMucSP;
import vn.com.greenacademy.shopping.Model.DanhMuc.MucSanPham;
import vn.com.greenacademy.shopping.Model.Home.MenuMain;
import vn.com.greenacademy.shopping.Model.Home.MenuPhoto;
import vn.com.greenacademy.shopping.Model.SlideMenu;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Network.AsynTask.GetServerData;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 8/17/2017.
 */

public class SearchHandler extends LoadDataSearchHandler {
    Activity activity;
    public static View.OnClickListener onClickListener;
    public static View.OnClickListener onClickListenerTopTuKhoa;

    public SearchHandler(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    public void click(){
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sanPham = (SanPham) v.getTag();
                Toast.makeText(activity,String.valueOf(sanPham.getIdSanPham()),Toast.LENGTH_SHORT).show();
            }
        };
        onClickListenerTopTuKhoa = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MucSanPham mucSanPham = (MucSanPham) v.getTag();
                Toast.makeText(activity,String.valueOf(mucSanPham.getId()),Toast.LENGTH_SHORT).show();
            }
        };
    }

    public RecyclerView.Adapter getAdapter(ArrayList<SanPham> dataTopXem) {
        AdapterTopSanPhamXem adapter = new AdapterTopSanPhamXem(activity,dataTopXem);
        return adapter;
    }

    public RecyclerView.Adapter getAdapterTopTim(ArrayList<MucSanPham> dataTopTim) {
        AdapterTopTuKhoaTim adapter = new AdapterTopTuKhoaTim(activity,dataTopTim);
        return adapter;
    }

    public RecyclerView.Adapter getAdapterLichSu(ArrayList<MucSanPham> dataLichSu) {
        AdapterLichSu adapter = new AdapterLichSu(activity, dataLichSu);
        return adapter;
    }
}

class LoadDataSearchHandler implements ServerCallBack {
    GetServerData getServerData;
    Activity activity;

    public LoadDataSearchHandler(Activity activity) {
        this.activity = activity;
    }

    public void getDataServer (){
        getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhSachSPMoi+"20", String.valueOf(SupportKeyList.NewProduct_Url));
    }

    // loadData la data cua recyclerView Top Tu Khoa Tim Kiem;
    public void loadData(){
        String []arrName = activity.getResources().getStringArray(R.array.name_top_search);
        String []arrMa = activity.getResources().getStringArray(R.array.id_top_search);

        ArrayList<MucSanPham> arrayList = new ArrayList<>();
        for(int i = 0; i< arrName.length; i++){
            MucSanPham mucSanPham = new MucSanPham();
            mucSanPham.setTenDanhMuc(arrName[i]);
            mucSanPham.setId(Integer.parseInt(arrMa[i]));
            arrayList.add(mucSanPham);
        }
        SearchFragment.objectCallBack.callBack(arrayList, Integer.parseInt(SupportKeyList.Search_TKHot));
    }

    public void loadLichSuSearch(){
        ArrayList<MucSanPham> arrayList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            MucSanPham mucSanPham = new MucSanPham();
            mucSanPham.setTenDanhMuc("");
            mucSanPham.setId(-1);
            arrayList.add(mucSanPham);
        }
        SearchFragment.objectCallBack.callBack(arrayList, Integer.parseInt(SupportKeyList.Search_LSSearch));
    }

    @Override
    public void serverCallBack(String dataServer) {

    }

    @Override
    public void serverCallBack(String dataServer, String key) {
        ParseNewProductList parseNewProductList = new ParseNewProductList(dataServer);
        containerData(parseNewProductList.parData(),SupportKeyList.Search_SPHot);
    }

    private void containerData(Object object, String key) {
        MenuPhoto menuPhoto = (MenuPhoto) object;
        SearchFragment.objectCallBack.callBack(menuPhoto.getSanPhamArrayList(), Integer.parseInt(key));
    }
}
