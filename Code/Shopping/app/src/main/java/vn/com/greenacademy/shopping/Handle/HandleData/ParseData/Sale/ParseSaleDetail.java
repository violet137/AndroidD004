package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Sale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Product.ParseListSanPham;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Product.ParseSanPham;
import vn.com.greenacademy.shopping.Model.Sale;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.HinhSanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

/**
 * Created by ADMIN on 8/22/2017.
 */

public class ParseSaleDetail {
    String data;
    public ParseSaleDetail (String data) {
        this.data=data;
    }

    public Sale parData(){
        Sale result = new Sale();
        try {
            JSONObject root = new JSONObject(data);
            if (root.getInt("Status") == 1){

                result.setId(root.getInt("Id"));
                result.setHinhDaiDien(root.getString("HinhDaiDien"));
                result.setMota(root.getString("Mota"));
                result.setTen(root.getString("Ten"));

                SanPham sanPham;
                ArrayList<SanPham> sanPhamArrayList = new ArrayList<>();
                JSONArray listSanPham = root.getJSONArray("ListSanPham");

                for (int j = 0; j < listSanPham.length() ; j++) {
                    JSONObject objSanPham = listSanPham.getJSONObject(j);

                    sanPham = new SanPham();
                    sanPham.setIdSanPham(objSanPham.getInt("Id"));
                    sanPham.setTenSanPham(objSanPham.getString("Ten"));
                    sanPham.setNgayTao(objSanPham.getString("NgayTao"));
                    sanPham.setGiaSanPham(objSanPham.getLong("GiaTien"));
                    sanPham.setGiamGia(objSanPham.getLong("GiaTienGiam"));
                    sanPham.setDescription(objSanPham.getString("MoTa"));
                    sanPham.setLoaiSanPham(objSanPham.getString("LoaiThoiTrang"));
                    sanPham.setChiTietSanPham(objSanPham.getString("ChiTiet"));

                    //Link hình
                    JSONArray arrHinhSanPham = objSanPham.getJSONArray("LinkHinh");
                    ArrayList<HinhSanPham> listHinhSanPham = new ArrayList<>();
                    for (int k = 0; k < arrHinhSanPham.length(); k++) {
                        JSONObject objHinhSanPham = arrHinhSanPham.getJSONObject(k);
                        HinhSanPham hinhSanPham = new HinhSanPham();
                        hinhSanPham.setMau(objHinhSanPham.getString("MauSac"));

                        //List hình
                        int test = objHinhSanPham.getJSONArray("LinkHinh").length();
                        String[] listHinh = new String[objHinhSanPham.getJSONArray("LinkHinh").length()];
                        for (int l = 0; l < objHinhSanPham.getJSONArray("LinkHinh").length(); l++) {
                            listHinh[l] = objHinhSanPham.getJSONArray("LinkHinh").getString(l);
                        }
                        hinhSanPham.setLinkHinh(listHinh);
                        listHinhSanPham.add(hinhSanPham);
                    }
                    sanPham.setHinhSanPham(listHinhSanPham);

                    //Màu sắc
                    String[] listMauSanPham = new String[objSanPham.getJSONArray("MauSac").length()];
                    for (int k = 0; k < objSanPham.getJSONArray("MauSac").length(); k++) {
                        listMauSanPham[k] = objSanPham.getJSONArray("MauSac").getString(k);
                    }
                    sanPham.setMauSanPham(listMauSanPham);

                    //Size
                    String[] listSize = new String[objSanPham.getJSONArray("Size").length()];
                    for (int k = 0; k < objSanPham.getJSONArray("Size").length(); k++) {
                        listSize[k] = objSanPham.getJSONArray("Size").getString(k);
                    }
                    sanPham.setSize(listSize);

                    sanPham.setDanhMucHangId(objSanPham.getInt("DanhMucHangId"));

                    sanPhamArrayList.add(sanPham);
                }
                result.setSanPhamArrayList(sanPhamArrayList);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}