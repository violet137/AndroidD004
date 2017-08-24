package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Sale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.Sale;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.HinhSanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

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
                SanPham sanPham;
                ArrayList<SanPham> sanPhamArrayList;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Sale sale = new Sale();

                    sale.setId(jsonObject.getInt("Id"));
                    sale.setHinhDaiDien(jsonObject.getString("HinhDaiDien"));
                    sale.setMota(jsonObject.getString("Mota"));
                    sale.setTen(jsonObject.getString("Ten"));


                    sanPhamArrayList = new ArrayList<>();
//                    JSONObject rootProducts = new JSONObject(data);
                    JSONArray listSanPham = jsonObject.getJSONArray("ListSanPham");
                    for (int j = 0; j < listSanPham.length(); j++) {
                        sanPham = new SanPham();
                        sanPham.setIdSanPham(listSanPham.getJSONObject(j).getInt("Id"));
                        sanPham.setTenSanPham(listSanPham.getJSONObject(j).getString("Ten"));
                        sanPham.setGiaSanPham(listSanPham.getJSONObject(j).getLong("GiaTien"));
                        sanPham.setGiamGia(listSanPham.getJSONObject(j).getLong("GiaGiam"));
                        sanPham.setHinhDaiDien(listSanPham.getJSONObject(j).getString("HinhSp"));
                        sanPham.setLoaiSanPham(listSanPham.getJSONObject(j).getString("LoaiThoiTrang"));

                        //Màu sắc
                        String[] listMauSanPham = new String[listSanPham.getJSONObject(j).getJSONArray("Mau").length()];
                        for (int k = 0; k < listSanPham.getJSONObject(j).getJSONArray("Mau").length(); k++) {
                            listMauSanPham[k] = listSanPham.getJSONObject(j).getJSONArray("Mau").getString(k);
                        }
                        sanPham.setMauSanPham(listMauSanPham);

                        sanPhamArrayList.add(sanPham);
                    }
                    sale.setSanPhamArrayList(sanPhamArrayList);

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
