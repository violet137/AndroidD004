package vn.com.greenacademy.shopping.asynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zzzzz on 7/3/2017.
 */

public class DangNhapAsyncTask extends AsyncTask<String, Void, Integer> {

    @Override
    protected Integer doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //Cài đặt các thiết lập gửi lên server
            conn.setRequestProperty("Accept", "json");
            conn.setRequestMethod("GET");
            conn.connect();

            //Kiểm tra kết nối thành công
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK || conn.getResponseCode() == HttpURLConnection.HTTP_CREATED){
                //Lấy status server trả về kiểm tra đăng nhập thành công hay không
                InputStream inputStream = conn.getInputStream();
                JSONObject jsonObject = new JSONObject(inputStream.toString());
                return jsonObject.getInt("Status");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        //Hiện thông báo kết quả đăng nhập
        if (integer == 1){
            Log.d("dangnhap","Đăng nhập thành công!");
        } else {
            Log.d("loginfailed", "Đăng nhập thất bại!");
        }
    }
}
