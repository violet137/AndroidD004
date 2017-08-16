package vn.com.greenacademy.shopping.Handle.HandleData.Support;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Handle.HandleData.Magazine.MagazineDetailHandle;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Magazine.ParseMagazineDetail;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.MainActivity;
import vn.com.greenacademy.shopping.Model.Magazine.MagazineDetail;
import vn.com.greenacademy.shopping.Network.AsynTask.GetServerData;
import vn.com.greenacademy.shopping.Util.ServerUrl;

/**
 * Created by ADMIN on 8/16/2017.
 */

public class WebViewHTMLHandler {
    Activity activity;
    WebView myWebView;

    public WebViewHTMLHandler(Activity activity) {
        this.activity = activity;
    }

    public void setLayout(WebView myWebView){
        this.myWebView = myWebView;
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(new MagazineDetailHandle.WebAppInterface(activity), "android");

    }

    public void containerData(Object object) {
        MagazineDetail magazineDetail = (MagazineDetail) object;
        MainActivity.tvTenMuc.setVisibility(View.VISIBLE);
        MainActivity.tvTenMuc.setText(magazineDetail.getTen());
        viewWebInApp(magazineDetail.getNoiDung());
    }

    public static class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        public WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }

    public void viewWebInApp(String url){
        // cach 1 laod view tren app
        // url la dia chi web
        myWebView.loadUrl(url);
    }

    public void viewWebInWeb(String url){
        // url la dia chi web
        //cach 2 load view tren web
        Uri uri = Uri.parse(url);
        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
        activity.startActivity(intent2);
    }
}
