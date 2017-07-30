package vn.com.greenacademy.shopping.Fragment.Magazine;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.com.greenacademy.shopping.Handle.HandleData.Magazine.MagazineHandler;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MagazineFragment extends Fragment {

    TextView textViewMain;
    public MagazineFragment(TextView textViewMain) {
        // Required empty public constructor
        this.textViewMain = textViewMain;
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
        magazineHandler.setLayoutMagazineFragment(viewPager, tabLayout, textViewMain);

        textViewMain.setVisibility(View.VISIBLE);
        textViewMain.setText("Magazine");

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();

        return view;
    }

}
