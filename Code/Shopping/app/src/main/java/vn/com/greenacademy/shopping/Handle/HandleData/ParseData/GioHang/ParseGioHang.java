package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.GioHang;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Product.ParseSanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPhamGioHang;

/**
 * Created by zzzzz on 8/12/2017.
 */

public class ParseGioHang {
    private String data;

    public ParseGioHang(String data){
        this.data = data;
    }

    public ArrayList<SanPhamGioHang> parseData(){
        ArrayList<SanPhamGioHang> result = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(data);
            for (int i = 0; i < root.getJSONObject("GioHangTranfer").getJSONArray("DanhSachSanPham").length(); i++) {
                JSONObject objSanPham = root.getJSONObject("GioHangTranfer").getJSONArray("DanhSachSanPham").getJSONObject(i);
                SanPhamGioHang sanPham = new ParseSanPhamGioHang(objSanPham.toString()).parData();
                result.add(sanPham);
            }
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
