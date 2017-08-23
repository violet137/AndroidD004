package vn.com.greenacademy.shopping.Handle.HandleUi;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * Created by ADMIN on 7/16/2017.
 */

public class ImageLoad {

    Activity activity;

    public ImageLoad(Activity activity) {
        this.activity = activity;
    }

    // centerCrop cho imageView
    public void load(String url, ImageView imageView) {
//        Log.d("load hinh","load hinh:"+url);
        Picasso.with(activity).load(url).fit().centerCrop().into(imageView);
    }

    // fix XY cho imageView
    public void loadFixXY(String url, ImageView imageView) {
        Picasso.with(activity).load(url).fit().into(imageView);
    }

    // centerInside cho imageView
    public void loadCenterInside(String url, ImageView imageView) {
        Picasso.with(activity).load(url).fit().centerInside().into(imageView);
    }
}
