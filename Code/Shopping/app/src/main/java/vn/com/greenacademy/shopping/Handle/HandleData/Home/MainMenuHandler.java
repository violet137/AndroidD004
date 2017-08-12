package vn.com.greenacademy.shopping.Handle.HandleData.Home;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Magazine.MagazineFragment;
import vn.com.greenacademy.shopping.Fragment.Main.DanhMucSanPham.DanhMucSPFragment;
import vn.com.greenacademy.shopping.Fragment.Home.MainFragment;
import vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang.XuHuongThoiTrangFragment;
import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main.ParseNewProductList;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main.ParseAdvertise;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main.ParseBanner;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main.ParseMyProducts;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Home.AdapterHomeListView;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Home.AdapterHomeRV;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Home.AdapterHomeRVFashion;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Home.AdapterHomeRVMagazine;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Home.AdapterHomeRVProducts;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Home.AdapterHomeRecyclerView;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Home.AdapterNewProductViewPager;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Model.Home.AdvertisePhoto;
import vn.com.greenacademy.shopping.Model.Home.BannerPhoto;
import vn.com.greenacademy.shopping.Model.Home.ItemHome;
import vn.com.greenacademy.shopping.Model.Home.MenuMain;
import vn.com.greenacademy.shopping.Model.Home.MenuPhoto;
import vn.com.greenacademy.shopping.Model.Home.ProductsPhoto;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
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
//    // tai du lieu tu adapter len list
//    public AdapterMenuMain getAdapter (ArrayList<MenuMain> menuMainArrayList) {
//        AdapterMenuMain adapterMenuMain = new AdapterMenuMain(activity, R.layout.item_listview_menu_main, menuMainArrayList,
//                onClickListenerAdvertise, onClickListenerProducts, onClickListenerHotTrend);
//        return adapterMenuMain;
//    }

    // tai du lieu tu adapter len list
    public AdapterHomeRecyclerView getAdapterRVMultipleView (ArrayList<MenuMain> menuMainArrayList) {
        AdapterHomeRecyclerView adapter = new AdapterHomeRecyclerView(activity, menuMainArrayList);
        return adapter;
    }

    public void setDataAdvertise(ArrayList<MenuMain> menuMainArrayList, ViewFlipper vfAdvertise) {
        ImageLoad imageLoad = new ImageLoad(activity);
        for (int i = 0; i < menuMainArrayList.get(0).getAdvertiseMenuMains().size() ; i++) {
            // chu y neu ko tai hinh dc thi kiem tra lai mang
            ImageView image = new ImageView(activity);

            ItemHome itemHome = new ItemHome();
            itemHome.setFlagItemHome(SupportKeyList.ClickHome_Advertise);
            itemHome.setId(String.valueOf(menuMainArrayList.get(0).getAdvertiseMenuMains().get(i).getId()));

            image.setTag(itemHome);
            image.setOnClickListener(ClickListenerHomeItem.onClickListener);

            image.setScaleType(ImageView.ScaleType.CENTER_CROP);

            // dùng hàm load ảnh của imageLoad để tải anh theo link web và set vào imageView
            imageLoad.load(menuMainArrayList.get(0).getAdvertiseMenuMains().get(i).getHinhDaiDien(), image);
            vfAdvertise.addView(image);
        }
        // bắt đầu chuyển imageView
        vfAdvertise.startFlipping();
    }

    public void setAdapter(ArrayList<MenuMain> menuMainArrayList, int flag, RecyclerView recycler) {
        switch (flag){
            case SupportKeyList.ClickHome_Products:
                AdapterHomeRVProducts adapterHomeRVProducts = new AdapterHomeRVProducts(activity, menuMainArrayList);
                recycler.setAdapter(adapterHomeRVProducts);
                break;
            case SupportKeyList.ClickHome_Fashion:
                AdapterHomeRVFashion adapterHomeRVFashion = new AdapterHomeRVFashion(activity, menuMainArrayList);
                recycler.setAdapter(adapterHomeRVFashion);
                break;
            case SupportKeyList.ClickHome_Magazine:
                AdapterHomeRVMagazine adapterHomeRVMagazine = new AdapterHomeRVMagazine(activity, menuMainArrayList);
                recycler.setAdapter(adapterHomeRVMagazine);
                break;
        }
    }
    private AdapterNewProductViewPager adapterNewProductViewPager;
    public void setDataNewProduct(FragmentManager fm ,ArrayList<MenuMain> menuMainArrayList, ViewPager vpNewProduct) {

       adapterNewProductViewPager =
                new AdapterNewProductViewPager(fm,
                        activity, menuMainArrayList.get(0).getSanPhamArrayList());

        vpNewProduct.setAdapter(adapterNewProductViewPager);

    }

    public ListAdapter getAdapterLV(ArrayList<MenuMain> menuMainArrayList) {
        AdapterHomeListView adapterHomeListView = new AdapterHomeListView(activity, R.layout.item_menu_home_list_view, menuMainArrayList);
        return adapterHomeListView;
    }

    public RecyclerView.Adapter getAdapterRV(ArrayList<MenuMain> menuMainArrayList) {
        AdapterHomeRV adapterHomeRV = new AdapterHomeRV(activity, menuMainArrayList);
        return adapterHomeRV;
    }
}

