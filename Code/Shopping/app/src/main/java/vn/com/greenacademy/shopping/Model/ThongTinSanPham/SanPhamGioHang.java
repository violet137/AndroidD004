package vn.com.greenacademy.shopping.Model.ThongTinSanPham;

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
    private long giaGiam;
    private long giaGoc;

    public SanPhamGioHang(){}

    public SanPhamGioHang(long idSanPham, String tenSanPham, String mauSanPham, String size, int soLuong, String linkHinh, long giaGiam, long giaGoc) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.mauSanPham = mauSanPham;
        this.size = size;
        this.soLuong = soLuong;
        this.linkHinh = linkHinh;
        this.giaGiam = giaGiam;
        this.giaGoc = giaGoc;
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

    public long getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(long giaGiam) {
        this.giaGiam = giaGiam;
    }

    public long getGiaGoc() {
        return giaGoc;
    }

    public void setGiaGoc(long giaGoc) {
        this.giaGoc = giaGoc;
    }
}