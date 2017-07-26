package vn.com.greenacademy.shopping.Network.AsynTask;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import vn.com.greenacademy.shopping.Interface.UrlPhotoCallBack;
import vn.com.greenacademy.shopping.Model.AdvertisePhoto;
import vn.com.greenacademy.shopping.Model.FashionType;
import vn.com.greenacademy.shopping.Model.IDNew;
import vn.com.greenacademy.shopping.Model.MenuPhoto;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 7/25/2017.
 */

public class GetAdvertise  extends AsyncTask<String, Object, String> {
    UrlPhotoCallBack urlPhotoCallBack;

    public GetAdvertise (UrlPhotoCallBack urlPhotoCallBack) {
        this.urlPhotoCallBack = urlPhotoCallBack;
    }
    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // sever tra du lieu ve kiei xml
            connection.addRequestProperty("Accept", "text/json");
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
        ParDataGetAdvertise par = new ParDataGetAdvertise(aVoid);
        urlPhotoCallBack.urlCallBack(par.parData(), SupportKeyList.Advertise_Url);
    }
}
class ParDataGetAdvertise {
    String data;
    public ParDataGetAdvertise (String data) {
        this.data=data;
    }

    public MenuPhoto parData(){
        MenuPhoto result = new MenuPhoto();
        ArrayList<AdvertisePhoto> temp;
        try {
            JSONObject root = new JSONObject(data);
            JSONArray jsonArray = root.getJSONArray("KhuyenMaiTranfers");
            temp = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                AdvertisePhoto advertisePhoto = new AdvertisePhoto();
                advertisePhoto.setId(jsonObject.getLong("Id"));
                advertisePhoto.setHinhDaiDien(jsonObject.getString("HinhDaiDien"));
                temp.add(advertisePhoto);
            }
            result.setAdvertisePhotoArrayList(temp);
            result.setStatus(root.getInt("Status"));
            result.setDescription(root.getString("Description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
