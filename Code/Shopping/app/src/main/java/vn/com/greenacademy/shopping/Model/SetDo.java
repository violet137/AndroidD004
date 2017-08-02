package vn.com.greenacademy.shopping.Model;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class SetDo {
    private long idSetDo;
    private String tenSetDo;
    private String descriptionSetDo;
    private String hinhMoTa;
    private boolean isVideo;
    private String hinhDaiDien;
    private String ngayTao;
    private ArrayList<SanPham> listSanPham = new ArrayList<>();

    public SetDo() {
    }

    public SetDo(long idSetDo, String tenSetDo, String descriptionSetDo, String hinhMoTa, boolean isVideo, String hinhDaiDien, String ngayTao, ArrayList<SanPham> listSanPham) {
        this.idSetDo = idSetDo;
        this.tenSetDo = tenSetDo;
        this.descriptionSetDo = descriptionSetDo;
        this.hinhMoTa = hinhMoTa;
        this.isVideo = isVideo;
        this.hinhDaiDien = hinhDaiDien;
        this.ngayTao = ngayTao;
        this.listSanPham = listSanPham;
    }

    public long getIdSetDo() {
        return idSetDo;
    }

    public void setIdSetDo(long idSetDo) {
        this.idSetDo = idSetDo;
    }

    public String getTenSetDo() {
        return tenSetDo;
    }

    public void setTenSetDo(String tenSetDo) {
        this.tenSetDo = tenSetDo;
    }

    public String getDescriptionSetDo() {
        return descriptionSetDo;
    }

    public void setDescriptionSetDo(String descriptionSetDo) {
        this.descriptionSetDo = descriptionSetDo;
    }

    public String getHinhMoTa() {
        return hinhMoTa;
    }

    public void setHinhMoTa(String hinhMoTa) {
        this.hinhMoTa = hinhMoTa;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public String getHinhDaiDien() {
        return hinhDaiDien;
    }

    public void setHinhDaiDien(String hinhDaiDien) {
        this.hinhDaiDien = hinhDaiDien;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public ArrayList<SanPham> getListSanPham() {
        return listSanPham;
    }

    public void setListSanPham(ArrayList<SanPham> listSanPham) {
        this.listSanPham = listSanPham;
    }
}
