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

    // tạo đối tượng để chuyển fragment
    private BaseFragment baseFragment;

    // đối tượng activity
    Activity activity;

    // các đối tượng để nhận sự kiện cick cái item trenen listView Main
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
                // bannner server trả về 3 loại banner được phân theo thứ tự tường loại
                switch (bannerPhoto.getLoaiBanner()){
                    // loại khuyen mãi
                    case SupportKeyList.KhuyenMai_BannerType:
                        Toast.makeText(activity, String.valueOf(bannerPhoto.getId()) + " " + bannerPhoto.getLoaiBanner(), Toast.LENGTH_SHORT).show();

                        break;

                    // loại xu hướng
                    case SupportKeyList.XuHuong_BannerType:
                        Toast.makeText(activity, String.valueOf(bannerPhoto.getId()) + " " + bannerPhoto.getLoaiBanner(), Toast.LENGTH_SHORT).show();
                        baseFragment.ChuyenFragment(new XuHuongThoiTrangFragment(bannerPhoto.getId()), SupportKeyList.TAG_XU_HUONG_THOI_TRANG, true);
                        break;

                    // loại tạp chí
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

    // mainArrayList chứa cái đối tượng MenuMain server trả về
    ArrayList<MenuMain> mainArrayList = new ArrayList<>();

    // getServerData đối tượng gọi lên server
    GetServerData getServerData;
    // tai du lieu tu file xml cua may vao doi tuong array de dua vao adapter
    public void getDataServer() {
        //sử dụng kiểu trả về 2 đối tượng của hàm getServerData

        // lấy danh sách tất cả các banner quảng cáo
        getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhSachKhuyenMai, String.valueOf(SupportKeyList.Advertise_Url));

        // lấy danh sách tất cả các banner sản phẩm
        getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhSachThoiTrang, String.valueOf(SupportKeyList.Products_Url));

        // lấy danh sách các banner phần xu hướng + khuyến mãi + tạp chí
        getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhBannerHome, String.valueOf(SupportKeyList.Banner_Url));

    }

    @Override
    public void serverCallBack(String dataServer) {
    }

    @Override
    public void serverCallBack(String dataServer, String key) {
        // hàm trả về 2 đối tượng của server

        // gọi hàm parse data
        switch (Integer.parseInt(key)){
            // parse cho banner quảng cáo
              case SupportKeyList.Advertise_Url:
                  ParseAdvertise parseAdvertise = new ParseAdvertise(dataServer);
                  containerData(parseAdvertise.parData(), key);
                  break;

            // parse cho banner sản phẩm
              case SupportKeyList.Products_Url:
                  ParseMyProducts parseMyProducts = new ParseMyProducts(dataServer);
                  containerData(parseMyProducts.parData(), key);
                  break;

            // parse cho banner xu hướng + khuyến mãi + tạp chí
              case SupportKeyList.Banner_Url:
                  ParseBanner parseBanner = new ParseBanner(dataServer);
                  containerData(parseBanner.parData(), key);
                  break;

              default:
                  break;

        }

    }

    // hàm nhận dữ liệu sau khi parse xong
    private void containerData(MenuPhoto menuPhoto, String key) {
        MenuMain menuMain;
        // đưa dữ liệu sau khi parse xong vao mainArrayList
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

                // sau khi nhận dư iệu của 3 loại banner hoàn tất trong listView ManiMenu
                finishLoadData(true);

                break;
            default:
                break;
        }
    }

    // dư liệu server trả về hoàn tất
    public void finishLoadData(boolean flag){
        if (flag){
            // trả dư liệu hoàn tất về MainFragmnet
            MainFragment.listMainMenuCallBack.callBack(mainArrayList);
        }
    }

}