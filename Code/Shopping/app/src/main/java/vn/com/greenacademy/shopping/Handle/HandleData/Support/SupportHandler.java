package vn.com.greenacademy.shopping.Handle.HandleData.Support;

import android.app.Activity;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Magazine.MagazineFragment;
import vn.com.greenacademy.shopping.Fragment.Support.CacCauHoiFragment;
import vn.com.greenacademy.shopping.Fragment.Support.SupportFragment;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Support.ParseCauHoiTG;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Support.AdapterCacCauHoiTGLV;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.Support.AdapterSupportLV;
import vn.com.greenacademy.shopping.Handle.HandleUi.Model.Support;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Model.CauHoiTG;
import vn.com.greenacademy.shopping.Model.SlideMenu;
import vn.com.greenacademy.shopping.Network.AsynTask.GetServerData;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by ADMIN on 8/12/2017.
 */

public class SupportHandler extends LoadDataSupportHandler{
    Activity activity;

    public SupportHandler(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    public void Cick(ListView listView){
        final BaseFragment baseFragment = new BaseFragment(activity, ((AppCompatActivity)activity).getSupportFragmentManager());
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case SupportKeyList.ClickSupport_LienHe:
                        Toast.makeText(activity, "Liên Hệ", Toast.LENGTH_SHORT).show();
                        break;
                    case SupportKeyList.ClickSupport_CauHoi:
                        baseFragment.ChuyenFragment(new CacCauHoiFragment(), SupportKeyList.TAG_FRAGMENT_CAUHOI, true);
                        break;
                    case SupportKeyList.ClickSupport_TheoDoi:
                        Toast.makeText(activity, "Theo Dõi", Toast.LENGTH_SHORT).show();
                        break;
                    case SupportKeyList.ClickSupport_GioiThieu:
                        Toast.makeText(activity, "Gới Thiệu", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        listView.setOnItemClickListener(onItemClickListener);
    }



    public AdapterSupportLV getAdapter(Object object){
        AdapterSupportLV adapterSupportLV = new AdapterSupportLV(activity, R.layout.item_slide_menu,(ArrayList<Support>) object);
        return adapterSupportLV;
    }

    public ListAdapter getAdapterCacCauHoi(Object object) {
        AdapterCacCauHoiTGLV adapter = new AdapterCacCauHoiTGLV(activity, R.layout.item_support_cac_cau_hoi_tg,(ArrayList<CauHoiTG>) object);
        return adapter;
    }

    public void
    ClickCauHoi(ListView lvCauHoi) {
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(activity, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        };
        lvCauHoi.setOnItemClickListener(onItemClickListener);
    }
}

class LoadDataSupportHandler implements ServerCallBack {

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

    public void loadDataCacCauHoiTG() {
        GetServerData getServerData = new GetServerData(this);
        getServerData.execute(ServerUrl.UrlCauHoiTG);
    }

    @Override
    public void serverCallBack(String dataServer) {
        ParseCauHoiTG parseCauHoiTG = new ParseCauHoiTG(dataServer);
        containerData(parseCauHoiTG.parData());
    }

    private void containerData(ArrayList<CauHoiTG> cauHoiTGs) {
        CacCauHoiFragment.objectCallBack.callBack(cauHoiTGs,0);
    }

    @Override
    public void serverCallBack(String dataServer, String key) {

    }
}
