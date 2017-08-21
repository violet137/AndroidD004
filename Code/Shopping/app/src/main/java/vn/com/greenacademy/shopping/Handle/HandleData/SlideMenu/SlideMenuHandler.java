package vn.com.greenacademy.shopping.Handle.HandleData.SlideMenu;

import android.app.Activity;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Magazine.MagazineFragment;
import vn.com.greenacademy.shopping.Fragment.Home.MainFragment;
import vn.com.greenacademy.shopping.Fragment.DanhMucSanPham.DanhMucSanPhamFragment;
import vn.com.greenacademy.shopping.Fragment.GioHang.GioHangFragment;
import vn.com.greenacademy.shopping.Fragment.Sale.SaleFragment;
import vn.com.greenacademy.shopping.Fragment.Store.FindStoreFragment;
import vn.com.greenacademy.shopping.Fragment.Support.SupportFragment;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.AdapterSlideMenu;
import vn.com.greenacademy.shopping.Fragment.MyShopping.MyShoppingFragment;
import vn.com.greenacademy.shopping.Model.SlideMenu;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by ADMIN on 7/16/2017.
 */

public class SlideMenuHandler extends LoadDataSlideMenuHandler{

    public SlideMenuHandler(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    // xu ly su kien click cua nguoi dung
    public void itemClickListener(int position) {
        BaseFragment baseFragment = new BaseFragment(activity,((AppCompatActivity)activity).getSupportFragmentManager());
        String temp ;
        int count = ((AppCompatActivity)activity).getSupportFragmentManager().getBackStackEntryCount();
        switch (position){
            case SupportKeyList.Products_slide:
                temp = "Products";
                while(count > 0){
                    baseFragment.XoaFragment();
                    count--;
                }
                baseFragment.ChuyenFragment(new MainFragment(), SupportKeyList.TAG_FRAGMENT_MAIN, true);
                break;
            case SupportKeyList.Ladies_slide:
                temp = "Ladies";
                while(count > 0){
                    baseFragment.XoaFragment();
                    count--;
                }
                baseFragment.ChuyenFragment(new DanhMucSanPhamFragment("Nu"), SupportKeyList.TAG_DANH_MUC_SAN_PHAM, true);
                break;
            case SupportKeyList.Men_slide:
                temp = "Men";
                while(count > 0){
                    baseFragment.XoaFragment();
                    count--;
                }
                baseFragment.ChuyenFragment(new DanhMucSanPhamFragment("Nam"), SupportKeyList.TAG_DANH_MUC_SAN_PHAM, true);
                break;
            case SupportKeyList.Kids_slide:
                temp = "Kids";
                while(count > 0){
                    baseFragment.XoaFragment();
                    count--;
                }
                baseFragment.ChuyenFragment(new DanhMucSanPhamFragment("TreEm"), SupportKeyList.TAG_DANH_MUC_SAN_PHAM, true);
                break;
            case SupportKeyList.Home_slide:
                temp = "Home";
                while(count > 0){
                    baseFragment.XoaFragment();
                    count--;
                }
                baseFragment.ChuyenFragment(new DanhMucSanPhamFragment("Home"), SupportKeyList.TAG_DANH_MUC_SAN_PHAM, true);
                break;
            case SupportKeyList.Sale_slide:
                temp = "Sale";
                while(count > 0){
                    baseFragment.XoaFragment();
                    count--;
                }
                baseFragment.ChuyenFragment(new SaleFragment(), SupportKeyList.TAG_FRAGMENT_SALE, true);
                break;
            case SupportKeyList.Magazine_slide:
                temp = "Magazine";
                while(count > 0){
                    baseFragment.XoaFragment();
                    count--;
                }
                baseFragment.ChuyenFragment(new MagazineFragment(), SupportKeyList.TAG_FRAGMENT_MAGAZINE, true);
                break;
            case SupportKeyList.Wish_list_slide:
                temp = "Wish list";
                while(count > 0){
                    baseFragment.XoaFragment();
                    count--;
                }
                baseFragment.ChuyenFragment(new GioHangFragment(), SupportKeyList.TAG_FRAGMENT_GIO_HANG, true);
                break;
            case SupportKeyList.My_Shopping_slide:
                temp = "My Shopping";
                while(count > 0){
                    baseFragment.XoaFragment();
                    count--;
                }
                baseFragment.ChuyenFragment(new MyShoppingFragment(), SupportKeyList.TAG_FRAGMENT_MY_SHOPPING, true);
                break;
            case SupportKeyList.Find_a_store_slide:
                temp = "Find a store";
                while(count > 0){
                    baseFragment.XoaFragment();
                    count--;
                }
                baseFragment.ChuyenFragment(new FindStoreFragment(), SupportKeyList.TAG_FRAGMENT_FINDSTORE, true);
                break;
            case SupportKeyList.Support_slide:
                temp = "Support";
                while(count > 0){
                    baseFragment.XoaFragment();
                    count--;
                }
                baseFragment.ChuyenFragment(new SupportFragment(), SupportKeyList.TAG_FRAGMENT_SUPPORT, true);
                break;
            default:
                temp = "null";
                break;
        }
    }

    // tai du lieu tu adapter len list
    public AdapterSlideMenu displayListview() {
        AdapterSlideMenu adapterSlideMenu = new AdapterSlideMenu(activity, R.layout.item_slide_menu,
                LoadDataSlideMenuHandler.arraySlideMenus);
        return adapterSlideMenu;
    }
}

class LoadDataSlideMenuHandler{

    int[] arrIcon;
    String[] arrName;
    Activity activity;
    public static ArrayList<SlideMenu> arraySlideMenus;

    public LoadDataSlideMenuHandler(Activity activity) {
        this.activity = activity;
    }

    // tai du lieu tu file xml cua may vao doi tuong array de dua vao adapter
    public void loadData() {

        arrName = activity.getResources().getStringArray(R.array.name_slide_menu);
        TypedArray listAnh = activity.getResources().obtainTypedArray(R.array.icon_slide_menu);

        arrIcon = new int[arrName.length];
        for(int i=0; i< arrName.length;i++){
            arrIcon[i]=listAnh.getResourceId(i,-1);
        }

        arraySlideMenus = new ArrayList<SlideMenu>();
        for(int i = 0; i< arrName.length; i++){
            SlideMenu slideMenu = new SlideMenu();
            slideMenu.setTen(arrName[i]);
            slideMenu.setIcon(arrIcon[i]);
            arraySlideMenus.add(slideMenu);
        }
    }

}

