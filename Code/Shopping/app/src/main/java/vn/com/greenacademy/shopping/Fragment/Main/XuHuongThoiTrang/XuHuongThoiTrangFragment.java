package vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham.ListSanPhamAdapter;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.XuHuongThoiTrang.ListSetDoAdapter;
import vn.com.greenacademy.shopping.Handle.HandleUi.Dialog.LoadingDialog;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Interface.SetDoCallBack;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Model.SetDo;
import vn.com.greenacademy.shopping.Model.XuHuongThoiTrang;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class XuHuongThoiTrangFragment extends Fragment implements DataCallBack, SetDoCallBack {
    private ImageView vBanner;
    private VideoView vVideoBanner;
    private RecyclerView vListSetDo;
    private RecyclerView vListSanPham;

    private LoadingDialog loadingDialog;
    private XuHuongThoiTrang xuHuongThoiTrang;
    private long idXuHuong;
    //Data test
    private XuHuongThoiTrang testXuHuongThoiTrang;
    private int[] listDataBanner;
    private int position;

//    public XuHuongThoiTrangFragment(long idXuHuong){
//        this.idXuHuong = idXuHuong;
//    }

    public XuHuongThoiTrangFragment(int position) {
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_xu_huong_thoi_trang, container, false);
        vBanner = (ImageView) root.findViewById(R.id.head_image_fragment_xu_huong_thoi_trang);
        vVideoBanner =(VideoView) root.findViewById(R.id.head_video_fragment_xu_huong_thoi_trang);
        vListSetDo = (RecyclerView) root.findViewById(R.id.list_set_do_fragment_xu_huong_thoi_trang);
        vListSanPham = (RecyclerView) root.findViewById(R.id.list_san_pham_fragment_xu_huong_thoi_trang);

        loadingDialog = new LoadingDialog(getActivity(), new BaseFragment(getActivity().getSupportFragmentManager()));
        loadingDialog.show();
        DataTest();
        LoadUI();

//        new GoiAPIServerAsyncTask(this).execute(SupportKeyList.API_DATA_XU_HUONG_THOI_TRANG, ServerUrl.XuHuongThoiTrangUrl + String.valueOf(idXuHuong), String.valueOf(idXuHuong));

        getActivity().supportInvalidateOptionsMenu();
        return root;
    }

    private void LoadUI() {
//        if(!xuHuongThoiTrang.isVideo()) {
//            ImageLoad imageLoad = new ImageLoad(getActivity());
//            imageLoad.ImageLoad(xuHuongThoiTrang.getLinkHinhMoTa(), vBanner);
//        } else {
//            vVideoBanner.setVisibility(View.VISIBLE);
//            vVideoBanner.setVideoURI(xuHuongThoiTrang.getLinkHinhMoTa());
//        }
        vBanner.setImageResource(listDataBanner[0]);
        //List set đồ
        if (testXuHuongThoiTrang.getListSetDo() != null) {
            vListSetDo.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            vListSetDo.setNestedScrollingEnabled(false);
            vListSetDo.setAdapter(new ListSetDoAdapter(getActivity() , this, testXuHuongThoiTrang.getListSetDo()));
        }
        //List sản phẩm
        vListSanPham.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        vListSanPham.setNestedScrollingEnabled(false);
        vListSanPham.setAdapter(new ListSanPhamAdapter(getActivity(), testXuHuongThoiTrang.getListSanPham(), new BaseFragment(getActivity().getSupportFragmentManager()), null));
        loadingDialog.dismiss();
    }

    @Override
    public void clickSetDo(int position) {
        ChiTietSetDoDialog chiTietSetDoDialog = new ChiTietSetDoDialog(getActivity(), testXuHuongThoiTrang.getListSetDo().get(position), new BaseFragment(getActivity().getSupportFragmentManager()));
        chiTietSetDoDialog.show();
    }

    public void DataTest(){
        ArrayList<SetDo> listSetDo = new ArrayList<>();
        ArrayList<SanPham> listSanPhamSetDo = new ArrayList<>();
        ArrayList<SanPham> listSanPham = new ArrayList<>();
        //List set đồ
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                SanPham sanPham = new SanPham("Sản phẩm " + String.valueOf(j+1), "Men", "Description " + String.valueOf(j+1), "Details " + String.valueOf(j+1), null, null, 20.99, null, 0);
                listSanPhamSetDo.add(sanPham);
            }
            listSetDo.add(new SetDo(i, "Set đồ thứ " + String.valueOf(i), "Description " + String.valueOf(i),null,  null, listSanPhamSetDo));
        }

        //List Sản phẩm
        for (int i = 0; i < 7; i++) {
            SanPham sanPham = new SanPham("Sản phẩm " + String.valueOf(i+1), "Men", "Description " + String.valueOf(i+1), "Details " + String.valueOf(i+1), null, null, 20.99 + i, null, 0);
            listSanPham.add(sanPham);
        }
        testXuHuongThoiTrang = new XuHuongThoiTrang(0, "testTenXuHuong", null, false, "Men", null, listSetDo, listSanPham);

        //Data banner
        TypedArray tempListBanner = getActivity().getResources().obtainTypedArray(R.array.arr_hinh);
        listDataBanner = new int[3];
        for (int i = 0; i < 3; i++) {
            listDataBanner[i] = tempListBanner.getResourceId(i, -1);
        }
    }

    @Override
    public void KetQua(String result, Bundle bundle) {
        switch (result){
            case SupportKeyList.LOI_DATA_SERVER:
                Toast.makeText(getActivity(), getString(R.string.toast_loi_data_server), Toast.LENGTH_LONG).show();
                break;
            case SupportKeyList.LOI_DATA:
                Toast.makeText(getActivity(), getString(R.string.toast_loi_data), Toast.LENGTH_LONG).show();
                break;
            case SupportKeyList.LAY_DATA_THANH_CONG:
                xuHuongThoiTrang = (XuHuongThoiTrang) bundle.getSerializable("DataXuHuongThoiTrang");
                DataTest();
                LoadUI();
                break;
            default:
                break;
        }
        loadingDialog.dismiss();
    }

}
