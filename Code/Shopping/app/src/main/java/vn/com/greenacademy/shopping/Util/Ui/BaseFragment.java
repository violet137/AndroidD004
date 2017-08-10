package vn.com.greenacademy.shopping.Util.Ui;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import vn.com.greenacademy.shopping.Fragment.SplashScreenFragment;
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
        FragmentTransaction transaction;
        if (toFragment instanceof SplashScreenFragment)
            transaction = fragmentManager.beginTransaction();
        else
            transaction = fragmentManager.beginTransaction().setCustomAnimations(R.anim.replace_fragment_slide_in_left, 0);

        if(toBackStack)
            transaction.replace(R.id.content_main, toFragment, tag).addToBackStack(tag).commit();
        else
            transaction.replace(R.id.content_main, toFragment, tag).commit();
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

}
