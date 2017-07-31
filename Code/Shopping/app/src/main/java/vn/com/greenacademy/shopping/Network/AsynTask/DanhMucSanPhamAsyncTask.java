package vn.com.greenacademy.shopping.Network.AsynTask;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.DanhMucSanPhamAdapter.DanhMucSanPhamAdapter;
import vn.com.greenacademy.shopping.Model.DanhMucSanPhamModel.DanhMucSanPhamModel;

/**
 * Created by Administrator on 29/07/2017.
 */

public class DanhMucSanPhamAsyncTask extends AsyncTask<String,Void,String> {
    Context context;
    ListView lv_danhmucsp;
    ImageView img_danhmucsp;

    public DanhMucSanPhamAsyncTask(Context context, TextView tv_danhmucsp, ListView lv_danhmucsp, ImageView img_danhmucsp) {
        this.context = context;
        this.lv_danhmucsp = lv_danhmucsp;
        this.img_danhmucsp = img_danhmucsp;
    }

    public static DanhMucSanPhamModel.DanhMucSanPham danhMucSanPham;
    public static ArrayList<String> listTenDanhMuc;
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject root = new JSONObject(s);
            DanhMucSanPhamModel danhMucSanPhamModel = new DanhMucSanPhamModel();
            danhMucSanPhamModel.Status = root.getInt("Status");
            danhMucSanPhamModel.Description = root.getString("Description");
            JSONObject danhMucHangJson = root.getJSONObject("DanhMucHangTranfers");

            DanhMucSanPhamModel.DanhMucHangTranfers danhMucHangTranfers=new DanhMucSanPhamModel.DanhMucHangTranfers();

            danhMucHangTranfers.LoaiThoiTrang = danhMucHangJson.getString("LoaiThoiTrang");
            danhMucHangTranfers.XuHuongTTrangId = danhMucHangJson.getInt("XuHuongTtrangId");
            danhMucHangTranfers.XuHuongTTrangLink = danhMucHangJson.getString("XuHuongTtrangLink");
            JSONArray danhMucListJson = danhMucHangJson.getJSONArray("DanhMucList");
            danhMucSanPham = new DanhMucSanPhamModel.DanhMucSanPham();
            listTenDanhMuc = new ArrayList<>();
            for (int i=0;i<danhMucListJson.length();i++){
                JSONObject json=danhMucListJson.getJSONObject(i);

                danhMucSanPham.Id = json.getInt("Id");
                danhMucSanPham.TenDanhMuc = json.getString("TenDanhMuc");
                danhMucSanPham.LoaiThoiTrang = json.getString("LoaiThoiTrang");
                danhMucHangTranfers.listDanhMucSP.add(danhMucSanPham);
                listTenDanhMuc.add(json.getString("TenDanhMuc"));

            }
            Picasso.with(context).load(danhMucHangTranfers.getXuHuongTTrangLink())
                    .centerInside().into(img_danhmucsp);
            DanhMucSanPhamAdapter danhMucSanPhamAdapter = new DanhMucSanPhamAdapter(listTenDanhMuc,context);

            lv_danhmucsp.setAdapter(danhMucSanPhamAdapter);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ;
    }



    @Override
    protected String doInBackground(String ... params) {
        try {
            URL url = new URL("http://tamod.vn:8050/api/Home/DanhMucHang?loaiThoiTrang=" + params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("content-type","application/json");
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK ||
                    connection.getResponseCode() == HttpURLConnection.HTTP_CREATED){
                InputStream inputStream=connection.getInputStream();
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer))!=-1){
                    result.write(buffer,0,length);
                }
                return result.toString("UTF-8");
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
