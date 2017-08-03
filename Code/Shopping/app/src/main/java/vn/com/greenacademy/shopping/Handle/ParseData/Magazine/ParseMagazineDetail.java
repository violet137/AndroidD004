package vn.com.greenacademy.shopping.Handle.ParseData.Magazine;

import org.json.JSONException;
import org.json.JSONObject;

import vn.com.greenacademy.shopping.Model.MagazineDetail;

/**
 * Created by ADMIN on 8/3/2017.
 */

public class ParseMagazineDetail {
    String data;
    public ParseMagazineDetail (String data) {
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
