package vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Main.SanPham.SanPhamPagerFragment;
import vn.com.greenacademy.shopping.Interface.UpdateDataViewPagerCallBack;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;

/**
 * Created by zzzzz on 7/17/2017.
 */

public class SanPhamPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<SanPham> listSanPham;
    private int pagePosition;
    private String actionForSinglePage;
    private Bundle bundleChangePage;

    public static String ACTION_HIDE_GIA = "HideGia";
    public static String ACTION_SHOW_GIA = "ShowGia";
    public static String ACTION_DOI_HINH_SAN_PHAM = "DoiHinh";

    public SanPhamPagerAdapter(FragmentManager fm, ArrayList<SanPham> listSanPham) {
        super(fm);
        this.listSanPham = listSanPham;
    }

    @Override
    public Fragment getItem(int position) {
        SanPhamPagerFragment sanPhamPagerFragment = new SanPhamPagerFragment(position, listSanPham);
        Bundle arg = new Bundle();
        arg.putInt("pagePosition", position);
        sanPhamPagerFragment.setArguments(arg);
        return sanPhamPagerFragment;
    }

    @Override
    public int getCount() {
        return listSanPham.size();
    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof UpdateDataViewPagerCallBack) {
            ((UpdateDataViewPagerCallBack) object).updateData(pagePosition, actionForSinglePage, bundleChangePage);
        }
        return super.getItemPosition(object);
    }

    //Cập nhật giao diện của page khi có thay đổi nội dung
    public void updateUiSinglePage(int pagePosition, String actionForSinglePage, Bundle bundleChangePage){
        this.pagePosition = pagePosition;
        this.actionForSinglePage = actionForSinglePage;
        this.bundleChangePage = bundleChangePage;
        notifyDataSetChanged();
    }
}
