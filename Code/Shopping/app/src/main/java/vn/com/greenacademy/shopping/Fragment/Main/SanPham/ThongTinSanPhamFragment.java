package vn.com.greenacademy.shopping.Fragment.Main.SanPham;


import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Main.ParseNewProductList;
import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham.SanPhamPagerAdapter;
import vn.com.greenacademy.shopping.Handle.HandleUi.Model.QuickActionItem;
import vn.com.greenacademy.shopping.Handle.HandleUi.SanPham.QuickActionPopup;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Network.AsynTask.GetServerData;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongTinSanPhamFragment extends Fragment implements View.OnClickListener, ServerCallBack {
    private TextView tvTenVaMau;
    private TextView tvSoLuong;
    private Button btnSizeInfo;
    private Button btnColor;
    private ImageView btnShare;
    private ImageView btnInfo;
    private LinearLayout topInfo;
    private LinearLayout bottomInfo;
    private ImageView btnHinh;
    private ViewPager pagerSanPham;
    private QuickActionPopup quickActionPopup;

    private SanPhamHandler sanPhamHandler;
    private SanPhamPagerAdapter sanPhamPagerAdapter;
    private ImageLoad imageLoad;
    private ArrayList<SanPham> listSanPham = new ArrayList<>();
    private SanPham sanPham;
    private SpannableString formatTenVaMau;
    private Bundle bundleForPage;
    private int idSanPham;
    private int position;
    private String mauDuocChon;
    private String callFrom = "";
    private boolean isFromBackStack = false;

    public ThongTinSanPhamFragment(String idSanPham, String callFrom) {
        if (idSanPham.equals("ALL"))
            this.idSanPham = -1;
        else
            this.idSanPham = Integer.parseInt(idSanPham);

        this.callFrom = callFrom;
    }

    public ThongTinSanPhamFragment(int position, ArrayList<SanPham> listSanPham) {
        this.listSanPham = listSanPham;
        this.position = position;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!callFrom.isEmpty() && callFrom.equals(SupportKeyList.TAG_FRAGMENT_MAIN)){
            GetServerData getServerData = new GetServerData(this);
            getServerData.execute(ServerUrl.UrlDanhSachSPMoi+"20", String.valueOf(SupportKeyList.NewProduct_Url));
        }
        imageLoad = new ImageLoad(getActivity());
        sanPhamHandler = new SanPhamHandler(getActivity());
        sanPhamPagerAdapter = new SanPhamPagerAdapter(getChildFragmentManager(),listSanPham);
        bundleForPage = new Bundle();
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

        if (callFrom.isEmpty()) {
            sanPham = listSanPham.get(position);
            pagerSanPham.setAdapter(sanPhamPagerAdapter);
            pagerSanPham.setCurrentItem(position);

            //Xử lý thông tin hiển thị
            setUpUi(position);
        }
        pagerSanPham.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int pos) {
                position = pos;
                sanPham = listSanPham.get(pos);
                setUpUi(pos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        getActivity().supportInvalidateOptionsMenu();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFromBackStack) {
            sanPhamPagerAdapter = new SanPhamPagerAdapter(getChildFragmentManager(),listSanPham);
            pagerSanPham.setAdapter(sanPhamPagerAdapter);
            pagerSanPham.setCurrentItem(position);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFromBackStack = true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_info_san_pham:
                //Hiện dialog chi tiết sản phẩm
                DetailsSanPhamDialog detailsSanPhamDialog = new DetailsSanPhamDialog(getActivity(), sanPham.getDescription(), sanPham.getChiTietSanPham());
                detailsSanPhamDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        showInfo();
                    }
                });
                detailsSanPhamDialog.show();
                hideInfo();
                break;
            case R.id.button_share_san_pham:
                Toast.makeText(getActivity(), "Share", Toast.LENGTH_LONG).show();
                break;
            case R.id.button_san_pham_khac:
                SanPhamPhuHopBottomDialog sanPhamPhuHopBottomDialog = new SanPhamPhuHopBottomDialog(getActivity(), listSanPham, new BaseFragment(getActivity(), getActivity().getSupportFragmentManager()));
                sanPhamPhuHopBottomDialog.show();
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
        formatTenVaMau = new SpannableString(sanPham.getTenSanPham() + " - " + SanPhamHandler.chuyenTenMau(sanPham.getHinhSanPham().get(0).getMau()));
        formatTenVaMau.setSpan(new StyleSpan(Typeface.BOLD), 0, sanPham.getTenSanPham().length(), 0);
        tvTenVaMau.setText(formatTenVaMau);
        tvSoLuong.setText(String.valueOf(pos + 1) + "/" + String.valueOf(listSanPham.size()));
        btnColor.setBackgroundResource(sanPhamHandler.doiMaMau(sanPham.getMauSanPham()[0]));
        for (int j = 0; j < sanPham.getHinhSanPham().size(); j++) {
            if (sanPham.getMauSanPham()[0].equals(sanPham.getHinhSanPham().get(j).getMau())) {
                mauDuocChon = sanPham.getMauSanPham()[0];
                imageLoad.load(sanPham.getHinhSanPham().get(0).getLinkHinh()[0], btnHinh);
                break;
            }
        }
        btnSizeInfo.setText(getResources().getString(R.string.chon_size));
    }

    //Tùy chọn size
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
                        btnSizeInfo.setText(sanPham.getSize()[i]);
                    }
                }
            }
        });
    }

    //Tùy chọn hình
    private void setUpQuickActionHinh() {
        //create QuickActionPopup. Use QuickActionPopup.VERTICAL or QuickActionPopup.HORIZONTAL //param to define orientation
        quickActionPopup = new QuickActionPopup(getActivity(), QuickActionPopup.HORIZONTAL, imageLoad);

        for (int i = 0; i < sanPham.getHinhSanPham().size(); i++) {
            if (sanPham.getHinhSanPham().get(i).getMau().equals(mauDuocChon)) {
                for (int j = 0; j < sanPham.getHinhSanPham().get(i).getLinkHinh().length; j++) {
                    QuickActionItem mauItem = new QuickActionItem(sanPham.getHinhSanPham().get(i).getLinkHinh()[j], null, sanPham.getHinhSanPham().get(i).getLinkHinh()[j]);
                    quickActionPopup.addActionItem(mauItem);
                }
                break;
            }
        }

        //Set listener for action item clicked
        quickActionPopup.setOnActionItemClickListener(new QuickActionPopup.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickActionPopup source, int pos, String actionId) {
                for (int i = 0; i < sanPham.getHinhSanPham().size(); i++) {
                    if (sanPham.getHinhSanPham().get(i).getMau().equals(mauDuocChon)) {
                        for (int j = 0; j < sanPham.getHinhSanPham().get(i).getLinkHinh().length; j++) {
                            if(actionId.equals(sanPham.getHinhSanPham().get(i).getLinkHinh()[j])){
                                imageLoad.load(sanPham.getHinhSanPham().get(i).getLinkHinh()[j], btnHinh);
                                //Đổi hình page
                                bundleForPage.putString("HinhSanPham", sanPham.getHinhSanPham().get(i).getLinkHinh()[j]);
                                sanPhamPagerAdapter.updateUiSinglePage(position, SanPhamPagerAdapter.ACTION_DOI_HINH_SAN_PHAM, bundleForPage);
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    //Tùy chọn màu
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
                                formatTenVaMau = new SpannableString(sanPham.getTenSanPham() + " - " + SanPhamHandler.chuyenTenMau(mauDuocChon));
                                formatTenVaMau.setSpan(new StyleSpan(Typeface.BOLD), 0, sanPham.getTenSanPham().length(), 0);
                                tvTenVaMau.setText(formatTenVaMau);

                                //Đổi hình page
                                bundleForPage.putString("HinhSanPham", sanPham.getHinhSanPham().get(j).getLinkHinh()[0]);
                                sanPhamPagerAdapter.updateUiSinglePage(position, SanPhamPagerAdapter.ACTION_DOI_HINH_SAN_PHAM, bundleForPage);
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    // Ẩn các thông tin không cần thiết khi hiện dialog chi tiết sản phẩm
    public void hideInfo() {
        topInfo.setVisibility(View.GONE);
        bottomInfo.setVisibility(View.GONE);
        btnShare.setVisibility(View.GONE);
        btnInfo.setVisibility(View.GONE);
        sanPhamPagerAdapter.updateUiSinglePage(position, SanPhamPagerAdapter.ACTION_HIDE_GIA, null);
    }

    // Hiện lại thông tin khi thoát dialog chi tiết sản phẩm
    public void showInfo() {
        topInfo.setVisibility(View.VISIBLE);
        bottomInfo.setVisibility(View.VISIBLE);
        btnShare.setVisibility(View.VISIBLE);
        btnInfo.setVisibility(View.VISIBLE);
        sanPhamPagerAdapter.updateUiSinglePage(position, SanPhamPagerAdapter.ACTION_SHOW_GIA, null);
    }

    @Override
    public void serverCallBack(String dataServer) {

    }

    @Override
    public void serverCallBack(String dataServer, String key) {
        switch (Integer.parseInt(key)) {
            case SupportKeyList.NewProduct_Url:
                ParseNewProductList parseNewProductList = new ParseNewProductList(dataServer);
                listSanPham = parseNewProductList.parData().getSanPhamArrayList();
                position = findSanPhamPosition(idSanPham, listSanPham);
                sanPham = listSanPham.get(position);
                sanPhamPagerAdapter = new SanPhamPagerAdapter(getChildFragmentManager(),listSanPham);
                pagerSanPham.setAdapter(sanPhamPagerAdapter);
                pagerSanPham.setCurrentItem(position);
                setUpUi(position);
                break;
        }
    }

    private int findSanPhamPosition(int idSanPham, ArrayList<SanPham> listSanPham) {
        if (idSanPham == -1){
            return 11;
        }
        else {
            for (int i = 0; i < listSanPham.size(); i++) {
                if (idSanPham == listSanPham.get(i).getIdSanPham())
                    return i;
            }
        }
        return -1;
    }
}
