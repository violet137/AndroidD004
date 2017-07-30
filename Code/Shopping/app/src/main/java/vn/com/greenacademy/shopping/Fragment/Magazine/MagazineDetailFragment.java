package vn.com.greenacademy.shopping.Fragment.Magazine;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import vn.com.greenacademy.shopping.Handle.HandleData.Magazine.MagazineDetailHandle;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MagazineDetailFragment extends Fragment {

    String id;
    public MagazineDetailFragment(String id) {
        // Required empty public constructor
        this.id = id;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_magazine_detail, container, false);

        WebView myWebView = (WebView) view.findViewById(R.id.webView_magazine_detail_fragment);

        MagazineDetailHandle magazineDetailHandle = new MagazineDetailHandle(getActivity());

        magazineDetailHandle.setLayout(myWebView);

        magazineDetailHandle.getData(id);


        return view;
    }

}
