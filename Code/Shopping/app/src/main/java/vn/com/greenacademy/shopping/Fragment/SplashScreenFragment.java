package vn.com.greenacademy.shopping.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;
import vn.com.greenacademy.shopping.Fragment.Main.MainFragment;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashScreenFragment extends Fragment {
    private ActionBar actionBar;
    private BaseFragment baseFragment;

    private static final int DELAY_TIME = 3000;

    public SplashScreenFragment(ActionBar actionBar) {
        this.actionBar = actionBar;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        actionBar.hide();
        View root = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        baseFragment = new BaseFragment(getActivity().getSupportFragmentManager());
        //Load màn hình splash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                actionBar.show();
                baseFragment.ChuyenFragment(new MainFragment(), SupportKeyList.TAG_FRAGMENT_MAIN, false);
            }
        }, DELAY_TIME);
        return root;
    }

}
