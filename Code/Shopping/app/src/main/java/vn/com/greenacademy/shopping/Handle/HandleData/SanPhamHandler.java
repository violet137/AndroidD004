package vn.com.greenacademy.shopping.Handle.HandleData;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by zzzzz on 8/7/2017.
 */

public class SanPhamHandler {

    public static String chuyenGia(long gia){
        DecimalFormat formatGia = new DecimalFormat("###,###,###");
        return formatGia.format(gia) + " đ";
    }
}
