package vn.com.greenacademy.shopping.Model;

import java.util.ArrayList;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class SetDo {
    private String tenSetDo;
    private String descriptionSetDo;
    private String hinhSetDo;
    private ArrayList<SanPham> listSanPham = new ArrayList<>();

    public SetDo(){}

    public SetDo(String tenSetDo, String descriptionSetDo, String hinhSetDo, ArrayList<SanPham> listSanPham) {
        this.tenSetDo = tenSetDo;
        this.descriptionSetDo = descriptionSetDo;
        this.hinhSetDo = hinhSetDo;
        this.listSanPham = listSanPham;
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

    public String getHinhSetDo() {
        return hinhSetDo;
    }

    public void setHinhSetDo(String hinhSetDo) {
        this.hinhSetDo = hinhSetDo;
    }

    public ArrayList<SanPham> getListSanPham() {
        return listSanPham;
    }

    public void setListSanPham(ArrayList<SanPham> listSanPham) {
        this.listSanPham = listSanPham;
    }
}
