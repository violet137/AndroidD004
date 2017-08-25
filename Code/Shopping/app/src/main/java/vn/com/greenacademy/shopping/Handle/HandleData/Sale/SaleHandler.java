package vn.com.greenacademy.shopping.Handle.HandleData.Sale;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Sale.SaleFragment;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.DanhMucSP.AdapterHotProductViewPager;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale.AdapterSale;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Sale.ParseSale;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale.AdapterVPPhoto;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Sale.AdapterViewPagerSale;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Model.Sale;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Network.AsynTask.GetServerData;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;

/**
 * Created by ADMIN on 8/1/2017.
 */

public class SaleHandler extends LoadDataSaleHandler implements View.OnClickListener{
    Activity activity;
    public static View.OnClickListener onClickListener;

    public SaleHandler(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    public void Click() {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<SanPham> sanPham;
                switch (v.getId()){
                    case R.id.llXemAll_saleFragment:

//                        SaleFragment.dataProductSaless;

                        break;
                    case R.id.llNu_saleFragment:
                        sanPham = new ArrayList<>();
                        for (int i = 0; i < SaleFragment.dataProductSaless.size(); i++) {
                            if (SaleFragment.dataProductSaless.get(i).getLoaiSanPham().equals("Nu")){
                                sanPham.add(SaleFragment.dataProductSaless.get(i));
                            }
                        }
                        break;
                    case R.id.llNam_saleFragment:
                        sanPham = new ArrayList<>();
                        for (int i = 0; i < SaleFragment.dataProductSaless.size(); i++) {
                            if (SaleFragment.dataProductSaless.get(i).getLoaiSanPham().equals("Nam")){
                                sanPham.add(SaleFragment.dataProductSaless.get(i));
                            }
                        }
                        break;
                    case R.id.llTreEm_saleFragment:
                        sanPham = new ArrayList<>();
                        for (int i = 0; i < SaleFragment.dataProductSaless.size(); i++) {
                            if (SaleFragment.dataProductSaless.get(i).getLoaiSanPham().equals("TreEm")){
                                sanPham.add(SaleFragment.dataProductSaless.get(i));
                            }
                        }
                        break;
                    case R.id.llTrangTri_saleFragment:
                        sanPham = new ArrayList<>();
                        for (int i = 0; i < SaleFragment.dataProductSaless.size(); i++) {
                            if (SaleFragment.dataProductSaless.get(i).getLoaiSanPham().equals("Home")){
                                sanPham.add(SaleFragment.dataProductSaless.get(i));
                            }
                        }
                        break;
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        Sale sale = (Sale) v.getTag();
        Toast.makeText(activity,String.valueOf(sale.getId()),Toast.LENGTH_SHORT).show();
    }

    public AdapterSale getAdapter(FragmentManager fm,ArrayList<Sale> saleArrayList){

        AdapterSale adapterSale = new AdapterSale(fm,activity,this,saleArrayList);

        return adapterSale;
    }

    public void setAdapterVPPhotos(FragmentManager childFragmentManager, ArrayList<Sale> dataPhotoSales, final ViewPager vpPhotos) {
        AdapterVPPhoto adapter = new AdapterVPPhoto(childFragmentManager,activity,dataPhotoSales);
        vpPhotos.setAdapter(adapter);

        final int maxSLViewPager = adapter.getCount();
        Thread thread = new Thread(new Runnable() {
            int i = 0;
            @Override
            public void run() {
                try {
                    while (true){
                        Thread.sleep(2500);
                        if (i+1>maxSLViewPager){
                            i=0;
                        }else {
                            i++;
                        }
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                vpPhotos.setCurrentItem(i);

                            }
                        });

                        }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });thread.start();

    }

    public void setAdapterVPProducts(FragmentManager childFragmentManager, ArrayList<SanPham> dataProductSaless, final ViewPager vpProducts, RadioGroup rgSLVPProduct) {
        AdapterViewPagerSale adapter = new AdapterViewPagerSale(childFragmentManager,activity,dataProductSaless);
        vpProducts.setAdapter(adapter);

        // [bat dau tao radio button hien thi so luong view pager]
        // ham click cua radiobutton
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // thay doi view pager khi click vao tab layout
                vpProducts.setCurrentItem((Integer) v.getTag());
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
            rgSLVPProduct.addView(radioButton);
            radioButtons.add(radioButton);
        }

        // [ket thuc tao radio button hien thi so luong view pager]

        vpProducts.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

class LoadDataSaleHandler implements ServerCallBack{

    Activity activity;

    public LoadDataSaleHandler(Activity activity) {
        this.activity = activity;
    }

    public void getDataServer() {
        GetServerData getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlKhuyenMai);


    }

    @Override
    public void serverCallBack(String dataServer) {
        ParseSale parseSale = new ParseSale(dataServer);
        containerData(parseSale.parData());
    }

    private void containerData(ArrayList<Sale> saleArrayList) {
        SaleFragment.saleCallBack.saleCallBack(saleArrayList);
    }

    @Override
    public void serverCallBack(String dataServer, String key) {

    }
}
