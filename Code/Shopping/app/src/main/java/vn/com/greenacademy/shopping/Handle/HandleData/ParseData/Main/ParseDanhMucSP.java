package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.DanhMucSP;
import vn.com.greenacademy.shopping.Model.MucSanPham;

/**
 * Created by ADMIN on 8/3/2017.
 */

public class ParseDanhMucSP {
    String data;
    public ParseDanhMucSP (String data) {
        this.data=data;
    }

    public DanhMucSP parData(){
        DanhMucSP result = new DanhMucSP();
        ArrayList<MucSanPham> temp = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(data);
            if (root.getInt("Status") == 1){
                JSONArray jsonArray = root.getJSONObject("DanhMucHangTranfers").getJSONArray("DanhMucList");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    MucSanPham mucSanPham = new MucSanPham();
                    mucSanPham.setId(jsonObject.getInt("Id"));
                    mucSanPham.setLoaiThoiTrang(jsonObject.getString("LoaiThoiTrang"));
                    mucSanPham.setTenDanhMuc(jsonObject.getString("TenDanhMuc"));

                    temp.add(mucSanPham);
                }
                result.setMucSanPhamArrayList(temp);
                result.setXuHuongTtrangId(root.getJSONObject("DanhMucHangTranfers").getInt("XuHuongTtrangId"));
                result.setLoaiThoiTrang(root.getJSONObject("DanhMucHangTranfers").getString("LoaiThoiTrang"));
                result.setXuHuongTtrangLink(root.getJSONObject("DanhMucHangTranfers").getString("XuHuongTtrangLink"));
            }
//            result.setDescription(root.getString("Description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}

