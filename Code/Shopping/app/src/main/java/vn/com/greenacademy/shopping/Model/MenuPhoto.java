package vn.com.greenacademy.shopping.Model;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

/**
 * Created by ADMIN on 7/18/2017.
 */

public class MenuPhoto {
    ArrayList<FashionType> fashionTypeArrayList;
    ArrayList<AdvertisePhoto> advertisePhotoArrayList;
    ArrayList<BannerPhoto> bannerPhotoArrayList;
    ArrayList<SanPham> sanPhamArrayList;
    int Status;
    String Description;

    public ArrayList<SanPham> getSanPhamArrayList() {
        return sanPhamArrayList;
    }

    public void setSanPhamArrayList(ArrayList<SanPham> sanPhamArrayList) {
        this.sanPhamArrayList = sanPhamArrayList;
    }

    public ArrayList<BannerPhoto> getBannerPhotoArrayList() {
        return bannerPhotoArrayList;
    }

    public void setBannerPhotoArrayList(ArrayList<BannerPhoto> bannerPhotoArrayList) {
        this.bannerPhotoArrayList = bannerPhotoArrayList;
    }

    public ArrayList<FashionType> getFashionTypeArrayList() {
        return fashionTypeArrayList;
    }

    public void setFashionTypeArrayList(ArrayList<FashionType> fashionTypeArrayList) {
        this.fashionTypeArrayList = fashionTypeArrayList;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public ArrayList<AdvertisePhoto> getAdvertisePhotoArrayList() {
        return advertisePhotoArrayList;
    }

    public void setAdvertisePhotoArrayList(ArrayList<AdvertisePhoto> advertisePhotoArrayList) {
        this.advertisePhotoArrayList = advertisePhotoArrayList;
    }
}
