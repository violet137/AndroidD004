package vn.com.greenacademy.shopping.Fragment.Main.Magazine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Handle.HandleData.MagazineHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine.AdapterMagazineRecyclerView;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MagazineRecyclerViewFragment extends Fragment {

    int positionViewPagger;
    public MagazineRecyclerViewFragment(int positionViewPagger) {
        // Required empty public constructor
        this.positionViewPagger = positionViewPagger;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.view_recyclerView_fragment);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        // magazineHandler doi tượng dieu khien cuc recyclerView
        MagazineHandler magazineHandler = new MagazineHandler(getContext());

        // ham nhan su kien click item tren view
        magazineHandler.clickItem(recyclerView);

        // ham đổ dữ liệu lên recyclerView
        magazineHandler.displayView(recyclerView,positionViewPagger);

        return view;
    }

}
