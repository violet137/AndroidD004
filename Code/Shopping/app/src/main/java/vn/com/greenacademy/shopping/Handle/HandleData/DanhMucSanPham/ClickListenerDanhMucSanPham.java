package vn.com.greenacademy.shopping.Handle.HandleData.DanhMucSanPham;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

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
                Toast.makeText(activity,String.valueOf((int)v.getTag()), Toast.LENGTH_SHORT).show();
            }
        };
    }
}
