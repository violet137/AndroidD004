package vn.com.greenacademy.shopping.Handle.HandleData.DanhMucSanPham;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.DanhMucSanPham.ChiTietDanhMucFragment;
import vn.com.greenacademy.shopping.Interface.ObjectCallBack;
import vn.com.greenacademy.shopping.Model.DanhMuc.MucSanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by ADMIN on 8/14/2017.
 */

public class ClickListenerDanhMucSanPham {
    public static View.OnClickListener onClickListener;
    public static View.OnClickListener onClickListenerHotProduct;
    public static ObjectCallBack objectCallBack;

    ArrayList<SanPham> arrHotProduct;


    Activity activity;
    private BaseFragment baseFragment;
    public ClickListenerDanhMucSanPham(Activity activity) {
        this.activity = activity;
        baseFragment = new BaseFragment(activity,((AppCompatActivity) activity).getSupportFragmentManager());

    }

    public void listSanPhamCallBcak(){
        objectCallBack = new ObjectCallBack() {
            @Override
            public void callBack(Object object, int flag) {
                arrHotProduct = (ArrayList<SanPham>) object;
            }
        };
    }

    public void Click(){
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MucSanPham mucSanPham = (MucSanPham) v.getTag();
                Toast.makeText(activity,mucSanPham.getTenDanhMuc(), Toast.LENGTH_SHORT).show();
                // gia tri -2 la id của mục text view xem tat ca
                if (mucSanPham.getId() >= 0 || mucSanPham.getId() == -2){
//                    if (mucSanPham.getId() == -2){
//                        Toast.makeText(activity,mucSanPham.getTenDanhMuc(),Toast.LENGTH_SHORT).show();
//                    }else {
                        baseFragment.ChuyenFragment(ChiTietDanhMucFragment.newInstance(mucSanPham.getId(), mucSanPham.getTenDanhMuc(), mucSanPham.getLoaiThoiTrang()), SupportKeyList.TAG_CHI_TIET_DANH_MUC_SAN_PHAM, true);
//                    }
                }
            }
        };
        onClickListenerHotProduct = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                SanPham item = (SanPham) v.getTag();

                Toast.makeText(activity,item.getTenSanPham(),Toast.LENGTH_SHORT).show();
                Toast.makeText(activity,"Số lượng sản phẩn trong list là: "+arrHotProduct.size(),Toast.LENGTH_SHORT).show();
            }
        };

    }

}
