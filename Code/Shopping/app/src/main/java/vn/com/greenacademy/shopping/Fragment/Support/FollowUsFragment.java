package vn.com.greenacademy.shopping.Fragment.Support;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import vn.com.greenacademy.shopping.Handle.HandleData.Support.SupportHandler;
import vn.com.greenacademy.shopping.Interface.ObjectCallBack;
import vn.com.greenacademy.shopping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowUsFragment extends Fragment {

    public static ObjectCallBack objectCallBack;
    ListView lvSocialNetWork;
    SupportHandler supportHandler;

    public FollowUsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportHandler = new SupportHandler(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_follow_us, container, false);

        lvSocialNetWork = (ListView) view.findViewById(R.id.lvSocialWorks_FollowUsFragment);

        supportHandler.ClickFollowUS(lvSocialNetWork);

        objectCallBack = new ObjectCallBack() {
            @Override
            public void callBack(Object object, int flag) {
                lvSocialNetWork.setAdapter(supportHandler.getAdapter(object));
            }
        };
        supportHandler.loadDataSocialNetWork();

        return view;
    }

}
