package vn.com.greenacademy.shopping.Fragment.Main.SanPham;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham.SanPhamPagerAdapter;
import vn.com.greenacademy.shopping.Handle.HandleUi.Model.QuickActionItem;
import vn.com.greenacademy.shopping.Handle.HandleUi.SanPham.QuickActionPopup;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongTinSanPhamFragment extends Fragment implements View.OnClickListener {
    Button btnSizeInfo;
    private QuickActionPopup quickActionPopup;

    private BaseFragment baseFragment;
    private ArrayList<SanPham> listSanPham = new ArrayList<>();
    private int position;

    public ThongTinSanPhamFragment(int position, ArrayList<SanPham> listSanPham) {
        this.listSanPham = listSanPham;
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_thong_tin_san_pham, container, false);
        final TextView tvTenVaMau = (TextView) root.findViewById(R.id.ten_va_mau_fragment_san_pham);
        final TextView tvSoLuong = (TextView) root.findViewById(R.id.so_luong_fragment_san_pham);
        ViewPager pagerSanPham = (ViewPager) root.findViewById(R.id.pager_fragment_san_pham);
        btnSizeInfo = (Button) root.findViewById(R.id.button_size_san_pham);

        root.findViewById(R.id.button_info_san_pham).setOnClickListener(this);
        root.findViewById(R.id.button_share_san_pham).setOnClickListener(this);
        root.findViewById(R.id.button_san_pham_khac).setOnClickListener(this);
        root.findViewById(R.id.button_hinh_san_pham).setOnClickListener(this);
        root.findViewById(R.id.button_color_san_pham).setOnClickListener(this);
        root.findViewById(R.id.button_size_san_pham).setOnClickListener(this);
        root.findViewById(R.id.button_them_san_pham).setOnClickListener(this);
        btnSizeInfo.setOnClickListener(this);

        baseFragment = new BaseFragment(getActivity().getSupportFragmentManager());
        pagerSanPham.setAdapter(new SanPhamPagerAdapter(getActivity().getSupportFragmentManager(),listSanPham));
        pagerSanPham.setCurrentItem(position);

        //Xử lý thông tin hiển thị
        final SpannableString formatTenVaMau = new SpannableString(listSanPham.get(position).getTenSanPham() + " - " + SanPhamHandler.chuyenTenMau(listSanPham.get(position).getHinhSanPham().get(0).getMau()));
        formatTenVaMau.setSpan(new StyleSpan(Typeface.BOLD), 0, listSanPham.get(position).getTenSanPham().length(), 0);
        pagerSanPham.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvTenVaMau.setText(formatTenVaMau);
                tvSoLuong.setText(String.valueOf(position + 1) + "/" + String.valueOf(listSanPham.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tvTenVaMau.setText(formatTenVaMau);
        tvSoLuong.setText(String.valueOf(position + 1) + "/" + String.valueOf(listSanPham.size()));

        getActivity().supportInvalidateOptionsMenu();
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_info_san_pham:
                new DetailsSanPhamFragment(getActivity(), listSanPham.get(position).getDescription(), listSanPham.get(position).getChiTietSanPham()).show();
                break;
            case R.id.button_share_san_pham:
                Toast.makeText(getActivity(), "Share", Toast.LENGTH_LONG).show();
                break;
            case R.id.button_san_pham_khac:
                Toast.makeText(getActivity(), "Sản phẩm khác", Toast.LENGTH_LONG).show();
                break;
            case R.id.button_hinh_san_pham:
                setUpQuickAction();
                quickActionPopup.show(v);
                break;
            case R.id.button_color_san_pham:
                setUpQuickAction();
                quickActionPopup.show(v);
                break;
            case R.id.button_size_san_pham:
                setUpQuickAction();
                quickActionPopup.show(v);
                break;
            case R.id.button_them_san_pham:
                Toast.makeText(getActivity(), "Thêm vào giỏ hàng", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void setUpQuickAction(){
        final int ID_VLC    = 1;
        final int ID_MAIL   = 2;
        final int ID_SAFARI = 3;

        QuickActionItem mailItem    = new QuickActionItem(ID_MAIL, null, getResources().getDrawable(R.drawable.red));
        QuickActionItem vlcItem     = new QuickActionItem(ID_VLC, null, getResources().getDrawable(R.drawable.black));
        QuickActionItem safariItem  = new QuickActionItem(ID_SAFARI, null, getResources().getDrawable(R.drawable.yellow));
        //create QuickActionPopup. Use QuickActionPopup.VERTICAL or QuickActionPopup.HORIZONTAL //param to define orientation
        quickActionPopup = new QuickActionPopup(getActivity(), QuickActionPopup.HORIZONTAL);

        //add action items into QuickActionPopup
        quickActionPopup.addActionItem(mailItem);
        quickActionPopup.addActionItem(vlcItem);
        quickActionPopup.addActionItem(safariItem);

        //Set listener for action item clicked
        quickActionPopup.setOnActionItemClickListener(new QuickActionPopup.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickActionPopup source, int pos, int actionId) {

                //filtering items by id
                if (actionId == ID_MAIL) {
                    Toast.makeText(getApplicationContext(), "Đỏ", Toast.LENGTH_SHORT).show();
                } else if (actionId == ID_VLC) {
                    Toast.makeText(getApplicationContext(), "Đen", Toast.LENGTH_SHORT).show();
                } else if(actionId == ID_SAFARI){
                    Toast.makeText(getApplicationContext(), "Vàng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
    }
}
