package vn.com.greenacademy.shopping.Model;

import java.io.Serializable;
import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class XuHuongThoiTrang implements Serializable {
    private long idXuHuong;
    private String tenXuHuong;
    private String linkHinhMoTa;
    private boolean isVideo;
    private String loai;
    private String hinhDaiDien;
    private ArrayList<SetDo> listSetDo = new ArrayList<>();
    private ArrayList<SanPham> listSanPham = new ArrayList<>();

    public XuHuongThoiTrang() {
    }

    public XuHuongThoiTrang(long idXuHuong, String tenXuHuong, String linkHinhMoTa, boolean isVideo, String loai, String hinhDaiDien, ArrayList<SetDo> listSetDo, ArrayList<SanPham> listSanPham) {
        this.idXuHuong = idXuHuong;
        this.tenXuHuong = tenXuHuong;
        this.linkHinhMoTa = linkHinhMoTa;
        this.isVideo = isVideo;
        this.loai = loai;
        this.hinhDaiDien = hinhDaiDien;
        this.listSetDo = listSetDo;
        this.listSanPham = listSanPham;
    }

    public long getIdXuHuong() {
        return idXuHuong;
    }

    public void setIdXuHuong(long idXuHuong) {
        this.idXuHuong = idXuHuong;
    }

    public String getTenXuHuong() {
        return tenXuHuong;
    }

    public void setTenXuHuong(String tenXuHuong) {
        this.tenXuHuong = tenXuHuong;
    }

    public String getLinkHinhMoTa() {
        return linkHinhMoTa;
    }

    public void setLinkHinhMoTa(String linkHinhMoTa) {
        this.linkHinhMoTa = linkHinhMoTa;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getHinhDaiDien() {
        return hinhDaiDien;
    }

    public void setHinhDaiDien(String hinhDaiDien) {
        this.hinhDaiDien = hinhDaiDien;
    }

    public ArrayList<SetDo> getListSetDo() {
        return listSetDo;
    }

    public void setListSetDo(ArrayList<SetDo> listSetDo) {
        this.listSetDo = listSetDo;
    }

    public ArrayList<SanPham> getListSanPham() {
        return listSanPham;
    }

    public void setListSanPham(ArrayList<SanPham> listSanPham) {
        this.listSanPham = listSanPham;
    }
}
