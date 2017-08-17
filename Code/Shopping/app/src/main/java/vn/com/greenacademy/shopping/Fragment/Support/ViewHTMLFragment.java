package vn.com.greenacademy.shopping.Fragment.Support;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import vn.com.greenacademy.shopping.Handle.HandleData.Magazine.MagazineDetailHandle;
import vn.com.greenacademy.shopping.Handle.HandleData.Support.WebViewHTMLHandler;
import vn.com.greenacademy.shopping.Model.Magazine.MagazineDetail;
import vn.com.greenacademy.shopping.R;

import static android.R.attr.id;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewHTMLFragment extends Fragment {

    String url;
    String ten;

    public ViewHTMLFragment(String url, String ten) {
        // Required empty public constructor
        this.url = url;
        this.ten = ten;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_html, container, false);

        WebView myWebView = (WebView) view.findViewById(R.id.wvHTML_ViewHTML_Fragment);

        WebViewHTMLHandler viewHTMLFragment = new WebViewHTMLHandler(getActivity());

        viewHTMLFragment.setLayout(myWebView);

        // muon model MagazineDetail dùng tạm
        MagazineDetail magazineDetail = new MagazineDetail();
        magazineDetail.setTen(ten);
        magazineDetail.setNoiDung(url);

        viewHTMLFragment.containerData(magazineDetail);

        return view;
    }

}
