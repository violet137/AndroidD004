package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 8/7/2017.
 */

public class SanPhamHandler {

    public TypedArray arrayMauSP;

    Activity activity;
    private Bundle bundleLocSanPham;

    public SanPhamHandler(Activity activity) {
        this.activity = activity;
        bundleLocSanPham = new Bundle();
    }

    public static String chuyenGia(long gia){
//        DecimalFormat formatGia = new DecimalFormat("000.000.000");
        NumberFormat formatGia = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return formatGia.format(gia);
    }

    public static String chuyenTenMau(String mau){
        switch (mau){
            case "Cam":
                return "Cam";
            case "Den":
                return "Đen";
            case "Do":
                return "Đỏ";
            case "Hong":
                return "Hồng";
            case "Nau":
                return "Nâu";
            case "Reu":
                return "Rêu";
            case "Tim":
                return "Tím";
            case "Vang":
                return "Vàng";
            case "Xam":
                return "Xám";
            case "XanhDuong":
                return "Xanh dương";
            case "XanhLa":
                return "Xanh lá";
            case "Trang":
                return "Trắng";
            default:
                return null;
        }
    }

    public int layMauTheoTen(String mau){
        switch (mau){
            case "Cam":
                return ContextCompat.getColor(activity, R.color.cam);
            case "Den":
                return ContextCompat.getColor(activity, R.color.den);
            case "Do":
                return ContextCompat.getColor(activity, R.color.mau_do);
            case "Hong":
                return ContextCompat.getColor(activity, R.color.hong);
            case "Nau":
                return ContextCompat.getColor(activity, R.color.nau);
            case "Reu":
                return ContextCompat.getColor(activity, R.color.reu);
            case "Tim":
                return ContextCompat.getColor(activity, R.color.tim);
            case "Vang":
                return ContextCompat.getColor(activity, R.color.vang);
            case "Xam":
                return ContextCompat.getColor(activity, R.color.xam);
            case "XanhDuong":
                return ContextCompat.getColor(activity, R.color.xanh_duong);
            case "XanhLa":
                return ContextCompat.getColor(activity, R.color.xanh_la);
            case "Trang":
                return ContextCompat.getColor(activity, R.color.trang);
        }
        return -1;
    }

    public int doiMaMau(String maMau){
        // tao ban mau mau
        arrayMauSP = activity.getResources().obtainTypedArray(R.array.arr_mauSP);
        switch (maMau){
            case "Cam":
                // cam
                return arrayMauSP.getResourceId(Integer.parseInt("0"),-1);
            case "Den":
                // den
                return arrayMauSP.getResourceId(Integer.parseInt("1"),-1);
            case "Do":
                // do
                return arrayMauSP.getResourceId(Integer.parseInt("2"),-1);
            case "Hong":
                // hong
                return arrayMauSP.getResourceId(Integer.parseInt("3"),-1);
            case "Nau":
                // nau
                return arrayMauSP.getResourceId(Integer.parseInt("4"),-1);
            case "Reu":
                // reu
                return arrayMauSP.getResourceId(Integer.parseInt("5"),-1);
            case "Tim":
                // tim
                return arrayMauSP.getResourceId(Integer.parseInt("6"),-1);
            case "Vang":
                // vang
                return arrayMauSP.getResourceId(Integer.parseInt("7"),-1);
            case "Xam":
                // xam
                return arrayMauSP.getResourceId(Integer.parseInt("8"),-1);
            case "XanhDuong":
                // xanh duong
                return arrayMauSP.getResourceId(Integer.parseInt("9"),-1);
            case "XanhLa":
                // xanh la
                return arrayMauSP.getResourceId(Integer.parseInt("10"),-1);
            case "Trang":
                // trang
                return arrayMauSP.getResourceId(Integer.parseInt("11"),-1);
            case "more":
                return arrayMauSP.getResourceId(Integer.parseInt("12"), -1);
            default:
                return arrayMauSP.getResourceId(Integer.parseInt("1"),-1);
        }
    }

