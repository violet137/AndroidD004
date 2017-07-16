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

    ArrayList<ModeMenuMain> arrayModeMenuMain;
    int[] arrPhoto;
    String[] arrName;
    ModeMenuMain modeMenuMain;
    AdapterMenuMain adapterMenuMain;
    private BaseFragment baseFragment;
    View.OnClickListener onClickListener;

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_main, container, false);


        lv_menu_main = (ListView) view.findViewById(R.id.lv_menu_mani);

        lv_menu_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                modeMenuMain = arrayModeMenuMain.get(position);
                itemClickListener(position);

            }
        });

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "okokokokokokoo", Toast.LENGTH_SHORT).show();
            }
        };

        //load data
        LoadData();
        displayListview();
        baseFragment = new BaseFragment(getActivity().getSupportFragmentManager());
        return view;
    }

    private void itemClickListener(int position) {
        String temp ;
        switch (position){
//            case SupportKeyList.Advertise:
//                temp = "Quảng Cáo";
//                break;
            case SupportKeyList.Ladies:
                temp = "Ladies";
                baseFragment.ChuyenFragment(new XuHuongThoiTrangFragment(1), SupportKeyList.TAG_XU_HUONG_THOI_TRANG, false);
                break;
            case SupportKeyList.Men:
                temp = "Men";
                break;
            case SupportKeyList.Kids:
                temp = "Kids";
                break;
            case SupportKeyList.Home:
                temp = "Home";
                break;
            case SupportKeyList.Magazine:
                temp = "Magazine";
                break;
            default:
                temp = "Mục chưa biết";
                break;
        }
        Toast.makeText(getActivity(), temp, Toast.LENGTH_SHORT).show();
    }

    private void displayListview() {
        adapterMenuMain = new AdapterMenuMain(getActivity(), R.layout.item_listview_menu_main, arrayModeMenuMain,onClickListener);
        lv_menu_main.setAdapter(adapterMenuMain);
    }

    private void LoadData() {
        arrName = getResources().getStringArray(R.array.name_menu_main);
        String []arrLink_MenuType = getResources().getStringArray(R.array.link_MenuType);
        String []arrLink_Fashion = getResources().getStringArray(R.array.link_Fashion);


        arrayModeMenuMain = new ArrayList<>();
        for(int i = 0; i< (arrLink_Fashion.length + arrLink_MenuType.length); i++){
            ModeMenuMain modeMenuMain = new ModeMenuMain();
            if (i<=5){
                modeMenuMain.setName(arrName[i]);
            }
            arrayModeMenuMain.add(modeMenuMain);
        }

    }


}
