package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.GioHang;

import org.json.JSONException;
import org.json.JSONObject;

import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPhamGioHang;

/**
 * Created by ADMIN on 8/3/2017.
 */

public class ParseSanPhamGioHang {
    private String data;

    public ParseSanPhamGioHang(String data) {
        this.data=data;
    }

    public SanPhamGioHang parData(){
        SanPhamGioHang sanPham = new SanPhamGioHang();
        try {
            JSONObject objSanPham = new JSONObject(data);
            sanPham = new SanPhamGioHang();
            sanPham.setIdSanPham(objSanPham.getInt("IdSanPham"));
            sanPham.setTenSanPham(objSanPham.getString("TenSanPham"));
            sanPham.setMauSanPham(objSanPham.getString("Color"));
            sanPham.setSize(objSanPham.getString("Size"));
            sanPham.setSoLuong(objSanPham.getInt("SoLuong"));
            sanPham.setLinkHinh(objSanPham.getString("LinkAnh"));
            sanPham.setGiaGiam(objSanPham.getLong("GiaTien"));
            sanPham.setGiaGoc(objSanPham.getLong("GiaGoc"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sanPham;
    }
}
