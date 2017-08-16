package vn.com.greenacademy.shopping.Handle.HandleData;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.FindStore.AdapterFindStore;
import vn.com.greenacademy.shopping.Handle.HandleUi.Dialog.DiaLogStoreDetail;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Store.ParseStore;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Model.Store;

import static com.facebook.FacebookSdk.getApplicationContext;
import vn.com.greenacademy.shopping.R;
/**
 * Created by ADMIN on 7/28/2017.
 */

public class FindStoreHandler implements OnMapReadyCallback, ServerCallBack {

    private GoogleMap mMap;
    Activity activity;
    ListView listView;
    ArrayList<Store> storeArrayList;
    Location location;

    public FindStoreHandler(Activity activity) {
        this.activity = activity;
    }

    //// [dialog store detail Start] ////
    public void showDialog(int position){
        DiaLogStoreDetail diaLogStoreDetail = new DiaLogStoreDetail(storeArrayList.get(position), location.getLatitude(), location.getLongitude());
        diaLogStoreDetail.show(((AppCompatActivity)activity).getSupportFragmentManager(), "dialogStoreDetail" );
    }
    //// [dialog store detail end] ////



    //// [listView Dia Chi start] ////
    public void setListView(ListView listView){
        this.listView = listView;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               showDialog(position);
            }
        });

    }
    //// [listView Dia Chi end] ////


    //// [ Google map start ] ///////

    public void getMyLocation(){
        LocationManager mlocationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            location = mlocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }else {
            Toast.makeText(getApplicationContext(), "ko the lay vi tri" , Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

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

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                showDialog(Integer.parseInt(marker.getId().substring(1)));
                return false;
            }
        });
    }

    private void showMarker(ArrayList<Store> storeArrayList) {
        mMap.clear();
        for (int i = 0; i < storeArrayList.size(); i++) {
            LatLng latLng = new LatLng(storeArrayList.get(i).getLat(), storeArrayList.get(i).getLng());
            mMap.addMarker(new MarkerOptions().position(latLng).title(storeArrayList.get(i).getTenCuaHang()));

        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(storeArrayList.get(0).getLat(), storeArrayList.get(0).getLng()),15));
    }

    @Override
    public void serverCallBack(String dataServer) {
        ParseStore parseStore = new ParseStore(dataServer);
        containerData(parseStore.parData());
    }

    private void containerData(ArrayList<Store> storeArrayList) {
        this.storeArrayList = storeArrayList;

        // load data len listview
        AdapterFindStore adapterFindStore = new AdapterFindStore(activity, R.layout.item_find_store, storeArrayList,
                location.getLatitude(), location.getLongitude());
        listView.setAdapter(adapterFindStore);

        // show map marker
        showMarker(storeArrayList);
    }

    @Override
    public void serverCallBack(String dataServer, String key) {

    }


    ///// [ Google map end ] ///////////
}
