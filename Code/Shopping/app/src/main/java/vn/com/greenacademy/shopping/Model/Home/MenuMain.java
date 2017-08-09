package vn.com.greenacademy.shopping.Model.Home;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.Home.AdvertisePhoto;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

/**
 * Created by ADMIN on 7/15/2017.
 */

public class MenuMain {
    ArrayList<AdvertisePhoto> advertiseMenuMains;
    String url;
    String id;
    int flag;
    String type;
    ArrayList<SanPham> sanPhamArrayList;
    String name;
    int flagItem;
    String magazineType;

    public int getFlagItem() {
        return flagItem;
    }

    public void setFlagItem(int flagItem) {
        this.flagItem = flagItem;
    }

    public ArrayList<SanPham> getSanPhamArrayList() {
        return sanPhamArrayList;
    }

    public void setSanPhamArrayList(ArrayList<SanPham> sanPhamArrayList) {
        this.sanPhamArrayList = sanPhamArrayList;
    }

    public String getMagazineType() {
        return magazineType;
    }

    public void setMagazineType(String magazineType) {
        this.magazineType = magazineType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public ArrayList<AdvertisePhoto> getAdvertiseMenuMains() {
        return advertiseMenuMains;
    }

    public void setAdvertiseMenuMains(ArrayList<AdvertisePhoto> advertiseMenuMains) {
        this.advertiseMenuMains = advertiseMenuMains;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
