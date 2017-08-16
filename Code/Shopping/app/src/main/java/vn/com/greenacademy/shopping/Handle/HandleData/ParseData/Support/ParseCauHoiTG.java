package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Support;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.Support.CauHoiTG;

/**
 * Created by ADMIN on 8/14/2017.
 */

public class ParseCauHoiTG  {
    String data;
    public ParseCauHoiTG (String data) {
        this.data=data;
    }

    public ArrayList<CauHoiTG> parData(){
        ArrayList<CauHoiTG> result = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(data);
            if (root.getInt("Status") == 1){
                JSONArray jsonArray = root.getJSONArray("CauHoiTranfers");
                CauHoiTG cauHoiTG;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    cauHoiTG = new CauHoiTG();

                    cauHoiTG.setId(jsonObject.getInt("Id"));
                    cauHoiTG.setNoiDungCauHoi(jsonObject.getString("NoiDungCauHoi"));
                    cauHoiTG.setTraLoi(jsonObject.getString("TraLoi"));
                    cauHoiTG.setHtml(jsonObject.getString("Html"));

                    result.add(cauHoiTG);
                }
            }
//            result.setDescription(root.getString("Description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
