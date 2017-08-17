package vn.com.greenacademy.shopping.Handle.HandleData.GioHang;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vn.com.greenacademy.shopping.Fragment.Support.SupportFragment;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Network.AsynTask.DataServerAsyncTask;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SharePreference.MySharedPreferences;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by zzzzz on 8/16/2017.
 */

public class GioHangHandler {
    private Context context;
    private JSONArray jsonArraySanPham;
    private MySharedPreferences mySharedPref;
    private DataServerAsyncTask serverAsyncTask;

    //Key DataServer
    private final String KEY_ACCOUNT = "Account";
    private final String KEY_LIST_GIO_HANG = "SanPhamList";

    //Key SharedPreferences
    private final String KEY_ID_SAN_PHAM = "IdSanPham";
    private final String KEY_SO_LUONG = "SoLuong";
    private final String KEY_SIZE = "Size";
    private final String KEY_COLOR = "Color";

    public GioHangHandler(Context context, DataCallBack dataCallBack){
        this.context = context;
        serverAsyncTask = new DataServerAsyncTask(dataCallBack);
        mySharedPref = new MySharedPreferences(context, SupportKeyList.SHAREDPREF_TEN_FILE);
    }

    public void themSanPhamGioHang(long idSanPham, int soLuong, String size, String mau){
        try {
            //Kiểm tra nếu giỏ hàng có sản phẩm thì add thêm ngược lại tạo mới
            if (mySharedPref.getGioHang() != null) {
                jsonArraySanPham = new JSONArray(mySharedPref.getGioHang());
                for (int i = 0; i < jsonArraySanPham.length(); i++) {
                    if (jsonArraySanPham.getJSONObject(i).getInt(KEY_ID_SAN_PHAM) == idSanPham){
                        jsonArraySanPham.remove(i);
                        mySharedPref.removeGioHang();
                        break;
                    }
                }
            }
            else
                jsonArraySanPham = new JSONArray();

            JSONObject objSanPham = new JSONObject();
            objSanPham.put(KEY_ID_SAN_PHAM, idSanPham);
            objSanPham.put(KEY_SO_LUONG, soLuong);
            objSanPham.put(KEY_SIZE, size);
            objSanPham.put(KEY_COLOR, mau);
            jsonArraySanPham.put(objSanPham);
            mySharedPref.setGioHang(jsonArraySanPham.toString());

            //Data server
            JSONObject objGioHang = new JSONObject();
            objGioHang.put(KEY_ACCOUNT, mySharedPref.getEmail());
            objGioHang.put(KEY_LIST_GIO_HANG, jsonArraySanPham.toString());
            serverAsyncTask.execute(SupportKeyList.API_THEM_GIO_HANG, ServerUrl.UrlUpdateGioHang, "POST", objGioHang.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void UpdateSanPhamGioHang(int idSanPham, int soLuong, String size, String mau){
        try{
            jsonArraySanPham = new JSONArray(mySharedPref.getGioHang());
            JSONObject objSanPham;
            for (int i = 0; i < jsonArraySanPham.length(); i++) {
                objSanPham = jsonArraySanPham.getJSONObject(i);
                if (objSanPham.getInt(KEY_ID_SAN_PHAM) == idSanPham){
                    if (soLuong != 0)
                        objSanPham.put(KEY_SO_LUONG, soLuong);
                    if (size != null)
                        objSanPham.put(KEY_SIZE, size);
                    if (mau != null)
                        objSanPham.put(KEY_COLOR, mau);
                }
            }
            mySharedPref.removeGioHang();
            mySharedPref.setGioHang(jsonArraySanPham.toString());

            //Data server
            JSONObject objGioHang = new JSONObject();
            objGioHang.put(KEY_ACCOUNT, mySharedPref.getEmail());
            objGioHang.put(KEY_LIST_GIO_HANG, jsonArraySanPham.toString());
            serverAsyncTask.execute(SupportKeyList.API_THEM_GIO_HANG, ServerUrl.UrlUpdateGioHang, "POST", objGioHang.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void XoaSanPhamGioHang(int idSanPham){
        try {
            jsonArraySanPham = new JSONArray(mySharedPref.getGioHang());
            JSONObject objSanPham;
            for (int i = 0; i < jsonArraySanPham.length(); i++) {
                objSanPham = jsonArraySanPham.getJSONObject(i);
                if (objSanPham.getInt("IdSanPham") == idSanPham){
                    jsonArraySanPham.remove(i);
                    break;
                }
            }
            mySharedPref.removeGioHang();
            mySharedPref.setGioHang(jsonArraySanPham.toString());

            //Data server
            JSONObject objGioHang = new JSONObject();
            objGioHang.put(KEY_ACCOUNT, mySharedPref.getEmail());
            objGioHang.put(KEY_LIST_GIO_HANG, jsonArraySanPham.toString());
            serverAsyncTask.execute(SupportKeyList.API_THEM_GIO_HANG, ServerUrl.UrlUpdateGioHang, "POST", objGioHang.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
