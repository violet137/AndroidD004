package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.DanhMucSanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Product.ParseSanPham;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.HinhSanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

/**
 * Created by zzzzz on 8/12/2017.
 */

public class ParseSanPhamChiTietDanhMuc {
    private String data;

    public ParseSanPhamChiTietDanhMuc(String data){
        this.data = data;
    }

    public ArrayList<SanPham> parseData(){
        ArrayList<SanPham> result = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(data);
            for (int i = 0; i < root.getJSONArray("SanPhamTranfers").length(); i++) {
                JSONObject objSanPham = root.getJSONArray("SanPhamTranfers").getJSONObject(i);
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
