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
import vn.com.greenacademy.shopping.Model.BannerPhoto;
import vn.com.greenacademy.shopping.Model.FashionType;
import vn.com.greenacademy.shopping.Model.IDNew;
import vn.com.greenacademy.shopping.Model.MenuPhoto;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 7/25/2017.
 */

public class GetBanner  extends AsyncTask<String, Object, String> {
    UrlPhotoCallBack urlPhotoCallBack;

    public GetBanner (UrlPhotoCallBack urlPhotoCallBack) {
        this.urlPhotoCallBack = urlPhotoCallBack;
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
        ParDataGetBanner par = new ParDataGetBanner(aVoid);
        urlPhotoCallBack.urlCallBack(par.parData(), SupportKeyList.Banner_Url);
    }
}
class ParDataGetBanner {
    String data;
    public ParDataGetBanner (String data) {
        this.data=data;
    }

    public MenuPhoto parData(){
        MenuPhoto result = new MenuPhoto();
        ArrayList<BannerPhoto> temp;
        try {
            JSONObject root = new JSONObject(data);
            JSONArray jsonArray = root.getJSONArray("BannerHomeTranfers");
            temp = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                BannerPhoto bannerPhoto = new BannerPhoto();
                bannerPhoto.setId(jsonObject.getLong("Id"));
                bannerPhoto.setLinkAnh(jsonObject.getString("LinkAnh"));
                bannerPhoto.setLoaiBanner(jsonObject.getString("LoaiBanner"));
                temp.add(bannerPhoto);
            }
            result.setBannerPhotoArrayList(temp);
            result.setStatus(root.getInt("Status"));
            result.setDescription(root.getString("Description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
