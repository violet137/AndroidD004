package vn.com.greenacademy.shopping.Handle.HandleData.Home;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import vn.com.greenacademy.shopping.Fragment.Magazine.MagazineFragment;
import vn.com.greenacademy.shopping.Model.Home.ItemHome;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * Created by ADMIN on 8/8/2017.
 */

public class ClickListenerHomeItem {
    public static View.OnClickListener onClickListener;

    public static void Click(final Activity activity){
        final BaseFragment baseFragment = new BaseFragment(((AppCompatActivity)activity).getSupportFragmentManager());
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemHome itemHome = (ItemHome) v.getTag();
                switch (itemHome.getFlagItemHome()){

                    case SupportKeyList.ClickHome_Advertise:
                        Toast.makeText(activity, itemHome.getId(), Toast.LENGTH_SHORT).show();
                        break;

                    case SupportKeyList.ClickHome_Products:
                        Toast.makeText(activity, itemHome.getId(), Toast.LENGTH_SHORT).show();
                        break;

                    case SupportKeyList.ClickHome_NewProduct:
                        Toast.makeText(activity, itemHome.getId(), Toast.LENGTH_SHORT).show();
                        break;

                    case SupportKeyList.ClickHome_Fashion:
                        Toast.makeText(activity, itemHome.getId(), Toast.LENGTH_SHORT).show();
                        break;

                    case SupportKeyList.ClickHome_Magazine:
                        Toast.makeText(activity, itemHome.getId(), Toast.LENGTH_SHORT).show();
                        break;

                    case SupportKeyList.ClickHome_Button_Magazine:
                        baseFragment.ChuyenFragment(
                                new MagazineFragment(), SupportKeyList.TAG_FRAGMENT_MAGAZINE, true);
                        break;

                    default:
                        break;
                }
            }
        };
    }
}
