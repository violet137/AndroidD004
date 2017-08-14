package vn.com.greenacademy.shopping.Handle.HandleData.DanhMucSanPham;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Model.MucSanPham;

/**
 * Created by ADMIN on 8/14/2017.
 */

public class ClickListenerDanhMucSanPham {
    public static View.OnClickListener onClickListener;

    Activity activity;

    public ClickListenerDanhMucSanPham(Activity activity) {
        this.activity = activity;
    }

    public void Click(){
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MucSanPham mucSanPham = (MucSanPham) v.getTag();
                Toast.makeText(activity,mucSanPham.getTenDanhMuc(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}
