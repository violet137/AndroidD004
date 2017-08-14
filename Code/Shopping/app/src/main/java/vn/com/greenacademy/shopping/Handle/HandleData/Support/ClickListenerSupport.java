package vn.com.greenacademy.shopping.Handle.HandleData.Support;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ADMIN on 8/14/2017.
 */

public class ClickListenerSupport {
    public static View.OnClickListener onClickListener;

    public void Click (final Activity activity){
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                Toast.makeText(activity, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        };
    }
}
