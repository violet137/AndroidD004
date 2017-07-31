package vn.com.greenacademy.shopping.Network.AsynTask;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import vn.com.greenacademy.shopping.Interface.DanhMucSPCallBack;
import vn.com.greenacademy.shopping.Interface.StoreCallBack;
import vn.com.greenacademy.shopping.Model.DanhMucSP;
import vn.com.greenacademy.shopping.Model.FashionType;
import vn.com.greenacademy.shopping.Model.MucSanPham;
import vn.com.greenacademy.shopping.Model.Store;

/**
 * Created by ADMIN on 7/31/2017.
 */

public class GetDanhMucSP extends AsyncTask<String, Object, String> {
    DanhMucSPCallBack danhMucSPCallBack;

    public GetDanhMucSP (DanhMucSPCallBack danhMucSPCallBack) {
        this.danhMucSPCallBack = danhMucSPCallBack;
    }
    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // sever tra du lieu ve kiei xml
            connection.addRequestProperty("Accept", "application/json");
            // phuong thuc truyen len sever
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int leght;
                while ((leght = inputStream.read(buffer)) != -1) {
                    result.write(buffer, 0, leght);
                }
                return result.toString("UTF-8");

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "false";
    }

    @Override
    protected void onPostExecute(String aVoid) {
        ParDataGetDanhMucSP par = new ParDataGetDanhMucSP(aVoid);
        danhMucSPCallBack.danhMucCallBack(par.parData());
    }
}
class ParDataGetDanhMucSP {
    String data;
    public ParDataGetDanhMucSP (String data) {
        this.data=data;
    }

    public DanhMucSP parData(){
        DanhMucSP result = new DanhMucSP();
        ArrayList<MucSanPham> temp = new ArrayList<>();
        try {
                JSONObject root = new JSONObject(data);
            if (root.getInt("Status") == 1){
                JSONArray jsonArray = root.getJSONObject("DanhMucHangTranfers").getJSONArray("DanhMucList");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    MucSanPham mucSanPham = new MucSanPham();
                    mucSanPham.setId(jsonObject.getInt("Id"));
                    mucSanPham.setLoaiThoiTrang(jsonObject.getString("LoaiThoiTrang"));
                    mucSanPham.setTenDanhMuc(jsonObject.getString("TenDanhMuc"));

                    temp.add(mucSanPham);
                }
                result.setMucSanPhamArrayList(temp);
                result.setXuHuongTtrangId(root.getJSONObject("DanhMucHangTranfers").getInt("XuHuongTtrangId"));
                result.setLoaiThoiTrang(root.getJSONObject("DanhMucHangTranfers").getString("LoaiThoiTrang"));
                result.setXuHuongTtrangLink(root.getJSONObject("DanhMucHangTranfers").getString("XuHuongTtrangLink"));
            }
//            result.setDescription(root.getString("Description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}

