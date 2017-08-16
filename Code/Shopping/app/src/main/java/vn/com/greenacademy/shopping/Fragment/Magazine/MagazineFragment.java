package vn.com.greenacademy.shopping.Fragment.Magazine;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.Magazine.MagazineHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Magazine.AdapterMagazineViewPager;
import vn.com.greenacademy.shopping.Interface.ObjectCallBack;
import vn.com.greenacademy.shopping.MainActivity;
import vn.com.greenacademy.shopping.Model.Magazine.MagazineDetail;
import vn.com.greenacademy.shopping.Model.Magazine.MagazineType;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MagazineFragment extends Fragment {

    public static ObjectCallBack objectCallBack;

    boolean listenerBack = false;
    boolean listenerNotPause = true;

    TabLayout tabLayout;
    ViewPager viewPager;
    MagazineHandler magazineHandler;
    ArrayList<MagazineType> dataMagazineType;

    public MagazineFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        magazineHandler = new MagazineHandler(getActivity());
        magazineHandler.getMagazineType();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MainActivity.tvTenMuc.setVisibility(View.VISIBLE);
        MainActivity.tvTenMuc.setText("Tạp Chí");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_magazine, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_magazine_fragment);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager_magazine_fragment);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // set su kien scroll cho tabLayout
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        // set su kien click tablayout thay doi viewPagger
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

        // nhận data server trả về hoàn tất
        objectCallBack = new ObjectCallBack() {
            @Override
            public void callBack(Object object, int flag) {
                switch (flag){
                    case 0:
                        dataMagazineType = (ArrayList<MagazineType>)object;
                        viewPager.setAdapter(magazineHandler.getAdapterMagazineType(
                                getChildFragmentManager(),(ArrayList<MagazineType>)object));
                        break;
                    case 1:
                        if (listenerNotPause){
                            for (int i = 0; i < ((AdapterMagazineViewPager)object).getCount(); i++) {
                                tabLayout.addTab(tabLayout.newTab().setText(((AdapterMagazineViewPager)object).getTitle(i)));
                            }
                        }
                        break;
                    case SupportKeyList.Magazine_video:
                        MagazineDetail magazineDetail = (MagazineDetail) object;
                        // sự dang url web
                        Uri web = Uri.parse(magazineDetail.getNoiDung());
                        Intent intent = new Intent(Intent.ACTION_VIEW,web);
                        startActivity(intent);
                        listenerNotPause = false;
                        break;
                }
            }
        };

        listenerNotPause = true;

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listenerBack){

            viewPager.setAdapter(magazineHandler.getAdapterMagazineType(getChildFragmentManager(),dataMagazineType));

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listenerBack = true;
    }
}
