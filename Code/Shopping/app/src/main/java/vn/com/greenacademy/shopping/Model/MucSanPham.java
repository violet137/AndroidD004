package vn.com.greenacademy.shopping.Model;

/**
 * Created by ADMIN on 7/31/2017.
 */

public class MucSanPham {

    int id;
    String loaiThoiTrang;
    String tenDanhMuc;

    String linkAnh;

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoaiThoiTrang() {
        return loaiThoiTrang;
    }

    public void setLoaiThoiTrang(String loaiThoiTrang) {
        this.loaiThoiTrang = loaiThoiTrang;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }
}
