package vn.com.greenacademy.shopping.Fragment.Main.SanPham;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsSanPhamFragment extends Dialog implements View.OnClickListener {
    private String description;
    private String details;

    public DetailsSanPhamFragment(Context context, String description, String details) {
        super(context);
        this.description = description;
        this.details = details;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = getLayoutInflater().inflate(R.layout.dialog_details_san_pham, null);
        TextView tvDescription = (TextView) root.findViewById(R.id.description_text_chi_tiet_san_pham);
        TextView tvChiTiet = (TextView) root.findViewById(R.id.details_text_chi_tiet_san_pham);

        root.findViewById(R.id.thoat_button_chi_tiet_san_pham).setOnClickListener(this);

//        tvDescription.setText(description);
//        tvChiTiet.setText(details);
        configDialog(root);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.thoat_button_chi_tiet_san_pham)
            dismiss();
    }

    private void configDialog(View root) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(root);
        this.getWindow().setBackgroundDrawableResource(R.drawable.dialog_scrim);
        this.getWindow().setLayout((int) (this.getContext().getResources().getDimension(R.dimen.dialog_chi_tiet_san_pham_width) / this.getContext().getResources().getDisplayMetrics().density), (int) (getContext().getResources().getDimension(R.dimen.dialog_chi_tiet_san_pham_height) / this.getContext().getResources().getDisplayMetrics().density));
    }
}
