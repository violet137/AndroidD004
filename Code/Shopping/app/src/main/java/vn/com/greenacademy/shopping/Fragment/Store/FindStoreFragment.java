package vn.com.greenacademy.shopping.Fragment.Store;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;

import vn.com.greenacademy.shopping.Handle.HandleData.FindStoreHandler;
import vn.com.greenacademy.shopping.Interface.FindStoreListenerCallBack;
import vn.com.greenacademy.shopping.Network.AsynTask.GetStore;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindStoreFragment extends Fragment {


    FindStoreListenerCallBack findStoreListenerCallBack;

    public FindStoreFragment(FindStoreListenerCallBack findStoreListenerCallBack ) {
        this.findStoreListenerCallBack = findStoreListenerCallBack;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_find_store, container, false);

        final ListView listView = (ListView) view.findViewById(R.id.lvStore_find_store);

        findStoreListenerCallBack.listViewCallBack(listView);

        FindStoreHandler findStoreHandler = new FindStoreHandler(getActivity());

        findStoreHandler.setListView(listView);


        findStoreHandler.getMyLocation();

        GetStore getStore = new GetStore(findStoreHandler);
        getStore.execute(ServerUrl.UrlDanhSachStore);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map_find_store);

        mapFragment.getMapAsync(findStoreHandler);

        //reset option menu
        getActivity().supportInvalidateOptionsMenu();

        return view;
    }





//    ArrayList<Store> storeArrayList = new ArrayList<>();
//
//    @Override
//    public Store getMapMarker(MapMarker mapMarker) {
//        listall = mapMarker.getMapMarkerTranfersArrayList();
//        for (int i = 0; i < listall.size(); i++) {
//            switch (mapMarker.getMapMarkerTranfersArrayList().get(i).getLoaiMarker()){
//                case "DiaDiem":
//                    listDiaDiem.add(mapMarker.getMapMarkerTranfersArrayList().get(i));
//                    break;
//                case "QuanAn":
//                    listQuanAn.add(mapMarker.getMapMarkerTranfersArrayList().get(i));
//                    break;
//            }
//        }
//        return null;
//    }
}
