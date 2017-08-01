package vn.com.greenacademy.shopping.Fragment.Main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.MainMenuHandler;
import vn.com.greenacademy.shopping.Interface.ListMainMenuCallBack;
import vn.com.greenacademy.shopping.MainActivity;
import vn.com.greenacademy.shopping.Model.MenuMain;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    public static ListMainMenuCallBack listMainMenuCallBack;

    private MainMenuHandler mainMenuHandler = null;

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.tvTenMuc.setVisibility(View.GONE);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        final ListView lv_menu_main = (ListView) view.findViewById(R.id.lv_menu_mani);

        //Xử lý sự kiện
        mainMenuHandler = new MainMenuHandler(getActivity(), new BaseFragment(getActivity().getSupportFragmentManager()));

        mainMenuHandler.getDataServer();

        listMainMenuCallBack = new ListMainMenuCallBack() {
            @Override
            public void callBack(ArrayList<MenuMain> menuMainArrayList) {
                lv_menu_main.setAdapter(mainMenuHandler.getAdapter(menuMainArrayList));
            }
        };

        // ham dieu khien click item tren menu main
        mainMenuHandler.clickItemMenuMain();

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();

        return view;
    }
}
