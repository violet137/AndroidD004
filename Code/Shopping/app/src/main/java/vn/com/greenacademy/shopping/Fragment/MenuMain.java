package vn.com.greenacademy.shopping.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuMain extends Fragment {


    public MenuMain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_main, container, false);

        final ViewFlipper viewFlipper = (ViewFlipper) view.findViewById(R.id.vf_menu_main);

        String[] arrayLinkPhoto = getResources().getStringArray(R.array.link_photo_menu);


        for (int i = 0; i < arrayLinkPhoto.length; i++) {
            // chu y neu ko tai hinh dc thi kiem t(ra lai mang
            ImageView image = new ImageView(getActivity());
//            Picasso.with(getActivity()).load(arrayLinkPhoto[i]).resize(380,150).centerCrop().into(image);
            Picasso.with(getActivity()).load(arrayLinkPhoto[i]).into(image);
            viewFlipper.addView(image);
        }
        viewFlipper.startFlipping();
        return view;
    }

}
