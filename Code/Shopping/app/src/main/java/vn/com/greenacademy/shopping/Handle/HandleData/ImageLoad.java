package vn.com.greenacademy.shopping.Handle.HandleData;

import android.app.Activity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 7/16/2017.
 */

public class ImageLoad {

    Activity activity;

    public ImageLoad(Activity activity) {
        this.activity = activity;
    }

    public void ImageLoad(String url, ImageView imageView) {
        Picasso.with(activity).load(url).into(imageView);
    }
}
