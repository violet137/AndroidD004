package vn.com.greenacademy.shopping.Data;

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
    private final String KEY_LUU_DANG_NHAP = "luu_dang_nhap";
    private final String KEY_LOAI_TAI_KHOAN = "loai_tai_khoan";

    public MySharedPreferences(Context context, String tenFile){
        this.context = context;

        //Khởi tạo biến sharedPreferences và biến GHI, XÓA editor
        sharedPreferences = context.getSharedPreferences(tenFile, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //Key đăng nhập
    public boolean getSHAREDPREF_LUU_DANG_NHAP() {
        return sharedPreferences.getBoolean(KEY_LUU_DANG_NHAP, false);
    }

    public void setSHAREDPREF_LUU_DANG_NHAP(boolean status){
        editor.putBoolean(KEY_LUU_DANG_NHAP, status);
        editor.commit();
    }

    //Key tai khoan
    public int getSHAREDPREF_LOAI_TAI_KHOAN(){
        return sharedPreferences.getInt(KEY_LOAI_TAI_KHOAN, -1);
    }

    public void setSHAREDPREF_LOAI_TAI_KHOAN(int loaiTK){
        editor.putInt(KEY_LOAI_TAI_KHOAN, loaiTK);
        editor.commit();
    }
}
