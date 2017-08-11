package vn.com.greenacademy.shopping.Network;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Handle.HandleUi.ActivityHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Dialog.NetworkDialog;
import vn.com.greenacademy.shopping.Interface.CheckInternetCallback;
import vn.com.greenacademy.shopping.MainActivity;

/**
 * Created by zzzzz on 8/11/2017.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Kiểm tra acitivity có visible không
        if (ActivityHandler.isActivityVisible()) {
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //Lấy thông tin kết nối
            NetworkInfo connInfo = connManager.getActiveNetworkInfo();
            if (connInfo != null &&connInfo.isConnected()) {
                MainActivity.showNetworkDialog(true, null, null);
            } else {
                MainActivity.showNetworkDialog(false, null, null);
            }

//            if (!MainActivity.checkFirstTime) {
//                MainActivity.networkInfo = connInfo;
//                if (connInfo != null && connInfo.isConnected()) {
//                    MainActivity.isConnect = true;
//                    MainActivity.showNetworkDialog(true, null, null);
//                } else {
//                    MainActivity.isConnect = false;
//                    MainActivity.showNetworkDialog(false, null, null);
//                }
//                MainActivity.checkFirstTime = true;
//            } else {
//                if (MainActivity.networkInfo != connInfo) {
//                    MainActivity.networkInfo = connInfo;
//                    if (connInfo != null &&connInfo.isConnected()) {
//                        MainActivity.isConnect = true;
//                        MainActivity.showNetworkDialog(true, null, null);
//                    } else {
//                        MainActivity.isConnect = false;
//                        MainActivity.showNetworkDialog(false, null, null);
//                    }
//                }
//            }

        }
    }
}
