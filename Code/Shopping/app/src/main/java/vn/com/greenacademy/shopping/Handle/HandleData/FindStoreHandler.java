package vn.com.greenacademy.shopping.Handle.HandleData;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.FindStore.AdapterFindStore;
import vn.com.greenacademy.shopping.Interface.StoreCallBack;
import vn.com.greenacademy.shopping.Model.Store;
import vn.com.greenacademy.shopping.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by ADMIN on 7/28/2017.
 */

public class FindStoreHandler implements StoreCallBack, OnMapReadyCallback {

    private GoogleMap mMap;
    Activity activity;
    ListView listView;

    //// [Filter Start] ////



    //// [Filter end] ////



    //// [listView Dia Chi start] ////
    public void setListView(ListView listView){
        this.listView = listView;
    }
    //// [listView Dia Chi end] ////


    //// [ Google map start ] ///////
    public FindStoreHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void storeCallBack(ArrayList<Store> storeArrayList) {
        // load data len listview
        AdapterFindStore adapterFindStore = new AdapterFindStore(activity, R.layout.item_find_store, storeArrayList);
        listView.setAdapter(adapterFindStore);

        // show map marker
        showMarker(storeArrayList);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").snippet("ha hu ca lu mu mu"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        Boolean mLocation = false;
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            mLocation = true;
        }else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},200);
        }

        if (mLocation){
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        }else {
            mMap.setMyLocationEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }


//        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(final Marker marker) {
//                MyDiaLogFragment myDiaLogFragment = new MyDiaLogFragment();
//                myDiaLogFragment.show(getSupportFragmentManager(),"123" );
//                MapMarkerTranfers mapMarkerTranfers = new MapMarkerTranfers();
//                mapMarkerTranfers.setTen(marker.getTitle());
//                mapMarkerTranfers.setMoTa(marker.getSnippet());
//                mapMarkerTranfers.setLat(marker.getPosition().latitude);
//                mapMarkerTranfers.setLng(marker.getPosition().longitude);
//                View.OnClickListener onClickListener = new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        chiduong(vitrihientai,marker.getPosition());
//                    }
//                };
//                myDiaLogFragment.setData(mapMarkerTranfers, onClickListener);
//                return false;
//            }
//        });
    }

    private void showMarker(ArrayList<Store> storeArrayList) {
        mMap.clear();
        for (int i = 0; i < storeArrayList.size(); i++) {
            LatLng latLng = new LatLng(storeArrayList.get(i).getLat(), storeArrayList.get(i).getLng());
            mMap.addMarker(new MarkerOptions().position(latLng).title(storeArrayList.get(i).getTenCuaHang()));

        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(storeArrayList.get(0).getLat(), storeArrayList.get(0).getLng()),15));
    }


    ///// [ Google map end ] ///////////
}
