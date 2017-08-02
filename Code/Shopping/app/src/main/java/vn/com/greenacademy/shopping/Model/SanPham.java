package vn.com.greenacademy.shopping.Model;

import java.util.ArrayList;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class SanPham {
    private long idSanPham;
    private String tenSanPham;
    private String ngayTao;
    private String loaiSanPham;
    private String description;
    private String chiTietSanPham;
    private String[] hinhSanPham;
    private String mauSanPham;
    private double giaSanPham;
    private String[] size;
    private int giamGia;

    // moddel theo server
    ArrayList<HinhTheoMau> hinhTheoMauArrayList;
    String[] mauSac;
    String[] spPhuHop;
    int danhMucHangId;
    int id;
    long giaTien;
    long giaTienGiam;
    String moTa;

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(long giaTien) {
        this.giaTien = giaTien;
    }

    public long getGiaTienGiam() {
        return giaTienGiam;
    }

    public void setGiaTienGiam(long giaTienGiam) {
        this.giaTienGiam = giaTienGiam;
    }

    public String[] getSpPhuHop() {
        return spPhuHop;
    }

    public void setSpPhuHop(String[] spPhuHop) {
        this.spPhuHop = spPhuHop;
    }

    public int getDanhMucHangId() {
        return danhMucHangId;
    }

    public void setDanhMucHangId(int danhMucHangId) {
        this.danhMucHangId = danhMucHangId;
    }

    public long getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(long idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public ArrayList<HinhTheoMau> getHinhTheoMauArrayList() {
        return hinhTheoMauArrayList;
    }

    public void setHinhTheoMauArrayList(ArrayList<HinhTheoMau> hinhTheoMauArrayList) {
        this.hinhTheoMauArrayList = hinhTheoMauArrayList;
    }

    public String[] getMauSac() {
        return mauSac;
    }

    public void setMauSac(String[] mauSac) {
        this.mauSac = mauSac;
    }

    public SanPham(){}

    public SanPham(String tenSanPham, String loaiSanPham, String description, String chiTietSanPham, String[] hinhSanPham, String mauSanPham, double giaSanPham, String[] size, int giamGia) {
        this.tenSanPham = tenSanPham;
        this.loaiSanPham = loaiSanPham;
        this.description = description;
        this.chiTietSanPham = chiTietSanPham;
        this.hinhSanPham = hinhSanPham;
        this.mauSanPham = mauSanPham;
        this.giaSanPham = giaSanPham;
        this.size = size;
        this.giamGia = giamGia;
    }

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

    public String getMauSanPham() {
        return mauSanPham;
    }

    public void setMauSanPham(String mauSanPham) {
        this.mauSanPham = mauSanPham;
    }

    public double getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(double giaSanPham) {
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
