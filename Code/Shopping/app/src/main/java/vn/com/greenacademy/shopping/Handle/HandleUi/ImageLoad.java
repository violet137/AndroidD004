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

    public void load(String url, ImageView imageView) {

//        Log.d("load hinh","load hinh:"+url);
        Picasso.with(activity).load(url).fit().centerCrop().into(imageView);
    }
}
