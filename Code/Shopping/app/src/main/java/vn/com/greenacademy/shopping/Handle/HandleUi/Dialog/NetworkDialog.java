package vn.com.greenacademy.shopping.Handle.HandleUi.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 8/11/2017.
 */

public class NetworkDialog extends Dialog {
    private View root;
    private TextView vMessage;
    private Button btnXacNhan;

    private String message;
    private View.OnClickListener onClickListener;

    public NetworkDialog(@NonNull Context context, String message, View.OnClickListener onClickListener) {
        super(context);
        this.onClickListener = onClickListener;
        this.message = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        root = inflater.inflate(R.layout.network_dialog, null);
        vMessage = (TextView) root.findViewById(R.id.message_dialog);
        btnXacNhan = (Button) root.findViewById(R.id.btn_xac_nhan_message_dialog);

        if (onClickListener != null)
            btnXacNhan.setOnClickListener(onClickListener);

        vMessage.setText(message);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

    }

    @Override
    public void show() {
        super.show();
        setContentView(root);
        this.setCanceledOnTouchOutside(false);
        this.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.getWindow().setDimAmount(0);
    }
}
