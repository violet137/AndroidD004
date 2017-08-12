package vn.com.greenacademy.shopping.Handle.HandleData.Support;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Support.SupportFragment;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Support.AdapterSupportLV;
import vn.com.greenacademy.shopping.Handle.HandleUi.Model.Support;
import vn.com.greenacademy.shopping.Model.SlideMenu;
import vn.com.greenacademy.shopping.R;

/**
 * Created by ADMIN on 8/12/2017.
 */

public class SupportHandler extends LoadDataSupportHandler implements AdapterView.OnItemClickListener {
    Activity activity;

    public SupportHandler(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    public void Cick(ListView listView){
        listView.setOnItemClickListener(this);
    }



    public AdapterSupportLV getAdapter(Object object){
        AdapterSupportLV adapterSupportLV = new AdapterSupportLV(activity, R.layout.item_slide_menu,(ArrayList<Support>) object);
        return adapterSupportLV;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(activity, String.valueOf(position), Toast.LENGTH_SHORT).show();
    }
}

class LoadDataSupportHandler {

    int[] arrIcon;
    String[] arrName;
    Activity activity;
    ArrayList<Support> supportArrayList;

    public LoadDataSupportHandler(Activity activity) {
        this.activity = activity;
    }

    // tai du lieu tu file xml cua may vao doi tuong array de dua vao adapter
    public void loadData() {
        arrName = activity.getResources().getStringArray(R.array.name_support_menu);
        TypedArray listAnh = activity.getResources().obtainTypedArray(R.array.icon_support_menu);

        arrIcon = new int[arrName.length];
        for(int i=0; i< arrName.length;i++){
            arrIcon[i]=listAnh.getResourceId(i,-1);
        }

        supportArrayList = new ArrayList<>();
        for(int i = 0; i< arrName.length; i++){
            Support support = new Support();
            support.setName(arrName[i]);
            support.setIcon(arrIcon[i]);
            supportArrayList.add(support);
        }

        SupportFragment.objectCallBack.callBack(supportArrayList,0);
    }

}
