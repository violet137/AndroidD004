package vn.com.greenacademy.shopping.Network.AsynTask;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by zzzzz on 7/3/2017.
 */

public class GoiAPIServerAsyncTask extends AsyncTask<String, Void, Integer> {
    private DataCallBack dataCallBack;
    private String loaiTaiKhoan;

    public GoiAPIServerAsyncTask(DataCallBack dataCallBack){
        this.dataCallBack = dataCallBack;
    }

    @Override
    protected Integer doInBackground(String... strings) {
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
        * string[3]: email
        *
        * 2. API ĐĂNG KÝ:
        * string[3]: Username (email)
        * string[4]: Password
        * string[5]: Tên hiển thị
        * */

        //Kiểm tra API được gọi
        switch (strings[0]){
            case SupportKeyList.API_DANG_NHAP:
                try {
                    URL url = new URL(strings[1]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
                            object.put("MatKhau", strings[4]);
                            object.put("KieuTK", "0");
                            break;
                        case SupportKeyList.ACCOUNT_GOOGLE:
                            object.put("Username", strings[3]);
                            object.put("KieuTK", "0");
                            break;
                        case SupportKeyList.ACCOUNT_FACEBOOK:
                            object.put("Username", strings[3]);
                            object.put("KieuTK", "0");
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

                        //Chuyển kết quả trả về từ string sang JSONObject
                        JSONObject jsonObject = new JSONObject(result.toString());
                        if(jsonObject.getInt("Status") == 1) {
                            loaiTaiKhoan = strings[2];
                            return SupportKeyList.DANG_NHAP_THANH_CONG;
                        }
                    }
                } catch (IOException | JSONException e) {
                    return SupportKeyList.LOI_KET_NOI;
                }
                return  SupportKeyList.DANG_NHAP_THAT_BAI;

            case SupportKeyList.API_DANG_KY:
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
                    conn.connect();

                    //Kiểm tra kết nối thành công
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK || conn.getResponseCode() == HttpURLConnection.HTTP_CREATED){
                        //Lấy status server trả về kiểm tra đăng nhập thành công hay không
                        InputStream inputStream = conn.getInputStream();
                        JSONObject jsonObject = new JSONObject(inputStream.toString());
                        if(jsonObject.getInt("Status") == 1)
                            return SupportKeyList.DANG_KY_THANH_CONG;
                    }
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
                return SupportKeyList.DANG_KY_THAT_BAI;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        //Trả kết quả về class yêu cầu
        switch (result){
            case SupportKeyList.LOI_KET_NOI:
                dataCallBack.KetQua(SupportKeyList.LOI_KET_NOI);
                break;
            case SupportKeyList.DANG_NHAP_THANH_CONG:
                switch (loaiTaiKhoan){
                    case SupportKeyList.ACCOUNT_THUONG:
                        dataCallBack.KetQua(SupportKeyList.DANG_NHAP_THANH_CONG);
                        break;
                    case SupportKeyList.ACCOUNT_GOOGLE:
                        dataCallBack.KetQua(SupportKeyList.DANG_NHAP_GOOGLE_THANH_CONG);
                        break;
                    case SupportKeyList.ACCOUNT_FACEBOOK:
                        dataCallBack.KetQua(SupportKeyList.DANG_NHAP_FACEBOOK_THANH_CONG);
                        break;
                }
                break;
            case SupportKeyList.DANG_NHAP_THAT_BAI:
                switch (loaiTaiKhoan){
                    case SupportKeyList.ACCOUNT_THUONG:
                        dataCallBack.KetQua(SupportKeyList.DANG_NHAP_THAT_BAI);
                        break;
                    case SupportKeyList.ACCOUNT_GOOGLE:
                        dataCallBack.KetQua(SupportKeyList.DANG_NHAP_GOOGLE_THAT_BAI);
                        break;
                    case SupportKeyList.ACCOUNT_FACEBOOK:
                        dataCallBack.KetQua(SupportKeyList.DANG_NHAP_FACEBOOK_THAT_BAI);
                        break;
                }
                break;
            case SupportKeyList.DANG_KY_THANH_CONG:
                dataCallBack.KetQua(SupportKeyList.DANG_KY_THANH_CONG);
                break;
            case SupportKeyList.DANG_KY_THAT_BAI:
                dataCallBack.KetQua(SupportKeyList.DANG_KY_THAT_BAI);
                break;
            default:
                break;
        }
    }
}
