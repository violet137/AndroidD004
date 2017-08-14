package vn.com.greenacademy.shopping.Fragment.Main.SanPham;

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

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.DanhMucSanPhamAdapter.LocMauSanPhamAdapter;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.DanhMucSanPhamAdapter.LocSizeSanPhamAdapter;
import vn.com.greenacademy.shopping.R;


/**
 * Created by zzzzz on 8/13/2017.
 */

public class LocSanPhamDialog extends Dialog {
    private View root;

    private Context context;
    private ArrayList<String> mListSize = new ArrayList<>();
    private ArrayList<String> mListMau = new ArrayList<>();
    private int soLuongSanPham;

    public LocSanPhamDialog(Context context, int theme, ArrayList<String> listSize, ArrayList<String> listMau, int soLuongSanPham){
        super(context, theme);
        this.context = context;
        mListSize = listSize;
        mListMau = listMau;
        this.soLuongSanPham = soLuongSanPham;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(context);
        root = inflater.inflate(R.layout.dialog_loc_san_pham, null);
        TextView tvSoLuong = (TextView) root.findViewById(R.id.text_so_luong_loc_san_pham);
        RecyclerView vListSize = (RecyclerView) root.findViewById(R.id.recycler_view_size_loc_san_pham);
        RecyclerView vListMau = (RecyclerView) root.findViewById(R.id.recycler_view_mau_loc_san_pham);

        tvSoLuong.setText(String.valueOf(soLuongSanPham) + " sản phẩm");

        vListSize.setLayoutManager(new GridLayoutManager(context, 5));
        vListSize.setAdapter(new LocSizeSanPhamAdapter(context, mListSize));
        vListMau.setLayoutManager(new GridLayoutManager(context, 2));
        vListMau.setAdapter(new LocMauSanPhamAdapter(context, mListMau));

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void show() {
        super.show();
        setContentView(root);
        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
