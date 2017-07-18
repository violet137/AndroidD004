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

import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Model.SanPham;
import vn.com.greenacademy.shopping.Model.SetDo;
import vn.com.greenacademy.shopping.Model.XuHuongThoiTrang;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by zzzzz on 7/3/2017.
 */

public class GoiAPIServerAsyncTask extends AsyncTask<String, Void, String> {
    private DataCallBack dataCallBack;
    private String loaiTaiKhoan = null;
    private String API = null;

    public GoiAPIServerAsyncTask(DataCallBack dataCallBack){
        this.dataCallBack = dataCallBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        /*Thứ tự tham số truyền vào:
        * string[0]: loại API
        * string[1]: URL
        *
        * 1. API ĐĂNG NHẬP:
        * string[2]: loại tài khoản
        *
        * Account thường:
        * string[3]: Tên đăng nhập (email)
        * string[4]: Password
        * string[5]: Tên hiển thị (cho đăng ký)
        *
        * Account Google/Facebook:
        * string[3]: google id
        *
        * 2. API ĐĂNG KÝ:
        * string[3]: Username (email)
        * string[4]: Password
        * string[5]: Tên hiển thị
        * */

        try {
            URL url = new URL(strings[1]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //Kiểm tra API được gọi
            API = strings[0];
            switch (API){
                case SupportKeyList.API_DANG_NHAP:
                    loaiTaiKhoan = strings[2];
                    try {
                        //Cài đặt các thiết lập gửi lên server
                        conn.setRequestProperty("Accept", "json");
                        conn.setRequestProperty("content-type", "application/json; charset=utf-8");
                        conn.setRequestMethod("POST");

                        //Chuẩn bị data
                        OutputStream outputStream = conn.getOutputStream();
                        JSONObject object = new JSONObject();

                        //Phân loại tài khoản
                        switch (strings[2]){
                            case SupportKeyList.ACCOUNT_THUONG:
                                object.put("Username", strings[3]);
                                object.put("Pwd", strings[4]);
                                object.put("AccountType", SupportKeyList.ACCOUNT_THUONG);
                                object.put("NenTang", "Android");
                                break;
                            case SupportKeyList.ACCOUNT_GOOGLE:
                                object.put("Username", strings[3]);
                                object.put("Pwd", "123456");
                                object.put("AccountType", SupportKeyList.ACCOUNT_GOOGLE);
                                object.put("NenTang", "Android");
                                break;
                            case SupportKeyList.ACCOUNT_FACEBOOK:
                                object.put("Username", strings[3]);
                                object.put("Pwd", "123456");
                                object.put("AccountType", SupportKeyList.ACCOUNT_FACEBOOK);
                                object.put("NenTang", "Android");
                                break;
                        }
                        outputStream.write(object.toString().getBytes());
                        conn.connect();

                        //Kiểm tra kết nối thành công
                        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK || conn.getResponseCode() == HttpURLConnection.HTTP_CREATED){
                            //Lấy status server trả về kiểm tra đăng nhập thành công hay không
                            InputStream inputStream = conn.getInputStream();
                            ByteArrayOutputStream result = new ByteArrayOutputStream();
                            byte[] buffer = new byte[1024];
                            int lenght;

                            //Ghép từng đoạn data server trả về
                            while((lenght = inputStream.read(buffer)) != -1)
                                result.write(buffer, 0, lenght);

                            //Kiểm tra đăng nhập thành công
                            JSONObject jsonObject = new JSONObject(result.toString());
                            if(jsonObject.getInt("Status") == 1) {
                                return result.toString("UTF-8");
                            }
                        }
                    } catch (JSONException e) {
                        dataCallBack.KetQua(SupportKeyList.LOI_DATA, null);
                    }
                    return  SupportKeyList.DANG_NHAP_THAT_BAI;

                case SupportKeyList.API_DANG_KY:
                    try {
                        //Cài đặt các thiết lập gửi lên server
                        conn.setRequestProperty("Accept", "json");
                        conn.setRequestProperty("content-type", "application/json; charset=utf-8");
                        conn.setRequestMethod("POST");

                        //Chuẩn bị data
                        OutputStream outputStream = conn.getOutputStream();
                        JSONObject object = new JSONObject();
                        object.put("Username", strings[2]);
                        object.put("MatKhau", strings[3]);
                        object.put("TenHienThi", strings[4]);
                        outputStream.write(object.toString().getBytes());
                        conn.connect();

                        //Kiểm tra kết nối thành công
                        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK || conn.getResponseCode() == HttpURLConnection.HTTP_CREATED){
                            //Lấy status server trả về kiểm tra đăng nhập thành công hay không
                            InputStream inputStream = conn.getInputStream();
                            ByteArrayOutputStream result = new ByteArrayOutputStream();
                            byte[] buffer = new byte[1024];
                            int lenght;

                            //Ghép từng đoạn data server trả về
                            while((lenght = inputStream.read(buffer)) != -1)
                                result.write(buffer, 0, lenght);

                            JSONObject jsonObject = new JSONObject(result.toString());
                            if(jsonObject.getInt("Status") == 1)
                                return SupportKeyList.DANG_KY_THANH_CONG;
                        }
                    } catch (JSONException e) {
                        dataCallBack.KetQua(SupportKeyList.LOI_DATA, null);
                    }
                    return SupportKeyList.DANG_KY_THAT_BAI;

                case SupportKeyList.API_DATA_XU_HUONG_THOI_TRANG:
                    try {
                        conn.setRequestProperty("Accept", "json");
                        conn.setRequestMethod("GET");
                        conn.connect();

                        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK || conn.getResponseCode() == HttpURLConnection.HTTP_CREATED){
                            InputStream inputStream = conn.getInputStream();
                            ByteArrayOutputStream result = new ByteArrayOutputStream();
                            byte[] buffer = new byte[1024];
                            int length;
                            while((length = inputStream.read(buffer)) == -1){
                                result.write(buffer, 0, length);
                            }

                            JSONObject object = new JSONObject(result.toString());
                            if (object.getInt("Status") == 1){
                                return result.toString("UTF-8");
                            }
                        }
                    } catch (JSONException e) {
                        dataCallBack.KetQua(SupportKeyList.LOI_DATA, null);
                    }
                    dataCallBack.KetQua(SupportKeyList.LOI_DATA_SERVER, null);
                    break;
            }
        } catch (IOException e) {
            dataCallBack.KetQua(SupportKeyList.LOI_KET_NOI, null);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (!result.isEmpty()) {
            //Phân loại kết quả của API
            switch (API) {
                case SupportKeyList.API_DANG_NHAP:
                    //Kiểm tra đăng nhập thành công
                    if (!result.equals(SupportKeyList.DANG_NHAP_THAT_BAI)) {
                        Bundle bundle = new Bundle();
                        try {
                            bundle.putString("Token", new JSONObject(result).getString("Token"));
                            //Parse data và trả kết quả về cho class yêu cầu
                            switch (loaiTaiKhoan) {
                                case SupportKeyList.ACCOUNT_THUONG:
                                    dataCallBack.KetQua(SupportKeyList.DANG_NHAP_THANH_CONG, bundle);
                                    break;
                                case SupportKeyList.ACCOUNT_GOOGLE:
                                    dataCallBack.KetQua(SupportKeyList.DANG_NHAP_GOOGLE_THANH_CONG, bundle);
                                    break;
                                case SupportKeyList.ACCOUNT_FACEBOOK:
                                    dataCallBack.KetQua(SupportKeyList.DANG_NHAP_FACEBOOK_THANH_CONG, bundle);
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        switch (loaiTaiKhoan) {
                            case SupportKeyList.ACCOUNT_THUONG:
                                dataCallBack.KetQua(SupportKeyList.DANG_NHAP_THAT_BAI, null);
                                break;
                            case SupportKeyList.ACCOUNT_GOOGLE:
                                dataCallBack.KetQua(SupportKeyList.DANG_NHAP_GOOGLE_THAT_BAI, null);
                                break;
                            case SupportKeyList.ACCOUNT_FACEBOOK:
                                dataCallBack.KetQua(SupportKeyList.DANG_NHAP_FACEBOOK_THAT_BAI, null);
                                break;
                        }
                    }
                    break;

                case SupportKeyList.API_DANG_KY:
                    //Kiểm tra đăng ký thành công
                    if (!result.equals(SupportKeyList.DANG_KY_THAT_BAI))
                        dataCallBack.KetQua(SupportKeyList.DANG_KY_THANH_CONG, null);
                    else
                        dataCallBack.KetQua(SupportKeyList.DANG_KY_THAT_BAI, null);
                    break;

                case SupportKeyList.API_DATA_XU_HUONG_THOI_TRANG:
                    XuLyData(result);
                    break;
                default:
                    break;
            }
        }
    }

    private void XuLyData(String result) {
        try {
            JSONObject root = new JSONObject(result);
            XuHuongThoiTrang xuHuongThoiTrang = new XuHuongThoiTrang();
            xuHuongThoiTrang.setTenXuHuong(root.getString("TenXuHuong"));
            xuHuongThoiTrang.setBanner(root.getString("Banner"));
            //List set đồ
            JSONArray jsonArrSetDo = root.getJSONArray("SetDo");
            for (int i = 0; i < jsonArrSetDo.length(); i++) {
                JSONObject objSetDo = jsonArrSetDo.getJSONObject(i);
                SetDo setDo = new SetDo();
                setDo.setTenSetDo(objSetDo.getString("TenSetDo"));
                setDo.setDescriptionSetDo(objSetDo.getString("Description"));
                setDo.setHinhSetDo(objSetDo.getString("Hinh"));
                //List sản phẩm
                JSONArray jsonArrSanPham = objSetDo.getJSONArray("SanPham");
                for (int j = 0; j < jsonArrSanPham.length(); j++) {
                    JSONObject objSanPham = jsonArrSanPham.getJSONObject(j);
                    SanPham sanPham = new SanPham();
                    sanPham.setTenSanPham(objSanPham.getString("TenSanPham"));
                    sanPham.setDescription(objSanPham.getString("Description"));
                    sanPham.setLoaiSanPham(objSanPham.getString("Loai"));
                    sanPham.setChiTietSanPham(objSanPham.getString("ChiTiet"));
                    //...
                }
            }
            Bundle data = new Bundle();
            data.putSerializable("DataXuHuongThoiTrang", xuHuongThoiTrang);
            dataCallBack.KetQua(SupportKeyList.LAY_DATA_THANH_CONG, data);
        } catch (JSONException e) {
            dataCallBack.KetQua(SupportKeyList.LOI_DATA, null);
        }
    }
}
