package vn.com.greenacademy.shopping.Network.AsynTask;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import vn.com.greenacademy.shopping.Model.Md_Account;
import vn.com.greenacademy.shopping.Network.ParserData.Par_GoogleID;

/**
 * Created by ADMIN on 7/6/2017.
 */

public class GooglePlusAsyncTask extends AsyncTask<String, Void, String>{
    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL("http://tamod.vn:9045/TaiKhoan/DangKy");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // sever tra du lieu ve kiei xml
            connection.addRequestProperty("Accept", "text/json");
            // du lieu dua len
            connection.addRequestProperty("Content-Type", "application/json");
            // phuong thuc truyen len sever
            connection.setRequestMethod("POST");
            // gui data
            OutputStream outputStream = connection.getOutputStream();
            JSONObject object = new JSONObject();
            object.put("Username", params[0]);
            object.put("MatKhau", params[1]);
            object.put("TenHienThi", params[2]);
            String json = object.toString();
            outputStream.write(json.getBytes());
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int leght;
                while ((leght = inputStream.read(buffer)) != -1) {
                    result.write(buffer, 0, leght);
                }
                return result.toString("UTF-8");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        Par_GoogleID parGoogleID = new Par_GoogleID(s);
        try {
            Md_Account modelAccount = parGoogleID.parID();
            modelAccount.getStatus();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
