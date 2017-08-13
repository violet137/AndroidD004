package vn.com.greenacademy.shopping.Fragment.Main.DanhMucSanPham;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Fragment.Main.SanPham.LocSanPhamDialog;
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
public class ChiTietDanhMucFragment extends Fragment implements DataCallBack, View.OnClickListener {
    private View root;
    private TextView tvTitle;
    private TextView tvSoLuong;
    private TextView tvMessage;
    private RecyclerView vListSanPham;

    private String idDanhMuc;
    private String tenDanhMuc;
    private ArrayList<SanPham> mListSanPham = new ArrayList<>();
    private String[] mListMauSanPham;
    private String[] mListSizeSanPham;
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

        root.findViewById(R.id.button_loc_san_pham).setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_loc_san_pham:
                layMauVaSizeListSanPham();

        }
    }

    private void layMauVaSizeListSanPham() {
        String[] mauSanPham = {"Cam", "Den", "Do", "Hong", "Nau", "Reu", "Tim", "Vang", "Xam", "XanhDuong", "XanhLa", "Trang"};
        String[] sizeSanPham = {""};
        int sttListMau, sttSanPham = 0, sttMauListSanPham, lengthListMauSanPham = 0;
        SanPham sanPham;
        mListMauSanPham = new String[mauSanPham.length];
        //Điều kiện dừng khi vượt quá số lượng màu của server hoặc quá số lượng sản phẩm của danh mục
        while(lengthListMauSanPham < mauSanPham.length && sttSanPham < mListSanPham.size()){
            sanPham = mListSanPham.get(sttSanPham);
            //Chạy từng sản phẩm để lấy màu
            for (sttMauListSanPham = 0; sttMauListSanPham < sanPham.getMauSanPham().length; sttMauListSanPham++) {
                //Nếu là sản phẩm đầu tiên thì thêm trực tiếp vào list màu không cần xét
                if (sttSanPham == 0){
                    mListMauSanPham = sanPham.getMauSanPham();
                    lengthListMauSanPham = sanPham.getMauSanPham().length;
                }
                //Từ sau sản phẩm đầu tiên quét so sanh từng màu của 1 sản phẩm với màu trong list màu
                else {
                    for (sttListMau = 0; sttListMau < mListMauSanPham.length; sttListMau++) {
                        //Nếu màu sản phẩm tại vị trí sttMauListSanPham không giống với toàn bộ màu của list màu thì thêm vào list màu
                        if (!sanPham.getMauSanPham()[sttMauListSanPham].equals(mListMauSanPham[sttListMau])){
                            mListMauSanPham[lengthListMauSanPham] = sanPham.getMauSanPham()[sttMauListSanPham];
                            lengthListMauSanPham++;
                        }
                    }
                }
            }
            sttSanPham++;
        }
        LocSanPhamDialog locSanPhamDialog = new LocSanPhamDialog(getActivity(), android.R.style.Theme_DeviceDefault_Light, mListSizeSanPham, mListMauSanPham, mListSanPham.size());
        locSanPhamDialog.show();
    }
}
