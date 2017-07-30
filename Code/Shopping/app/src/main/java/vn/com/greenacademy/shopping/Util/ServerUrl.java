package vn.com.greenacademy.shopping.Util;

/**
 * Created by 508-16 on 7/11/2017.
 */

public class ServerUrl {
    public static final String ServerLink="http://tamod.vn:8050/";

    public static final String HomeUrl = ServerLink + "api/Home/";
    public static final String DangNhapUrl = ServerLink + "api/Auth/Login";
    public static final String StoreUrl = ServerLink + "api/CuaHang/";
    public static final String MagazineUrl = ServerLink + "api/TapChi/";

    public static final String DangKyUrl = ServerLink + "TaiKhoan/DangKy";
    public static final String DataUrl = ServerLink + "";

    public static final String UrlDanhSachThoiTrang = HomeUrl + "DanhSachThoiTrang";
    public static final String UrlDanhSachKhuyenMai = HomeUrl + "KhuyenMai";
    public static final String UrlDanhBannerHome = HomeUrl + "BannerHome";
    public static final String UrlDanhSachStore = StoreUrl + "DanhSachCuaHang";
    public static final String UrlDanhSachMagazine = MagazineUrl + "TapChi?loaiTapChi=";
    public static final String UrlDanhSachMagazineType = MagazineUrl + "LoaiThoiTrang";
    public static final String UrlMagazineDetail = MagazineUrl + "ChiTietTapChi?idTapChi=";


}