    //return số lượng sản phẩm và list màu theo size
    public Bundle locMauTheoSize(String size, ArrayList<SanPham> listSanPham){
        ArrayList<String> result = new ArrayList<>();
        int sttListMau, sttSanPham = 0, sttMauListSanPham;
        boolean trungMau = false;
        SanPham sanPham;

        //Điều kiện dừng khi vượt quá số lượng màu của server hoặc quá số lượng sản phẩm của danh mục
        while(result.size() < 12 && sttSanPham < listSanPham.size()){
            sanPham = listSanPham.get(sttSanPham);

            //Nếu sản phẩm có size yêu cầu thì lấy toàn bộ màu của sp đó
            for (int i = 0; i < sanPham.getSize().length; i++) {
                if (size.equals(sanPham.getSize()[i])){
                    //Chạy từng sản phẩm để lấy màu
                    //Nếu là sản phẩm đầu tiên thì add toàn bộ màu vào list result
                    if (sttSanPham == 0){
                        Collections.addAll(result, sanPham.getMauSanPham());
                    } else {
                        for (sttMauListSanPham = 0; sttMauListSanPham < sanPham.getMauSanPham().length; sttMauListSanPham++) {
                            //So sanh từng màu của 1 sản phẩm với màu trong list màu
                            for (sttListMau = 0; sttListMau < result.size(); sttListMau++) {
                                //Nếu màu sản phẩm tại vị trí sttMauListSanPham không giống với toàn bộ màu của list màu thì thêm vào list màu
                                if (sanPham.getMauSanPham()[sttMauListSanPham].equals(result.get(sttListMau)))
                                    trungMau = true;
                            }
                            if (!trungMau)
                                result.add(sanPham.getMauSanPham()[sttMauListSanPham]);
                        }
                    }

                    sttSanPham++;
                    trungMau = false;
                    break;
                }
            }
        }
        bundleLocSanPham.putString("SoLuongSanPham", String.valueOf(sttSanPham));
        bundleLocSanPham.putSerializable("ListMau", result);
        return bundleLocSanPham;
    }

    //return số lượng sản phẩm và list size theo màu
    public Bundle locSizeTheoMau(String mau, ArrayList<SanPham> listSanPham){
        ArrayList<String> resultSize = new ArrayList<>();
        int sttListMau, sttSanPham = 0, sttMauListSanPham;
        boolean trungMau = false;
        SanPham sanPham;

        //Điều kiện dừng khi vượt quá số lượng màu của server hoặc quá số lượng sản phẩm của danh mục
        while(resultSize.size() < 4 && sttSanPham < listSanPham.size()){
            sanPham = listSanPham.get(sttSanPham);

            //Nếu sản phẩm có màu yêu cầu thì lấy toàn bộ size của sp đó
            for (int i = 0; i < sanPham.getSize().length; i++) {
                if (resultSize.equals(sanPham.getSize()[i])){
                    //Chạy từng sản phẩm để lấy màu
                    if (sttSanPham == 0){
                        Collections.addAll(resultSize, sanPham.getMauSanPham());
                    } else {
                        //Màu
                        for (sttMauListSanPham = 0; sttMauListSanPham < sanPham.getMauSanPham().length; sttMauListSanPham++) {
                            //So sanh từng màu của 1 sản phẩm với màu trong list màu
                            for (sttListMau = 0; sttListMau < resultSize.size(); sttListMau++) {
                                //Nếu màu sản phẩm tại vị trí sttMauListSanPham không giống với toàn bộ màu của list màu thì thêm vào list màu
                                if (sanPham.getMauSanPham()[sttMauListSanPham].equals(resultSize.get(sttListMau)))
                                    trungMau = true;
                            }
                            if (!trungMau)
                                resultSize.add(sanPham.getMauSanPham()[sttMauListSanPham]);
                        }
                    }

                    sttSanPham++;
                    trungMau = false;
                    break;
                }
            }
        }
        bundleLocSanPham.putString("SoLuongSanPham", String.valueOf(sttSanPham));
        bundleLocSanPham.putSerializable("ListMau", resultSize);
        return bundleLocSanPham;
    }
}
