package vn.com.greenacademy.shopping.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class XuHuongThoiTrang implements Serializable {
    private String tenXuHuong;
    private String loai;
    private String banner;
    private ArrayList<SetDo> listSetDo = new ArrayList<>();
    private ArrayList<SanPham> listSanPham = new ArrayList<>();

    public XuHuongThoiTrang(){}
    public XuHuongThoiTrang(String tenXuHuong, String loai, String banner, ArrayList<SetDo> listSetDo, ArrayList<SanPham> listSanPham) {
        this.tenXuHuong = tenXuHuong;
        this.loai = loai;
        this.banner = banner;
        this.listSetDo = listSetDo;
        this.listSanPham = listSanPham;
    }

    public String getTenXuHuong() {
        return tenXuHuong;
    }

    public void setTenXuHuong(String tenXuHuong) {
        this.tenXuHuong = tenXuHuong;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
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
