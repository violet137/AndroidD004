package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Magazine.MagazineFragment;
import vn.com.greenacademy.shopping.Fragment.Main.DanhMucSanPham.DanhMucSPFragment;
import vn.com.greenacademy.shopping.Fragment.Main.MainFragment;
import vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang.XuHuongThoiTrangFragment;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.AdapterMenuMain;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main.ParseAdvertise;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main.ParseBanner;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main.ParseMyProducts;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Model.AdvertisePhoto;
import vn.com.greenacademy.shopping.Model.BannerPhoto;
import vn.com.greenacademy.shopping.Model.MenuMain;
import vn.com.greenacademy.shopping.Model.MenuPhoto;
import vn.com.greenacademy.shopping.Model.ProductsPhoto;
import vn.com.greenacademy.shopping.Network.AsynTask.GetServerData;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by ADMIN on 7/18/2017.
 */

public class MainMenuHandler extends LoadDataMainMenuHandler{

    private BaseFragment baseFragment;
    Activity activity;

    View.OnClickListener onClickListenerAdvertise;
    View.OnClickListener onClickListenerProducts;
    View.OnClickListener onClickListenerHotTrend;


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
                if (((ProductsPhoto) v.getTag()).getId().equals("TapChi")){
                    baseFragment.ChuyenFragment(new MagazineFragment(), SupportKeyList.TAG_FRAGMENT_MAGAZINE, true);
                }else {
                    baseFragment.ChuyenFragment(new DanhMucSPFragment(((ProductsPhoto) v.getTag()).getId()), SupportKeyList.TAG_DANH_MUC_SAN_PHAM, true);
                }
                Toast.makeText(activity, productsPhoto.getId(), Toast.LENGTH_SHORT).show();
            }
        };

        // click phan bai bao va xu huong
        onClickListenerHotTrend = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BannerPhoto bannerPhoto  = (BannerPhoto) v.getTag();
                switch (bannerPhoto.getLoaiBanner()){
                    case SupportKeyList.KhuyenMai_BannerType:
                        Toast.makeText(activity, String.valueOf(bannerPhoto.getId()) + " " + bannerPhoto.getLoaiBanner(), Toast.LENGTH_SHORT).show();

                        break;
                    case SupportKeyList.XuHuong_BannerType:
                        Toast.makeText(activity, String.valueOf(bannerPhoto.getId()) + " " + bannerPhoto.getLoaiBanner(), Toast.LENGTH_SHORT).show();
                        baseFragment.ChuyenFragment(new XuHuongThoiTrangFragment(bannerPhoto.getId()), SupportKeyList.TAG_XU_HUONG_THOI_TRANG, true);
                        break;
                    case SupportKeyList.TapChi_BannerType:
                        Toast.makeText(activity, String.valueOf(bannerPhoto.getId()) + " " + bannerPhoto.getLoaiBanner(), Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        };
    }
    // tai du lieu tu adapter len list
    public AdapterMenuMain getAdapter (ArrayList<MenuMain> menuMainArrayList) {
        AdapterMenuMain adapterMenuMain = new AdapterMenuMain(activity, R.layout.item_listview_menu_main, menuMainArrayList,
                onClickListenerAdvertise, onClickListenerProducts, onClickListenerHotTrend);
        return adapterMenuMain;
    }
}

class LoadDataMainMenuHandler implements ServerCallBack{

    ArrayList<MenuMain> mainArrayList = new ArrayList<>();
    GetServerData getServerData;
    // tai du lieu tu file xml cua may vao doi tuong array de dua vao adapter
    public void getDataServer() {
//        GetAdvertise getAdvertise = new GetA;dvertise(this);
//        getAdvertise.execute(ServerUrl.UrlDanhSachKhuyenMai);
        getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhSachKhuyenMai, String.valueOf(SupportKeyList.Advertise_Url));

//        GetMainMenuPhotos getMainMenuPhotos  = new GetMainMenuPhotos(this);
//        getMainMenuPhotos.execute(ServerUrl.UrlDanhSachThoiTrang);
        getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhSachThoiTrang, String.valueOf(SupportKeyList.Products_Url));

