package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Magazine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.Magazine.MagazineType;

/**
 * Created by ADMIN on 8/3/2017.
 */

public class ParseMagazineType {
    String data;
    public ParseMagazineType (String data) {
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
