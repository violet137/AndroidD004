package vn.com.greenacademy.shopping.Fragment.Support;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import vn.com.greenacademy.shopping.Handle.HandleData.Support.SupportHandler;
import vn.com.greenacademy.shopping.Interface.ObjectCallBack;
import vn.com.greenacademy.shopping.Model.Support.LoaiHoTro;
import vn.com.greenacademy.shopping.Model.Support.Mail;
import vn.com.greenacademy.shopping.R;
import vn.com.greenacademy.shopping.Util.SupportKeyList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoiMailFragment extends Fragment implements View.OnClickListener {
    public static ObjectCallBack objectServerCallBack;

    boolean listenerBack = false;

    public static ObjectCallBack objectCallBack;

    Spinner snLoai;
    EditText etNoiDung;
    EditText etTen;
    EditText etEmail;
    Button btnGoi;

    SupportHandler supportHandler;

    String loaiHoTro[];
    ArrayList<LoaiHoTro> loaiHoTroArrayList;

    public GoiMailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportHandler = new SupportHandler(getActivity());
        supportHandler.loadDataLoaiVanDe();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goi_mail, container, false);

        snLoai = (Spinner) view.findViewById(R.id.snLoaiVD_GoiMail_Fragment);
        etNoiDung = (EditText) view.findViewById(R.id.etNoiDung_GoiMail_Fragment);
        etTen = (EditText) view.findViewById(R.id.etTen_GoiMail_Fragment);
        etEmail = (EditText) view.findViewById(R.id.etEmail_GoiMail_Fragment);
        btnGoi = (Button) view.findViewById(R.id.btnGui_GoiMail_Fragment);

        objectCallBack = new ObjectCallBack() {
            @Override
            public void callBack(Object object, int flag) {
                loaiHoTroArrayList = (ArrayList<LoaiHoTro>) object;

                loaiHoTro = new String[loaiHoTroArrayList.size()];

                for (int i = 0; i < loaiHoTroArrayList.size(); i++) {
                    loaiHoTro[i] = loaiHoTroArrayList.get(i).getTenHoTro();
                }

                ArrayAdapter<String> adapterWork = new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_list_item_1,loaiHoTro);

                snLoai.setAdapter(adapterWork);

            }
        };

        objectServerCallBack = new ObjectCallBack() {
            @Override
            public void callBack(Object object, int flag) {
                if ((boolean) object){
                    Toast.makeText(getActivity(), "Không thể kết nối với server", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Đã Gữi", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnGoi.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listenerBack){
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_1,loaiHoTro);
            snLoai.setAdapter(adapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listenerBack = true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGui_GoiMail_Fragment:
                int idLoaiHoTro = (int) snLoai.getSelectedItemId();
                if (idLoaiHoTro != 0){

                    Mail mail = new Mail();
                    mail.setLoaiHoTro(loaiHoTro[idLoaiHoTro]);
                    mail.setNoiDungHoTro(etNoiDung.getText().toString());
                    mail.setTen(etTen.getText().toString());
                    mail.setEmail(etEmail.getText().toString());

                    if (!mail.getNoiDungHoTro().equals("")){
                        supportHandler.postMail(mail);
                    }else {
                        Toast.makeText(getActivity(), "Vui lòng nhận nội dung", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getActivity(), "Vui lòng chọn \"Loại Vấn Đề\"", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }
    }
}
