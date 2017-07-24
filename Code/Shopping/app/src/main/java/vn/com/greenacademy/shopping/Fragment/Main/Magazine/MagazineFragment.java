package vn.com.greenacademy.shopping.Fragment.Main.Magazine;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine.AdapterMagazineViewPager;
import vn.com.greenacademy.shopping.R;

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

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_magazine_fragment);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager_magazine_fragment);

        AdapterMagazineViewPager adapter = new AdapterMagazineViewPager(getActivity().getSupportFragmentManager(),getContext(),"ALL");
        viewPager.setAdapter(adapter);

        for (int i = 0; i < adapter.getCount(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(adapter.getTitle(i)));
        }

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // thay doi view pager khi click vao tab layout
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

}
