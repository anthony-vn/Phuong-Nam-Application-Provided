package com.anthonycode.bookstorephuongnamaplication.Dialog;

import android.os.Bundle;
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

import com.anthonycode.bookstorephuongnamaplication.Adapter.Adapter_Sach;
import com.anthonycode.bookstorephuongnamaplication.DAO.SachDAO;
import com.anthonycode.bookstorephuongnamaplication.DAO.TheLoaiDAO;
import com.anthonycode.bookstorephuongnamaplication.Model.Sach;
import com.anthonycode.bookstorephuongnamaplication.Model.TheLoai;
import com.anthonycode.bookstorephuongnamaplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import static com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_Books.rcv_book;
import static com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_Category.adapter_theLoai;


public class BottomSheet_Update_Book extends BottomSheetDialogFragment {
    EditText masach, tensach, tacgia, nhaxuatban, giabia, soluong;
    Button btn_updateSach;
    Spinner spMaTheLoai;
    TheLoaiDAO theLoaiDAO;
    ArrayList<TheLoai> ds_tl = new ArrayList<>();
    SachDAO sachDAO;
    ArrayList<Sach> ds_sach;
    int id;
    Adapter_Sach adapter_sach;
    String matheloai_x = null;

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
        getSpinnerTheLoai();

        // Get Bundle
        Bundle mArgs = getArguments();
        String masach_ = mArgs.getString("MS");
        String tensach_ = mArgs.getString("TS");
        String tacgia_ = mArgs.getString("TG");
        String nhaxuatban_ = mArgs.getString("NXB");
        Double giabia_ = mArgs.getDouble("GB");
        int soluong_ = mArgs.getInt("SL");

        // Hiển thị lên itemKhoanthu Update
        masach.setText(masach_);
        tensach.setText(tensach_);
        tacgia.setText(tacgia_);
        nhaxuatban.setText(nhaxuatban_);
        giabia.setText(String.valueOf(giabia_));
        soluong.setText(String.valueOf(soluong_));
//         Get trạng thái của GIAO_DICH

        //Update khoanthu
        sachDAO = new SachDAO(getContext());
        ds_sach = new ArrayList<>();

        //Get spinner

        spMaTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                matheloai_x = ds_tl.get(spMaTheLoai.getSelectedItemPosition()).getMaTheLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_updateSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String masach_X = masach.getText().toString().trim();
                String tensach_X = tensach.getText().toString().trim();
                String tacgia_X = tacgia.getText().toString().trim();
                String nxb_X = nhaxuatban.getText().toString().trim();
                String giabia_X = giabia.getText().toString().trim();
                String soluong_X = soluong.getText().toString().trim();

                Sach sach = new Sach(masach_X, matheloai_x, tensach_X, tacgia_X, nxb_X, Double.valueOf(giabia_X), Integer.valueOf(soluong_X));
                sachDAO = new SachDAO(getContext());
                sachDAO.updateSach(sach);

                capnhat();
                Toast.makeText(getContext(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                dismiss();
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
        spMaTheLoai.setAdapter(dataAdapter);
    }

    public void capnhat() {
        ds_sach = new ArrayList<>();
        ds_sach = sachDAO.getAllSach();
        adapter_sach = new Adapter_Sach(ds_sach, getContext());
        rcv_book.setAdapter(adapter_theLoai);
    }

}
