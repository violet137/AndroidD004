package vn.com.greenacademy.shopping.Network.AsynTask;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import vn.com.greenacademy.shopping.Interface.ProductDetailCallBack;
import vn.com.greenacademy.shopping.Model.HinhTheoMau;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.HinhSanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

/**
 * Created by ADMIN on 8/2/2017.
 */

public class GetFollowIDProductsIfor extends AsyncTask<String, Object, String> {
    ProductDetailCallBack productDetailCallBack;

    public GetFollowIDProductsIfor(ProductDetailCallBack productDetailCallBack) {
        this.productDetailCallBack = productDetailCallBack;
    }
    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // sever tra du lieu ve kiei xml
            connection.addRequestProperty("Accept", "application/json");
            // phuong thuc truyen len sever
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int leght;
                while ((leght = inputStream.read(buffer)) != -1) {
                    result.write(buffer, 0, leght);
                }
                return result.toString("UTF-8");

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "false";
    }

    @Override
    protected void onPostExecute(String aVoid) {
        ParDataGetFollowIDProductsIfor par = new ParDataGetFollowIDProductsIfor(aVoid);
        productDetailCallBack.callBack(par.parData());
    }
}
class ParDataGetFollowIDProductsIfor {
    String data;
    public ParDataGetFollowIDProductsIfor (String data) {
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
