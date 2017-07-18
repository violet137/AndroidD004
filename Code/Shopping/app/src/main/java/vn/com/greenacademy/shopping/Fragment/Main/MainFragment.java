package vn.com.greenacademy.shopping.Fragment.Main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import vn.com.greenacademy.shopping.Handle.HandleData.MainMenuHandler;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    ListView lv_menu_main;

    private MainMenuHandler mainMenuHandler = null;
    private BaseFragment baseFragment;

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        lv_menu_main = (ListView) view.findViewById(R.id.lv_menu_mani);

        //Xử lý sự kiện
        mainMenuHandler = new MainMenuHandler(getActivity());
        lv_menu_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mainMenuHandler.itemClickMenu(position);
            }
        });
        mainMenuHandler.clickAdvertise();

        //Khởi tạo giá trị
        mainMenuHandler.loadData();
        mainMenuHandler.displayListview(lv_menu_main);
        return view;
    }
}
