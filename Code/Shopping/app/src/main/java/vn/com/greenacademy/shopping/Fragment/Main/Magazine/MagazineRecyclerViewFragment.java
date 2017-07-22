package vn.com.greenacademy.shopping.Fragment.Main.Magazine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine.AdapterMagazineRecyclerView;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MagazineRecyclerViewFragment extends Fragment {


    public MagazineRecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.view_recyclerView_fragment);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        AdapterMagazineRecyclerView adapterMagazineRecyclerView = new AdapterMagazineRecyclerView(getContext());
        recyclerView.setAdapter(adapterMagazineRecyclerView);
        return view;
    }

}
