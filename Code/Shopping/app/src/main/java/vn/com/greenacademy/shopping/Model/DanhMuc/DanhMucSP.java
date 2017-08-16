package vn.com.greenacademy.shopping.Model.DanhMuc;

import java.util.ArrayList;

/**
 * Created by ADMIN on 7/31/2017.
 */

public class DanhMucSP {
    int xuHuongTtrangId;
    String xuHuongTtrangLink;
    String loaiThoiTrang;
    ArrayList<MucSanPham> mucSanPhamArrayList ;

    public int getXuHuongTtrangId() {
        return xuHuongTtrangId;
    }

    public void setXuHuongTtrangId(int xuHuongTtrangId) {
        this.xuHuongTtrangId = xuHuongTtrangId;
    }

    public String getXuHuongTtrangLink() {
        return xuHuongTtrangLink;
    }

    public void setXuHuongTtrangLink(String xuHuongTtrangLink) {
        this.xuHuongTtrangLink = xuHuongTtrangLink;
    }

    public String getLoaiThoiTrang() {
        return loaiThoiTrang;
    }

    public void setLoaiThoiTrang(String loaiThoiTrang) {
        this.loaiThoiTrang = loaiThoiTrang;
    }

    public ArrayList<MucSanPham> getMucSanPhamArrayList() {
        return mucSanPhamArrayList;
    }

    public void setMucSanPhamArrayList(ArrayList<MucSanPham> mucSanPhamArrayList) {
        this.mucSanPhamArrayList = mucSanPhamArrayList;
    }
}