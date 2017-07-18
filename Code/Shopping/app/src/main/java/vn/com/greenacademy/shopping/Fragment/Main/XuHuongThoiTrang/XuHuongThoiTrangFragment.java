package vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham.ListSanPhamAdapter;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.XuHuongThoiTrang.ListSetDoAdapter;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Model.SanPham;
import vn.com.greenacademy.shopping.Model.SetDo;
import vn.com.greenacademy.shopping.Model.XuHuongThoiTrang;
import vn.com.greenacademy.shopping.Network.AsynTask.GoiAPIServerAsyncTask;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class XuHuongThoiTrangFragment extends Fragment implements DataCallBack {
    private XuHuongThoiTrang xuHuongThoiTrang;
    private int position;

    //Data test
    private int[] listDataBanner;

    public XuHuongThoiTrangFragment(String tenXuHuong){
        new GoiAPIServerAsyncTask(this).execute(SupportKeyList.API_DATA_XU_HUONG_THOI_TRANG, ServerUrl.DataUrl, tenXuHuong);
    }

    public XuHuongThoiTrangFragment(int pos) {
       position = pos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_xu_huong_thoi_trang, container, false);
        ImageView vBanner = (ImageView) root.findViewById(R.id.head_image_fragment_xu_huong_thoi_trang);
        RecyclerView vListSetDo = (RecyclerView) root.findViewById(R.id.list_set_do_fragment_xu_huong_thoi_trang);
        RecyclerView vListSanPham = (RecyclerView) root.findViewById(R.id.list_san_pham_fragment_xu_huong_thoi_trang);

        DataTest();

        vBanner.setImageResource(listDataBanner[position]);
        if (xuHuongThoiTrang.getListSetDo() != null) {
            vListSetDo.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            vListSetDo.setNestedScrollingEnabled(false);
            vListSetDo.setAdapter(new ListSetDoAdapter(getActivity(), xuHuongThoiTrang.getListSetDo()));
        }
        vListSanPham.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        vListSanPham.setNestedScrollingEnabled(false);
        vListSanPham.setAdapter(new ListSanPhamAdapter(getActivity(), xuHuongThoiTrang.getListSanPham(), new BaseFragment(getActivity().getSupportFragmentManager())));
        return root;
    }

    public void DataTest(){
        ArrayList<SetDo> listSetDo = new ArrayList<>();
        ArrayList<SanPham> listSanPhamSetDo = new ArrayList<>();
        ArrayList<SanPham> listSanPham = new ArrayList<>();
        //List set đồ
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                SanPham sanPham = new SanPham("Sản phẩm " + String.valueOf(j+1), "Men", "Description " + String.valueOf(j+1), "Details " + String.valueOf(j+1), null, null, 20.99, null, 0);
                listSanPhamSetDo.add(sanPham);
            }
            listSetDo.add(new SetDo("Set đồ thứ " + String.valueOf(i), "Description " + String.valueOf(i), null, listSanPhamSetDo));
        }

        //List Sản phẩm
        for (int i = 0; i < 7; i++) {
            SanPham sanPham = new SanPham("Sản phẩm " + String.valueOf(i+1), "Men", "Description " + String.valueOf(i+1), "Details " + String.valueOf(i+1), null, null, 20.99 + i, null, 0);
            listSanPham.add(sanPham);
        }
        xuHuongThoiTrang = new XuHuongThoiTrang("testTenXuHuong", "Men", null, listSetDo, listSanPham);

        //Data banner
        TypedArray tempListBanner = getActivity().getResources().obtainTypedArray(R.array.arr_hinh);
        listDataBanner = new int[3];
        for (int i = 0; i < 3; i++) {
            listDataBanner[i] = tempListBanner.getResourceId(i, -1);
        }
    }

    @Override
    public void KetQua(String result, Bundle bundle) {
        switch (result){
            case SupportKeyList.LOI_DATA_SERVER:
                Toast.makeText(getActivity(), getString(R.string.toast_loi_data_server), Toast.LENGTH_LONG).show();
                break;
            case SupportKeyList.LOI_DATA:
                Toast.makeText(getActivity(), getString(R.string.toast_loi_data), Toast.LENGTH_LONG).show();
                break;
            case SupportKeyList.LAY_DATA_THANH_CONG:
                xuHuongThoiTrang = (XuHuongThoiTrang) bundle.getSerializable("DataXuHuongThoiTrang");
                break;
            default:
                break;
        }
    }

}
