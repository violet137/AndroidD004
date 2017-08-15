package vn.com.greenacademy.shopping.Network.AsynTask;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.DanhMucSanPham.ParseSanPhamChiTietDanhMuc;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Product.ParseListSanPham;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Product.ParseSanPham;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.XuHuongThoiTrang.ParseXuHuongThoiTrang;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Model.SetDo;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.HinhSanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Model.XuHuongThoiTrang;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by zzzzz on 8/12/2017.
 */

public class DataServerAsyncTask extends AsyncTask<String, Void, String> {
    private DataCallBack dataCallBack;
    private String API = null;
    private String phuongThucGet = "GET";
    private String phuongThucPost = "POST";

    public DataServerAsyncTask(DataCallBack dataCallBack) {
        this.dataCallBack = dataCallBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        /*Thứ tự tham số truyền vào:
        * string[0]: loại API
        * string[1]: URL
        * string[2]: phương thức
        * */

        try {
            URL url = new URL(strings[1]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //Kiểm tra API được gọi
            API = strings[0];
            if (strings[2].equals(phuongThucGet)){
                try {
                    //Cài đặt các thiết lập gửi server
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setRequestMethod("GET");
                    conn.connect();

                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK || conn.getResponseCode() == HttpURLConnection.HTTP_CREATED){
                        InputStream inputStream = conn.getInputStream();
                        ByteArrayOutputStream result = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1024];
                        int length;
                        while((length = inputStream.read(buffer)) != -1){
                            result.write(buffer, 0, length);
                        }

                        JSONObject object = new JSONObject(result.toString("UTF-8"));
                        if (object.getInt("Status") == 1){
                            return result.toString("UTF-8");
                        }
                    }
                } catch (JSONException e) {
                    return SupportKeyList.LOI_DATA;
                }
                return SupportKeyList.LOI_DATA_SERVER;
            }
        } catch (IOException e) {
            return SupportKeyList.LOI_KET_NOI;
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (!result.isEmpty()) {
            if (result.equals(SupportKeyList.LOI_KET_NOI) || result.equals(SupportKeyList.LOI_DATA_SERVER)){
                dataCallBack.KetQua(result, null);
            }

            //Phân loại kết quả của API
            switch (API) {
                case SupportKeyList.API_DATA_XU_HUONG_THOI_TRANG:
                    new ParseXuHuongThoiTrang(result, dataCallBack).parseData();
                    break;
                case SupportKeyList.API_DATA_SAN_PHAM_CHI_TIET_DANH_MUC:
                    //Truyền kết quả về cho class yêu cầu
                    ArrayList<SanPham> mListSanPhamChiTietDanhMuc = new ParseSanPhamChiTietDanhMuc(result).parseData();
                    Bundle dataChiTietDanhMuc = new Bundle();
                    dataChiTietDanhMuc.putSerializable(SupportKeyList.API_DATA_SAN_PHAM_CHI_TIET_DANH_MUC, mListSanPhamChiTietDanhMuc);
                    dataCallBack.KetQua(SupportKeyList.LAY_DATA_THANH_CONG, dataChiTietDanhMuc);
                    break;
                case SupportKeyList.API_DATA_SAN_PHAM_THEO_THOI_TRANG:
                    //Truyền kết quả về cho class yêu cầu
                    ArrayList<SanPham> mListSanPhamTheoThoiTrang = new ParseSanPhamChiTietDanhMuc(result).parseData();
                    Bundle dataSanPhamTheoThoiTrang = new Bundle();
                    dataSanPhamTheoThoiTrang.putSerializable(SupportKeyList.API_DATA_SAN_PHAM_CHI_TIET_DANH_MUC, mListSanPhamTheoThoiTrang);
                    dataCallBack.KetQua(SupportKeyList.LAY_DATA_THANH_CONG, dataSanPhamTheoThoiTrang);
                    break;
                default:
                    break;
            }
        }
    }

}
