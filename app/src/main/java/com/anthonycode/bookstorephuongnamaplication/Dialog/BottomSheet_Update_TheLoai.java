package com.anthonycode.bookstorephuongnamaplication.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.anthonycode.bookstorephuongnamaplication.Adapter.Adapter_TheLoai;
import com.anthonycode.bookstorephuongnamaplication.DAO.TheLoaiDAO;
import com.anthonycode.bookstorephuongnamaplication.Model.TheLoai;
import com.anthonycode.bookstorephuongnamaplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import static com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_Category.rcv_theloai;


public class BottomSheet_Update_TheLoai extends BottomSheetDialogFragment {
    EditText edt_matheloai, edt_tentheloai, edt_mota;
    Button btn_updateTheLoai;
    TheLoaiDAO theLoaiDAO;
    ArrayList<TheLoai> ds_gd;
    int id;
    Adapter_TheLoai adapter_theLoai;

    public BottomSheet_Update_TheLoai() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_update_theloai, container, false);
        edt_matheloai = view.findViewById(R.id.edtUpdate_matheloai);
        edt_tentheloai = view.findViewById(R.id.edtUpdate_tentheloai);
        edt_mota = view.findViewById(R.id.edtUpdate_motatheloai);
        btn_updateTheLoai = view.findViewById(R.id.btnUpdate_theloai);

        // Get Bundle
        Bundle mArgs = getArguments();
        id = mArgs.getInt("id_TL");
        final String matheloai = mArgs.getString("ma_TL");
        final String tentheloai = mArgs.getString("ten_TL");
        String motatheloai = mArgs.getString("mota_TL");

        // Hiển thị lên itemKhoanthu Update
        edt_matheloai.setText(matheloai);
        edt_tentheloai.setText(tentheloai);
        edt_mota.setText(motatheloai);
        // Get trạng thái của GIAO_DICH

        //Update khoanthu
        theLoaiDAO = new TheLoaiDAO(getContext());
        ds_gd = new ArrayList<>();

        btn_updateTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matheloai_ = edt_matheloai.getText().toString();
                String tentheloai_ = edt_tentheloai.getText().toString();
                String mota_ = edt_mota.getText().toString();

                TheLoai theLoai = new TheLoai(id, matheloai_, tentheloai_, mota_);
                theLoaiDAO = new TheLoaiDAO(getContext());
                theLoaiDAO.updateTheLoai(theLoai);

                capnhat();
                Toast.makeText(getContext(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        return view;
    }

    public void capnhat() {
        ds_gd = new ArrayList<>();
        ds_gd = theLoaiDAO.getAllTheLoai();
        adapter_theLoai = new Adapter_TheLoai(ds_gd, getContext());
        rcv_theloai.setAdapter(adapter_theLoai);
    }

}
