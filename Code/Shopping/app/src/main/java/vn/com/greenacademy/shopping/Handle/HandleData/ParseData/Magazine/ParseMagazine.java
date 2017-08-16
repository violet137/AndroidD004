package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Magazine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.Magazine.Magazine;

/**
 * Created by ADMIN on 8/3/2017.
 */

public class ParseMagazine {
    String data;
    public ParseMagazine (String data) {
        this.data=data;
    }

    public ArrayList<Magazine> parData(){
        ArrayList<Magazine> result = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(data);
            if (root.getInt("Status") == 1){
                JSONArray jsonArray = root.getJSONArray("TapChiTranfers");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Magazine magazine = new Magazine();

                    magazine.setIdTapChi(jsonObject.getInt("IdTapChi"));
                    magazine.setLoaiTapChi(jsonObject.getString("LoaiTapChi"));
                    magazine.setTen(jsonObject.getString("Ten"));
                    magazine.setMoTa(jsonObject.getString("MoTa"));
                    magazine.setLinkHinh(jsonObject.getString("LinkHinh"));
//
                    result.add(magazine);
                }
            }
//            result.setDescription(root.getString("Description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
