package vn.com.greenacademy.shopping.Fragment.Sale;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.Sale.SaleHandler;
import vn.com.greenacademy.shopping.Interface.SaleCallBack;
import vn.com.greenacademy.shopping.MainActivity;
import vn.com.greenacademy.shopping.Model.Sale;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleFragment extends Fragment {

    public static SaleCallBack saleCallBack;

    boolean listenerBack = false;

    SaleHandler saleHandler;
    ArrayList<Sale> dataPhotoSales;
    public static ArrayList<SanPham> dataProductSaless;

    ViewPager vpPhotos;
    ViewPager vpProducts;
    RadioGroup rgSLVPProduct;
    LinearLayout llDanhMuc;
    LinearLayout itemAll;

    public SaleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        saleHandler =new SaleHandler(getActivity());

        saleHandler.getDataServer();

        saleHandler.Click();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.tvTenMuc.setText("Khuyến Mãi");
        MainActivity.tvTenMuc.setVisibility(View.VISIBLE);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale, container, false);

        llDanhMuc = (LinearLayout) view.findViewById(R.id.listDM_saleFragment);

        view.findViewById(R.id.llXemAll_saleFragment).setOnClickListener(SaleHandler.onClickListener);
        view.findViewById(R.id.llNu_saleFragment).setOnClickListener(SaleHandler.onClickListener);
        view.findViewById(R.id.llNam_saleFragment).setOnClickListener(SaleHandler.onClickListener);
        view.findViewById(R.id.llTreEm_saleFragment).setOnClickListener(SaleHandler.onClickListener);
        view.findViewById(R.id.llTrangTri_saleFragment).setOnClickListener(SaleHandler.onClickListener);

        vpPhotos = (ViewPager) view.findViewById(R.id.vpPhotos_saleFragment);

        vpProducts = (ViewPager) view.findViewById(R.id.vpProducts_saleFragment);

        rgSLVPProduct = (RadioGroup) view.findViewById(R.id.rgSoLuongVPProducts_saleFragment);

        saleCallBack = new SaleCallBack() {
            @Override
            public void saleCallBack(ArrayList<Sale> saleArrayList) {
                dataPhotoSales = saleArrayList;
                dataProductSaless = new ArrayList<>();
                for (int i = 0; i < saleArrayList.size(); i++) {
                    ArrayList<SanPham> listSP = saleArrayList.get(i).getSanPhamArrayList();
                    for (int j = 0; j < listSP.size(); j++) {
                        if (!listSP.get(j).getHinhDaiDien().equals("string")){
                            dataProductSaless.add(listSP.get(j));
                        }else {
                            break;
                        }
                    }
                }

                saleHandler.setAdapterVPPhotos(getChildFragmentManager(),dataPhotoSales,vpPhotos);

                saleHandler.setAdapterVPProducts(getChildFragmentManager(),dataProductSaless,vpProducts, rgSLVPProduct);

                llDanhMuc.setVisibility(View.VISIBLE);
            }
        };

        getActivity().supportInvalidateOptionsMenu();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listenerBack){
            saleHandler.setAdapterVPPhotos(getChildFragmentManager(),dataPhotoSales,vpPhotos);

            saleHandler.setAdapterVPProducts(getChildFragmentManager(),dataProductSaless,vpProducts, rgSLVPProduct);

            llDanhMuc.setVisibility(View.VISIBLE);
        }
        listenerBack = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listenerBack = true;
    }
}
