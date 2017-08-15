package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Model.Home.MenuPhoto;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.HinhSanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

/**
 * Created by ADMIN on 8/8/2017.
 */

public class ParseNewProductList  {
    String data;
    public ParseNewProductList(String data) {
        this.data=data;
    }

    public MenuPhoto parData(){
        MenuPhoto rezult = new MenuPhoto();
        ArrayList<SanPham> sanPhamArrayList = new ArrayList<>();
        SanPham sanPham;
        try {
            JSONObject root = new JSONObject(data);
            if (root.getInt("Status") == 1){
                /// co the co loi o dong  can 30 get jsonArray ko get jsonObject
                JSONArray jsonArray = root.getJSONArray("SanPhamTranfers");
                for (int i = 0; i < jsonArray.length() ; i++) {
                    JSONObject objSanPham = jsonArray.getJSONObject(i);

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
//                    JSONArray arrListHinh = objHinhSanPham.getJSONArray("LinkHinh");
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

                    //Sản phẩm phù hợp
//                String[] listSanPhamPhuHop = new String[objSanPham.getJSONArray("SpPhuHop").length()];
//                for (int k = 0; k < objSanPham.getJSONArray("SpPhuHop").length(); k++) {
//                    listSanPhamPhuHop[k] = objSanPham.getJSONArray("SpPhuHop").getString(k);
//                }
//                sanPham.setSanPhamPhuHop(listSanPhamPhuHop);

                    sanPham.setDanhMucHangId(objSanPham.getInt("DanhMucHangId"));

                    sanPhamArrayList.add(sanPham);
                }

                rezult.setSanPhamArrayList(sanPhamArrayList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//            result.setDescription(root.getString("Description"));
        return rezult;
    }
}
