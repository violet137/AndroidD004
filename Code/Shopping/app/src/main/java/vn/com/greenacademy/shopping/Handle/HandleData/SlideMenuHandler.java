package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Adapter.AdapterSlideMenu;
import vn.com.greenacademy.shopping.Fragment.Main.MyShopping.MyShoppingFragment;
import vn.com.greenacademy.shopping.Model.ModeSlideMenu;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by ADMIN on 7/16/2017.
 */

public class SlideMenuHandler {

    ArrayList<ModeSlideMenu> arrayModeSlideMenus;
    int[] arrIcon;
    String[] arrName;
    AdapterSlideMenu adapterSlideMenu;
    Activity activity;

    public SlideMenuHandler(Activity activity) {
        this.activity = activity;
    }

    // xu ly su kien click cua nguoi dung
    public void itemClickListener(int position, BaseFragment baseFragment) {
        String temp ;
        switch (position){
            case SupportKeyList.Products_slide:
                temp = "Products";
                break;
            case SupportKeyList.Ladies_slide:
                temp = "Ladies";
                break;
            case SupportKeyList.Men_slide:
                temp = "Men";
                break;
            case SupportKeyList.Kids_slide:
                temp = "Kids";
                break;
            case SupportKeyList.Home_slide:
                temp = "Home";
                break;
            case SupportKeyList.Magazine_slide:
                temp = "Magazine";
                break;
            case SupportKeyList.Wish_list_slide:
                temp = "Wish list";
                break;
            case SupportKeyList.My_Shopping_slide:
                temp = "My Shopping";
                int count = ((AppCompatActivity)activity).getSupportFragmentManager().getBackStackEntryCount();
                while(count > 0){
                    baseFragment.XoaFragment();
                    count--;
                }
                baseFragment.ChuyenFragment(new MyShoppingFragment(), SupportKeyList.TAG_FRAGMENT_MY_SHOPPING, true);
                break;
            case SupportKeyList.Support_slide:
                temp = "Support";
                break;
            case SupportKeyList.Find_a_store_slide:
                temp = "Find a store";
                break;
            case SupportKeyList.Newsletter_slide:
                temp = "Newsletter";
                break;
            default:
                temp = "null";
                break;
        }
        Toast.makeText(activity, temp, Toast.LENGTH_SHORT).show();
    }

    // tai du lieu tu adapter len list
    public void displayListview(ListView listView) {
        adapterSlideMenu = new AdapterSlideMenu(activity, R.layout.item_slide_menu, arrayModeSlideMenus);
        listView.setAdapter(adapterSlideMenu);
    }

    // tai du lieu tu file xml cua may vao doi tuong array de dua vao adapter
    public void loadData() {
        arrName = activity.getResources().getStringArray(R.array.name_slide_menu);
        TypedArray listAnh = activity.getResources().obtainTypedArray(R.array.icon_slide_menu);
        arrIcon = new int[arrName.length];
        for(int i=0; i< arrName.length;i++){
            arrIcon[i]=listAnh.getResourceId(i,-1);
        }

        arrayModeSlideMenus = new ArrayList<ModeSlideMenu>();
        for(int i = 0; i< arrName.length; i++){
            ModeSlideMenu modeSlideMenu = new ModeSlideMenu();
            modeSlideMenu.setTen(arrName[i]);
            modeSlideMenu.setIcon(arrIcon[i]);
            arrayModeSlideMenus.add(modeSlideMenu);
        }

    }


}
