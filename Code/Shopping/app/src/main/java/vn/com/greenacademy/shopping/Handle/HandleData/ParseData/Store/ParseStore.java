package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.Home.FashionType;
import vn.com.greenacademy.shopping.Model.Store;

/**
 * Created by ADMIN on 8/3/2017.
 */

public class ParseStore {
    String data;
    public ParseStore (String data) {
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
