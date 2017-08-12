package vn.com.greenacademy.shopping.Handle.HandleData;

import android.content.Context;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Model.XuHuongThoiTrang;
import vn.com.greenacademy.shopping.Network.AsynTask.TaiKhoanServerAsyncTask;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SharePreference.MySharedPreferences;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by zzzzz on 7/11/2017.
 */

public class DataHandler{
    private Context context;
    private DataCallBack dataCallBack;
    private MySharedPreferences mySharedPref;

    private ArrayList<XuHuongThoiTrang> listXuHuongThoiTrang;

    //Constructor không cần kết quả trả về
    public DataHandler(Context context){
        mySharedPref = new MySharedPreferences(context, SupportKeyList.SHAREDPREF_TEN_FILE);
    }

    //Constructor cần kết quả trả về
    public DataHandler(Context context, DataCallBack dataCallBack){
        this.context = context;
        this.dataCallBack = dataCallBack;
        mySharedPref = new MySharedPreferences(context, SupportKeyList.SHAREDPREF_TEN_FILE);
    }

    public void DangNhap(String loai, String email, String password){
        switch (loai){
            case SupportKeyList.ACCOUNT_THUONG:
                //Gọi API server
                new TaiKhoanServerAsyncTask(dataCallBack).execute(SupportKeyList.API_DANG_NHAP, ServerUrl.DangNhapUrl, SupportKeyList.ACCOUNT_THUONG, email, password);
                break;
            case SupportKeyList.ACCOUNT_GOOGLE:
                //Gọi API server
                new TaiKhoanServerAsyncTask(dataCallBack).execute(SupportKeyList.API_DANG_NHAP, ServerUrl.DangNhapUrl, SupportKeyList.ACCOUNT_GOOGLE, email);
                break;
            case SupportKeyList.ACCOUNT_FACEBOOK:
                //Gọi API server
                new TaiKhoanServerAsyncTask(dataCallBack).execute(SupportKeyList.API_DANG_NHAP, ServerUrl.DangNhapUrl, SupportKeyList.ACCOUNT_FACEBOOK, email);
                break;
        }
    }

    public void setTrangThaiDangNhap(String token, String loaiTaiKhoan, String email, String tenHienThi, boolean luuDangNhap){
        mySharedPref.setToken(token);
        mySharedPref.setEmail(email);
        mySharedPref.setTenTaiKhoan(tenHienThi);
        mySharedPref.setDaDangNhap(true);
        mySharedPref.setLuuDangNhap(luuDangNhap);
        mySharedPref.setLoaiTaiKhoan(loaiTaiKhoan);
    }

    public void DangXuat(){
        mySharedPref.setToken("");
        mySharedPref.setEmail("");
        mySharedPref.setTenTaiKhoan("");
        mySharedPref.setDaDangNhap(false);
        mySharedPref.setLuuDangNhap(false);
        mySharedPref.setLoaiTaiKhoan("");
    }

}
