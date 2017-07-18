package vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Main.SanPham.ThongTinSanPhamFragment;
import vn.com.greenacademy.shopping.Model.SanPham;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class SanPhamHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private BaseFragment baseFragment;
    private ArrayList<SanPham> listSanPham;

    public SanPhamHolder(View itemView, BaseFragment baseFragment, ArrayList<SanPham> listSanPham) {
        super(itemView);
        ImageView imgSanPham = (ImageView) itemView.findViewById(R.id.san_pham_image);
        TextView tvTenSanPham = (TextView) itemView.findViewById(R.id.ten_san_pham_text_view);
        TextView tvGia = (TextView) itemView.findViewById(R.id.gia_text_view);
        LinearLayout itemSanPham = (LinearLayout) itemView.findViewById(R.id.layout_item_san_pham);

        itemSanPham.setOnClickListener(this);

        this.baseFragment = baseFragment;
        this.listSanPham = listSanPham;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_item_san_pham:
                baseFragment.ChuyenFragment(new ThongTinSanPhamFragment(getLayoutPosition(), listSanPham), SupportKeyList.TAG_THONG_TIN_SAN_PHAM, true);
                break;
        }
    }
}
