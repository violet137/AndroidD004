package vn.com.greenacademy.shopping.Model;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

/**
 * Created by ADMIN on 8/1/2017.
 */

public class Sale {
    int Id;
    String Ten;
    String HinhDaiDien;
    String Mota;
    ArrayList<Integer> listIDSanPham;
    ArrayList<SanPham> sanPhamArrayList;

    public ArrayList<SanPham> getSanPhamArrayList() {
        return sanPhamArrayList;
    }

    public void setSanPhamArrayList(ArrayList<SanPham> sanPhamArrayList) {
        this.sanPhamArrayList = sanPhamArrayList;
    }

    public ArrayList<Integer> getListIDSanPham() {
        return listIDSanPham;
    }

    public void setListIDSanPham(ArrayList<Integer> listIDSanPham) {
        this.listIDSanPham = listIDSanPham;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getHinhDaiDien() {
        return HinhDaiDien;
    }

    public void setHinhDaiDien(String hinhDaiDien) {
        HinhDaiDien = hinhDaiDien;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }
}


