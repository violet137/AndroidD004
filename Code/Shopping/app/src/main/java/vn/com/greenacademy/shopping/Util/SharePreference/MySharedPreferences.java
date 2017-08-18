package vn.com.greenacademy.shopping.Util.SharePreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zzzzz on 7/4/2017.
 */

public class MySharedPreferences {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Context context;
    //Key Data
    private final String KEY_TOKEN = "token";
    private final String KEY_ID_TAI_KHOAN = "key_id_tai_khoan"; //Lưu id google/facebook
    private final String KEY_TEN_TAI_KHOAN = "ten_tai_khoan";
    private final String KEY_EMAIL = "email";
    private final String KEY_DA_DANG_NHAP = "da_dang_nhap";
    private final String KEY_LUU_DANG_NHAP = "luu_dang_nhap";
    private final String KEY_LOAI_TAI_KHOAN = "loai_tai_khoan";
    private final String KEY_GIO_HANG = "gio_hang";

    public MySharedPreferences(Context context, String tenFile){
        this.context = context;

        //Khởi tạo biến sharedPreferences và biến GHI, XÓA editor
        sharedPreferences = context.getSharedPreferences(tenFile, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //Key token
    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, null);
    }

    public void setToken(String token){
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    //Key Id tài khoản
    public String getIdTaiKhoan() {
        return sharedPreferences.getString(KEY_ID_TAI_KHOAN, null);
    }

    public void setIdTaiKhoan(String idTaiKhoan){
        editor.putString(KEY_ID_TAI_KHOAN, idTaiKhoan);
        editor.commit();
    }

    //Key tên tài khoản
    public String getTenTaiKhoan(){
        return sharedPreferences.getString(KEY_TEN_TAI_KHOAN, null);
    }

    public void setTenTaiKhoan(String ten_tai_khoan){
        editor.putString(KEY_TEN_TAI_KHOAN, ten_tai_khoan);
        editor.commit();
    }

    //Key Email
    public String getEmail(){
        return sharedPreferences.getString(KEY_EMAIL, null);
    }

    public void setEmail(String email){
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    //Key đăng nhập
    public boolean getDaDangNhap(){
        return sharedPreferences.getBoolean(KEY_DA_DANG_NHAP, false);
    }

    public void setDaDangNhap(boolean status){
        editor.putBoolean(KEY_DA_DANG_NHAP, status);
        editor.commit();
    }

    public boolean getLuuDangNhap() {
        return sharedPreferences.getBoolean(KEY_LUU_DANG_NHAP, false);
    }

    public void setLuuDangNhap(boolean status){
        editor.putBoolean(KEY_LUU_DANG_NHAP, status);
        editor.commit();
    }

    //Key loại tài khoản
    public String getLoaiTaiKhoan(){
        return sharedPreferences.getString(KEY_LOAI_TAI_KHOAN, "");
    }

    public void setLoaiTaiKhoan(String loaiTK){
        editor.putString(KEY_LOAI_TAI_KHOAN, loaiTK);
        editor.commit();
    }

    //Key giỏ hàng

    public String getGioHang() {
        if (sharedPreferences.contains(KEY_GIO_HANG))
            return sharedPreferences.getString(KEY_GIO_HANG, null);
        else
            return null;
    }

    public void setGioHang(String dataGioHang){
        editor.putString(KEY_GIO_HANG, dataGioHang);
        editor.commit();
    }

    public void removeGioHang(){
        editor.remove(KEY_GIO_HANG);
        editor.commit();
    }
}
