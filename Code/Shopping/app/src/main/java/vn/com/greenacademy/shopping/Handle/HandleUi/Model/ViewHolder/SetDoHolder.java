package vn.com.greenacademy.shopping.Handle.HandleUi.Model.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 7/15/2017.
 */

public class SetDoHolder extends RecyclerView.ViewHolder {
    public TextView tvDescription;
    public ImageView imgSetDo;

    public SetDoHolder(View itemView) {
        super(itemView);

        tvDescription = (TextView) itemView.findViewById(R.id.description_textview_item_set_do);
        imgSetDo = (ImageView) itemView.findViewById(R.id.set_do_image_item_set_do);
    }
}
