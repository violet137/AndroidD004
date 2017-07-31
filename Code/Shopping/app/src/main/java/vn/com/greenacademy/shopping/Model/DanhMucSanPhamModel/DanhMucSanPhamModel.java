package vn.com.greenacademy.shopping.Model.DanhMucSanPhamModel;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 29/07/2017.
 */

public class DanhMucSanPhamModel {

    public JSONObject DanhMucHangTranfers;
    public int Status;
    public String Description;



    public JSONObject getDanhMucHangTranfers() {
        return DanhMucHangTranfers;
    }

    public void setDanhMucHangTranfers(JSONObject danhMucHangTranfers) {
        DanhMucHangTranfers = danhMucHangTranfers;
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


    public static class DanhMucHangTranfers{
        public static List<DanhMucSanPham> listDanhMucSP = new LinkedList<>();
        public String LoaiThoiTrang,XuHuongTTrangLink;
        public int XuHuongTTrangId;

        public static List<DanhMucSanPham> getListDanhMucSP() {
            return listDanhMucSP;
        }

        public void setListDanhMucSP(List<DanhMucSanPham> listDanhMucSP) {
            this.listDanhMucSP = listDanhMucSP;
        }

        public String getLoaiThoiTrang() {
            return LoaiThoiTrang;
        }

        public void setLoaiThoiTrang(String loaiThoiTrang) {
            LoaiThoiTrang = loaiThoiTrang;
        }

        public String getXuHuongTTrangLink() {
            return XuHuongTTrangLink;
        }

        public void setXuHuongTTrangLink(String xuHuongTTrangLink) {
            XuHuongTTrangLink = xuHuongTTrangLink;
        }

        public int getXuHuongTTrangId() {
            return XuHuongTTrangId;
        }

        public void setXuHuongTTrangId(int xuHuongTTrangId) {
            XuHuongTTrangId = xuHuongTTrangId;
        }
    }

    public static class DanhMucSanPham{
        public static int Id;
        public static String TenDanhMuc,LoaiThoiTrang;

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public static String getTenDanhMuc() {
            return TenDanhMuc;
        }

        public static void setTenDanhMuc(String tenDanhMuc) {
            TenDanhMuc = tenDanhMuc;
        }

        public String getLoaiThoiTrang() {
            return LoaiThoiTrang;
        }

        public void setLoaiThoiTrang(String loaiThoiTrang) {
            LoaiThoiTrang = loaiThoiTrang;
        }
    }
}

