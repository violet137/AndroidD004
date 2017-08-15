package vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 8/13/2017.
 */

public class LocSizeHolder extends RecyclerView.ViewHolder {
    public Button btnSize;

    public LocSizeHolder(View itemView) {
        super(itemView);
        btnSize = (Button) itemView.findViewById(R.id.button_item_size_loc_san_pham);
    }
}
