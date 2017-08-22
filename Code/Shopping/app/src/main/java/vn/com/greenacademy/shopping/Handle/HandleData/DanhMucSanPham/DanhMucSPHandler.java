package vn.com.greenacademy.shopping.Handle.HandleData.DanhMucSanPham;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.DanhMucSanPham.ChiTietDanhMucFragment;
import vn.com.greenacademy.shopping.Fragment.DanhMucSanPham.DanhMucSanPhamFragment;
import vn.com.greenacademy.shopping.Handle.HandleUi.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Product.ParseNewProductList;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main.ParseDanhMucSP;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.DanhMucSP.AdapterDanhMucSanPham;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.DanhMucSP.AdapterHotProductViewPager;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Model.DanhMuc.DanhMucSP;
import vn.com.greenacademy.shopping.Model.DanhMuc.MucSanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Network.AsynTask.GetServerData;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by ADMIN on 7/31/2017.
 */

public class DanhMucSPHandler extends LoadDataDanhMucSPHandler implements View.OnClickListener{
    private Activity activity;
    ImageLoad imageLoad;

    public DanhMucSPHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        BaseFragment baseFragment = new BaseFragment(activity,((AppCompatActivity)activity).getSupportFragmentManager());
        MucSanPham mucSanPham = (MucSanPham) v.getTag();
        Toast.makeText(activity, String.valueOf(mucSanPham.getId()), Toast.LENGTH_SHORT).show();
        baseFragment.ChuyenFragment(ChiTietDanhMucFragment.newInstance(mucSanPham.getId(), mucSanPham.getTenDanhMuc(), mucSanPham.getLoaiThoiTrang()), SupportKeyList.TAG_CHI_TIET_DANH_MUC_SAN_PHAM, true);
    }

    public void setPhoto(MucSanPham mucSanPham, ImageView ivPhoto) {
        imageLoad = new ImageLoad(activity);
        if (mucSanPham.getLinkAnh().equals("")){
            imageLoad.load("https://lmt.com.vn/media/k2/items/cache/4251dec72b18ac89643edfb7a8300016_XL.jpg", ivPhoto);
        }else {
            MucSanPham temp = new MucSanPham();
            mucSanPham.setId(-1);
            ivPhoto.setTag(mucSanPham);
            ivPhoto.setOnClickListener(ClickListenerDanhMucSanPham.onClickListener);

            imageLoad.load(mucSanPham.getLinkAnh(), ivPhoto);
        }
    }

    public RecyclerView.Adapter getAdapterListDM(ArrayList<MucSanPham> object) {
        AdapterDanhMucSanPham adapter = new AdapterDanhMucSanPham(activity, object);
        return adapter;
    }

    public void getAdapterHotProduct(FragmentManager fm, ArrayList<SanPham> dataHotProduct, final ViewPager viewPager, RadioGroup radioGroup) {
        AdapterHotProductViewPager adapter = new AdapterHotProductViewPager(fm,activity,dataHotProduct);
        viewPager.setAdapter(adapter);

        // [bat dau tao radio button hien thi so luong view pager]
        // ham click cua radiobutton
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // thay doi view pager khi click vao tab layout
                viewPager.setCurrentItem((Integer) v.getTag());
            }
        };

        final ArrayList<RadioButton> radioButtons = new ArrayList<>();
        RadioButton radioButton;
        for (int i = 0; i < adapter.getCount(); i++) {
            radioButton = new RadioButton(activity);
            radioButton.setScaleX((float) 0.5);
            radioButton.setScaleY((float) 0.5);
            radioButton.setTag(i);
            radioButton.setOnClickListener(onClickListener);
            radioGroup.addView(radioButton);
            radioButtons.add(radioButton);
        }

        // [ket thuc tao radio button hien thi so luong view pager]

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // thay doi radio button(hien thi so luong view pager) dc chon
                radioButtons.get(position).setChecked(true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}

class LoadDataDanhMucSPHandler implements ServerCallBack{

    public void getDataServer (String loaiSP){
        GetServerData getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlDanhMucSP+loaiSP, SupportKeyList.ListDanhMucSP);
    }

    public void getDataNewProductServer (String loaiSP, int numberSP){
        GetServerData getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlSPMoiTheoDM + loaiSP + ServerUrl.AndSoLuonSanPham + numberSP, SupportKeyList.ViewPagerDMSPMoi);
//        getServerData.execute(ServerUrl.UrlSPMoiTheoDM + loaiSP + "&soLuong=9", SupportKeyList.ViewPagerDMSPMoi);
    }

    @Override
    public void serverCallBack(String dataServer) {}

    private void containerData(Object object, String key) {
        switch (key){
            case SupportKeyList.ListDanhMucSP:
                DanhMucSP danhMucSP = (DanhMucSP) object;

                MucSanPham newMucSanPham = new MucSanPham();
                newMucSanPham.setLinkAnh(danhMucSP.getXuHuongTtrangLink());
                newMucSanPham.setId(danhMucSP.getXuHuongTtrangId());
                newMucSanPham.setLoaiThoiTrang(danhMucSP.getLoaiThoiTrang());

                DanhMucSanPhamFragment.objectCallBack.callBack(
                        newMucSanPham,SupportKeyList.ClickDanhMuc_Photo);

                DanhMucSanPhamFragment.objectCallBack.callBack(
                        danhMucSP.getMucSanPhamArrayList(),SupportKeyList.ClickDanhMuc_Menu);
                break;
            case SupportKeyList.ViewPagerDMSPMoi:
                DanhMucSanPhamFragment.objectCallBack.callBack(object, SupportKeyList.ClickDanhMuc_HotProduct);
                break;
            default:
                Log.d("case containerData DanhMucSPHandler","lỗi");
                break;
        }

    }


    @Override
    public void serverCallBack(String dataServer, String key) {
        switch (key){
            case SupportKeyList.ListDanhMucSP:
                ParseDanhMucSP parseDanhMucSP = new ParseDanhMucSP(dataServer);
                containerData(parseDanhMucSP.parData(),key);
                break;
            case SupportKeyList.ViewPagerDMSPMoi:
                ParseNewProductList parseNewProductList = new ParseNewProductList(dataServer);
                containerData(parseNewProductList.parData(),key);
                break;
            default:
                Log.d("case severCallBack DanhMucSPHandler","lôi");
                break;
        }
    }
}