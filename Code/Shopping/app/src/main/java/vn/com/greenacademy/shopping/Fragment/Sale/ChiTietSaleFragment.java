package vn.com.greenacademy.shopping.Fragment.Sale;

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

import vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang.ChiTietSetDoDialog;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham.ListSanPhamAdapter;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.XuHuongThoiTrang.ListSetDoAdapter;
import vn.com.greenacademy.shopping.Handle.HandleUi.Dialog.LoadingDialog;
import vn.com.greenacademy.shopping.Handle.HandleUi.ImageLoad;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Interface.SetDoCallBack;
import vn.com.greenacademy.shopping.Model.XuHuongThoiTrang;
import vn.com.greenacademy.shopping.Network.AsynTask.DataServerAsyncTask;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChiTietSaleFragment extends Fragment implements DataCallBack {
    private ImageView vBanner;
    private VideoView vVideoBanner;
    private RecyclerView vListSanPham;
    private ScrollView scrollView;

    private LoadingDialog loadingDialog;
    private ImageLoad imageLoad;
    private XuHuongThoiTrang xuHuongThoiTrang;
    private int scrollPosition = 0;
    private boolean isFromBackStack = false;

    public static ChiTietSaleFragment newInstance(long idSale) {
        Bundle args = new Bundle();
        args.putSerializable("idSale", idSale);

        ChiTietSaleFragment fragment = new ChiTietSaleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog(getActivity(), new BaseFragment(getActivity(), getActivity().getSupportFragmentManager()));
        imageLoad = new ImageLoad(getActivity());
        new DataServerAsyncTask(this).execute(SupportKeyList.API_DATA_XU_HUONG_THOI_TRANG, ServerUrl.XuHuongThoiTrangUrl + String.valueOf(getArguments().getLong("idSale")), "GET");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (!isFromBackStack)
            loadingDialog.show();
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_xu_huong_thoi_trang, container, false);
        vBanner = (ImageView) root.findViewById(R.id.head_image_fragment_chi_tiet_sale);
        vVideoBanner =(VideoView) root.findViewById(R.id.head_video_fragment_chi_tiet_sale);
        vListSanPham = (RecyclerView) root.findViewById(R.id.list_san_pham_fragment_chi_tiet_sale);
        scrollView = (ScrollView) root.findViewById(R.id.sv_fragment_chi_tiet_sale);

        getActivity().supportInvalidateOptionsMenu();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFromBackStack)
            LoadUI();
    }

    @Override
    public void onPause() {
        super.onPause();
        scrollPosition = scrollView.getScrollY();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFromBackStack = true;
    }

    private void LoadUI() {
//        SanPhamHandler sanPhamHandler = new SanPhamHandler(getActivity());
        if(!xuHuongThoiTrang.isVideo()) {
            imageLoad.load(xuHuongThoiTrang.getHinhDaiDien(), vBanner);
        }
//        else {
//            vVideoBanner.setVisibility(View.VISIBLE);
//            vVideoBanner.setVideoURI(xuHuongThoiTrang.getLinkHinhMoTa());
//        }

        //List sản phẩm
        vListSanPham.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        vListSanPham.setNestedScrollingEnabled(false);
        vListSanPham.setAdapter(new ListSanPhamAdapter(getActivity(), xuHuongThoiTrang.getListSanPham(), new BaseFragment(getActivity(), getActivity().getSupportFragmentManager()), null, imageLoad));
        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0, scrollPosition);
                loadingDialog.dismiss();
            }
        }, 150L);
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
                LoadUI();
                break;
            default:
                break;
        }
    }

}
