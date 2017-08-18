package vn.com.greenacademy.shopping.Model.ThongTinSanPham;

import java.util.ArrayList;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class SanPhamGioHang {
    private long idSanPham;
    private String tenSanPham;
    private String mauSanPham;
    private String size;
    private int soLuong;
    private String linkHinh;
    private long Gia;

    public SanPhamGioHang(){}

    public SanPhamGioHang(long idSanPham, String tenSanPham, String mauSanPham, String size, int soLuong, String linkHinh, long gia) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.mauSanPham = mauSanPham;
        this.size = size;
        this.soLuong = soLuong;
        this.linkHinh = linkHinh;
        Gia = gia;
    }

    public long getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(long idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMauSanPham() {
        return mauSanPham;
    }

    public void setMauSanPham(String mauSanPham) {
        this.mauSanPham = mauSanPham;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getLinkHinh() {
        return linkHinh;
    }

    public void setLinkHinh(String linkHinh) {
        this.linkHinh = linkHinh;
    }

    public long getGia() {
        return Gia;
    }

    public void setGia(long gia) {
        Gia = gia;
    }
}