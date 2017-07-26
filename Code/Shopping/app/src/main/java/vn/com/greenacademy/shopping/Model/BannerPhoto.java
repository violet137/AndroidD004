package vn.com.greenacademy.shopping.Model;

/**
 * Created by ADMIN on 7/26/2017.
 */

public class BannerPhoto {
    long Id;
    String LinkAnh;
    String LoaiBanner;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        LinkAnh = linkAnh;
    }

    public String getLoaiBanner() {
        return LoaiBanner;
    }

    public void setLoaiBanner(String loaiBanner) {
        LoaiBanner = loaiBanner;
    }
}
