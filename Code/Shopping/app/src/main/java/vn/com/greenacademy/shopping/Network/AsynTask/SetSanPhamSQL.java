package vn.com.greenacademy.shopping.Network.AsynTask;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main.ParseDanhMucSP;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main.ParseMyProducts;
import vn.com.greenacademy.shopping.Handle.HandleData.SQLite.MySQLite;
import vn.com.greenacademy.shopping.Interface.ObjectCallBack;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Model.DanhMuc.DanhMucSP;
import vn.com.greenacademy.shopping.Model.Home.FashionType;
import vn.com.greenacademy.shopping.Model.Home.MenuPhoto;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.HinhSanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 8/20/2017.
 */

public class SetSanPhamSQL extends Thread{

    MySQLite mySQLite;
    Context context;
    ServerCallBack serverCallBack;

    public SetSanPhamSQL(Context context){
        this.context = context;
    }

    @Override
    public void run() {
        super.run();

        final ObjectCallBack objectCallBack = new ObjectCallBack() {
            @Override
            public void callBack(Object object, int flag) {
                switch (flag){
                    case SupportKeyList.ClickHome_Products:
                        MenuPhoto menuPhoto = (MenuPhoto) object;
                        for (int i = 0; i < menuPhoto.getFashionTypeArrayList().size(); i++) {
                            GetServerData getServerData = new GetServerData(serverCallBack);
                            getServerData.execute(ServerUrl.UrlDanhMucSP+menuPhoto.getFashionTypeArrayList().get(i).getLoaiThoiTrang());
                        }
                        break;
                }

            }
        };

        serverCallBack = new ServerCallBack() {
            @Override
            public void serverCallBack(String dataServer) {
                ParseDanhMucSP parseDanhMucSP = new ParseDanhMucSP(dataServer);
                DanhMucSP danhMucSP = parseDanhMucSP.parData();
                for (int i = 0; i < danhMucSP.getMucSanPhamArrayList().size(); i++) {
                    mySQLite = new MySQLite(context);
                    SanPham sanPham = new SanPham();
                    sanPham.setIdSanPham(danhMucSP.getMucSanPhamArrayList().get(i).getId());
                    sanPham.setTenSanPham(danhMucSP.getMucSanPhamArrayList().get(i).getTenDanhMuc());
                    mySQLite.setSanpham(sanPham);
                }
            }

            @Override
            public void serverCallBack(String dataServer, String key) {
                switch (Integer.parseInt(key)){
                    case SupportKeyList.Products_Url:
                        ParseMyProducts parseMyProducts = new ParseMyProducts(dataServer);
                        objectCallBack.callBack(parseMyProducts.parData(),SupportKeyList.ClickHome_Products);
                        break;
                }
            }
        };

        GetServerData getServerData = new GetServerData(serverCallBack);
        getServerData.execute(ServerUrl.UrlDanhSachThoiTrang, String.valueOf(SupportKeyList.Products_Url));

    }

}
