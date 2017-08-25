package vn.com.greenacademy.shopping.Util.Ui;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Fragment.Home.MainFragment;
import vn.com.greenacademy.shopping.Handle.HandleUi.Dialog.NetworkDialog;
import vn.com.greenacademy.shopping.MainActivity;
import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 7/10/2017.
 */

public class BaseFragment extends Fragment {
    private FragmentManager fragmentManager;
    private Context context;
    private Fragment toFragment;
    private String tag;
    private boolean toBackStack;

    public BaseFragment(Context context, FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    public void ChuyenFragment(Fragment toFragment, @Nullable String tag, boolean toBackStack){
        this.toFragment = toFragment;
        this.tag = tag;
        this.toBackStack = toBackStack;

        if (toFragment!=null) {
            //Kiểm tra internet
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                FragmentTransaction transaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.replace_fragment_slide_in_left, 0);
                if (toBackStack)
                    transaction.replace(R.id.content_main, toFragment, tag).addToBackStack(tag).commit();
                else
                    transaction.replace(R.id.content_main, toFragment, tag).commit();

                //Kiểm tra nếu chuyển sang fragment main thì xóa backstack
                if (toFragment instanceof MainFragment && fragmentManager.getBackStackEntryCount() != 0) {
                    for (int i = 1; i < fragmentManager.getBackStackEntryCount(); i++) {
                        XoaFragment();
                    }
                }
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("toFragmentTag", tag);
                bundle.putBoolean("toBackStack", toBackStack);
                MainActivity.showNetworkDialog(false, bundle, toFragment);
            }
        }
    }

    public void XoaFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction().setCustomAnimations(0, R.anim.replace_fragment_slide_out_right);
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //Lấy fragment hiện tại và xóa
        Fragment fragment = fragmentManager.findFragmentById(R.id.content_main);
        if (fragment != null){
            transaction.remove(fragment).commit();
            fragmentManager.popBackStack();
        }
    }

    public void XoaFragmentTruocDo(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();

    }
}
