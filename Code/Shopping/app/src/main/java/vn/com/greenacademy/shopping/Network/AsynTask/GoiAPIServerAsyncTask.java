package vn.com.greenacademy.shopping.Network.AsynTask;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Product.ParseSanPham;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.HinhSanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Model.SetDo;
import vn.com.greenacademy.shopping.Model.XuHuongThoiTrang;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by zzzzz on 7/3/2017.
 */

public class GoiAPIServerAsyncTask extends AsyncTask<String, Void, String> {
    private DataCallBack dataCallBack;
    private String loaiTaiKhoan = null;
    private String API = null;

    public GoiAPIServerAsyncTask(DataCallBack dataCallBack){
        this.dataCallBack = dataCallBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        /*Thứ tự tham số truyền vào:
        * string[0]: loại API
        * string[1]: URL
        *
        * 1. API ĐĂNG NHẬP:
        * string[2]: loại tài khoản
        *
        * Account thường:
        * string[3]: Tên đăng nhập (email)
        * string[4]: Password
        * string[5]: Tên hiển thị (cho đăng ký)
        *
        * Account Google/Facebook:
        * string[3]: google id
        *
        * 2. API ĐĂNG KÝ:
        * string[3]: Username (email)
        * string[4]: Password
        * string[5]: Tên hiển thị
        * */

        try {
            URL url = new URL(strings[1]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //Kiểm tra API được gọi
            API = strings[0];
            switch (API){
                case SupportKeyList.API_DANG_NHAP:
                    loaiTaiKhoan = strings[2];
                    try {
                        //Cài đặt các thiết lập gửi lên server
                        conn.setRequestProperty("Accept", "json");
                        conn.setRequestProperty("content-type", "application/json; charset=utf-8");
                        conn.setRequestMethod("POST");

                        //Chuẩn bị data
                        OutputStream outputStream = conn.getOutputStream();
                        JSONObject object = new JSONObject();

                        //Phân loại tài khoản
                        switch (strings[2]){
                            case SupportKeyList.ACCOUNT_THUONG:
                                object.put("Username", strings[3]);
                                object.put("Pwd", strings[4]);
                                object.put("AccountType", SupportKeyList.ACCOUNT_THUONG);
                                object.put("NenTang", "Android");
                                break;
                            case SupportKeyList.ACCOUNT_GOOGLE:
                                object.put("Username", strings[3]);
                                object.put("Pwd", "123456");
                                object.put("AccountType", SupportKeyList.ACCOUNT_GOOGLE);
                                object.put("NenTang", "Android");
                                break;
                            case SupportKeyList.ACCOUNT_FACEBOOK:
                                object.put("Username", strings[3]);
                                object.put("Pwd", "123456");
                                object.put("AccountType", SupportKeyList.ACCOUNT_FACEBOOK);
                                object.put("NenTang", "Android");
                                break;
                        }
                        outputStream.write(object.toString().getBytes());
                        conn.connect();

                        //Kiểm tra kết nối thành công
                        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK || conn.getResponseCode() == HttpURLConnection.HTTP_CREATED){
                            //Lấy status server trả về kiểm tra đăng nhập thành công hay không
                            InputStream inputStream = conn.getInputStream();
                            ByteArrayOutputStream result = new ByteArrayOutputStream();
                            byte[] buffer = new byte[1024];
                            int lenght;

                            //Ghép từng đoạn data server trả về
                            while((lenght = inputStream.read(buffer)) != -1)
                                result.write(buffer, 0, lenght);

                            //Kiểm tra đăng nhập thành công
                            JSONObject jsonObject = new JSONObject(result.toString());
                            if(jsonObject.getInt("Status") == 1) {
                                return result.toString("UTF-8");
                            }
                        }
                    } catch (JSONException e) {
                        return SupportKeyList.LOI_DATA;
                    }
                    return  SupportKeyList.DANG_NHAP_THAT_BAI;

                case SupportKeyList.API_DANG_KY:
                    try {
                        //Cài đặt các thiết lập gửi lên server
                        conn.setRequestProperty("Accept", "json");
                        conn.setRequestProperty("content-type", "application/json; charset=utf-8");
                        conn.setRequestMethod("POST");

                        //Chuẩn bị data
                        OutputStream outputStream = conn.getOutputStream();
                        JSONObject object = new JSONObject();
                        object.put("Username", strings[2]);
                        object.put("MatKhau", strings[3]);
                        object.put("TenHienThi", strings[4]);
                        outputStream.write(object.toString().getBytes());
                        conn.connect();

                        //Kiểm tra kết nối thành công
                        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK || conn.getResponseCode() == HttpURLConnection.HTTP_CREATED){
                            InputStream inputStream = conn.getInputStream();
                            ByteArrayOutputStream result = new ByteArrayOutputStream();
                            byte[] buffer = new byte[1024];
                            int lenght;

                            while((lenght = inputStream.read(buffer)) != -1)
                                result.write(buffer, 0, lenght);

                            JSONObject jsonObject = new JSONObject(result.toString());
                            if(jsonObject.getInt("Status") == 1)
                                return SupportKeyList.DANG_KY_THANH_CONG;
                        } else if (conn.getResponseCode() == 500){
                            return SupportKeyList.LOI_KET_NOI;
                        }
                    } catch (JSONException e) {
                        return SupportKeyList.LOI_DATA;
                    }
                    return SupportKeyList.DANG_KY_THAT_BAI;

                case SupportKeyList.API_DATA_XU_HUONG_THOI_TRANG:
                    try {
                        //Cài đặt các thiết lập gửi server
                        conn.setRequestProperty("Accept", "application/json");
                        conn.setRequestMethod("GET");
                        conn.connect();

                        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK || conn.getResponseCode() == HttpURLConnection.HTTP_CREATED){
                            InputStream inputStream = conn.getInputStream();
                            ByteArrayOutputStream result = new ByteArrayOutputStream();
                            byte[] buffer = new byte[1024];
                            int length;
                            while((length = inputStream.read(buffer)) != -1){
                                result.write(buffer, 0, length);
                            }

                            JSONObject object = new JSONObject(result.toString("UTF-8"));
                            if (object.getInt("Status") == 1){
                                return result.toString("UTF-8");
                            }
                        }
                    } catch (JSONException e) {
                        return SupportKeyList.LOI_DATA;
                    }
                    return SupportKeyList.LOI_DATA_SERVER;
            }
        } catch (IOException e) {
            return SupportKeyList.LOI_KET_NOI;
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (!result.isEmpty()) {
            if (result.equals(SupportKeyList.LOI_KET_NOI) || result.equals(SupportKeyList.LOI_DATA_SERVER)){
                dataCallBack.KetQua(result, null);
            }

            //Phân loại kết quả của API
            switch (API) {
                case SupportKeyList.API_DANG_NHAP:
                    //Kiểm tra đăng nhập thành công
                    if (!result.equals(SupportKeyList.DANG_NHAP_THAT_BAI)) {
                        Bundle bundle = new Bundle();
                        try {
                            bundle.putString("Token", new JSONObject(result).getString("Token"));
                            //Parse data và trả kết quả về cho class yêu cầu
                            switch (loaiTaiKhoan) {
                                case SupportKeyList.ACCOUNT_THUONG:
                                    dataCallBack.KetQua(SupportKeyList.DANG_NHAP_THANH_CONG, bundle);
                                    break;
                                case SupportKeyList.ACCOUNT_GOOGLE:
                                    dataCallBack.KetQua(SupportKeyList.DANG_NHAP_GOOGLE_THANH_CONG, bundle);
                                    break;
                                case SupportKeyList.ACCOUNT_FACEBOOK:
                                    dataCallBack.KetQua(SupportKeyList.DANG_NHAP_FACEBOOK_THANH_CONG, bundle);
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        switch (loaiTaiKhoan) {
                            case SupportKeyList.ACCOUNT_THUONG:
                                dataCallBack.KetQua(SupportKeyList.DANG_NHAP_THAT_BAI, null);
                                break;
                            case SupportKeyList.ACCOUNT_GOOGLE:
                                dataCallBack.KetQua(SupportKeyList.DANG_NHAP_GOOGLE_THAT_BAI, null);
                                break;
                            case SupportKeyList.ACCOUNT_FACEBOOK:
                                dataCallBack.KetQua(SupportKeyList.DANG_NHAP_FACEBOOK_THAT_BAI, null);
                                break;
                        }
                    }
                    break;

                case SupportKeyList.API_DANG_KY:
                    //Kiểm tra đăng ký thành công
                    if (!result.equals(SupportKeyList.DANG_KY_THAT_BAI))
                        dataCallBack.KetQua(SupportKeyList.DANG_KY_THANH_CONG, null);
                    else
                        dataCallBack.KetQua(SupportKeyList.DANG_KY_THAT_BAI, null);
                    break;

                case SupportKeyList.API_DATA_XU_HUONG_THOI_TRANG:
                    XuLyData(result);
                    break;
                default:
                    break;
            }
        }
    }

    private void XuLyData(String result) {
        try {
            //Parse data
            JSONObject root = new JSONObject(result);
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
//                JSONArray jsonArrSanPham = objSetDo.getJSONArray("SanPham");
                SanPham sanPham;
                for (int j = 0; j < objSetDo.getJSONArray("SanPham").length(); j++) {
                    JSONObject objSanPham = objSetDo.getJSONArray("SanPham").getJSONObject(j);
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
//                    setDo.getListSanPham().add(new ParseSanPham(objSanPham.toString()).parData());
                    setDo.getListSanPham().add(sanPham);
                }
                xuHuongThoiTrang.getListSetDo().add(setDo);
            }
            //List sản phẩm
            JSONArray jsonArrListSanPham =  objXuHuongThoiTrang.getJSONArray("ListSanPham");
            for (int j = 0; j < jsonArrListSanPham.length(); j++) {
                JSONObject objSanPham = jsonArrListSanPham.getJSONObject(j);
                SanPham sanPham = new SanPham();
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
                xuHuongThoiTrang.getListSanPham().add(sanPham);
            }
            //Truyền kết quả về cho class yêu cầu
            Bundle data = new Bundle();
            data.putSerializable("DataXuHuongThoiTrang", xuHuongThoiTrang);
            dataCallBack.KetQua(SupportKeyList.LAY_DATA_THANH_CONG, data);
        } catch (JSONException e) {
            dataCallBack.KetQua(SupportKeyList.LOI_DATA, null);
        }
    }
}
