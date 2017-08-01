package vn.com.greenacademy.shopping.Network.AsynTask;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import vn.com.greenacademy.shopping.Interface.ErrorCallBack;
import vn.com.greenacademy.shopping.Model.IDNew;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 7/19/2017.
 */

public class PostIDNew  extends AsyncTask<Object, Object, String> {

    ErrorCallBack errorCallBack;
    public PostIDNew(ErrorCallBack errorCallBack) {
        this.errorCallBack = errorCallBack;
    }
//    String id;
//    String pass;
//    String name;
//
//    public PostIDNew (String id, String pass, String name) {
//        this.id = id;
//        this.pass = pass;
//        this.name = name;
//    }

    @Override
    protected String doInBackground(Object... params) {
        URL url = null;
        try {
            url = new URL((String) params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // sever tra du lieu ve kiei xml
            connection.addRequestProperty("Accept", "text/json");
            // du lieu dua len
            connection.addRequestProperty("Content-Type", "application/json");
            // phuong thuc truyen len sever
            connection.setRequestMethod("POST");

            connection.connect();
            // gui data
            OutputStream outputStream = connection.getOutputStream();
            JSONObject object = new JSONObject();
            object.put("Username", params[1]);
            object.put("Pwd", params[2]);
            object.put("Ten", params[3]);
            String json = object.toString();
            outputStream.write(json.getBytes());

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK || connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {

                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int leght;
                while ((leght = inputStream.read(buffer)) != -1) {
                    result.write(buffer, 0, leght);
                }
                return result.toString("UTF-8");

            } else if (connection.getResponseCode() == 500){
                Log.d("Loi Server", "doInBackground: ");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "false";
    }

    @Override
    protected void onPostExecute(String aVoid) {
        ParDataPostIDNew par = new ParDataPostIDNew(aVoid);
        try {
            errorCallBack.errorCallBack(String.valueOf(par.parData()), SupportKeyList.Email_Error);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
class ParDataPostIDNew {
    String data;

    public ParDataPostIDNew(String data) {
        this.data = data;
    }

    public int parData() throws JSONException {
        JSONObject root = new JSONObject(data);
        return root.getInt("Status");
    }
}