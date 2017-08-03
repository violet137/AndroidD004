package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.FashionType;
import vn.com.greenacademy.shopping.Model.MenuPhoto;

/**
 * Created by ADMIN on 8/3/2017.
 */

public class ParseMyProducts {
    String data;
    public ParseMyProducts (String data) {
        this.data=data;
    }

    public MenuPhoto parData(){
        MenuPhoto result = new MenuPhoto();
        ArrayList<FashionType> temp;
        try {
            JSONObject root = new JSONObject(data);
            JSONArray jsonArray = root.getJSONArray("LoaiThoiTrangTranfers");
            temp = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                FashionType fashionType = new FashionType();
                fashionType.setTen(jsonObject.getString("Ten"));
                fashionType.setLinkHinh(jsonObject.getString("LinkHinh"));
                fashionType.setLoaiThoiTrang(jsonObject.getString("loaiThoiTrang"));
                temp.add(fashionType);
            }
            result.setFashionTypeArrayList(temp);
            result.setStatus(root.getInt("Status"));
            result.setDescription(root.getString("Description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}