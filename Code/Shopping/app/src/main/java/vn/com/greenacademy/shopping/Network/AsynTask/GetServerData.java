package vn.com.greenacademy.shopping.Network.AsynTask;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import vn.com.greenacademy.shopping.Interface.ServerCallBack;

/**
 * Created by ADMIN on 7/25/2017.
 */

public class GetServerData extends AsyncTask<String, Object, String> {
    ServerCallBack serverCallBack;
    String key;

    public GetServerData(ServerCallBack serverCallBack) {
        this.serverCallBack = serverCallBack;
    }
    @Override
    protected String doInBackground(String... params) {
        URL url;
        if (params.length > 1){
            key = params[1];
        }
        try {
            url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // sever tra du lieu ve kiei xml
            connection.addRequestProperty("Accept", "application/json");
            // phuong thuc truyen len sever
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
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
        }
        return "false";
    }

    @Override
    protected void onPostExecute(String aVoid) {
        if (key != null){
            serverCallBack.serverCallBack(aVoid, key);
        }else {
            serverCallBack.serverCallBack(aVoid);
        }
    }
}