package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Support;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.Magazine.Magazine;
import vn.com.greenacademy.shopping.Model.Support.LoaiHoTro;

/**
 * Created by ADMIN on 8/3/2017.
 */

public class ParseLoaiVanDe {
    String data;
    public ParseLoaiVanDe(String data) {
        this.data=data;
    }

    public ArrayList<LoaiHoTro> parData(){
        ArrayList<LoaiHoTro> result = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(data);
            if (root.getInt("Status") == 1){
                JSONArray jsonArray = root.getJSONArray("LoaiHoTroTranfers");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    LoaiHoTro loaiHoTro = new LoaiHoTro();

                    loaiHoTro.setIdHoTro(jsonObject.getString("IdHoTro"));
                    loaiHoTro.setTenHoTro(jsonObject.getString("TenHoTro"));

                    result.add(loaiHoTro);
                }
            }
//            result.setDescription(root.getString("Description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
