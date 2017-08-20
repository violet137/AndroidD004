package vn.com.greenacademy.shopping.Network.AsynTask;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.SQLite.MySQLite;
import vn.com.greenacademy.shopping.Interface.ObjectCallBack;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Model.Home.FashionType;
import vn.com.greenacademy.shopping.Model.Home.MenuPhoto;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.HinhSanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Util.ServerUrl;

/**
 * Created by ADMIN on 8/20/2017.
 */

public class SetSanPhamSQL extends Thread{

    MySQLite mySQLite;
    Context context;

    public SetSanPhamSQL(Context context){
        this.context = context;
    }

    @Override
    public void run() {
        super.run();

        final ObjectCallBack objectCallBack = new ObjectCallBack() {
            @Override
            public void callBack(Object object, int flag) {
                ArrayList<SanPham> sanPhamArrayList = (ArrayList<SanPham>) object;
                for (int i = 0; i < sanPhamArrayList.size() ; i++) {
                    mySQLite = new MySQLite(context);
                    mySQLite.setSanpham(sanPhamArrayList.get(i));
                }
            }
        };

        ServerCallBack serverCallBack = new ServerCallBack() {
            @Override
            public void serverCallBack(String dataServer) {
                objectCallBack.callBack(parData(dataServer),0);
            }

            @Override
            public void serverCallBack(String dataServer, String key) {

            }
        };

        GetServerData getServerData = new GetServerData(serverCallBack);
        getServerData.execute(ServerUrl.UrlCauHoiTG);

    }

    public ArrayList<SanPham> parData(String data){
        ArrayList<SanPham> result = new ArrayList<>();
        SanPham sanPham;
        try {
            JSONObject root = new JSONObject(data);
            if (root.getInt("Status") == 1){

                JSONArray jsonArray = root.getJSONArray("SanPhamTranfers");
                for (int i = 0; i < jsonArray.length() ; i++) {
                    JSONObject objSanPham = jsonArray.getJSONObject(i);

                    sanPham = new SanPham();
                    sanPham.setIdSanPham(objSanPham.getInt("Id"));
                    sanPham.setTenSanPham(objSanPham.getString("Ten"));

                    result.add(sanPham);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

}
