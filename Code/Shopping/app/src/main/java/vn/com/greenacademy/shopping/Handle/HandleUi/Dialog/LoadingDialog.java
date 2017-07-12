package vn.com.greenacademy.shopping.Handle.HandleUi.Dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 7/12/2017.
 */

public class LoadingDialog extends ProgressDialog {
    public LoadingDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setMessage(getContext().getString(R.string.dialog_loading));
        setCancelable(false);
        setInverseBackgroundForced(false);
        super.onCreate(savedInstanceState);
    }
}
