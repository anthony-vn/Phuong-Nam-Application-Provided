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

public class BottomSheet_Insert_TheLoai extends BottomSheetDialogFragment {
    EditText matheloai, tentheloai, mota;
    Button btn_them_theloai;
    TheLoaiDAO theLoaiDAO;
    ArrayList<TheLoai> ds_tl;
    Adapter_TheLoai adapter_theLoai;

    public BottomSheet_Insert_TheLoai() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_insert_theloai, container, false);
        matheloai = view.findViewById(R.id.edtInsert_matheloai);
        tentheloai = view.findViewById(R.id.edtInsert_tentheloai);
        mota = view.findViewById(R.id.edtInsert_mota);
        btn_them_theloai = view.findViewById(R.id.btnAdd_theloai);

        theLoaiDAO = new TheLoaiDAO(getContext());
        ds_tl = new ArrayList<>();

        btn_them_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matheloai_ = matheloai.getText().toString().trim();
                String tentheloai_ = tentheloai.getText().toString().trim();
                String mota_ = mota.getText().toString().trim();

                if (matheloai_.isEmpty() || tentheloai_.isEmpty()) {
                    matheloai.setError("Ma the loai is empty!");
                    tentheloai.setError("Ten the loai is empty!");
                } else {
                    //Thêm thể loại
                    theLoaiDAO = new TheLoaiDAO(getContext());
                    TheLoai tl = new TheLoai(matheloai_, tentheloai_, mota_);
                    theLoaiDAO.inserTheLoai(tl);

                    capnhat();

                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }
        });
        return view;
    }

    public void capnhat() {
        ds_tl = theLoaiDAO.getAllTheLoai();
        adapter_theLoai = new Adapter_TheLoai(ds_tl, getContext());
        rcv_theloai.setAdapter(adapter_theLoai);
    }
}
