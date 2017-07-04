package vn.com.greenacademy.shopping.Asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import vn.com.greenacademy.shopping.Data.MySharedPreferences;
import vn.com.greenacademy.shopping.Fragment.Main.MainFragment;
import vn.com.greenacademy.shopping.Fragment.TaiKhoan.DangNhapFragment;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by zzzzz on 7/3/2017.
 */

public class GoiAPIServerAsyncTask extends AsyncTask<String, Void, Integer> {
    private Context context;
    private FragmentManager fragmentManager;
    private MySharedPreferences mySharedPreferences;

    //Key phân loại kết quả trả về của từng API
    private static final int DANG_NHAP_THAT_BAI = 0;
    private static final int DANG_NHAP_THANH_CONG = 1;
    private static final int DANG_KY_THAT_BAI = 2;
    private static final int DANG_KY_THANH_CONG = 3;

    public GoiAPIServerAsyncTask(Context context, FragmentManager fragmentManager){
        this.context = context;
        this.fragmentManager = fragmentManager;
        mySharedPreferences = new MySharedPreferences(context, SupportKeyList.PREFERENCES_TEN_FILE);
    }

    @Override
    protected Integer doInBackground(String... strings) {
        /*Thứ tự tham số truyền vào:
        * string[0]: loại API
        * string[1]: URL
        * string[2]: Tên đăng nhập
        * string[3]: Password
        * string[4]: Tên hiển thị (cho đăng ký)
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
                    object.put("Username", strings[2]);
                    object.put("MatKhau", strings[3]);
                    object.put("KieuTK", "0");
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
                        if(jsonObject.getInt("Status") == 1)
                            return DANG_NHAP_THANH_CONG;
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                return  DANG_NHAP_THAT_BAI;

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
                            return DANG_KY_THANH_CONG;
                    }
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
                return DANG_KY_THAT_BAI;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        //Xử lý kết quả từ server
        switch (result){
            case DANG_NHAP_THANH_CONG:
                //Hiện thông báo, chuyển vào màn hình main, lưu đăng nhập
                Toast.makeText(context, R.string.toast_dang_nhap_thanh_cong, Toast.LENGTH_SHORT).show();
                fragmentManager.beginTransaction().replace(R.id.content_Main, new MainFragment(context)).commit();
                mySharedPreferences.setSHAREDPREF_LUU_DANG_NHAP(true);
                break;

            case DANG_NHAP_THAT_BAI:
                Toast.makeText(context, R.string.toast_dang_nhap_that_bai, Toast.LENGTH_SHORT).show();
                break;

            case DANG_KY_THANH_CONG:
                Toast.makeText(context, R.string.toast_dang_ky_thanh_cong, Toast.LENGTH_SHORT).show();
                break;

            case DANG_KY_THAT_BAI:
                Toast.makeText(context, R.string.toast_dang_ky_that_bai, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
