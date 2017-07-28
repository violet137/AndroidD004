package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang.XuHuongThoiTrangFragment;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.AdapterMenuMain;
import vn.com.greenacademy.shopping.Interface.UrlPhotoCallBack;
import vn.com.greenacademy.shopping.Model.AdvertisePhoto;
import vn.com.greenacademy.shopping.Model.BannerPhoto;
import vn.com.greenacademy.shopping.Model.MenuMain;
import vn.com.greenacademy.shopping.Model.MenuPhoto;
import vn.com.greenacademy.shopping.Model.ProductsPhoto;
import vn.com.greenacademy.shopping.Network.AsynTask.GetAdvertise;
import vn.com.greenacademy.shopping.Network.AsynTask.GetBanner;
import vn.com.greenacademy.shopping.Network.AsynTask.GetMainMenuPhotos;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by ADMIN on 7/18/2017.
 */

public class MainMenuHandler implements UrlPhotoCallBack {

    AdapterMenuMain adapterMenuMain;
    private BaseFragment baseFragment;
    Activity activity;
    View.OnClickListener onClickListenerAdvertise;
    View.OnClickListener onClickListenerProducts;
    View.OnClickListener onClickListenerHotTrend;

    ArrayList<MenuMain> mainArrayList = new ArrayList<>();
    ListView listView;

    public MainMenuHandler(Activity activity, BaseFragment baseFragment) {
        this.activity = activity;
        this.baseFragment = baseFragment;
    }

    // dieu khien phần click menu main
    public void clickItemMenuMain(){
        // click phan quang cao
        onClickListenerAdvertise = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvertisePhoto advertisePhoto = (AdvertisePhoto) v.getTag();
                Toast.makeText(activity, String.valueOf( advertisePhoto.getId()), Toast.LENGTH_SHORT).show();
            }
        };

        // click phan san pham
        onClickListenerProducts = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductsPhoto productsPhoto = (ProductsPhoto) v.getTag();
                Toast.makeText(activity, productsPhoto.getId(), Toast.LENGTH_SHORT).show();
            }
        };

        // click phan bai bao va xu huong
        onClickListenerHotTrend = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BannerPhoto bannerPhoto  = (BannerPhoto) v.getTag();
                baseFragment.ChuyenFragment(new XuHuongThoiTrangFragment(0), SupportKeyList.TAG_XU_HUONG_THOI_TRANG, true);
                Toast.makeText(activity, String.valueOf(bannerPhoto.getId()) + " " + bannerPhoto.getLoaiBanner(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    // tai du lieu tu adapter len list
    public void displayListview() {
        adapterMenuMain = new AdapterMenuMain(activity, R.layout.item_listview_menu_main, mainArrayList,
                onClickListenerAdvertise, onClickListenerProducts, onClickListenerHotTrend);
        listView.setAdapter(adapterMenuMain);
    }

    // tai du lieu tu file xml cua may vao doi tuong array de dua vao adapter
    public void loadData(ListView listView) {
        GetAdvertise getAdvertise = new GetAdvertise(this);
        getAdvertise.execute(ServerUrl.UrlDanhSachKhuyenMai);

        GetMainMenuPhotos getMainMenuPhotos  = new GetMainMenuPhotos(this);
        getMainMenuPhotos.execute(ServerUrl.UrlDanhSachThoiTrang);

        GetBanner getBanner  = new GetBanner(this);
        getBanner.execute(ServerUrl.UrlDanhBannerHome);

        this.listView = listView;
    }



    @Override
    public void urlCallBack(MenuPhoto menuPhoto, int flag) {
        MenuMain menuMain;
        switch (flag){
            case SupportKeyList.Advertise_Url:
                menuMain = new MenuMain();
                ArrayList<AdvertisePhoto> advertisePhotos = new ArrayList<>();
                for (int i = 0; i < menuPhoto.getAdvertisePhotoArrayList().size(); i++) {
                    AdvertisePhoto advertisePhoto = new AdvertisePhoto();
                    advertisePhoto.setHinhDaiDien(menuPhoto.getAdvertisePhotoArrayList().get(i).getHinhDaiDien());
                    advertisePhoto.setId(menuPhoto.getAdvertisePhotoArrayList().get(i).getId());
                    advertisePhotos.add(advertisePhoto);
                }
                menuMain.setAdvertiseMenuMains(advertisePhotos);
                mainArrayList.add(menuMain);
                break;

            case SupportKeyList.Products_Url:
                for (int i = 0; i < menuPhoto.getFashionTypeArrayList().size(); i++) {
                    menuMain = new MenuMain();
                    menuMain.setFlag(SupportKeyList.Products);
                    menuMain.setUrl(menuPhoto.getFashionTypeArrayList().get(i).getLinkHinh());
                    menuMain.setId(menuPhoto.getFashionTypeArrayList().get(i).getLoaiThoiTrang());
                    menuMain.setName(menuPhoto.getFashionTypeArrayList().get(i).getTen());
                    mainArrayList.add(menuMain);
                }

                break;
            case SupportKeyList.Banner_Url:
                for (int i = 0; i < menuPhoto.getBannerPhotoArrayList().size(); i++) {
                    menuMain = new MenuMain();
                    menuMain.setFlag(SupportKeyList.Banner);
                    menuMain.setUrl(menuPhoto.getBannerPhotoArrayList().get(i).getLinkAnh());
                    menuMain.setId(String.valueOf(menuPhoto.getBannerPhotoArrayList().get(i).getId()));
                    menuMain.setType(menuPhoto.getBannerPhotoArrayList().get(i).getLoaiBanner());
                    mainArrayList.add(menuMain);
                }
                displayListview();
                break;
            default:
                break;
        }
    }
}
