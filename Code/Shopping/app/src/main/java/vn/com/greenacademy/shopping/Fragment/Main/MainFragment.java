package vn.com.greenacademy.shopping.Fragment.Main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Adapter.AdapterMenuMain;
import vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang.XuHuongThoiTrangFragment;
import vn.com.greenacademy.shopping.Handle.HandleData.MainMenuHandler;
import vn.com.greenacademy.shopping.Model.ModeMenuMain;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private RecyclerView listXuHuongThoiTrang;

    ListView lv_menu_main;
    MainMenuHandler mainMenuHandler;

    private BaseFragment baseFragment;

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_main, container, false);

        lv_menu_main = (ListView) view.findViewById(R.id.lv_menu_mani);
        // mainMenuHandler la phần dieu khien click list main menu
        mainMenuHandler = new MainMenuHandler(getActivity());

        lv_menu_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ham click vao phan main menu
                mainMenuHandler.itemClickMenu(position);

            }
        });

        // ham click vao phan ảnh quảng cáo
        mainMenuHandler.clickAdvertise();

        //load data
        mainMenuHandler.loadData();
        mainMenuHandler.displayListview(lv_menu_main);
        return view;
    }




}
