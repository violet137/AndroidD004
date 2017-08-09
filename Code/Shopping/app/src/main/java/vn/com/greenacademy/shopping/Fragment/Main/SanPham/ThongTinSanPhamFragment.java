package vn.com.greenacademy.shopping.Fragment.Main.SanPham;


import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Line;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
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
    private TextView tvTenVaMau;
    private TextView tvSoLuong;
    private Button btnSizeInfo;
    private Button btnColor;
    private static ImageView btnShare;
    private static ImageView btnInfo;
    private static LinearLayout topInfo;
    private static LinearLayout bottomInfo;
    private ImageView btnHinh;
    private ViewPager pagerSanPham;
    private QuickActionPopup quickActionPopup;

    private SanPhamHandler sanPhamHandler;
    private SanPhamPagerAdapter sanPhamPagerAdapter;
    private ImageLoad imageLoad;
    private ArrayList<SanPham> listSanPham = new ArrayList<>();
    private SanPham sanPham;
    private int position;
    private String mauDuocChon;

    public ThongTinSanPhamFragment(int position, ArrayList<SanPham> listSanPham) {
        this.listSanPham = listSanPham;
        this.position = position;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoad = new ImageLoad(getActivity());
        sanPhamHandler = new SanPhamHandler(getActivity());
        sanPhamPagerAdapter = new SanPhamPagerAdapter(getActivity().getSupportFragmentManager(),listSanPham);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_thong_tin_san_pham, container, false);
        tvTenVaMau = (TextView) root.findViewById(R.id.ten_va_mau_fragment_san_pham);
        tvSoLuong = (TextView) root.findViewById(R.id.so_luong_fragment_san_pham);
        pagerSanPham = (ViewPager) root.findViewById(R.id.pager_fragment_san_pham);
        btnShare = (ImageView) root.findViewById(R.id.button_share_san_pham);
        btnInfo = (ImageView) root.findViewById(R.id.button_info_san_pham);
        btnSizeInfo = (Button) root.findViewById(R.id.button_size_san_pham);
        btnColor = (Button) root.findViewById(R.id.button_color_san_pham);
        btnHinh = (ImageView) root.findViewById(R.id.button_hinh_san_pham);
        topInfo = (LinearLayout) root.findViewById(R.id.top_info);
        bottomInfo = (LinearLayout) root.findViewById(R.id.bottom_info);

        root.findViewById(R.id.button_san_pham_khac).setOnClickListener(this);
        root.findViewById(R.id.button_them_san_pham).setOnClickListener(this);
        btnInfo.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnSizeInfo.setOnClickListener(this);
        btnColor.setOnClickListener(this);
        btnHinh.setOnClickListener(this);

        sanPham = listSanPham.get(position);
        pagerSanPham.setAdapter(sanPhamPagerAdapter);
        pagerSanPham.setCurrentItem(position);

        //Xử lý thông tin hiển thị
        setUpUi(0);
        pagerSanPham.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                sanPham = listSanPham.get(position);
                setUpUi(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        getActivity().supportInvalidateOptionsMenu();
        return root;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_info_san_pham:
                new DetailsSanPhamFragment(getActivity(), sanPham.getDescription(), sanPham.getChiTietSanPham()).show();
                hideInfo();
                break;
            case R.id.button_share_san_pham:
                Toast.makeText(getActivity(), "Share", Toast.LENGTH_LONG).show();
                break;
            case R.id.button_san_pham_khac:
                Toast.makeText(getActivity(), "Sản phẩm khác", Toast.LENGTH_LONG).show();
                break;
            case R.id.button_hinh_san_pham:
                setUpQuickActionHinh();
                quickActionPopup.show(v);
                break;
            case R.id.button_color_san_pham:
                setUpQuickActionColor();
                quickActionPopup.show(v);
                break;
            case R.id.button_size_san_pham:
                setUpQuickActionSize();
                quickActionPopup.show(v);
                break;
            case R.id.button_them_san_pham:
                Toast.makeText(getActivity(), "Thêm vào giỏ hàng", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void setUpUi(int pos) {
        final SpannableString formatTenVaMau = new SpannableString(sanPham.getTenSanPham() + " - " + SanPhamHandler.chuyenTenMau(sanPham.getHinhSanPham().get(0).getMau()));
        formatTenVaMau.setSpan(new StyleSpan(Typeface.BOLD), 0, sanPham.getTenSanPham().length(), 0);
        tvTenVaMau.setText(formatTenVaMau);
        tvSoLuong.setText(String.valueOf(pos + 1) + "/" + String.valueOf(listSanPham.size()));
        btnColor.setBackgroundResource(sanPhamHandler.doiMaMau(sanPham.getMauSanPham()[0]));
        for (int j = 0; j < sanPham.getHinhSanPham().size(); j++) {
            if (sanPham.getMauSanPham()[0].equals(sanPham.getHinhSanPham().get(j).getMau())) {
                mauDuocChon = sanPham.getMauSanPham()[0];
                imageLoad.load(sanPham.getHinhSanPham().get(j).getLinkHinh()[0], btnHinh);
                break;
            }
        }
        btnSizeInfo.setText(getResources().getString(R.string.chon_size));
    }

    private void setUpQuickActionSize() {
        //create QuickActionPopup. Use QuickActionPopup.VERTICAL or QuickActionPopup.HORIZONTAL //param to define orientation
        quickActionPopup = new QuickActionPopup(getActivity(), QuickActionPopup.HORIZONTAL);

        for (int i = 0; i < sanPham.getSize().length; i++) {
            QuickActionItem mauItem = new QuickActionItem(sanPham.getSize()[i], sanPham.getSize()[i], -1);
            quickActionPopup.addActionItem(mauItem);
        }

        //Set listener for action item clicked
        quickActionPopup.setOnActionItemClickListener(new QuickActionPopup.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickActionPopup source, int pos, String actionId) {
                for (int i = 0; i < sanPham.getSize().length; i++) {
                    if (actionId.equals(sanPham.getSize()[i])) {
                        Toast.makeText(getActivity(), sanPham.getSize()[i], Toast.LENGTH_LONG).show();
                        btnSizeInfo.setText(sanPham.getSize()[i]);
                    }
                }
            }
        });
    }

    private void setUpQuickActionHinh() {
        //create QuickActionPopup. Use QuickActionPopup.VERTICAL or QuickActionPopup.HORIZONTAL //param to define orientation
        quickActionPopup = new QuickActionPopup(getActivity(), QuickActionPopup.HORIZONTAL, imageLoad);

        for (int i = 0; i < sanPham.getHinhSanPham().size(); i++) {
            if (sanPham.getHinhSanPham().get(i).getMau().equals(mauDuocChon)) {
                for (int j = 0; j < sanPham.getHinhSanPham().get(i).getLinkHinh().length; j++) {
                    QuickActionItem mauItem = new QuickActionItem(sanPham.getMauSanPham()[i], null, sanPham.getHinhSanPham().get(i).getLinkHinh()[j]);
                    quickActionPopup.addActionItem(mauItem);
                }
                return;
            }
        }

        //Set listener for action item clicked
        quickActionPopup.setOnActionItemClickListener(new QuickActionPopup.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickActionPopup source, int pos, String actionId) {
                for (int i = 0; i < sanPham.getMauSanPham().length; i++) {
                    if (actionId.equals(sanPham.getMauSanPham()[i])) {
                        Toast.makeText(getActivity(), sanPham.getMauSanPham()[i], Toast.LENGTH_LONG).show();
                        btnColor.setBackgroundResource(sanPhamHandler.doiMaMau(sanPham.getMauSanPham()[i]));
                    }
                }
            }
        });
    }

    public void setUpQuickActionColor() {
        final int ID_MAIL = 2;
        final int ID_SAFARI = 3;

        //create QuickActionPopup. Use QuickActionPopup.VERTICAL or QuickActionPopup.HORIZONTAL //param to define orientation
        quickActionPopup = new QuickActionPopup(getActivity(), QuickActionPopup.HORIZONTAL);

        for (int i = 0; i < sanPham.getMauSanPham().length; i++) {
            QuickActionItem mauItem = new QuickActionItem(sanPham.getMauSanPham()[i], null, sanPhamHandler.doiMaMau(sanPham.getMauSanPham()[i]));
            quickActionPopup.addActionItem(mauItem);
        }

//        QuickActionItem mailItem    = new QuickActionItem(ID_MAIL, null, getResources().getDrawable(R.drawable.red));
//        QuickActionItem vlcItem     = new QuickActionItem(ID_VLC, null, getResources().getDrawable(R.drawable.black));
//        QuickActionItem safariItem  = new QuickActionItem(ID_SAFARI, null, getResources().getDrawable(R.drawable.yellow));


        //add action items into QuickActionPopup
//        quickActionPopup.addActionItem(mailItem);
//        quickActionPopup.addActionItem(vlcItem);
//        quickActionPopup.addActionItem(safariItem);

        //Set listener for action item clicked
        quickActionPopup.setOnActionItemClickListener(new QuickActionPopup.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickActionPopup source, int pos, String actionId) {
                for (int i = 0; i < sanPham.getMauSanPham().length; i++) {
                    if (actionId.equals(sanPham.getMauSanPham()[i])) {
                        btnColor.setBackgroundResource(sanPhamHandler.doiMaMau(sanPham.getMauSanPham()[i]));
                        for (int j = 0; j < sanPham.getHinhSanPham().size(); j++) {
                            if (sanPham.getMauSanPham()[i].equals(sanPham.getHinhSanPham().get(j).getMau())) {
                                mauDuocChon = sanPham.getMauSanPham()[i];
                                imageLoad.load(sanPham.getHinhSanPham().get(j).getLinkHinh()[0], btnHinh);
                                return;
                            }
                        }
                    }
                }
            }
        });
    }

    public void hideInfo() {
        topInfo.setVisibility(View.GONE);
        bottomInfo.setVisibility(View.GONE);
        btnShare.setVisibility(View.GONE);
        btnInfo.setVisibility(View.GONE);
        SanPhamPagerFragment.tvGia.setVisibility(View.GONE);
        sanPhamPagerAdapter.notifyDataSetChanged();
        pagerSanPham.invalidate();
    }

    public static void showInfo() {
        topInfo.setVisibility(View.VISIBLE);
        bottomInfo.setVisibility(View.VISIBLE);
        btnShare.setVisibility(View.VISIBLE);
        btnInfo.setVisibility(View.VISIBLE);
        SanPhamPagerFragment.tvGia.setVisibility(View.VISIBLE);
    }
}
