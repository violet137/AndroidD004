package vn.com.greenacademy.shopping.Handle.ParseData.Sale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.Sale;

/**
 * Created by ADMIN on 8/3/2017.
 */

public class ParseSale {
    String data;
    public ParseSale (String data) {
        this.data=data;
    }

    public ArrayList<Sale> parData(){
        ArrayList<Sale> result = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(data);
            if (root.getInt("Status") == 1){
                JSONArray jsonArray = root.getJSONArray("KhuyenMaiTranfers");
                ArrayList<Integer> temp;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Sale sale = new Sale();

                    sale.setId(jsonObject.getInt("Id"));
                    sale.setHinhDaiDien(jsonObject.getString("HinhDaiDien"));
                    sale.setMota(jsonObject.getString("Mota"));
                    sale.setTen(jsonObject.getString("Ten"));

                    temp = new ArrayList<>();
                    JSONArray jsonArrayListSanPham = jsonObject.getJSONArray("ListSanPham");
                    for (int j = 0; j < jsonArrayListSanPham.length(); j++) {
                        temp.add(jsonArrayListSanPham.getInt(j));
                    }

                    sale.setListIDSanPham(temp);

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
