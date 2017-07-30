package vn.com.greenacademy.shopping.Network.AsynTask;

import android.os.AsyncTask;
import android.widget.TextView;

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

import vn.com.greenacademy.shopping.Interface.MagazineCallBack;
import vn.com.greenacademy.shopping.Interface.MagazineDetailCallBack;
import vn.com.greenacademy.shopping.Model.Magazine;
import vn.com.greenacademy.shopping.Model.MagazineDetail;

/**
 * Created by ADMIN on 7/30/2017.
 */

public class GetMagazineDetail extends AsyncTask<String, Object, String> {
    MagazineDetailCallBack magazineDetailCallBack;
    TextView textViewMain;

    public GetMagazineDetail(MagazineDetailCallBack magazineDetailCallBack, TextView textViewMain) {
        this.magazineDetailCallBack = magazineDetailCallBack;
        this.textViewMain =textViewMain;
    }
    @Override
    protected String doInBackground(String... params) {
        URL url = null;
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
        ParDataGetMagazineDetail par = new ParDataGetMagazineDetail(aVoid);
        magazineDetailCallBack.magazineDetailCallBack(par.parData(),textViewMain);
    }
}
class ParDataGetMagazineDetail {
    String data;
    public ParDataGetMagazineDetail (String data) {
        this.data=data;
    }

    public MagazineDetail parData(){
        MagazineDetail result = new MagazineDetail();
        try {
            JSONObject root = new JSONObject(data);
            if (root.getInt("Status") == 1){
                result.setId(root.getJSONObject("TapChi").getInt("Id"));
                result.setTen(root.getJSONObject("TapChi").getString("Ten"));
                result.setMoTa(root.getJSONObject("TapChi").getString("Mota"));
                result.setLoaiTapChi(root.getJSONObject("TapChi").getString("LoaiTapChi"));
                result.setHinhDaiDien(root.getJSONObject("TapChi").getString("HinhDaiDien"));
                result.setNoiDung(root.getJSONObject("TapChi").getString("NoiDung"));
            }
//            result.setDescription(root.getString("Description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
