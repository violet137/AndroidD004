package vn.com.greenacademy.shopping.Util;

/**
 * Created by 508-16 on 7/11/2017.
 */

public class ServerUrl {
    public static final String ServerLink="http://tamod.vn:8050/";

    //Tài khoản
    public static final String DangNhapUrl = ServerLink + "api/Auth/Login";
    public static final String DangKyUrl = ServerLink + "api/Auth/Register";

    //Data
    public static final String HomeUrl = ServerLink + "api/Home/";
    public static final String StoreUrl = ServerLink + "api/CuaHang/";
    public static final String ProductUrl = ServerLink + "api/SanPham/";
    public static final String MagazineUrl = ServerLink + "api/TapChi/";
    public static final String XuHuongThoiTrangUrl = ServerLink + "api/XuHuongTtrang/XuHuongTtrangById?idXuHuong=";
    public static final String HoTroUrl = ServerLink + "api/HoTro/";
    public static final String DonHangUrl = ServerLink + "api/DonHang/";

    public static final String UrlDanhSachThoiTrang = HomeUrl + "DanhSachThoiTrang";
    public static final String UrlDanhSachSPMoi = ProductUrl + "SanPhamMoi?soLuong=";
    public static final String UrlDanhSachKhuyenMai = HomeUrl + "KhuyenMai";
    public static final String UrlDanhBannerHome = HomeUrl + "BannerHome";
    public static final String UrlDanhSachStore = StoreUrl + "DanhSachCuaHang";
    public static final String UrlDanhSachMagazine = MagazineUrl + "TapChi?loaiTapChi=";
    public static final String UrlDanhSachMagazineType = MagazineUrl + "LoaiThoiTrang";
    public static final String UrlMagazineDetail = MagazineUrl + "ChiTietTapChi?idTapChi=";
    public static final String UrlDanhMucSP = HomeUrl + "DanhMucHang?loaiThoiTrang=";
    public static final String UrlKhuyenMai = HomeUrl + "KhuyenMai";
    public static final String UrlSanPhamTheoDanhMuc = ProductUrl + "SanPhamTheoDanhMuc?danhMucHangId=";
    public static final String UrlSanPhamTheoThoiTrang = ProductUrl + "SanPhamTheoThoiTrang?loaiThoiTrang=";
    public static final String UrlSPMoiTheoDM = ProductUrl + "SpMoiTheoThoiTrang?loaiThoiTrang=";
    public static final String AndSoLuonSanPham = "&soLuong=";
    //Đơn hàng
    public static final String UrlGetGioHang = DonHangUrl + "GetGioHang?account=";
    public static final String UrlUpdateGioHang = DonHangUrl + "UpdateGioHang";

    //Support
    public static final String UrlCauHoiTG = HoTroUrl + "DanhSachCauHoi";
    public static final String UrlLoaiVD = HoTroUrl + "LoaiHoTro";
    public static final String UrlGuiMail = HoTroUrl + "GuiHoTro";
    public static final String UrlFacebookFashionAndLife = "https://www.facebook.com/FL-Fashion-and-Life-643129985890828/?sw_fnr_id=2339152974&fnr_t=0";
    public static final String UrlYouTubeFashionAndLife = "https://www.youtube.com/channel/UCTBFYNdyZPCSINDjQNhSOAA";
    public static final String UrlGooglePlusFashionAndLife = "https://plus.google.com/u/0/110092588554737079316";
    public static final String UrlInstagramFashionAndLife = "https://www.instagram.com/appshoppingd004/?hl=vi";
    public static final String UrlGioThieuFAndL = ServerLink + "images/about.html";


}
