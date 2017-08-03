package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.ThongTinSanPham.HinhSanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

/**
 * Created by ADMIN on 8/3/2017.
 */

public class ParseFIDProduct {
    String data;
    public ParseFIDProduct (String data) {
        this.data=data;
    }

    public SanPham parData(){
        SanPham sanPham = new SanPham();
        try {
            JSONObject root = new JSONObject(data);
            if (root.getInt("Status") == 1){
                JSONObject objSanPham = root.getJSONArray("SanPhamTranfers").getJSONObject(0);
                sanPham = new SanPham();
                sanPham.setIdSanPham(objSanPham.getInt("Id"));
                sanPham.setTenSanPham(objSanPham.getString("TenSanPham"));
                sanPham.setNgayTao(objSanPham.getString("NgayTao"));
                sanPham.setGiaSanPham(objSanPham.getLong("GiaTien"));
                sanPham.setGiamGia(objSanPham.getLong("GiaTienGiam"));
                sanPham.setDescription(objSanPham.getString("MoTa"));
                sanPham.setLoaiSanPham(objSanPham.getString("LoaiThoiTrang"));
                sanPham.setChiTietSanPham(objSanPham.getString("ChiTiet"));

                //List hình
                JSONArray arrHinhSanPham = objSanPham.getJSONArray("LinkHinh");
                ArrayList<HinhSanPham> listHinhSanPham = new ArrayList<>();
                for (int k = 0; k < arrHinhSanPham.length(); k++) {
                    JSONObject objHinhSanPham = arrHinhSanPham.getJSONObject(k);
                    HinhSanPham hinhSanPham = new HinhSanPham();
                    hinhSanPham.setMau(objHinhSanPham.getString("MauSac"));
                    JSONArray arrListHinh = objHinhSanPham.getJSONArray("LinkHinh");
                    String[] listHinh = new String[arrHinhSanPham.length()];
                    for (int l = 0; l < listHinh.length; l++) {
                        listHinh[l] = arrListHinh.getString(l);
                    }
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

                //Sản phẩm phù hợp
                String[] listSanPhamPhuHop = new String[objSanPham.getJSONArray("SpPhuHop").length()];
                for (int k = 0; k < objSanPham.getJSONArray("SpPhuHop").length(); k++) {
                    listSanPhamPhuHop[k] = objSanPham.getJSONArray("SpPhuHop").getString(k);
                }
                sanPham.setSanPhamPhuHop(listSanPhamPhuHop);

                sanPham.setDanhMucHangId(objSanPham.getInt("DanhMucHangId"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//            result.setDescription(root.getString("Description"));
        return sanPham;
    }
}
