package vn.com.greenacademy.shopping.Fragment.Sale;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.SaleHandler;
import vn.com.greenacademy.shopping.Interface.SaleCallBack;
import vn.com.greenacademy.shopping.Model.Sale;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleFragment extends Fragment {

    public static SaleCallBack saleCallBack;

    public SaleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale, container, false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_SaleFragment);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        final SaleHandler saleHandler =new SaleHandler(getActivity());

        saleHandler.getDataServer();

        saleCallBack = new SaleCallBack() {
            @Override
            public void saleCallBack(ArrayList<Sale> saleArrayList) {
                recyclerView.setAdapter(saleHandler.getAdapter(saleArrayList));
            }
        };

        return view;
    }

}
