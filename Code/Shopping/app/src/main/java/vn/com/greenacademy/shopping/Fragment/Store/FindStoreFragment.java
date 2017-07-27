package vn.com.greenacademy.shopping.Fragment.Store;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Interface.StoreCallBack;
import vn.com.greenacademy.shopping.Model.Store;
import vn.com.greenacademy.shopping.Network.AsynTask.GetStore;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;

import static android.content.Context.LOCATION_SERVICE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindStoreFragment extends Fragment implements StoreCallBack, OnMapReadyCallback {

    private GoogleMap mMap;

    LocationManager mlocationManager;
    Location location;

    public FindStoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_find_store, container, false);

        GetStore getStore = new GetStore(this);
        getStore.execute(ServerUrl.UrlDanhSachStore);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map_find_store);
        mapFragment.getMapAsync(this);

        return view;
    }


    @Override
    public void storeCallBack(ArrayList<Store> storeArrayList) {
        showMarker(storeArrayList);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").snippet("ha hu ca lu mu mu"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        Boolean mLocation = false;
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            mLocation = true;
        }else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},200);
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
