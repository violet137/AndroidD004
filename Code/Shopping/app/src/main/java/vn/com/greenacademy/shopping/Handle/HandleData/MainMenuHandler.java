package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.AdapterMenuMain;
import vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang.XuHuongThoiTrangFragment;
import vn.com.greenacademy.shopping.Interface.UrlPhotoCallBack;
import vn.com.greenacademy.shopping.Model.AdvertisePhoto;
import vn.com.greenacademy.shopping.Model.MenuMain;
import vn.com.greenacademy.shopping.Model.MenuPhoto;
import vn.com.greenacademy.shopping.Network.AsynTask.GetMainMenuPhotos;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by ADMIN on 7/18/2017.
 */

public class MainMenuHandler {

    String[] arrName;
    AdapterMenuMain adapterMenuMain;
    private BaseFragment baseFragment;
    Activity activity;
    View.OnClickListener onClickListener;
    UrlPhotoCallBack urlPhotoCallBack;

    public MainMenuHandler(Activity activity) {
        this.activity = activity;
    }


    // dieu khien phần click mục quang cao
    public void clickAdvertise(){
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvertisePhoto advertisePhoto = (AdvertisePhoto) v.getTag();
                String temp = null;
                switch (advertisePhoto.getId()){
                    case 0:
                        temp = "Sale up to 50%";
                        break;
                    case 1:
                        temp = "Phong cách đơn giản";
                        break;
                    case 2:
                        temp = "Canada fashion";
                        break;
                    default:
                        temp = "ok";
                        break;
                }
                Toast.makeText(activity, temp, Toast.LENGTH_SHORT).show();
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
                baseFragment.ChuyenFragment(new XuHuongThoiTrangFragment(1), SupportKeyList.TAG_XU_HUONG_THOI_TRANG, true);
                break;
        }
        Toast.makeText(activity, temp, Toast.LENGTH_SHORT).show();
    }

    // tai du lieu tu adapter len list
    public void displayListview(final ListView listView) {
        urlPhotoCallBack = new UrlPhotoCallBack() {
            @Override
            public void urlCallBack(MenuPhoto menuPhoto) {
                ArrayList<MenuMain> mainArrayList = new ArrayList<>();
                for (int i = 0; i < menuPhoto.getFashionTypeArrayList().size(); i++) {
                    MenuMain menuMain = new MenuMain();
                    menuMain.setUrl(menuPhoto.getFashionTypeArrayList().get(i).getLinkHinh());
                    menuMain.setId(menuPhoto.getFashionTypeArrayList().get(i).getLoaiThoiTrang());
                    mainArrayList.add(menuMain);
                }
                adapterMenuMain = new AdapterMenuMain(activity, R.layout.item_listview_menu_main, mainArrayList,onClickListener);
                listView.setAdapter(adapterMenuMain);
            }
        };
    }

    // tai du lieu tu file xml cua may vao doi tuong array de dua vao adapter
    public void loadData() {
        GetMainMenuPhotos getMainMenuPhotos  = new GetMainMenuPhotos(urlPhotoCallBack);
        getMainMenuPhotos.execute(ServerUrl.UrlDanhSachThoiTrang);
    }
}
