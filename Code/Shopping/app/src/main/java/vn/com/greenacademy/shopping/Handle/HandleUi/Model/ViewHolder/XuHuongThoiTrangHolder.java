package vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang.XuHuongThoiTrangFragment;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by zzzzz on 7/13/2017.
 */

public class XuHuongThoiTrangHolder extends RecyclerView.ViewHolder {
    public ImageView banner;

    public XuHuongThoiTrangHolder(View itemView) {
        super(itemView);
        banner = (ImageView) itemView.findViewById(R.id.banner_xu_huong_thoi_trang);
    }
}
