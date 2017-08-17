package vn.com.greenacademy.shopping.Fragment.Home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.Home.ClickListenerHomeItem;
import vn.com.greenacademy.shopping.Handle.HandleData.Home.MainMenuHandler;
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
    // cac doi tuong xml
    RecyclerView rvProduct;
    RecyclerView rvFashion;
    RecyclerView rvMagazien;
    ViewPager vpNewProduct;
    ViewFlipper vfAdvertise;
    NestedScrollView vListHome;
    LinearLayout itemAdvertise;
    LinearLayout itemNewProduct;

    // listMainMenuCallBack nhận dữ liệu hoàn chỉnh trên server trả về
    public static ListMainMenuCallBack listMainMenuCallBack;

    // mainMenuHandler lớp điều khiển của MainFragment
    private MainMenuHandler mainMenuHandler = null;

    // luu data server trả về khi goi thanh cong
    private ArrayList<MenuMain> dataAdvertise;
    private ArrayList<MenuMain> dataProducts;
    private ArrayList<MenuMain> dataNewProduct;
    private ArrayList<MenuMain> dataFashion;
    private ArrayList<MenuMain> dataMagazine;
    private int scrollPosition = 0;
    //
    boolean backListener = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Xử lý sự kiện
        mainMenuHandler = new MainMenuHandler(getActivity(), new BaseFragment(getActivity(), getActivity().getSupportFragmentManager()));

        // goi hàm lấy dữ liệu trên server xuống
        mainMenuHandler.getDataServer();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // MainFragment ko sử dụng tvTenMuc ngoai MainActivity
        MainActivity.tvTenMuc.setVisibility(View.GONE);

        ClickListenerHomeItem.Click(getActivity());

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_menu_home, container, false);

        vListHome = (NestedScrollView) view.findViewById(R.id.scroll_view_list_home);

        vfAdvertise = (ViewFlipper) view.findViewById(R.id.vfAdvertise_item_main_menu);

        rvProduct = (RecyclerView) view.findViewById(R.id.rvProduct_menuHome);

        vpNewProduct = (ViewPager) view.findViewById(R.id.vpNewProduct_item_main_menu);

        rvFashion = (RecyclerView) view.findViewById(R.id.rvFashion_menuHome);

        rvMagazien = (RecyclerView) view.findViewById(R.id.rvMagazine_menuHome);

        itemAdvertise = (LinearLayout) view.findViewById(R.id.itemAdvertise_menuHome);

        itemNewProduct = (LinearLayout) view.findViewById(R.id.itemNewProduct_menuHome);
        // cách dưới để disable scroll recyclerview mà vẫn có thể click item dc
        // thiết lập xml android:layout_weight="1" cho recyclerView để có thể show toàn bộ nội dung trong list

        rvProduct.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        rvProduct.setNestedScrollingEnabled(false);

        rvFashion.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        rvFashion.setNestedScrollingEnabled(false);

        rvMagazien.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        rvMagazien.setNestedScrollingEnabled(false);

        // nhận dữ liệu server trả về
        listMainMenuCallBack = new ListMainMenuCallBack() {
            @Override
            public void callBack(ArrayList<MenuMain> menuMainArrayList, int flag) {

                switch (flag){
                    case SupportKeyList.ClickHome_Advertise:
                        dataAdvertise = menuMainArrayList;
                        mainMenuHandler.setDataAdvertise(menuMainArrayList, vfAdvertise);
                        itemAdvertise.setVisibility(View.VISIBLE);
                        break;
                    case SupportKeyList.ClickHome_Products:
                        dataProducts = menuMainArrayList;
                        mainMenuHandler.setAdapter(menuMainArrayList, flag, rvProduct);
                        break;
                    case SupportKeyList.ClickHome_NewProduct:
                        dataNewProduct = menuMainArrayList;
                        mainMenuHandler.setDataNewProduct(getChildFragmentManager(), menuMainArrayList, vpNewProduct);
                        itemNewProduct.setVisibility(View.VISIBLE);
                        break;
                    case SupportKeyList.ClickHome_Fashion:
                        dataFashion = menuMainArrayList;
                        mainMenuHandler.setAdapter(menuMainArrayList, flag, rvFashion);
                        break;
                    case SupportKeyList.ClickHome_Magazine:
                        dataMagazine = menuMainArrayList;
                        mainMenuHandler.setAdapter(menuMainArrayList, flag, rvMagazien);
                        break;
                }
                MainActivity.thoatSplashScreen();
            }

        };

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (backListener){
            mainMenuHandler.setDataAdvertise(dataAdvertise, vfAdvertise);
            itemAdvertise.setVisibility(View.VISIBLE);

            mainMenuHandler.setAdapter(dataProducts, SupportKeyList.ClickHome_Products, rvProduct);

            mainMenuHandler.setDataNewProduct(getChildFragmentManager(),dataNewProduct, vpNewProduct);
            itemNewProduct.setVisibility(View.VISIBLE);

            mainMenuHandler.setAdapter(dataFashion, SupportKeyList.ClickHome_Fashion, rvFashion);

            mainMenuHandler.setAdapter(dataMagazine, SupportKeyList.ClickHome_Magazine, rvMagazien);

            vListHome.scrollTo(0, scrollPosition);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        backListener = true;
        scrollPosition = vListHome.getScrollY();
    }

}

