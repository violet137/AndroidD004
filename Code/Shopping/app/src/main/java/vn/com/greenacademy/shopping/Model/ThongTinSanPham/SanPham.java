package vn.com.greenacademy.shopping.Model.ThongTinSanPham;

import java.io.Serializable;
import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.ThongTinSanPham.HinhSanPham;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class SanPham implements Serializable {
    private long idSanPham;
    private long danhMucHangId;
    private String tenSanPham;
    private String ngayTao;
    private String loaiSanPham;
    private String description;
    private String chiTietSanPham;
    private ArrayList<HinhSanPham> hinhSanPham = new ArrayList<>();
    private String[] mauSanPham;
    private String[] size;
    private String[] sanPhamPhuHop;
    private long giaSanPham;
    private long giamGia;
    private String hinhDaiDien;

    public SanPham(){}

    public SanPham(long idSanPham, long danhMucHangId, String tenSanPham, String ngayTao, String loaiSanPham, String description, String chiTietSanPham, ArrayList<HinhSanPham> hinhSanPham, String[] mauSanPham, String[] size, String[] sanPhamPhuHop, long giaSanPham, long giamGia) {
        this.idSanPham = idSanPham;
        this.danhMucHangId = danhMucHangId;
        this.tenSanPham = tenSanPham;
        this.ngayTao = ngayTao;
        this.loaiSanPham = loaiSanPham;
        this.description = description;
        this.chiTietSanPham = chiTietSanPham;
        this.hinhSanPham = hinhSanPham;
        this.mauSanPham = mauSanPham;
        this.size = size;
        this.sanPhamPhuHop = sanPhamPhuHop;
        this.giaSanPham = giaSanPham;
        this.giamGia = giamGia;
    }

    public String getHinhDaiDien() {
        return hinhDaiDien;
    }

    public void setHinhDaiDien(String hinhDaiDien) {
        this.hinhDaiDien = hinhDaiDien;
    }

    public long getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(long idSanPham) {
        this.idSanPham = idSanPham;
    }

    public long getDanhMucHangId() {
        return danhMucHangId;
    }

    public void setDanhMucHangId(long danhMucHangId) {
        this.danhMucHangId = danhMucHangId;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
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

    public ArrayList<HinhSanPham> getHinhSanPham() {
        return hinhSanPham;
    }

    public void setHinhSanPham(ArrayList<HinhSanPham> hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public String[] getMauSanPham() {
        return mauSanPham;
    }

    public void setMauSanPham(String[] mauSanPham) {
        this.mauSanPham = mauSanPham;
    }

    public String[] getSize() {
        return size;
    }

    public void setSize(String[] size) {
        this.size = size;
    }

    public String[] getSanPhamPhuHop() {
        return sanPhamPhuHop;
    }

    public void setSanPhamPhuHop(String[] sanPhamPhuHop) {
        this.sanPhamPhuHop = sanPhamPhuHop;
    }

    public long getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(long giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public long getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(long giamGia) {
        this.giamGia = giamGia;
    }
}