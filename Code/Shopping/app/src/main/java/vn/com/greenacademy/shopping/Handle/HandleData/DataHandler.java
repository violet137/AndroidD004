package vn.com.greenacademy.shopping.Handle.HandleData;

import android.content.Context;

import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Network.AsynTask.GoiAPIServerAsyncTask;
import vn.com.greenacademy.shopping.Util.SharePreference.MySharedPreferences;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by zzzzz on 7/11/2017.
 */

public class DataHandler{
    private Context context;
    private DataCallBack dataCallBack;
    private MySharedPreferences mySharedPref;

    public DataHandler(Context context){
        mySharedPref = new MySharedPreferences(context, SupportKeyList.SHAREDPREF_TEN_FILE);
    }

    public DataHandler(Context context, DataCallBack dataCallBack){
        this.context = context;
        this.dataCallBack = dataCallBack;
        mySharedPref = new MySharedPreferences(context, SupportKeyList.SHAREDPREF_TEN_FILE);
    }

    public void DangNhap(String loai, String email, String password){
        switch (loai){
            case SupportKeyList.ACCOUNT_THUONG:
                //Gọi API server
                new GoiAPIServerAsyncTask(dataCallBack).execute(SupportKeyList.API_DANG_NHAP, SupportKeyList.URL_DANG_NHAP, SupportKeyList.ACCOUNT_THUONG, email, password);
                break;
            case SupportKeyList.ACCOUNT_GOOGLE:
                //Gọi API server
                new GoiAPIServerAsyncTask(dataCallBack).execute(SupportKeyList.API_DANG_NHAP, SupportKeyList.URL_DANG_NHAP, SupportKeyList.ACCOUNT_GOOGLE, email);
                break;
            case SupportKeyList.ACCOUNT_FACEBOOK:
                //Gọi API server
                new GoiAPIServerAsyncTask(dataCallBack).execute(SupportKeyList.API_DANG_NHAP, SupportKeyList.URL_DANG_NHAP, SupportKeyList.ACCOUNT_FACEBOOK, email);
                break;
        }
    }

    public void setTrangThaiDangNhap(String loaiTaiKhoan, String email, String tenHienThi, boolean luuDangNhap){
        mySharedPref.setEmail(email);
        mySharedPref.setTenTaiKhoan(tenHienThi);
        mySharedPref.setDaDangNhap(true);
        mySharedPref.setLuuDangNhap(luuDangNhap);
        mySharedPref.setLoaiTaiKhoan(loaiTaiKhoan);
    }

    public void DangXuat(){
        mySharedPref.setEmail("");
        mySharedPref.setTenTaiKhoan("");
        mySharedPref.setDaDangNhap(false);
        mySharedPref.setLuuDangNhap(false);
        mySharedPref.setLoaiTaiKhoan("");
    }
}