class LoadDataMainMenuHandler implements ServerCallBack{

    // mainArrayList chứa cái đối tượng MenuMain server trả về
    ArrayList<MenuMain> mainArrayList ;

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

        // lấy danh sách một s61 sản phẩm mới lấy cố định 11 sản phẩm mới để lên đẹp layout
        getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhSachSPMoi+"11", String.valueOf(SupportKeyList.NewProduct_Url));

        // lấy danh sách các banner phần xu hướng + tạp chí
        getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhBannerHome, String.valueOf(SupportKeyList.Banner_Url));

    }

    @Override
    public void serverCallBack(String dataServer) {
    }

//    @Override
//    public void serverCallBack(String dataServer, String key) {
//        // hàm trả về 2 đối tượng của server
//
//        // gọi hàm parse data
//        switch (Integer.parseInt(key)){
//            // parse cho banner quảng cáo
//              case SupportKeyList.Advertise_Url:
//                  ParseAdvertise parseAdvertise = new ParseAdvertise(dataServer);
//                  containerData(parseAdvertise.parData(), key);
//                  break;
//
//            // parse cho banner sản phẩm
//              case SupportKeyList.Products_Url:
//                  ParseMyProducts parseMyProducts = new ParseMyProducts(dataServer);
//                  containerData(parseMyProducts.parData(), key);
//                  break;
//
//            // parse cho banner xu hướng + khuyến mãi + tạp chí
//              case SupportKeyList.Banner_Url:
//                  ParseBanner parseBanner = new ParseBanner(dataServer);
//                  containerData(parseBanner.parData(), key);
//                  break;
//
//              default:
//                  break;
//
//        }
//
//    }

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

            case SupportKeyList.NewProduct_Url:
                ParseNewProductList parseNewProductList = new ParseNewProductList(dataServer);
                containerData(parseNewProductList.parData(), key);
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
                mainArrayList = new ArrayList<>();
                menuMain = new MenuMain();
                menuMain.setFlag(Integer.parseInt(key));
                menuMain.setFlagItem(SupportKeyList.ClickHome_Advertise);
                ArrayList<AdvertisePhoto> advertisePhotos = new ArrayList<>();
                for (int i = 0; i < menuPhoto.getAdvertisePhotoArrayList().size(); i++) {
                    AdvertisePhoto advertisePhoto = new AdvertisePhoto();
                    advertisePhoto.setHinhDaiDien(menuPhoto.getAdvertisePhotoArrayList().get(i).getHinhDaiDien());
                    advertisePhoto.setId(menuPhoto.getAdvertisePhotoArrayList().get(i).getId());
                    advertisePhotos.add(advertisePhoto);
                }
                menuMain.setAdvertiseMenuMains(advertisePhotos);
                mainArrayList.add(menuMain);
                finishLoadData(SupportKeyList.ClickHome_Advertise);
                break;

            case SupportKeyList.Products_Url:
                mainArrayList = new ArrayList<>();
                for (int i = 0; i < menuPhoto.getFashionTypeArrayList().size(); i++) {
                    menuMain = new MenuMain();
                    menuMain.setFlag(Integer.parseInt(key));
                    menuMain.setFlagItem(SupportKeyList.ClickHome_Products);
                    menuMain.setUrl(menuPhoto.getFashionTypeArrayList().get(i).getLinkHinh());
                    menuMain.setId(menuPhoto.getFashionTypeArrayList().get(i).getLoaiThoiTrang());
                    menuMain.setName(menuPhoto.getFashionTypeArrayList().get(i).getTen());
                    mainArrayList.add(menuMain);
                }
                finishLoadData(SupportKeyList.ClickHome_Products);
                break;
            case SupportKeyList.NewProduct_Url:
                mainArrayList = new ArrayList<>();
                menuMain = new MenuMain();
                ArrayList<SanPham> sanPhamArrayList = new ArrayList<>();
                for (int i = 0; i < menuPhoto.getSanPhamArrayList().size(); i++) {
                    SanPham sanPham = new SanPham();
                    sanPham.setIdSanPham(menuPhoto.getSanPhamArrayList().get(i).getIdSanPham());
                    sanPham.setTenSanPham(menuPhoto.getSanPhamArrayList().get(i).getTenSanPham());
                    sanPham.setNgayTao(menuPhoto.getSanPhamArrayList().get(i).getNgayTao());
                    sanPham.setGiaSanPham(menuPhoto.getSanPhamArrayList().get(i).getGiaSanPham());
                    sanPham.setGiamGia(menuPhoto.getSanPhamArrayList().get(i).getGiamGia());
                    sanPham.setChiTietSanPham(menuPhoto.getSanPhamArrayList().get(i).getChiTietSanPham());
                    sanPham.setHinhSanPham(menuPhoto.getSanPhamArrayList().get(i).getHinhSanPham());
                    sanPham.setMauSanPham(menuPhoto.getSanPhamArrayList().get(i).getMauSanPham());
                    sanPham.setSize(menuPhoto.getSanPhamArrayList().get(i).getSize());
//                    sanPham.getDanhMucHangId(menuPhoto.getSanPhamArrayList().get(i).getDanhMucHangId());
                    sanPham.setLoaiSanPham(menuPhoto.getSanPhamArrayList().get(i).getLoaiSanPham());
                    sanPham.setDescription(menuPhoto.getSanPhamArrayList().get(i).getDescription());

                    sanPhamArrayList.add(sanPham);
                }
                menuMain.setFlag(Integer.parseInt(key));
                menuMain.setFlagItem(SupportKeyList.ClickHome_NewProduct);
                menuMain.setSanPhamArrayList(sanPhamArrayList);
                mainArrayList.add(menuMain);
                finishLoadData(SupportKeyList.ClickHome_NewProduct);
                break;
            default:
                int count =0;
                mainArrayList = new ArrayList<>();
                for (int i = 0; i < menuPhoto.getBannerPhotoArrayList().size(); i++) {
                    menuMain = new MenuMain();
                    menuMain.setFlag(Integer.parseInt(key));
                    menuMain.setUrl(menuPhoto.getBannerPhotoArrayList().get(i).getLinkAnh());
                    menuMain.setId(String.valueOf(menuPhoto.getBannerPhotoArrayList().get(i).getId()));
                    menuMain.setType(menuPhoto.getBannerPhotoArrayList().get(i).getLoaiBanner());
                    if (menuPhoto.getBannerPhotoArrayList().get(i).getLoaiTapChi() != null){
                        if (count == 0){
                            finishLoadData(SupportKeyList.ClickHome_Fashion);
                            mainArrayList = new ArrayList<>();
                            count = 1;
                        }
                        menuMain.setFlagItem(SupportKeyList.ClickHome_Magazine);
                        menuMain.setMagazineType(menuPhoto.getBannerPhotoArrayList().get(i).getLoaiTapChi());
                        menuMain.setName(menuPhoto.getBannerPhotoArrayList().get(i).getName());
                    }else {
                        menuMain.setFlagItem(SupportKeyList.ClickHome_Fashion);
                    }
                    mainArrayList.add(menuMain);
                }
                finishLoadData(SupportKeyList.ClickHome_Magazine);
                break;
        }
    }

    // dư liệu server trả về hoàn tất
    public void finishLoadData(int flag){
        switch (flag){
            case SupportKeyList.ClickHome_Advertise:
                MainFragment.listMainMenuCallBack.callBack(mainArrayList, flag);
                break;
            case SupportKeyList.ClickHome_Products:
                MainFragment.listMainMenuCallBack.callBack(mainArrayList, flag);
                break;
            case SupportKeyList.ClickHome_NewProduct:
                MainFragment.listMainMenuCallBack.callBack(mainArrayList, flag);
                break;
            case SupportKeyList.ClickHome_Fashion:
                MainFragment.listMainMenuCallBack.callBack(mainArrayList, flag);
                break;
            case SupportKeyList.ClickHome_Magazine:
                MainFragment.listMainMenuCallBack.callBack(mainArrayList, flag);
                break;
        }
    }

}