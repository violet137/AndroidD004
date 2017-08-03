package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.AdvertisePhoto;
import vn.com.greenacademy.shopping.Model.MenuPhoto;

/**
 * Created by ADMIN on 8/3/2017.
 */

public class ParseAdvertise {
    String data;
    public ParseAdvertise (String data) {
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

