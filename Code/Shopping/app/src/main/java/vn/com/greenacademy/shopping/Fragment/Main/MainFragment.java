package vn.com.greenacademy.shopping.Fragment.Main;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.XuHuongThoiTrang.BannerXuHuongThoiTrangAdapter;
import vn.com.greenacademy.shopping.Model.ModeMenuMain;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment{
    private RecyclerView listXuHuongThoiTrang;
    private BaseFragment baseFragment;
    ListView lv_menu_main;

    ArrayList<ModeMenuMain> arrayModeMenuMain;
    int[] arrPhoto;
    String[] arrName;
    ModeMenuMain modeMenuMain;
    AdapterMenuMain adapterMenuMain;

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

        //load data
        LoadData();
        displayListview();
        baseFragment = new BaseFragment(getActivity().getSupportFragmentManager());
        return view;
    }

    private void itemClickListener(int position) {
        String temp ;
        switch (position){
            case 0:
                temp = "Quảng Cáo";
                break;
            case 1:
                temp = "Ladies";
                baseFragment.ChuyenFragment(new XuHuongThoiTrangFragment(2), SupportKeyList.TAG_XU_HUONG_THOI_TRANG, true);
                break;
            case 2:
                temp = "Men";
                break;
            case 3:
                temp = "Kids";
                break;
            case 4:
                temp = "Home";
                break;
            case 5:
                temp = "Magazine";
                break;
            default:
                temp = "Mục chưa biết";
                break;
        }
        Toast.makeText(getActivity(), temp, Toast.LENGTH_SHORT).show();
    }

    private void displayListview() {
        adapterMenuMain = new AdapterMenuMain(getActivity(), R.layout.item_listview_menu_main, arrayModeMenuMain);
        lv_menu_main.setAdapter(adapterMenuMain);
    }

    private void LoadData() {
        arrName = getResources().getStringArray(R.array.name_menu_main);
        String[] array = getResources().getStringArray(R.array.photo_menu_main);
        TypedArray listAnh = getResources().obtainTypedArray(R.array.photo_menu_main);
        arrPhoto = new int[array.length];
        for(int i=0; i< arrPhoto.length;i++){
            arrPhoto[i]=listAnh.getResourceId(i,-1);
        }
        // danh sach bai hat
        arrayModeMenuMain = new ArrayList<ModeMenuMain>();
        for(int i = 0; i< arrPhoto.length; i++){
            ModeMenuMain modeMenuMain = new ModeMenuMain();
            if (i<=5){
                modeMenuMain.setName(arrName[i]);
            }
            modeMenuMain.setPhoto(arrPhoto[i]);
            arrayModeMenuMain.add(modeMenuMain);
        }

    }

}
