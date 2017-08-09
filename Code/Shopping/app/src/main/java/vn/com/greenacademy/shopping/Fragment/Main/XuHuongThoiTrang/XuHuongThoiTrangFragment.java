package vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham.ListSanPhamAdapter;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.XuHuongThoiTrang.ListSetDoAdapter;
import vn.com.greenacademy.shopping.Handle.HandleUi.Dialog.LoadingDialog;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Interface.SetDoCallBack;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Model.XuHuongThoiTrang;
import vn.com.greenacademy.shopping.Network.AsynTask.GoiAPIServerAsyncTask;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;
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
    private ScrollView scrollView;

    private LoadingDialog loadingDialog;
    private XuHuongThoiTrang xuHuongThoiTrang;
    private long idXuHuong;
    private int scrollPosition = 0;

    public XuHuongThoiTrangFragment(long idXuHuong){
        this.idXuHuong = idXuHuong;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loadingDialog = new LoadingDialog(getActivity(), 0, new BaseFragment(getActivity().getSupportFragmentManager()));
        loadingDialog.show();
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_xu_huong_thoi_trang, container, false);
        vBanner = (ImageView) root.findViewById(R.id.head_image_fragment_xu_huong_thoi_trang);
        vVideoBanner =(VideoView) root.findViewById(R.id.head_video_fragment_xu_huong_thoi_trang);
        vListSetDo = (RecyclerView) root.findViewById(R.id.list_set_do_fragment_xu_huong_thoi_trang);
        vListSanPham = (RecyclerView) root.findViewById(R.id.list_san_pham_fragment_xu_huong_thoi_trang);
        scrollView = (ScrollView) root.findViewById(R.id.sv_xu_huong_thoi_trang);

        getActivity().supportInvalidateOptionsMenu();
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new GoiAPIServerAsyncTask(this).execute(SupportKeyList.API_DATA_XU_HUONG_THOI_TRANG, ServerUrl.XuHuongThoiTrangUrl + String.valueOf(idXuHuong), String.valueOf(idXuHuong));
    }

    @Override
    public void onPause() {
        super.onPause();
        scrollPosition = scrollView.getScrollY();
    }

    private void LoadUI() {
        ImageLoad imageLoad = new ImageLoad(getActivity());
        SanPhamHandler sanPhamHandler = new SanPhamHandler(getActivity());
        if(!xuHuongThoiTrang.isVideo()) {
            imageLoad.load(xuHuongThoiTrang.getHinhDaiDien(), vBanner);
        }
//        else {
//            vVideoBanner.setVisibility(View.VISIBLE);
//            vVideoBanner.setVideoURI(xuHuongThoiTrang.getLinkHinhMoTa());
//        }

        //List set đồ
        if (xuHuongThoiTrang.getListSetDo().size() != 0) {
            vListSetDo.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            vListSetDo.setNestedScrollingEnabled(false);
            vListSetDo.setAdapter(new ListSetDoAdapter(getActivity() , this, xuHuongThoiTrang.getListSetDo(), imageLoad));
        }
        //List sản phẩm
        vListSanPham.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        vListSanPham.setNestedScrollingEnabled(false);
        vListSanPham.setAdapter(new ListSanPhamAdapter(getActivity(), xuHuongThoiTrang.getListSanPham(), new BaseFragment(getActivity().getSupportFragmentManager()), null, imageLoad));
        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0, scrollPosition);
                loadingDialog.dismiss();
            }
        }, 150L);
    }

    @Override
    public void clickSetDo(int position) {
        ChiTietSetDoDialog chiTietSetDoDialog = new ChiTietSetDoDialog(getActivity(), xuHuongThoiTrang.getListSetDo().get(position).getListSanPham(), new BaseFragment(getActivity().getSupportFragmentManager()));
        chiTietSetDoDialog.show();
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
                LoadData((XuHuongThoiTrang) bundle.getSerializable("DataXuHuongThoiTrang"));
                LoadUI();
                break;
            default:
                break;
        }
    }

    private void LoadData(XuHuongThoiTrang dataXuHuongThoiTrang) {
        xuHuongThoiTrang = dataXuHuongThoiTrang;
        if (dataXuHuongThoiTrang.getListSetDo().size() != 0)
            for (int i = 0; i < dataXuHuongThoiTrang.getListSetDo().size(); i++)
                for (int j = 0; j < dataXuHuongThoiTrang.getListSetDo().get(i).getListSanPham().size(); j++)
                    xuHuongThoiTrang.getListSanPham().add(dataXuHuongThoiTrang.getListSetDo().get(i).getListSanPham().get(j));
    }

}
