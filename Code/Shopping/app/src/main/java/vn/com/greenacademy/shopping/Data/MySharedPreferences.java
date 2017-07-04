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
    private final String SHAREDPREF_LUU_DANG_NHAP = "luu_dang_nhap";

    public MySharedPreferences(Context context, String tenFile){
        this.context = context;

        //Khởi tạo biến sharedPreferences và biến GHI, XÓA editor
        sharedPreferences = context.getSharedPreferences(tenFile, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean getSHAREDPREF_LUU_DANG_NHAP() {
        return sharedPreferences.getBoolean(SHAREDPREF_LUU_DANG_NHAP, false);
    }

    public void setSHAREDPREF_LUU_DANG_NHAP(boolean status){
        editor.putBoolean(SHAREDPREF_LUU_DANG_NHAP, status);
        editor.commit();
    }
}
