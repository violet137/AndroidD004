package vn.com.greenacademy.shopping.Fragment.Main.DanhMucSanPham;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.DanhMucSanPham.ClickListenerDanhMucSanPham;
import vn.com.greenacademy.shopping.Handle.HandleData.DanhMucSanPham.DanhMucSPHandler;
import vn.com.greenacademy.shopping.Interface.ObjectCallBack;
import vn.com.greenacademy.shopping.Model.Home.MenuPhoto;
import vn.com.greenacademy.shopping.Model.MucSanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DanhMucSanPhamFragment extends Fragment {

    public static ObjectCallBack objectCallBack;

    boolean listenerBack = false;

    ClickListenerDanhMucSanPham clickListenerDanhMucSanPham;

    DanhMucSPHandler danhMucSPHandler;

    ArrayList<MucSanPham> dataMucSanPham;

    ArrayList<SanPham> dataHotProduct;

    MucSanPham dataPhotoMucSP;

    String idDanhMuc;

    LinearLayout llXemTatCa;
    ImageView ivPhoto;
    ViewPager vpHotProduct;
    RecyclerView rvMenu;
    LinearLayout itemHotProduct;

    public DanhMucSanPhamFragment(String idDanhMuc) {
        this.idDanhMuc = idDanhMuc;
    }

    //    public static DanhMucSanPhamFragment newInstance(String idDanhMuc) {
//        Bundle args = new Bundle();
//        args.putString("a",idDanhMuc);
//        DanhMucSanPhamFragment fragment = new DanhMucSanPhamFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clickListenerDanhMucSanPham = new ClickListenerDanhMucSanPham(getActivity());

        clickListenerDanhMucSanPham.Click();

        clickListenerDanhMucSanPham.listSanPhamCallBcak();

        danhMucSPHandler = new DanhMucSPHandler(getActivity());
//        String a = getArguments().getString("idDanhMuc");
        danhMucSPHandler.getDataServer(idDanhMuc);

        danhMucSPHandler.getDataNewProductServer(idDanhMuc, 9);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_danh_muc_san_pham, container, false);

        ivPhoto = (ImageView) view.findViewById(R.id.ivFashion_item_main_menu);

        vpHotProduct = (ViewPager) view.findViewById(R.id.vpSanPham_vp_sp_hot);

        itemHotProduct = (LinearLayout) view.findViewById(R.id.item_vp_sp_hot);

        llXemTatCa = (LinearLayout) view.findViewById(R.id.llXemAll_DanhMucSanPham_Fragment);

        MucSanPham temp = new MucSanPham();
        temp.setId(-2);
        temp.setTenDanhMuc("Tất Cả");
        switch (idDanhMuc){
            case "Nam":
                temp.setLoaiThoiTrang("Nam");
                break;
            case "Nu":
                temp.setLoaiThoiTrang("Nữ");
                break;
            case "TreEm":
                temp.setLoaiThoiTrang("Trẻ Em");
                break;
            case "Home":
                temp.setLoaiThoiTrang("Trang Trí");
                break;
            default:
                temp.setLoaiThoiTrang("");
                break;
        }
        llXemTatCa.setTag(temp);
        llXemTatCa.setOnClickListener(ClickListenerDanhMucSanPham.onClickListener);

        rvMenu = (RecyclerView) view.findViewById(R.id.rvMenu_DanhMucSanPham_Fragment);
        rvMenu.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        rvMenu.setNestedScrollingEnabled(false);

        objectCallBack = new ObjectCallBack() {
            @Override
            public void callBack(Object object, int flag) {
                switch (flag){
                    case SupportKeyList.ClickDanhMuc_Photo:
                        dataPhotoMucSP = (MucSanPham) object;
                        danhMucSPHandler.setPhoto((MucSanPham) object, ivPhoto);
                        break;
                    case SupportKeyList.ClickDanhMuc_Menu:
                        dataMucSanPham = (ArrayList<MucSanPham>) object;
                        rvMenu.setAdapter(danhMucSPHandler.getAdapterListDM((ArrayList<MucSanPham>)object));
                        break;
                    case SupportKeyList.ClickDanhMuc_HotProduct:
                        llXemTatCa.setVisibility(View.VISIBLE);
                        dataHotProduct = ((MenuPhoto)object).getSanPhamArrayList();
                        vpHotProduct.setAdapter(danhMucSPHandler.getAdapterHotProduct(getChildFragmentManager(),dataHotProduct));
                        itemHotProduct.setVisibility(View.VISIBLE);
                        break;
                    default:
                        Toast.makeText(getActivity(),"Lổi case DanhMucSanPhamFragment", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listenerBack){
            danhMucSPHandler.setPhoto(dataPhotoMucSP, ivPhoto);

            rvMenu.setAdapter(danhMucSPHandler.getAdapterListDM(dataMucSanPham));

            vpHotProduct.setAdapter(danhMucSPHandler.getAdapterHotProduct(getChildFragmentManager(),dataHotProduct));

            llXemTatCa.setVisibility(View.VISIBLE);

            itemHotProduct.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listenerBack = true;
    }
}
