package vn.com.greenacademy.shopping.Handle.HandleData.Magazine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Interface.MagazineDetailCallBack;
import vn.com.greenacademy.shopping.Model.MagazineDetail;
import vn.com.greenacademy.shopping.Network.AsynTask.GetMagazineDetail;
import vn.com.greenacademy.shopping.Util.ServerUrl;

/**
 * Created by ADMIN on 7/30/2017.
 */

public class MagazineDetailHandle implements MagazineDetailCallBack{
    Activity activity;
    WebView myWebView;
    TextView textView;

    public MagazineDetailHandle(Activity activity) {
        this.activity = activity;
    }

    public void getData(String id){
        GetMagazineDetail getMagazineDetail = new GetMagazineDetail(this,textView);
        getMagazineDetail.execute(ServerUrl.UrlMagazineDetail+id);
    }

    public void setLayout(WebView myWebView, TextView textView){
        this.myWebView = myWebView;
        this.textView = textView;
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(new WebAppInterface(activity), "android");

    }
    
    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
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

    @Override
    public void magazineDetailCallBack(MagazineDetail magazineDetail, TextView textView) {
        textView.setVisibility(View.VISIBLE);
        textView.setText(magazineDetail.getTen());
        viewWebInApp(magazineDetail.getNoiDung());
    }
}
