package vn.com.greenacademy.shopping.Util.Ui;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import vn.com.greenacademy.shopping.MainActivity;
import vn.com.greenacademy.shopping.R;

/**
 * Created by zzzzz on 7/10/2017.
 */

public class BaseFragment extends Fragment {
    private FragmentManager fragmentManager;

    public BaseFragment(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    public void ChuyenFragment(Fragment toFragment, @Nullable String tag, boolean toBackStack){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(toBackStack)
            transaction.replace(R.id.content_main, toFragment, tag).addToBackStack(tag).commit();
        else
            transaction.replace(R.id.content_main, toFragment, tag).commit();
    }

    public void XoaFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //Lấy fragment hiện tại và xóa
        Fragment fragment = fragmentManager.findFragmentById(R.id.content_main);
        if (fragment != null){
            fragmentManager.popBackStack();
            transaction.remove(fragment).commit();
        }
    }

}
