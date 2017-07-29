package vn.com.greenacademy.shopping.Fragment.Magazine;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.MagazineHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine.AdapterMagazineViewPager;
import vn.com.greenacademy.shopping.Interface.MagazineTypeCallBack;
import vn.com.greenacademy.shopping.Model.MagazineType;
import vn.com.greenacademy.shopping.Network.AsynTask.GetMagazineType;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;

/**
 * A simple {@link Fragment} subclass.
 */
public class MagazineFragment extends Fragment {


    public MagazineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_magazine, container, false);

        final TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_magazine_fragment);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager_magazine_fragment);

        MagazineHandler magazineHandler = new MagazineHandler(getActivity());
        magazineHandler.getMagazineType();
        magazineHandler.setLayoutMagazineFragment(viewPager, tabLayout);

        return view;
    }

}
