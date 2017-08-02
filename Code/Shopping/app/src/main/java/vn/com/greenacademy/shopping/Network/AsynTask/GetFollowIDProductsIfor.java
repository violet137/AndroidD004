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
import vn.com.greenacademy.shopping.Model.SanPham;

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
                JSONArray jsonArray = root.getJSONArray("SanPhamTranfers");
                ArrayList<HinhTheoMau> linkHinh;
                String[] mauSac;
                String[] spPhuHop;
                String[] size;

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    sanPham.setId(jsonObject.getInt("Id"));
                    sanPham.setTenSanPham(jsonObject.getString("Ten"));
                    sanPham.setNgayTao(jsonObject.getString("NgayTao"));
                    sanPham.setGiaTien(jsonObject.getLong("GiaTien"));
                    sanPham.setGiaTienGiam(jsonObject.getLong("GiaTienGiam"));
                    sanPham.setMoTa(jsonObject.getString("MoTa"));
                    sanPham.setChiTietSanPham(jsonObject.getString("ChiTiet"));

                    linkHinh = new ArrayList<>();
                    HinhTheoMau hinhTheoMau ;
                    JSONArray jsonArrayListLinkHinh = jsonObject.getJSONArray("LinkHinh");
                    for (int j = 0; j < jsonArrayListLinkHinh.length(); j++) {

                        hinhTheoMau = new HinhTheoMau();
                        hinhTheoMau.setMauSac(jsonArrayListLinkHinh.getJSONObject(j).getString("MauSac"));

                        JSONArray jsonArray1UrlPhotos = jsonArrayListLinkHinh.getJSONObject(j).getJSONArray("LinkHinh");
                        String[] urlPhotos = new String[jsonArray1UrlPhotos.length()];
                        for (int k = 0; k < jsonArray1UrlPhotos.length(); k++) {
                            urlPhotos[k] = jsonArray1UrlPhotos.getString(k);
                        }

                        hinhTheoMau.setLinkHinhArrayList(urlPhotos);

                        linkHinh.add(hinhTheoMau);
                    }
                    sanPham.setHinhTheoMauArrayList(linkHinh);

                    mauSac = new String[jsonObject.getJSONArray("MauSac").length()];
                    for (int j = 0; j < jsonObject.getJSONArray("MauSac").length(); j++) {
                        mauSac[j] = jsonObject.getJSONArray("MauSac").getString(j);
                    }
                    sanPham.setMauSac(mauSac);

                    size = new String[jsonObject.getJSONArray("Size").length()];
                    for (int j = 0; j < jsonObject.getJSONArray("Size").length() ; j++) {
                        size[j] = jsonObject.getJSONArray("Size").getString(j);
                    }
                    sanPham.setSize(size);

                    spPhuHop = new String[jsonObject.getJSONArray("SpPhuHop").length()];
                    for (int j = 0; j < jsonObject.getJSONArray("SpPhuHop").length(); j++) {
                        spPhuHop[j] = jsonObject.getJSONArray("SpPhuHop").getString(j);
                    }
                    sanPham.setSpPhuHop(spPhuHop);

                    sanPham.setDanhMucHangId(jsonObject.getInt("DanhMucHangId"));
                    sanPham.setLoaiSanPham("LoaiThoiTrang");

                }
            }
//            result.setDescription(root.getString("Description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sanPham;
    }
}
