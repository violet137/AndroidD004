package vn.com.greenacademy.shopping.Fragment.Sale;

import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang.ChiTietSetDoDialog;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Sale.ParseSaleDetail;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham.ListSanPhamAdapter;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.XuHuongThoiTrang.ListSetDoAdapter;
import vn.com.greenacademy.shopping.Handle.HandleUi.Dialog.LoadingDialog;
import vn.com.greenacademy.shopping.Handle.HandleUi.ImageLoad;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Interface.SetDoCallBack;
import vn.com.greenacademy.shopping.Model.Sale;
import vn.com.greenacademy.shopping.Model.XuHuongThoiTrang;
import vn.com.greenacademy.shopping.Network.AsynTask.DataServerAsyncTask;
import vn.com.greenacademy.shopping.Network.AsynTask.GetServerData;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChiTietSaleFragment extends Fragment {
    private TextView tvDescription;
    private ImageView vBanner;
    private RecyclerView vListSanPham;
    private NestedScrollView scrollView;

    private LoadingDialog loadingDialog;
    private ImageLoad imageLoad;
    private Sale sale;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chi_tiet_sale, container, false);
        // Inflate the layout for this fragment
        vBanner = (ImageView) root.findViewById(R.id.head_image_fragment_chi_tiet_sale);
        tvDescription = (TextView) root.findViewById(R.id.description_text_fragment_chi_tiet_sale);
        vListSanPham = (RecyclerView) root.findViewById(R.id.list_san_pham_fragment_chi_tiet_sale);
        scrollView = (NestedScrollView) root.findViewById(R.id.sv_fragment_chi_tiet_sale);

        if (!isFromBackStack) {
            loadingDialog.show();

            ServerCallBack serverCallBack = new ServerCallBack() {
                @Override
                public void serverCallBack(String dataServer) {
                    ParseSaleDetail parseSaleDetail = new ParseSaleDetail(dataServer);
                    sale = parseSaleDetail.parData();
                    LoadUI();
                }

                @Override
                public void serverCallBack(String dataServer, String key) {

                }
            };

            GetServerData getServerData = new GetServerData(serverCallBack);
            getServerData.execute(ServerUrl.UrlChiTietKhuyenMai+ String.valueOf(getArguments().getLong("idSale")));
        }

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

        imageLoad.load(sale.getHinhDaiDien(), vBanner);
        tvDescription.setText(sale.getMota());
        //List sản phẩm
        vListSanPham.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        vListSanPham.setNestedScrollingEnabled(false);
        vListSanPham.setAdapter(new ListSanPhamAdapter(getActivity(), sale.getSanPhamArrayList(), new BaseFragment(getActivity(), getActivity().getSupportFragmentManager()), null, imageLoad));
        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0, scrollPosition);
                loadingDialog.dismiss();
            }
        }, 150L);
    }

}
