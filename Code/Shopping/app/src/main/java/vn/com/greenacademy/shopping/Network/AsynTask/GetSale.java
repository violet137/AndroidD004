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
import vn.com.greenacademy.shopping.Interface.SaleCallBack;
import vn.com.greenacademy.shopping.Model.MagazineType;
import vn.com.greenacademy.shopping.Model.Sale;

/**
 * Created by ADMIN on 8/1/2017.
 */

public class GetSale extends AsyncTask<String, Object, String> {
    SaleCallBack saleCallBack;

    public GetSale (SaleCallBack saleCallBack) {
        this.saleCallBack = saleCallBack;
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
        ParDataGetSale par = new ParDataGetSale(aVoid);
        saleCallBack.saleCallBack(par.parData());
    }
}
class ParDataGetSale {
    String data;
    public ParDataGetSale (String data) {
        this.data=data;
    }

    public ArrayList<Sale> parData(){
        ArrayList<Sale> result = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(data);
            if (root.getInt("Status") == 1){
                JSONArray jsonArray = root.getJSONArray("KhuyenMaiTranfers");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Sale sale = new Sale();

                    sale.setId(jsonObject.getInt("Id"));
                    sale.setHinhDaiDien(jsonObject.getString("HinhDaiDien"));
                    sale.setMota(jsonObject.getString("Mota"));
                    sale.setTen(jsonObject.getString("Ten"));

                    result.add(sale);
                }
            }
//            result.setDescription(root.getString("Description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
