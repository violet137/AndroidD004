package vn.com.greenacademy.shopping.Handle.HandleData.DanhMucSanPham;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Fragment.Main.DanhMucSanPham.ChiTietDanhMucFragment;
import vn.com.greenacademy.shopping.Handle.HandleUi.Model.Support;
import vn.com.greenacademy.shopping.Model.MucSanPham;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by ADMIN on 8/14/2017.
 */

public class ClickListenerDanhMucSanPham {
    public static View.OnClickListener onClickListener;

    Activity activity;
    private BaseFragment baseFragment;
    public ClickListenerDanhMucSanPham(Activity activity) {
        this.activity = activity;
        baseFragment = new BaseFragment(activity,((AppCompatActivity) activity).getSupportFragmentManager());
    }

    public void Click(){
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MucSanPham mucSanPham = (MucSanPham) v.getTag();
                Toast.makeText(activity,mucSanPham.getTenDanhMuc(), Toast.LENGTH_SHORT).show();
                baseFragment.ChuyenFragment(ChiTietDanhMucFragment.newInstance(mucSanPham.getId(), mucSanPham.getTenDanhMuc(), mucSanPham.getLoaiThoiTrang()), SupportKeyList.TAG_CHI_TIET_DANH_MUC_SAN_PHAM, true);
            }
        };
    }
}
