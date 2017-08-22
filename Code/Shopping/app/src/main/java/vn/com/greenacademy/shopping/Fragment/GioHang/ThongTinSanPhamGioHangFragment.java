package vn.com.greenacademy.shopping.Fragment.GioHang;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
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

import vn.com.greenacademy.shopping.Fragment.Main.SanPham.DetailsSanPhamDialog;
import vn.com.greenacademy.shopping.Fragment.Main.SanPham.SanPhamPhuHopBottomDialog;
import vn.com.greenacademy.shopping.Handle.HandleData.GioHang.GioHangHandler;
import vn.com.greenacademy.shopping.Handle.HandleData.ParseData.Product.ParseNewProductList;
import vn.com.greenacademy.shopping.Handle.HandleData.SanPhamHandler;
import vn.com.greenacademy.shopping.Handle.HandleUi.Adapter.SanPham.SanPhamPagerAdapter;
import vn.com.greenacademy.shopping.Handle.HandleUi.ImageLoad;
import vn.com.greenacademy.shopping.Handle.HandleUi.Model.QuickActionItem;
import vn.com.greenacademy.shopping.Handle.HandleUi.SanPham.QuickActionPopup;
import vn.com.greenacademy.shopping.Interface.DataCallBack;
import vn.com.greenacademy.shopping.Interface.ServerCallBack;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPham;
import vn.com.greenacademy.shopping.Model.ThongTinSanPham.SanPhamGioHang;
import vn.com.greenacademy.shopping.Network.AsynTask.DataServerAsyncTask;
import vn.com.greenacademy.shopping.Network.AsynTask.GetServerData;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.ServerUrl;
import vn.com.greenacademy.shopping.Util.SupportKeyList;
import vn.com.greenacademy.shopping.Util.Ui.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongTinSanPhamGioHangFragment extends Fragment implements View.OnClickListener, DataCallBack {
    private View root;
    private TextView tvTenVaMau;
    private TextView tvSoLuong;
    private Button btnSizeInfo;
    private Button btnColor;
    private Button btnSoLuong;
    private ImageView btnXoa;
    private ImageView btnInfo;
    private ImageView btnHinh;
    private ViewPager pagerSanPham;
    private QuickActionPopup quickActionPopup;
    private ProgressDialog progressDialog;

    private SanPhamHandler sanPhamHandler;
    private GioHangHandler gioHangHandler;
    private SanPhamPagerAdapter sanPhamPagerAdapter;
    private ImageLoad imageLoad;
    private SpannableString formatTenVaMau;
    private Bundle bundleForPage;
    private ArrayList<SanPham> listSanPham = new ArrayList<>();
    private SanPham sanPham;
    private SanPhamGioHang sanPhamGioHang;
    private int idSanPham;
    private int position;
    private String mauDuocChon;
    private String sizeDuocChon = null;
    private boolean isFromBackStack = false;
    private boolean themGioHang = false;

    public static ThongTinSanPhamGioHangFragment newInstance(SanPhamGioHang sanPhamGioHang) {

        Bundle args = new Bundle();
        args.putSerializable("sanPhamGioHang", sanPhamGioHang);

        ThongTinSanPhamGioHangFragment fragment = new ThongTinSanPhamGioHangFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoad = new ImageLoad(getActivity());
        gioHangHandler = new GioHangHandler(getActivity(), this);
        sanPhamHandler = new SanPhamHandler(getActivity());
        sanPhamPagerAdapter = new SanPhamPagerAdapter(getChildFragmentManager(),listSanPham);
        bundleForPage = new Bundle();
        progressDialog = new ProgressDialog(getActivity());
        position = 0;
        sanPhamGioHang = (SanPhamGioHang) getArguments().getSerializable("sanPhamGioHang");
        DataServerAsyncTask serverAsyncTask = new DataServerAsyncTask(this);
        serverAsyncTask.execute(SupportKeyList.API_GET_SAN_PHAM, ServerUrl.UrlSanPhamTheoId + String.valueOf(sanPhamGioHang.getIdSanPham()), "GET");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_thong_tin_san_pham_gio_hang, container, false);
        tvTenVaMau = (TextView) root.findViewById(R.id.ten_va_mau_fragment_san_pham);
        tvSoLuong = (TextView) root.findViewById(R.id.so_luong_fragment_san_pham);
        pagerSanPham = (ViewPager) root.findViewById(R.id.pager_fragment_san_pham);
        btnXoa = (ImageView) root.findViewById(R.id.button_xoa_san_pham);
        btnInfo = (ImageView) root.findViewById(R.id.button_info_san_pham);
        btnSizeInfo = (Button) root.findViewById(R.id.button_size_san_pham_gio_hang);
        btnColor = (Button) root.findViewById(R.id.button_color_san_pham);
        btnHinh = (ImageView) root.findViewById(R.id.button_hinh_san_pham);
        btnSoLuong = (Button) root.findViewById(R.id.button_so_luong_san_pham_gio_hang);

        root.findViewById(R.id.button_xoa_san_pham).setOnClickListener(this);
        btnSoLuong.setOnClickListener(this);
        btnInfo.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
        btnSizeInfo.setOnClickListener(this);
        btnColor.setOnClickListener(this);
        btnHinh.setOnClickListener(this);

        pagerSanPham.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int pos) {
                position = pos;
                themGioHang = false;
                sizeDuocChon = null;
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
            setUpUi(position);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFromBackStack = true;
        position = pagerSanPham.getCurrentItem();
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
            case R.id.button_xoa_san_pham:
                gioHangHandler.XoaSanPhamGioHang(sanPhamGioHang.getIdSanPham());
                break;
            case R.id.button_hinh_san_pham:
                setUpQuickActionHinh();
                quickActionPopup.show(v);
                break;
            case R.id.button_color_san_pham:
                setUpQuickActionColor();
                quickActionPopup.show(v);
                break;
            case R.id.button_size_san_pham_gio_hang:
                setUpQuickActionSize();
                quickActionPopup.show(v);
                break;
            case R.id.button_so_luong_san_pham_gio_hang:
                setUpQuickActionSoLuong();
                quickActionPopup.show(v);
                break;
        }
    }

    private void setUpUi(int pos) {
        formatTenVaMau = new SpannableString(sanPham.getTenSanPham() + " - " + SanPhamHandler.chuyenTenMau(sanPham.getHinhSanPham().get(0).getMau()));
        formatTenVaMau.setSpan(new StyleSpan(Typeface.BOLD), 0, sanPham.getTenSanPham().length(), 0);
        tvTenVaMau.setText(formatTenVaMau);
        tvSoLuong.setText(String.valueOf(pos + 1) + "/" + String.valueOf(listSanPham.size()));
        btnColor.setBackgroundResource(sanPhamHandler.doiMaMau(sanPhamGioHang.getMauSanPham()));
        imageLoad.load(sanPhamGioHang.getLinkHinh(), btnHinh);
        btnSizeInfo.setText(String.valueOf(sanPhamGioHang.getSize()));
        pagerSanPham.setAdapter(sanPhamPagerAdapter);
        mauDuocChon = sanPhamGioHang.getMauSanPham();

        //Đổi hình page
        bundleForPage.putString("HinhSanPham", sanPhamGioHang.getLinkHinh());
        sanPhamPagerAdapter.updateUiSinglePage(position, SanPhamPagerAdapter.ACTION_DOI_HINH_SAN_PHAM, bundleForPage);
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
                        sizeDuocChon = sanPham.getSize()[i];
                        btnSizeInfo.setText(sanPham.getSize()[i]);
                        if (themGioHang){
                            progressDialog.show();
                            gioHangHandler.themSanPhamGioHang(sanPham.getIdSanPham(), 1, sizeDuocChon, mauDuocChon);
                        }
                        themGioHang = false;

                        //Cập nhật thay đổi lên server
                        gioHangHandler.UpdateSanPhamGioHang(sanPhamGioHang.getIdSanPham(), 0, sizeDuocChon, null);
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

        //create QuickActionPopup. Use QuickActionPopup.VERTICAL or QuickActionPopup.HORIZONTAL //param to define orientation
        quickActionPopup = new QuickActionPopup(getActivity(), QuickActionPopup.HORIZONTAL);

        for (int i = 0; i < sanPham.getMauSanPham().length; i++) {
            QuickActionItem mauItem = new QuickActionItem(sanPham.getMauSanPham()[i], null, sanPhamHandler.doiMaMau(sanPham.getMauSanPham()[i]));
            quickActionPopup.addActionItem(mauItem);
        }

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

                                //Cập nhật thay đổi lên server
                                gioHangHandler.UpdateSanPhamGioHang(sanPhamGioHang.getIdSanPham(), 0 , null, mauDuocChon);
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    //Tùy chọn số lượng
    private void setUpQuickActionSoLuong() {
        //create QuickActionPopup. Use QuickActionPopup.VERTICAL or QuickActionPopup.HORIZONTAL //param to define orientation
        quickActionPopup = new QuickActionPopup(getActivity(), QuickActionPopup.HORIZONTAL);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View root = inflater.inflate(R.layout.quick_pop_up_so_luong_san_pham, null);
        final TextView tvSoLuong = (TextView) root.findViewById(R.id.text_so_luong);

        root.findViewById(R.id.btn_tru_so_luong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(tvSoLuong.getText().toString()) == 2) {
                    tvSoLuong.setText(String.valueOf(Integer.parseInt(tvSoLuong.getText().toString()) - 1));
                    root.findViewById(R.id.btn_tru_so_luong).setVisibility(View.INVISIBLE);
                }
                else
                    tvSoLuong.setText(String.valueOf(Integer.parseInt(tvSoLuong.getText().toString()) - 1));
                btnSoLuong.setText(tvSoLuong.getText());
                gioHangHandler.UpdateSanPhamGioHang(sanPhamGioHang.getIdSanPham(), Integer.parseInt(btnSoLuong.getText().toString()), null, null);

                root.findViewById(R.id.btn_tru_so_luong).setClickable(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        root.findViewById(R.id.btn_tru_so_luong).setClickable(true);
                    }
                }, 1000);
            }
        });

        root.findViewById(R.id.btn_cong_so_luong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(tvSoLuong.getText().toString()) == 1) {
                    tvSoLuong.setText(String.valueOf(Integer.parseInt(tvSoLuong.getText().toString())+1));
                    root.findViewById(R.id.btn_tru_so_luong).setVisibility(View.VISIBLE);
                }
                else
                    tvSoLuong.setText(String.valueOf(Integer.parseInt(tvSoLuong.getText().toString())+1));
                btnSoLuong.setText(tvSoLuong.getText());
                gioHangHandler.UpdateSanPhamGioHang(sanPhamGioHang.getIdSanPham(), Integer.parseInt(btnSoLuong.getText().toString()), null, null);

                root.findViewById(R.id.btn_cong_so_luong).setClickable(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        root.findViewById(R.id.btn_cong_so_luong).setClickable(true);
                    }
                }, 1000);
            }
        });

        if (Integer.parseInt(tvSoLuong.getText().toString()) == 1)
            root.findViewById(R.id.btn_tru_so_luong).setVisibility(View.INVISIBLE);

        quickActionPopup.setRootViewId(root);
