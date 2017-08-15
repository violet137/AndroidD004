package vn.com.greenacademy.shopping.Fragment.Main.SanPham;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.DanhMucSanPhamAdapter.LocMauSanPhamAdapter;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.DanhMucSanPhamAdapter.LocSizeSanPhamAdapter;
import vn.com.greenacademy.shopping.Interface.ClickListenerSizeVaMau;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.R;


/**
 * Created by zzzzz on 8/13/2017.
 */

public class LocSanPhamDialog extends Dialog implements View.OnClickListener, ClickListenerSizeVaMau {
    private View root;
    private TextView tvSoLuong;
    private RecyclerView vListMau;

    private Context context;
    private SanPhamHandler sanPhamHandler;
    private LocMauSanPhamAdapter locMauSanPhamAdapter;
    private LocSizeSanPhamAdapter locSizeSanPhamAdapter;
    private ArrayList<SanPham> mListSanPham = new ArrayList<>();
    private ArrayList<String> mListSize = new ArrayList<>();
    private ArrayList<String> mListMau = new ArrayList<>();
    private int soLuongSanPham;
    private Bundle bundleTheoSize;

    public LocSanPhamDialog(Context context, int theme, ArrayList<SanPham> listSanPham, ArrayList<String> listSize, ArrayList<String> listMau, int soLuongSanPham){
        super(context, theme);
        this.context = context;
        sanPhamHandler = new SanPhamHandler((Activity)context);
        mListSanPham = listSanPham;
        mListSize = listSize;
        mListMau = listMau;
        this.soLuongSanPham = soLuongSanPham;
        locSizeSanPhamAdapter = new LocSizeSanPhamAdapter(context, mListSize, this);
        locMauSanPhamAdapter = new LocMauSanPhamAdapter(context, mListMau, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(context);
        root = inflater.inflate(R.layout.dialog_loc_san_pham, null);
        tvSoLuong = (TextView) root.findViewById(R.id.text_so_luong_loc_san_pham);
        RecyclerView vListSize = (RecyclerView) root.findViewById(R.id.recycler_view_size_loc_san_pham);
        vListMau = (RecyclerView) root.findViewById(R.id.recycler_view_mau_loc_san_pham);

        root.findViewById(R.id.btn_clear_all_dialog_loc_san_pham).setOnClickListener(this);
        root.findViewById(R.id.btn_loc_dialog_loc_san_pham).setOnClickListener(this);

        tvSoLuong.setText(String.valueOf(soLuongSanPham) + " sản phẩm");
        vListSize.setLayoutManager(new GridLayoutManager(context, 5));
        vListSize.setAdapter(locSizeSanPhamAdapter);
        vListMau.setLayoutManager(new GridLayoutManager(context, 2));
        vListMau.setAdapter(locMauSanPhamAdapter);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void show() {
        super.show();
        setContentView(root);
        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_clear_all_dialog_loc_san_pham:
                break;
            case R.id.btn_loc_dialog_loc_san_pham:
                break;
        }
    }

    @Override
    public void onClickSizeVaMau(String loai, String item) {
        switch (loai){
            case "size":
                bundleTheoSize = sanPhamHandler.locMauTheoSize(item, mListSanPham);
                ArrayList<String> listMauTheoSize = (ArrayList<String>)bundleTheoSize.getSerializable("ListMau");
                tvSoLuong.setText(bundleTheoSize.getString("SoLuongSanPham") + " sản phẩm");
                locMauSanPhamAdapter.setData(listMauTheoSize);
                break;
            case "mau":
                Toast.makeText(context, SanPhamHandler.chuyenTenMau(item), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
