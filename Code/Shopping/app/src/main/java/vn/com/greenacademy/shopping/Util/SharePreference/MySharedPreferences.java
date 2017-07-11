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
    private final String KEY_TEN_TAI_KHOAN = "ten_tai_khoan";
    private final String KEY_DA_DANG_NHAP = "da_dang_nhap";
    private final String KEY_LUU_DANG_NHAP = "luu_dang_nhap";
    private final String KEY_LOAI_TAI_KHOAN = "loai_tai_khoan";

    public MySharedPreferences(Context context, String tenFile){
        this.context = context;

        //Khởi tạo biến sharedPreferences và biến GHI, XÓA editor
        sharedPreferences = context.getSharedPreferences(tenFile, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //Key tên tài khoản
    public String getTEN_TAI_KHOAN(){
        return sharedPreferences.getString(KEY_TEN_TAI_KHOAN, null);
    }

    public void setTEN_TAI_KHOAN(String ten_tai_khoan){
        editor.putString(KEY_TEN_TAI_KHOAN, ten_tai_khoan);
        editor.commit();
    }

    //Key đăng nhập
    public boolean getDA_DANG_NHAP(){
        return sharedPreferences.getBoolean(KEY_DA_DANG_NHAP, false);
    }

    public void setDA_DANG_NHAP(boolean status){
        editor.putBoolean(KEY_DA_DANG_NHAP, status);
        editor.commit();
    }

    public boolean getLUU_DANG_NHAP() {
        return sharedPreferences.getBoolean(KEY_LUU_DANG_NHAP, false);
    }

    public void setLUU_DANG_NHAP(boolean status){
        editor.putBoolean(KEY_LUU_DANG_NHAP, status);
        editor.commit();
    }

    //Key tai khoan
    public int getLOAI_TAI_KHOAN(){
        return sharedPreferences.getInt(KEY_LOAI_TAI_KHOAN, -1);
    }

    public void setLOAI_TAI_KHOAN(int loaiTK){
        editor.putInt(KEY_LOAI_TAI_KHOAN, loaiTK);
        editor.commit();
    }
}
