package vn.com.greenacademy.shopping.Fragment.Main.XuHuongThoiTrang;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham.ListSanPhamAdapter;
import vn.com.greenacademy.shopping.Interface.SanPhamCallBack;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by zzzzz on 7/21/2017.
 */

public class ChiTietSetDoDialog extends BottomSheetDialog implements SanPhamCallBack, View.OnClickListener {
    private Context context;
    private BottomSheetBehavior bottomSheetBehavior;
    private ArrayList<SanPham> mListSanPham;
    private BaseFragment baseFragment;

    public ChiTietSetDoDialog(Context context, ArrayList<SanPham> mListSanPham, BaseFragment baseFragment) {
        super(context);
        this.context = context;
        this.mListSanPham = mListSanPham;
        this.baseFragment = baseFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.dialog_chi_tiet_set_do, null);
        ImageButton ibThoat = (ImageButton) bottomSheetView.findViewById(R.id.thoat_button_chi_tiet_set_do);
        RecyclerView vListSanPham = (RecyclerView) bottomSheetView.findViewById(R.id.list_san_pham_chi_tiet_set_do);

        ibThoat.setOnClickListener(this);

        vListSanPham.setLayoutManager(new GridLayoutManager(context, mListSanPham.size() < 3 ? 2 : 3));
        vListSanPham.setAdapter(new ListSanPhamAdapter(context, true, mListSanPham, baseFragment, this, new ImageLoad((Activity) context)));
        vListSanPham.setNestedScrollingEnabled(false);

        configBottomSheetBehavior(bottomSheetView);
    }

    @Override
    public void clickSanPham(int position) {
        dismiss();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.thoat_button_chi_tiet_set_do)
            dismiss();
    }

    private void configBottomSheetBehavior(View bottomSheetView) {
        setContentView(bottomSheetView);
        this.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        bottomSheetBehavior = BottomSheetBehavior.from((View)bottomSheetView.getParent());
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheet.requestLayout();
                    bottomSheet.invalidate();
                }
                if (newState == BottomSheetBehavior.STATE_DRAGGING)
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {}
        });
    }

}
