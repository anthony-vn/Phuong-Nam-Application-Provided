package com.anthonycode.bookstorephuongnamaplication.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.anthonycode.bookstorephuongnamaplication.DAO.SachDAO;
import com.anthonycode.bookstorephuongnamaplication.Model.Sach;
import com.anthonycode.bookstorephuongnamaplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class BottomSheet_Update_Book extends BottomSheetDialogFragment {
    EditText masach, tensach, tacgia, nhaxuatban, giabia, soluong;
    Button btn_updateSach;
    Spinner spMaTheLoai;
    SachDAO sachDAO;
    ArrayList<Sach> ds_sach;
    int id;
//    Adapter_Books adapter_books;

    public BottomSheet_Update_Book() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_update_books, container, false);
        masach = view.findViewById(R.id.edtUpdate_masach);
        tensach = view.findViewById(R.id.edtUpdate_tensach);
        tacgia = view.findViewById(R.id.edtUpdate_tacgia);
        nhaxuatban = view.findViewById(R.id.edtUpdate_nhaxuatban);
        giabia = view.findViewById(R.id.edtUpdate_giabia);
        soluong = view.findViewById(R.id.edtUpdate_soluong);
        spMaTheLoai = view.findViewById(R.id.spnUpdateSach);
        btn_updateSach = view.findViewById(R.id.btnUpdateBook);

//        Bundle b = new Bundle();
//        b.putInt("IdSach", ds_sach.get(position).getId());
//        b.putString("MaSach", ds_sach.get(position).getMaSach());
//        b.putString("TheLoai", ds_sach.get(position).getMaTheLoai());
//        b.putString("TenSach", ds_sach.get(position).getTenSach());
//        b.putString("TacGia", ds_sach.get(position).getTacGia());
//        b.putString("NXB", ds_sach.get(position).getNXB());
//        b.putDouble("GiaBia", ds_sach.get(position).getGiaBia());
//        b.putInt("SoLuong", ds_sach.get(position).getSoLuong());
        // Get Bundle
        Bundle mArgs = getArguments();
        id = mArgs.getInt("IdSach");
        final String masach_ = mArgs.getString("MaSach");
        final String theloai_ = mArgs.getString("TheLoai");
        final String tensach_ = mArgs.getString("TenSach");
        final String tacgia_ = mArgs.getString("TacGia");
        final String nhaxuatban_ = mArgs.getString("NXB");
        final Double giabia_ = mArgs.getDouble("GiaBia");
        final int soluong_ = mArgs.getInt("SoLuong");

        // Hiển thị lên itemKhoanthu Update
//        masach.setText(masach_);
//        tensach.setText(masach_);
//        t.setText(masach_);
//        masach.setText(masach_);
//        masach.setText(masach_);
//        masach.setText(masach_);
//        masach.setText(masach_);
//        masach.setText(masach_);
        // Get trạng thái của GIAO_DICH

        //Update khoanthu
//        theLoaiDAO = new TheLoaiDAO(getContext());
//        ds_gd = new ArrayList<>();
//
//        btn_updateTheLoai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String matheloai_ = edt_matheloai.getText().toString();
//                String tentheloai_ = edt_tentheloai.getText().toString();
//                String mota_ = edt_mota.getText().toString();
//
//                TheLoai theLoai = new TheLoai(id, matheloai_, tentheloai_, mota_);
//                theLoaiDAO = new TheLoaiDAO(getContext());
//                theLoaiDAO.updateTheLoai(theLoai);
//
//                capnhat();
//                Toast.makeText(getContext(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
//                dismiss();
//            }
//        });
        return view;
    }

//    public void capnhat() {
//        ds_gd = new ArrayList<>();
//        ds_gd = theLoaiDAO.getAllTheLoai();
//        adapter_category = new Adapter_Category(ds_gd, getContext());
//        rcv_theloai.setAdapter(adapter_category);
//    }

}
