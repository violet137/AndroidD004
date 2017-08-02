package vn.com.greenacademy.shopping.Model.ThongTinSanPham;

/**
 * Created by zzzzz on 8/2/2017.
 */

public class HinhSanPham {
    private String[] linkHinh;
    private String mau;

    public HinhSanPham() {
    }

    public HinhSanPham(String[] linkHinh, String mau) {
        this.linkHinh = linkHinh;
        this.mau = mau;
    }

    public String[] getLinkHinh() {
        return linkHinh;
    }

    public void setLinkHinh(String[] linkHinh) {
        this.linkHinh = linkHinh;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }
}
