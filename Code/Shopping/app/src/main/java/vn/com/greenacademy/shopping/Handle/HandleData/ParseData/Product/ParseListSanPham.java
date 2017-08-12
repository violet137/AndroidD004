package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

/**
 * Created by zzzzz on 8/12/2017.
 */

public class ParseListSanPham {
    private String data;

    public ParseListSanPham(String data){
        this.data = data;
    }

    public ArrayList<SanPham> parseData(){
        ArrayList<SanPham> result = new ArrayList<>();
        try {
            JSONArray jsonArraySanPham = new JSONArray(data);
            for (int i = 0; i < jsonArraySanPham.length(); i++) {
                JSONObject objSanPham = jsonArraySanPham.getJSONObject(i);
                SanPham sanPham = new ParseSanPham(objSanPham.toString()).parData();
                result.add(sanPham);
            }
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
