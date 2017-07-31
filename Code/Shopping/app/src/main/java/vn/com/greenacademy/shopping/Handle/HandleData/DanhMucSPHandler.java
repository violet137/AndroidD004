package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.AdapterDanhMucSP;
import vn.com.greenacademy.shopping.Interface.DanhMucSPCallBack;
import vn.com.greenacademy.shopping.Model.DanhMucSP;
import vn.com.greenacademy.shopping.Model.MucSanPham;
import vn.com.greenacademy.shopping.Network.AsynTask.GetDanhMucSP;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;

/**
 * Created by ADMIN on 7/31/2017.
 */

public class DanhMucSPHandler implements DanhMucSPCallBack, View.OnClickListener{
    ListView listView;
    Activity activity;

    public DanhMucSPHandler(Activity activity) {
        this.activity = activity;
    }

    public void setListView (ListView listView, String loaiSP){
        this.listView = listView;

        GetDanhMucSP getDanhMucSP = new GetDanhMucSP(this);
        getDanhMucSP.execute(ServerUrl.UrlDanhMucSP+loaiSP);
    }

    @Override
    public void danhMucCallBack(DanhMucSP danhMucSP) {
        ArrayList<MucSanPham> arrayList = new ArrayList<>();

        MucSanPham mucSanPham1 = new MucSanPham();
        mucSanPham1.setLinkAnh(danhMucSP.getXuHuongTtrangLink());
        mucSanPham1.setId(danhMucSP.getXuHuongTtrangId());
        mucSanPham1.setLoaiThoiTrang(danhMucSP.getLoaiThoiTrang());
        arrayList.add(mucSanPham1);

        for (int i = 0; i < danhMucSP.getMucSanPhamArrayList().size(); i++) {
            arrayList.add(danhMucSP.getMucSanPhamArrayList().get(i));
        }
        AdapterDanhMucSP adapterDanhMucSP = new AdapterDanhMucSP(activity, R.layout.item_danh_muc_san_pham, arrayList,this);
        listView.setAdapter(adapterDanhMucSP);
    }

    @Override
    public void onClick(View v) {
        MucSanPham mucSanPham = (MucSanPham) v.getTag();
        Toast.makeText(activity, String.valueOf(mucSanPham.getId()), Toast.LENGTH_SHORT).show();
    }
}
