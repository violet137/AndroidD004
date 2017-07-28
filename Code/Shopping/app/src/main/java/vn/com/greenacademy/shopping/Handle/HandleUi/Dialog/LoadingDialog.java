package vn.com.greenacademy.shopping.Handle.HandleUi.Dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

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
    public LoadingDialog(Context context, BaseFragment baseFragment) {
        super(context);
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
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
        baseFragment.XoaFragment();
    }
}
