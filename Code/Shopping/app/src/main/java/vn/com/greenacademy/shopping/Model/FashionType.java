package vn.com.greenacademy.shopping.Model;

/**
 * Created by ADMIN on 7/22/2017.
 */

public class FashionType {
    String loaiThoiTrang;
    String LinkHinh;
    String Ten;

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getLoaiThoiTrang() {
        return loaiThoiTrang;
    }

    public void setLoaiThoiTrang(String loaiThoiTrang) {
        this.loaiThoiTrang = loaiThoiTrang;
    }

    public String getLinkHinh() {
        return LinkHinh;
    }

    public void setLinkHinh(String linkHinh) {
        LinkHinh = linkHinh;
    }
}
