package vn.com.greenacademy.shopping.Handle.HandleData.Home;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Magazine.MagazineDetailFragment;
import vn.com.greenacademy.shopping.Fragment.Magazine.MagazineFragment;
import vn.com.greenacademy.shopping.Fragment.Main.DanhMucSanPham.DanhMucSanPhamFragment;
import vn.com.greenacademy.shopping.Fragment.Main.SanPham.ThongTinSanPhamFragment;
import vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang.XuHuongThoiTrangFragment;
import vn.com.greenacademy.shopping.Model.Home.ItemHome;
import vn.com.greenacademy.shopping.Model.Home.ProductsPhoto;
import vn.com.greenacademy.shopping.Model.Item_recyclerView_magazine;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by ADMIN on 8/8/2017.
 */

public class ClickListenerHomeItem {
    public static View.OnClickListener onClickListener;

    public static void Click(final Activity activity){
        final BaseFragment baseFragment = new BaseFragment(((AppCompatActivity)activity), ((AppCompatActivity)activity).getSupportFragmentManager());
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemHome itemHome = (ItemHome) v.getTag();
                switch (itemHome.getFlagItemHome()){

                    case SupportKeyList.ClickHome_Advertise:
                        Toast.makeText(activity, itemHome.getId(), Toast.LENGTH_SHORT).show();
                        break;

                    case SupportKeyList.ClickHome_Products:
                        if (itemHome.getId().equals("TapChi")){
                            baseFragment.ChuyenFragment(new MagazineFragment(), SupportKeyList.TAG_FRAGMENT_MAGAZINE, true);
                        }else {
                            baseFragment.ChuyenFragment(new DanhMucSanPhamFragment(itemHome.getId()), SupportKeyList.TAG_DANH_MUC_SAN_PHAM, true);
                        }
                        break;

                    case SupportKeyList.ClickHome_NewProduct:
//                        Toast.makeText(activity, itemHome.getId(), Toast.LENGTH_SHORT).show();
                        baseFragment.ChuyenFragment(new ThongTinSanPhamFragment(itemHome.getId(), SupportKeyList.TAG_FRAGMENT_MAIN), SupportKeyList.TAG_FRAGMENT_MAIN, true);
                        break;

                    case SupportKeyList.ClickHome_Fashion:
//                        Toast.makeText(activity, itemHome.getId(), Toast.LENGTH_SHORT).show();
                        baseFragment.ChuyenFragment(new XuHuongThoiTrangFragment(Long.parseLong(itemHome.getId())), SupportKeyList.TAG_THONG_TIN_SAN_PHAM, true);
                        break;

                    case SupportKeyList.ClickHome_Magazine:
                        baseFragment.ChuyenFragment(new MagazineDetailFragment(itemHome.getId()), SupportKeyList.TAG_FRAGMENT_MAGAZINE_DETAIL, true);
                        break;

                    case SupportKeyList.ClickHome_Button_Magazine:
                        baseFragment.ChuyenFragment(new MagazineFragment(), SupportKeyList.TAG_FRAGMENT_MAGAZINE, true);
                        break;

                    default:
                        break;
                }
            }
        };
    }
}
