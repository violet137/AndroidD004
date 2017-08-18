package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.GioHang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPhamGioHang;

/**
 * Created by zzzzz on 8/12/2017.
 */

public class ParseGioHangSharedPreferences {
    private String data;

    public ParseGioHangSharedPreferences(String data){
        this.data = data;
    }

    public ArrayList<SanPhamGioHang> parseData(){
        if (data != null) {
            ArrayList<SanPhamGioHang> result = new ArrayList<>();
            try {
                JSONArray root = new JSONArray(data);
                for (int i = 0; i < root.length(); i++) {
                    JSONObject objSanPham = root.getJSONObject(i);
                    SanPhamGioHang sanPham = new ParseSanPhamGioHang(objSanPham.toString()).parData();
                    result.add(sanPham);
                }
                return result;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }
        return null;
    }
}
