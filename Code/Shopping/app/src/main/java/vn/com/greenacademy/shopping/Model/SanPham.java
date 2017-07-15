package vn.com.greenacademy.shopping.Model;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class SanPham {
    private String tenSanPham;
    private String loaiSanPham;
    private String description;
    private String chiTietSanPham;
    private String[] hinhSanPham;
    private String[] mauSanPham;
    private int[] giaSanPham;
    private String[] size;
    private int giamGia;

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChiTietSanPham() {
        return chiTietSanPham;
    }

    public void setChiTietSanPham(String chiTietSanPham) {
        this.chiTietSanPham = chiTietSanPham;
    }

    public String[] getHinhSanPham() {
        return hinhSanPham;
    }

    public void setHinhSanPham(String[] hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public String[] getMauSanPham() {
        return mauSanPham;
    }

    public void setMauSanPham(String[] mauSanPham) {
        this.mauSanPham = mauSanPham;
    }

    public int[] getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int[] giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String[] getSize() {
        return size;
    }

    public void setSize(String[] size) {
        this.size = size;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }
}
