package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Adapter.AdapterMenuMain;
import vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang.XuHuongThoiTrangFragment;
import vn.com.greenacademy.shopping.Model.ModeMenuMain;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by ADMIN on 7/18/2017.
 */

public class MainMenuHandler {

    ArrayList<ModeMenuMain> arrayModeMenuMain;
    String[] arrName;
    AdapterMenuMain adapterMenuMain;
    private BaseFragment baseFragment;
    Activity activity;
    View.OnClickListener onClickListener;


    public MainMenuHandler(Activity activity) {
        this.activity = activity;
    }

    // dieu khien phần click mục quang cao
    public void clickAdvertise(){
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "okokokokokokoo", Toast.LENGTH_SHORT).show();
            }
        };
    }

    // dieu khien phần click list main menu ko co phần click mục quang cao
    public void itemClickMenu(int position) {
        String temp ;
        baseFragment = new BaseFragment(((AppCompatActivity)activity).getSupportFragmentManager());
        switch (position){
//            case SupportKeyList.Advertise:
//                temp = "Quảng Cáo";
//                break;
            case SupportKeyList.Ladies:
                temp = "Ladies";
                baseFragment.ChuyenFragment(new XuHuongThoiTrangFragment(1), SupportKeyList.TAG_XU_HUONG_THOI_TRANG, false);
                break;
            case SupportKeyList.Men:
                temp = "Men";
                break;
            case SupportKeyList.Kids:
                temp = "Kids";
                break;
            case SupportKeyList.Home:
                temp = "Home";
                break;
            case SupportKeyList.Magazine:
                temp = "Magazine";
                break;
            default:
                temp = "Mục chưa biết";
                break;
        }
        Toast.makeText(activity, temp, Toast.LENGTH_SHORT).show();
    }

    // tai du lieu tu adapter len list
    public void displayListview(ListView listView) {
        adapterMenuMain = new AdapterMenuMain(activity, R.layout.item_listview_menu_main, arrayModeMenuMain,onClickListener);
        listView.setAdapter(adapterMenuMain);
    }

    // tai du lieu tu file xml cua may vao doi tuong array de dua vao adapter
    public void loadData() {
        arrName = activity.getResources().getStringArray(R.array.name_menu_main);
        String []arrLink_MenuPhotos = activity.getResources().getStringArray(R.array.link_MenuPhotos);
        arrayModeMenuMain = new ArrayList<>();
        for(int i = 0; i< (arrLink_MenuPhotos.length); i++){
            ModeMenuMain modeMenuMain = new ModeMenuMain();
            if (i<=5){
                modeMenuMain.setName(arrName[i]);
            }
            arrayModeMenuMain.add(modeMenuMain);
        }

    }
}
