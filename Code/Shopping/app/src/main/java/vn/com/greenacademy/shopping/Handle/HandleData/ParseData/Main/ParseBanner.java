package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.BannerPhoto;
import vn.com.greenacademy.shopping.Model.MenuPhoto;

/**
 * Created by ADMIN on 8/3/2017.
 */

public class ParseBanner {
    String data;
    public ParseBanner (String data) {
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
                if (jsonObject.getString("LoaiBanner").equals("TapChi")){
                    bannerPhoto.setLoaiTapChi(jsonObject.getString("LoaiTapChi"));
                    bannerPhoto.setName(jsonObject.getString("TenTapChi"));
                }
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
