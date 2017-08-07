package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;
import android.content.res.TypedArray;

import java.text.DecimalFormat;
import java.text.NumberFormat;

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
            default:
                return arrayMauSP.getResourceId(Integer.parseInt("1"),-1);
        }
    }
}
