package vn.com.greenacademy.shopping.Fragment.Sale;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Handle.HandleUi.ImageLoad;
import vn.com.greenacademy.shopping.Model.Sale;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalePhotoFragment extends Fragment implements View.OnClickListener {

    Sale sale;

    public SalePhotoFragment(Sale sale) {
        this.sale = sale;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sale_photo, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.ivPhoto_saleFragment);
        imageView.setTag(sale);
        imageView.setOnClickListener(this);
        ImageLoad imageLoad = new ImageLoad(getActivity());
        imageLoad.load(sale.getHinhDaiDien(), imageView);

        return  view;
    }

    @Override
    public void onClick(View v) {
        Sale sale = (Sale) v.getTag();
        Toast.makeText(getContext(), String.valueOf(sale.getId()), Toast.LENGTH_SHORT).show();
    }
}
