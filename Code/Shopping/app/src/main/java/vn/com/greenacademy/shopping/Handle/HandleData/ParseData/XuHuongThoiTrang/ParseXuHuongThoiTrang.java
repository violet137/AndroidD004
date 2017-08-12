package vn.com.greenacademy.shopping.Handle.HandleData.ParseData.XuHuongThoiTrang;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Product.ParseListSanPham;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Model.SetDo;
import vn.com.greenacademy.shopping.Model.XuHuongThoiTrang;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by zzzzz on 8/12/2017.
 */

public class ParseXuHuongThoiTrang {
    private String data;
    private DataCallBack dataCallBack;

    public ParseXuHuongThoiTrang(String data, DataCallBack dataCallBack){
        this.data = data;
        this.dataCallBack = dataCallBack;
    }

    public void parseData(){
        try {
            //Parse data
            JSONObject root = new JSONObject(data);
            XuHuongThoiTrang xuHuongThoiTrang = new XuHuongThoiTrang();
            JSONObject objXuHuongThoiTrang = root.getJSONArray("XuHuongTtrangTranfers").getJSONObject(0);
            xuHuongThoiTrang.setIdXuHuong(objXuHuongThoiTrang.getInt("IdXuHuong"));
            xuHuongThoiTrang.setTenXuHuong(objXuHuongThoiTrang.getString("TenXuHuong"));
            xuHuongThoiTrang.setVideo(objXuHuongThoiTrang.getBoolean("IsVideo"));
            xuHuongThoiTrang.setLinkHinhMoTa(objXuHuongThoiTrang.getString("LinkHinhMoTa"));
            xuHuongThoiTrang.setHinhDaiDien(objXuHuongThoiTrang.getString("HinhDaiDien"));
            //List set đồ
            JSONArray jsonArrSetDo = objXuHuongThoiTrang.getJSONArray("ListSetDo");
            SetDo setDo;
            for (int i = 0; i < jsonArrSetDo.length(); i++) {
                JSONObject objSetDo = jsonArrSetDo.getJSONObject(i);
                setDo = new SetDo();
                setDo.setIdSetDo(objSetDo.getInt("Id"));
                setDo.setTenSetDo(objSetDo.getString("Ten"));
                setDo.setDescriptionSetDo(objSetDo.getString("MoTa"));
                setDo.setHinhMoTa(objSetDo.getString("HinhMoTa"));
                setDo.setNgayTao(objSetDo.getString("NgayTao"));
                setDo.setVideo(objSetDo.getBoolean("IsVideo"));
                setDo.setHinhDaiDien(objSetDo.getString("HinhDaiDien"));

                //List sản phẩm
                setDo.setListSanPham(new ParseListSanPham(objSetDo.getJSONArray("SanPham").toString()).parseData());

                xuHuongThoiTrang.getListSetDo().add(setDo);
            }
            //List sản phẩm
            JSONArray jsonArrListSanPham =  objXuHuongThoiTrang.getJSONArray("ListSanPham");
            xuHuongThoiTrang.setListSanPham(new ParseListSanPham(jsonArrListSanPham.toString()).parseData());

            //Truyền kết quả về cho class yêu cầu
            Bundle data = new Bundle();
            data.putSerializable("DataXuHuongThoiTrang", xuHuongThoiTrang);
            dataCallBack.KetQua(SupportKeyList.LAY_DATA_THANH_CONG, data);
        } catch (JSONException e) {
            dataCallBack.KetQua(SupportKeyList.LOI_DATA, null);
        }
    }
}
