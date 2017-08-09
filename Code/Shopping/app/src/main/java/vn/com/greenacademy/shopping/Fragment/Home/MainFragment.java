package vn.com.greenacademy.shopping.Fragment.Home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.Home.ClickListenerHomeItem;
import vn.com.greenacademy.shopping.Handle.HandleData.Home.MainMenuHandler;
import vn.com.greenacademy.shopping.Interface.FlagCallBack;
import vn.com.greenacademy.shopping.Interface.ListMainMenuCallBack;
import vn.com.greenacademy.shopping.MainActivity;
import vn.com.greenacademy.shopping.Model.Home.MenuMain;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    // listMainMenuCallBack nhận dữ liệu hoàn chỉnh trên server trả về
    public static ListMainMenuCallBack listMainMenuCallBack;

    // mainMenuHandler lớp điều khiển của MainFragment
    private MainMenuHandler mainMenuHandler = null;

    public static FlagCallBack flagCallBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // MainFragment ko sử dụng tvTenMuc ngoai MainActivity
        MainActivity.tvTenMuc.setVisibility(View.GONE);

        ClickListenerHomeItem.Click(getActivity());

//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_main, container, false);
//
//        // ánh xạ file xml
//        final ListView lv_menu_main = (ListView) view.findViewById(R.id.lv_menu_mani);

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_menu_home, container, false);

//        final RecyclerView rvMenu = (RecyclerView) view.findViewById(R.id.rvMenu_MenuHome_fragment);
        final RecyclerView rvProduct = (RecyclerView) view.findViewById(R.id.rvProduct_menuHome);
        final RecyclerView rvFashion = (RecyclerView) view.findViewById(R.id.rvFashion_menuHome);
        final RecyclerView rvMagazien = (RecyclerView) view.findViewById(R.id.rvMagazine_menuHome);
//        rvMenu.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        // có thể dùng 1 trong 2 cách dưới để disable scroll recyclerview mà vẫn có thể click item dc
        // thiết lập xml android:layout_weight="1" cho recyclerView để có thể show toàn bộ nội dung trong list

        rvProduct.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        rvProduct.setNestedScrollingEnabled(false);

        rvFashion.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        rvFashion.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                // Stop only scrolling.
                return rv.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING;
            }
        });
        rvMagazien.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        rvMagazien.setNestedScrollingEnabled(false);

        final ViewFlipper vfAdvertise = (ViewFlipper) view.findViewById(R.id.vfAdvertise_item_main_menu);
        final ViewPager vpNewProduct = (ViewPager) view.findViewById(R.id.vpNewProduct_item_main_menu);



        //Xử lý sự kiện
        mainMenuHandler = new MainMenuHandler(getActivity(), new BaseFragment(getActivity().getSupportFragmentManager()));

        // goi hàm lấy dữ liệu trên server xuống
        mainMenuHandler.getDataServer();

        // nhận dữ liệu server trả về
        listMainMenuCallBack = new ListMainMenuCallBack() {
            @Override
            public void callBack(ArrayList<MenuMain> menuMainArrayList, int flag) {

                switch (flag){
                    case SupportKeyList.ClickHome_Advertise:
                        mainMenuHandler.setDataAdvertise(menuMainArrayList, vfAdvertise);
                        break;
                    case SupportKeyList.ClickHome_Products:
                        mainMenuHandler.setAdapter(menuMainArrayList, flag, rvProduct);
                        break;
                    case SupportKeyList.ClickHome_NewProduct:
                        view.findViewById(R.id.layoutNewProduct_menuHome).setVisibility(View.VISIBLE);
                        mainMenuHandler.setDataNewProduct(menuMainArrayList, vpNewProduct);
                        break;
                    case SupportKeyList.ClickHome_Fashion:
                        mainMenuHandler.setAdapter(menuMainArrayList, flag, rvFashion);
                        break;
                    case SupportKeyList.ClickHome_Magazine:
                        mainMenuHandler.setAdapter(menuMainArrayList, flag, rvMagazien);
                        break;
                }
            }

        };

        // ham dieu khien click item tren menu main
        mainMenuHandler.clickItemMenuMain();

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();

        return view;
    }
}

