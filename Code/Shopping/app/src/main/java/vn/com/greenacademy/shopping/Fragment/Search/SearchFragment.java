package vn.com.greenacademy.shopping.Fragment.Search;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.Search.SearchHandler;
import vn.com.greenacademy.shopping.Interface.ObjectCallBack;
import vn.com.greenacademy.shopping.Model.DanhMuc.MucSanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    public static ObjectCallBack objectCallBack;

    boolean listenerBack = false;

    SearchHandler searchHandler;

    ArrayList<SanPham> dataTopXem;
    ArrayList<MucSanPham> dataTopTim;
    ArrayList<MucSanPham> dataLichSu;

    RecyclerView rvTopXem;
    RecyclerView rvTopTim;
    RecyclerView rvLichSu;

    LinearLayout itemGiaoHang;
    LinearLayout itemThanhToan;
    LinearLayout itemTraHang;
    LinearLayout itemHoTro;
    public static ScrollView itemLayout;
    public static ListView lvTuKhoa;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchHandler = new SearchHandler(getActivity());

        searchHandler.click();

        searchHandler.getDataServer();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        rvTopXem = (RecyclerView) view.findViewById(R.id.rvTopXem_SearchFragment);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvTopXem.setLayoutManager(horizontalLayoutManagaer);
        rvTopXem.setDrawingCacheEnabled(true);

        rvTopTim = (RecyclerView) view.findViewById(R.id.rvTopTim_SearchFragment);
        rvTopTim.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        rvLichSu = (RecyclerView) view.findViewById(R.id.rvTimKiem_SearchFragment);
        rvLichSu.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        itemGiaoHang = (LinearLayout) view.findViewById(R.id.itemGiaoHang_SearchFragment);
        itemThanhToan = (LinearLayout) view.findViewById(R.id.itemThanhToan_SearchFragment);
        itemTraHang = (LinearLayout) view.findViewById(R.id.itemTraSP_SearchFragment);
        itemHoTro   = (LinearLayout) view.findViewById(R.id.itemHoTro_SearchFragment);
        itemLayout = (ScrollView) view.findViewById(R.id.itemLayout_search);
        lvTuKhoa = (ListView) view.findViewById(R.id.lvTuKhoa_search);

        objectCallBack = new ObjectCallBack() {
            @Override
            public void callBack(Object object, int flag) {
                switch (String.valueOf(flag)){
                    case SupportKeyList.Search_SPHot:
                        dataTopXem = (ArrayList<SanPham>) object;
                        rvTopXem.setAdapter(searchHandler.getAdapter(dataTopXem));
                        break;
                    case SupportKeyList.Search_TKHot:
                        dataTopTim = (ArrayList<MucSanPham>) object;
                        rvTopTim.setAdapter(searchHandler.getAdapterTopTim(dataTopTim));
                        break;
                    case SupportKeyList.Search_LSSearch:
                        dataLichSu = (ArrayList<MucSanPham>) object;
                        rvLichSu.setAdapter(searchHandler.getAdapterLichSu(dataLichSu));
                        break;

                }
            }
        };

        searchHandler.loadData();
        searchHandler.loadLichSuSearch();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listenerBack){
            rvTopXem.setAdapter(searchHandler.getAdapter(dataTopXem));

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listenerBack = true;
    }
}
