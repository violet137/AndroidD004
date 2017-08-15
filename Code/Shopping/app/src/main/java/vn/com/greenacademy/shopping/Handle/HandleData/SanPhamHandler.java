package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 8/7/2017.
 */

public class SanPhamHandler {

    public TypedArray arrayMauSP;

    Activity activity;

    public SanPhamHandler(Activity activity) {
        this.activity = activity;
    }

    public static String chuyenGia(long gia){
        DecimalFormat formatGia = new DecimalFormat("###,###,###");
        return formatGia.format(gia) + " đ";
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
        // tao ban mau mau
        arrayMauSP = activity.getResources().obtainTypedArray(R.array.arr_mauSanPham);
        switch (mau){
            case "Cam":
                return arrayMauSP.getResourceId(Integer.parseInt("0"),-1);
            case "Den":
                return arrayMauSP.getResourceId(Integer.parseInt("1"),-1);
            case "Do":
                return arrayMauSP.getResourceId(Integer.parseInt("2"),-1);
            case "Hong":
                return arrayMauSP.getResourceId(Integer.parseInt("3"),-1);
            case "Nau":
                return arrayMauSP.getResourceId(Integer.parseInt("4"),-1);
            case "Reu":
                return arrayMauSP.getResourceId(Integer.parseInt("5"),-1);
            case "Tim":
                return arrayMauSP.getResourceId(Integer.parseInt("6"),-1);
            case "Vang":
                return arrayMauSP.getResourceId(Integer.parseInt("7"),-1);
            case "Xam":
                return arrayMauSP.getResourceId(Integer.parseInt("8"),-1);
            case "XanhDuong":
                return arrayMauSP.getResourceId(Integer.parseInt("9"),-1);
            case "XanhLa":
                return arrayMauSP.getResourceId(Integer.parseInt("10"),-1);
            case "Trang":
                return arrayMauSP.getResourceId(Integer.parseInt("11"),-1);
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
                    if (sttSanPham == 0){
                        Collections.addAll(result, sanPham.getMauSanPham());
                    } else {
                        //Màu
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
        Bundle bundle = new Bundle();
        bundle.putString("SoLuongSanPham", String.valueOf(sttSanPham));
        bundle.putSerializable("ListMau", result);
        return bundle;
    }
}
