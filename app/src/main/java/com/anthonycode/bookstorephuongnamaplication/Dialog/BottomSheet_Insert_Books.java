package com.anthonycode.bookstorephuongnamaplication.Dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.anthonycode.bookstorephuongnamaplication.Adapter.Adapter_Books;
import com.anthonycode.bookstorephuongnamaplication.DAO.SachDAO;
import com.anthonycode.bookstorephuongnamaplication.DAO.TheLoaiDAO;
import com.anthonycode.bookstorephuongnamaplication.Model.Sach;
import com.anthonycode.bookstorephuongnamaplication.Model.TheLoai;
import com.anthonycode.bookstorephuongnamaplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import static com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_Books.rcv_book;

public class BottomSheet_Insert_Books extends BottomSheetDialogFragment {
    EditText maSach, tensach, tacgia, nxb, giabia, soluong;
    Button btn_them_sach;
    Spinner spTheLoai;
    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    ArrayList<Sach> ds_book;
    ArrayList<TheLoai> ds_tl;
    Adapter_Books adapter_books;

    private String matheloai_x;

    public BottomSheet_Insert_Books() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_insert_books, container, false);
        tensach = view.findViewById(R.id.edt_tensach);
        tacgia = view.findViewById(R.id.edt_tacgia);
        nxb = view.findViewById(R.id.edt_nhaxuatban);
        giabia = view.findViewById(R.id.edt_giabia);
        soluong = view.findViewById(R.id.edt_soluong);
        spTheLoai = view.findViewById(R.id.spnTheLoai);
        maSach = view.findViewById(R.id.edt_masachs);
        btn_them_sach = view.findViewById(R.id.btnAddBook);

        //View
        theLoaiDAO = new TheLoaiDAO(getContext());
        ds_tl = new ArrayList<>();
        sachDAO = new SachDAO(getContext());
        ds_book = new ArrayList<>();

        //Get spinner
        getSpinnerTheLoai();
        spTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                matheloai_x = ds_tl.get(spTheLoai.getSelectedItemPosition()).getMaTheLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_them_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String masach_ = maSach.getText().toString().trim();
                String tensach_ = tensach.getText().toString().trim();
                String tacgia_ = tacgia.getText().toString().trim();
                String nxb_ = nxb.getText().toString().trim();
                String giabia_ = giabia.getText().toString().trim();
                String soluong_ = soluong.getText().toString().trim();

                if (masach_.isEmpty() || tensach_.isEmpty() || tacgia_.isEmpty() || nxb_.isEmpty() || giabia_.isEmpty() || soluong_.isEmpty()) {
                    maSach.setError("Is empty!");
                    tensach.setError("Is empty!");
                    tacgia.setError("Is empty!");
                    nxb.setError("Is empty!");
                    giabia.setError("Is empty!");
                    soluong.setError("Is empty!");
                } else {
                    //Thêm sách
                    sachDAO = new SachDAO(getContext());
                    Sach sach = new Sach(masach_, matheloai_x, tensach_, tacgia_, nxb_, Double.parseDouble(giabia_), Integer.parseInt(soluong_));
                    sachDAO.inserSach(sach);
                    capnhat();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Insert Book: " + sachDAO.getAllSach());
                    dismiss();
                }
            }
        });

        return view;
    }

    public void getSpinnerTheLoai() {
        theLoaiDAO = new TheLoaiDAO(getContext());

        ds_tl = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<TheLoai> dataAdapter = new ArrayAdapter<TheLoai>(getContext(),
                R.layout.row_spinner_item, ds_tl);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTheLoai.setAdapter(dataAdapter);
    }

    public void capnhat() {
        ds_book = sachDAO.getAllSach();
        adapter_books = new Adapter_Books(ds_book, getContext());
        rcv_book.setAdapter(adapter_books);
    }
}