//        quickActionPopup.setContentView(root);
    }

    // Ẩn các thông tin không cần thiết khi hiện dialog chi tiết sản phẩm
    public void hideInfo() {
        root.findViewById(R.id.top_info).setVisibility(View.GONE);
        root.findViewById(R.id.bottom_info).setVisibility(View.GONE);
        btnXoa.setVisibility(View.GONE);
        btnInfo.setVisibility(View.GONE);
        sanPhamPagerAdapter.updateUiSinglePage(position, SanPhamPagerAdapter.ACTION_HIDE_GIA, null);
    }

    // Hiện lại thông tin khi thoát dialog chi tiết sản phẩm
    public void showInfo() {
        root.findViewById(R.id.top_info).setVisibility(View.VISIBLE);
        root.findViewById(R.id.bottom_info).setVisibility(View.VISIBLE);
        btnXoa.setVisibility(View.VISIBLE);
        btnInfo.setVisibility(View.VISIBLE);
        sanPhamPagerAdapter.updateUiSinglePage(position, SanPhamPagerAdapter.ACTION_SHOW_GIA, null);
    }

    @Override
    public void KetQua(String result, @Nullable Bundle bundle) {
        switch (result){
            case SupportKeyList.LOI_DATA_SERVER:
                Toast.makeText(getActivity(), getString(R.string.toast_loi_data_server), Toast.LENGTH_SHORT).show();
                break;
            case SupportKeyList.LOI_DATA:
                Toast.makeText(getActivity(), getString(R.string.toast_loi_data), Toast.LENGTH_SHORT).show();
                break;
            case SupportKeyList.API_GET_SAN_PHAM:
                sanPham = (SanPham) bundle.getSerializable("sanPham");
                listSanPham.add(sanPham);
                setUpUi(position);
                break;
            case SupportKeyList.API_XOA_GIO_HANG:
                Toast.makeText(getActivity(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                BaseFragment baseFragment = new BaseFragment(getActivity(), getActivity().getSupportFragmentManager());
                baseFragment.XoaFragment();
                break;
            case SupportKeyList.CAP_NHAT_THANH_CONG:
                Toast.makeText(getActivity(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                break;
            case SupportKeyList.CAP_NHAT_THAT_BAI:
                Toast.makeText(getActivity(), "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                break;
        }
        progressDialog.dismiss();
    }
}