//        GetBanner getBanner  = new GetBanner(this);
//        getBanner.execute(ServerUrl.UrlDanhBannerHome);
        getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhBannerHome, String.valueOf(SupportKeyList.Banner_Url));

    }

//    @Override
//    public void urlCallBack(MenuPhoto menuPhoto, int flag) {
//        MenuMain menuMain;
//        switch (flag){
//            case SupportKeyList.Advertise_Url:
//                menuMain = new MenuMain();
//                ArrayList<AdvertisePhoto> advertisePhotos = new ArrayList<>();
//                for (int i = 0; i < menuPhoto.getAdvertisePhotoArrayList().size(); i++) {
//                    AdvertisePhoto advertisePhoto = new AdvertisePhoto();
//                    advertisePhoto.setHinhDaiDien(menuPhoto.getAdvertisePhotoArrayList().get(i).getHinhDaiDien());
//                    advertisePhoto.setId(menuPhoto.getAdvertisePhotoArrayList().get(i).getId());
//                    advertisePhotos.add(advertisePhoto);
//                }
//                menuMain.setAdvertiseMenuMains(advertisePhotos);
//                mainArrayList.add(menuMain);
//                break;
//
//            case SupportKeyList.Products_Url:
//                for (int i = 0; i < menuPhoto.getFashionTypeArrayList().size(); i++) {
//                    menuMain = new MenuMain();
//                    menuMain.setFlag(SupportKeyList.Products);
//                    menuMain.setUrl(menuPhoto.getFashionTypeArrayList().get(i).getLinkHinh());
//                    menuMain.setId(menuPhoto.getFashionTypeArrayList().get(i).getLoaiThoiTrang());
//                    menuMain.setName(menuPhoto.getFashionTypeArrayList().get(i).getTen());
//                    mainArrayList.add(menuMain);
//                }
//
//                break;
//            case SupportKeyList.Banner_Url:
//                for (int i = 0; i < menuPhoto.getBannerPhotoArrayList().size(); i++) {
//                    menuMain = new MenuMain();
//                    menuMain.setFlag(SupportKeyList.Banner);
//                    menuMain.setUrl(menuPhoto.getBannerPhotoArrayList().get(i).getLinkAnh());
//                    menuMain.setId(String.valueOf(menuPhoto.getBannerPhotoArrayList().get(i).getId()));
//                    menuMain.setType(menuPhoto.getBannerPhotoArrayList().get(i).getLoaiBanner());
//                    mainArrayList.add(menuMain);
//                }
//
//                finishLoadData(true);
//
//                break;
//            default:
//                break;
//        }
//    }

    @Override
    public void serverCallBack(String dataServer) {
    }

    @Override
    public void serverCallBack(String dataServer, String key) {
        switch (Integer.parseInt(key)){
              case SupportKeyList.Advertise_Url:
                  ParseAdvertise parseAdvertise = new ParseAdvertise(dataServer);
                  containerData(parseAdvertise.parData(), key);
                  break;

              case SupportKeyList.Products_Url:
                  ParseMyProducts parseMyProducts = new ParseMyProducts(dataServer);
                  containerData(parseMyProducts.parData(), key);
                  break;

              case SupportKeyList.Banner_Url:
                  ParseBanner parseBanner = new ParseBanner(dataServer);
                  containerData(parseBanner.parData(), key);
                  break;
              default:
                  break;

        }

    }

    private void containerData(MenuPhoto menuPhoto, String key) {
        MenuMain menuMain;
        switch (Integer.parseInt(key)){
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

                finishLoadData(true);

                break;
            default:
                break;
        }
    }

    public void finishLoadData(boolean flag){
        if (flag){
            MainFragment.listMainMenuCallBack.callBack(mainArrayList);
        }
    }

}