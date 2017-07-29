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

import vn.com.greenacademy.shopping.Interface.StoreCallBack;
import vn.com.greenacademy.shopping.Interface.UrlPhotoCallBack;
import vn.com.greenacademy.shopping.Model.BannerPhoto;
import vn.com.greenacademy.shopping.Model.FashionType;
import vn.com.greenacademy.shopping.Model.MenuPhoto;
import vn.com.greenacademy.shopping.Model.Store;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 7/27/2017.
 */

public class GetStore extends AsyncTask<String, Object, String> {
    StoreCallBack storeCallBack;

    public GetStore (StoreCallBack storeCallBack) {
        this.storeCallBack = storeCallBack;
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
        ParDataGetStore par = new ParDataGetStore(aVoid);
        storeCallBack.storeCallBack(par.parData());
    }
}
class ParDataGetStore {
    String data;
    public ParDataGetStore (String data) {
        this.data=data;
    }

    public ArrayList<Store> parData(){
        ArrayList<Store> result = new ArrayList<>();
        ArrayList<FashionType> temp;
        try {
            JSONObject root = new JSONObject(data);
            if (root.getInt("Status") == 1){
                JSONArray jsonArray = root.getJSONArray("CuaHangTranfers");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Store store = new Store();
                    store.setId(jsonObject.getInt("Id"));
                    store.setTenCuaHang(jsonObject.getString("TenCuaHang"));
                    store.setDiaChi(jsonObject.getString("DiaChi"));
                    store.setLat(jsonObject.getDouble("Lat"));
                    store.setLng(jsonObject.getDouble("Lng"));
                    store.setLinkAnh(jsonObject.getString("LinkAnh"));
                    store.setGioMoCua(jsonObject.getString("GioMoCua"));
                    store.setSoDienThoai(jsonObject.getString("SoDienThoai"));
                    store.setDanhGia(jsonObject.getLong("DanhGia"));
//
//                    ArrayList<String> stringArrayListLoaiThoiTrang = new ArrayList<>();
//                    JSONArray jsonArrayLoaiThoiTrang = jsonObject.getJSONArray("LoaiThoiTrang");
//                    for (int j = 0; j < jsonArrayLoaiThoiTrang.length(); j++) {
//                        String jsonObjectLoaiThoiTrang = jsonArrayLoaiThoiTrang.getString(j);
//
//                        stringArrayListLoaiThoiTrang.add(jsonObjectLoaiThoiTrang);
//                    }
//                    store.setLoaiThoiTrang(stringArrayListLoaiThoiTrang);
//                    JSONArray jsonArray = root.getJSONArray("LoaiThoiTrangTranfers");
//
                    temp = new ArrayList<>();
                    JSONArray jsonArrayFashionType = jsonObject.getJSONArray("LoaiThoiTrang");
                    for (int j = 0; j < jsonArrayFashionType.length(); j++) {
                        JSONObject jsonObjectFashionType = jsonArrayFashionType.getJSONObject(j);
                        FashionType fashionType = new FashionType();
                        fashionType.setTen(jsonObjectFashionType.getString("Ten"));
                        fashionType.setLoaiThoiTrang(jsonObjectFashionType.getString("loaiThoiTrang"));
                        temp.add(fashionType);
                    }
                    store.setLoaiThoiTrang(temp);


                    result.add(store);
                }
            }
//            result.setDescription(root.getString("Description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
