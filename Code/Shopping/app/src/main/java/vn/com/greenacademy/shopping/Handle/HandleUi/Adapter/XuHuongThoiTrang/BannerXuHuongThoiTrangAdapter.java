package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.XuHuongThoiTrang;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang.XuHuongThoiTrangFragment;
import vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder.XuHuongThoiTrangHolder;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by zzzzz on 7/13/2017.
 */

public class BannerXuHuongThoiTrangAdapter extends RecyclerView.Adapter<XuHuongThoiTrangHolder> {
    private Context context;
    private BaseFragment baseFragment;

    public BannerXuHuongThoiTrangAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        baseFragment = new BaseFragment(fragmentManager);
    }

    @Override
    public XuHuongThoiTrangHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.item_banner_xu_huong_thoi_trang, parent, false);
        return new XuHuongThoiTrangHolder(root);
    }

    @Override
    public void onBindViewHolder(final XuHuongThoiTrangHolder holder, int position) {
        //Data test
        TypedArray tempListBanner = context.getResources().obtainTypedArray(R.array.arr_hinh);
        int[] listBanner = new int[3];
        for (int i = 0; i < 3; i++) {
            listBanner[i] = tempListBanner.getResourceId(i, -1);
        }
        holder.banner.setImageResource(listBanner[position]);

        holder.banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseFragment.ChuyenFragment(new XuHuongThoiTrangFragment(holder.getLayoutPosition()), SupportKeyList.TAG_XU_HUONG_THOI_TRANG, true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
