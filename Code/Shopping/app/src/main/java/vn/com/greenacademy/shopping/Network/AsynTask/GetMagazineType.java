package vn.com.greenacademy.shopping.Network.AsynTask;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import vn.com.greenacademy.shopping.Interface.MagazineTypeCallBack;
import vn.com.greenacademy.shopping.Model.MagazineType;

/**
 * Created by ADMIN on 7/28/2017.
 */

public class GetMagazineType extends AsyncTask<String, Object, String> {
    MagazineTypeCallBack magazineTypeCallBack;

    public GetMagazineType (MagazineTypeCallBack magazineTypeCallBack) {
        this.magazineTypeCallBack = magazineTypeCallBack;
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
        ParDataGetMagazineType par = new ParDataGetMagazineType(aVoid);
        magazineTypeCallBack.magazineTypeCallBack(par.parData());
    }
}
class ParDataGetMagazineType {
    String data;
    public ParDataGetMagazineType (String data) {
        this.data=data;
    }

    public ArrayList<MagazineType> parData(){
        ArrayList<MagazineType> result = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(data);
            if (root.getInt("Status") == 1){
                JSONArray jsonArray = root.getJSONArray("LoaiTapChiTranfers");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    MagazineType magazineType = new MagazineType();

                    magazineType.setTen(jsonObject.getString("Ten"));
                    magazineType.setLoaiTapChi(jsonObject.getString("LoaiTapChi"));

                    result.add(magazineType);
                }
            }
//            result.setDescription(root.getString("Description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
