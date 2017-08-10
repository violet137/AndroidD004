package vn.com.greenacademy.shopping.Handle.HandleUi.Dialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by zzzzz on 7/12/2017.
 */

public class LoadingDialog extends ProgressDialog {
    private BaseFragment baseFragment;

    public LoadingDialog(Context context) {
        super(context);
    }
    public LoadingDialog(Context context, int theme, BaseFragment baseFragment) {
        super(context, theme);
        this.baseFragment = baseFragment;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setMessage(getContext().getString(R.string.dialog_loading));
        setCancelable(false);
        setInverseBackgroundForced(false);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void show() {
        super.show();
        setContentView(R.layout.loader);
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        this.getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels;
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.getWindow().setDimAmount(0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
        baseFragment.XoaFragment();
    }
}
