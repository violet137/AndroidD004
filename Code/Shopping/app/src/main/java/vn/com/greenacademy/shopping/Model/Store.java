package vn.com.greenacademy.shopping.Model;

import java.util.ArrayList;

/**
 * Created by ADMIN on 7/27/2017.
 */

public class Store {
    int Id;
    String TenCuaHang;
    String DiaChi;
    double Lat;
    double Lng;
    String LinkAnh;
    ArrayList<String> LoaiThoiTrang;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenCuaHang() {
        return TenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        TenCuaHang = tenCuaHang;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(double lng) {
        Lng = lng;
    }

    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        LinkAnh = linkAnh;
    }

    public ArrayList<String> getLoaiThoiTrang() {
        return LoaiThoiTrang;
    }

    public void setLoaiThoiTrang(ArrayList<String> loaiThoiTrang) {
        LoaiThoiTrang = loaiThoiTrang;
    }
}
