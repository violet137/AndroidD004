package vn.com.greenacademy.shopping.Fragment.Search;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import vn.com.greenacademy.shopping.Handle.HandleData.Search.SearchHandler;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    boolean listenerBack = false;

    RecyclerView rvTopXem;
    RecyclerView rvTopTim;
    ListView lvTimKiem;
    LinearLayout itemGiaoHang;
    LinearLayout itemThanhToan;
    LinearLayout itemTraHang;
    LinearLayout itemHoTro;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchHandler searchHandler = new SearchHandler(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
//        LinearLayoutManager horizontalLayoutManagaer
//                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
//        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);
        rvTopXem = (RecyclerView) view.findViewById(R.id.rvTopXem_SearchFragment);
        rvTopTim = (RecyclerView) view.findViewById(R.id.rvTopTim_SearchFragment);
        lvTimKiem = (ListView) view.findViewById(R.id.lvTimKiem_SearchFragment);
        itemGiaoHang = (LinearLayout) view.findViewById(R.id.itemGiaoHang_SearchFragment);
        itemThanhToan = (LinearLayout) view.findViewById(R.id.itemThanhToan_SearchFragment);
        itemTraHang = (LinearLayout) view.findViewById(R.id.itemTraSP_SearchFragment);
        itemHoTro   = (LinearLayout) view.findViewById(R.id.itemHoTro_SearchFragment);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listenerBack){

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listenerBack = true;
    }
}
