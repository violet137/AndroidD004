package vn.com.greenacademy.shopping.Fragment.Magazine;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.Magazine.MagazineHandler;
import vn.com.greenacademy.shopping.Interface.MagazineCallBack;
import vn.com.greenacademy.shopping.MainActivity;
import vn.com.greenacademy.shopping.Model.Magazine;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MagazineFragment extends Fragment {

    public MagazineFragment() {
        // Required empty public constructor
    }

    MagazineHandler magazineHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_magazine, container, false);

        final TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_magazine_fragment);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager_magazine_fragment);


        magazineHandler = new MagazineHandler(getActivity());
        magazineHandler.getMagazineType();
        magazineHandler.setLayoutMagazineFragment(viewPager, tabLayout);

        MainActivity.tvTenMuc.setVisibility(View.VISIBLE);
        MainActivity.tvTenMuc.setText("Tạp Chí");

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();

        return view;
    }
}
