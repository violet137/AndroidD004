package vn.com.greenacademy.shopping.Fragment.Main.DanhMucSanPham;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham.ListSanPhamAdapter;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Network.AsynTask.DataServerAsyncTask;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChiTietDanhMucFragment extends Fragment implements DataCallBack {
    private View root;
    private TextView tvTitle;
    private TextView tvSoLuong;
    private TextView tvMessage;
    private RecyclerView vListSanPham;

    private String idDanhMuc;
    private String tenDanhMuc;
    private ArrayList<SanPham> mListSanPham = new ArrayList<>();
    private boolean isFromBackStack = false;

    public ChiTietDanhMucFragment() {
    }

    public static ChiTietDanhMucFragment newInstance(int idDanhMuc, String tenDanhMuc) {

        Bundle args = new Bundle();
        args.putString("idDanhMuc", String.valueOf(idDanhMuc));
        args.putString("tenDanhMuc", tenDanhMuc);
        ChiTietDanhMucFragment fragment = new ChiTietDanhMucFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            idDanhMuc = getArguments().getString("idDanhMuc");
            tenDanhMuc = getArguments().getString("tenDanhMuc");
            DataServerAsyncTask dataServerAsyncTask = new DataServerAsyncTask(this);
            dataServerAsyncTask.execute(SupportKeyList.API_DATA_SAN_PHAM_CHI_TIET_DANH_MUC, ServerUrl.UrlSanPhamTheoDanhMuc + idDanhMuc, "GET");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_chi_tiet_danh_muc, container, false);
        tvTitle = (TextView) root.findViewById(R.id.text_loai_nganh_thoi_trang);
        tvSoLuong = (TextView) root.findViewById(R.id.text_so_luong_nganh_thoi_trang);
        vListSanPham = (RecyclerView) root.findViewById(R.id.list_san_pham_nganh_thoi_trang);
        //        ListView vListPhanLoai = (ListView) root.findViewById(R.id.list_phan_loai);

        getActivity().supportInvalidateOptionsMenu();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFromBackStack){
            loadUi(mListSanPham);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFromBackStack = true;
    }

    public void loadUi(ArrayList<SanPham> mListSanPham){
        //Set ui
        if (mListSanPham.size() == 0){
            root.findViewById(R.id.message_chi_tiet_danh_muc).setVisibility(View.VISIBLE);
            root.findViewById(R.id.btns_phan_loai_san_pham).setVisibility(View.GONE);

        } else {
            vListSanPham.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            vListSanPham.setAdapter(new ListSanPhamAdapter(getActivity(), mListSanPham, new BaseFragment(getActivity(), getActivity().getSupportFragmentManager()), null, new ImageLoad(getActivity())));
            vListSanPham.setNestedScrollingEnabled(false);
        }
        tvTitle.setText(tenDanhMuc);
        tvSoLuong.setText(String.valueOf(mListSanPham.size()));
    }

    @Override
    public void KetQua(String result, @Nullable Bundle bundle) {
        switch (result){
            case SupportKeyList.LOI_DATA_SERVER:
                Toast.makeText(getActivity(), getString(R.string.toast_loi_data_server), Toast.LENGTH_LONG).show();
                break;
            case SupportKeyList.LOI_DATA:
                Toast.makeText(getActivity(), getString(R.string.toast_loi_data), Toast.LENGTH_LONG).show();
                break;
            case SupportKeyList.LAY_DATA_THANH_CONG:
                if (bundle != null){
                    mListSanPham = (ArrayList<SanPham>) bundle.getSerializable(SupportKeyList.API_DATA_SAN_PHAM_CHI_TIET_DANH_MUC);
                    loadUi(mListSanPham);
                }
                break;
        }
    }
}
