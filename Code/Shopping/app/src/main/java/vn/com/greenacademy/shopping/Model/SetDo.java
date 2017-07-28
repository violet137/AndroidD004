package vn.com.greenacademy.shopping.Model;

import java.util.ArrayList;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class SetDo {
    private long idSetDo;
    private String tenSetDo;
    private String descriptionSetDo;
    private String hinhMoTa;
    private String ngayTao;
    private ArrayList<SanPham> listSanPham = new ArrayList<>();

    public SetDo() {
    }

    public SetDo(long idSetDo, String tenSetDo, String descriptionSetDo, String hinhMoTa, String ngayTao, ArrayList<SanPham> listSanPham) {
        this.idSetDo = idSetDo;
        this.tenSetDo = tenSetDo;
        this.descriptionSetDo = descriptionSetDo;
        this.hinhMoTa = hinhMoTa;
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
