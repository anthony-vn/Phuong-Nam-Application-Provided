package com.anthonycode.bookstorephuongnamaplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anthonycode.bookstorephuongnamaplication.Adapter.CartAdapter;
import com.anthonycode.bookstorephuongnamaplication.DAO.HoaDonChiTietDAO;
import com.anthonycode.bookstorephuongnamaplication.DAO.SachDAO;
import com.anthonycode.bookstorephuongnamaplication.Model.HoaDon;
import com.anthonycode.bookstorephuongnamaplication.Model.HoaDonChiTiet;
import com.anthonycode.bookstorephuongnamaplication.Model.Sach;
import com.anthonycode.bookstorephuongnamaplication.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddHoaDonChiTietActivity extends AppCompatActivity {
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    Spinner spMaSach;
    EditText edSoLuong;
    TextView tvThanhTien, tvMaHoaDon;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    SachDAO bookDAO;
    List<Sach> ds_sach = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    double thanhTien = 0;
    String maSachX = null;
    String maHoaDonZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("CHI TIẾT HOÁ ĐƠN");
        setContentView(R.layout.activity_add_hoa_don_chi_tiet);
        spMaSach = (Spinner) findViewById(R.id.spMaSach);
        tvMaHoaDon = (TextView) findViewById(R.id.tvMaHoaDon_hdct);
        edSoLuong = (EditText) findViewById(R.id.edSoLuongMua_hdct);
        lvCart = (ListView) findViewById(R.id.lvCart);
        tvThanhTien = (TextView) findViewById(R.id.tvThanhTien);
        getMaSach();
        adapter = new CartAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            tvMaHoaDon.setText(b.getString("MaHoaDonX"));
        }
        maHoaDonZ = b.getString("MaHoaDonX");

        spMaSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSachX = ds_sach.get(spMaSach.getSelectedItemPosition()).getMaSach();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void getMaSach() {
        bookDAO = new SachDAO(AddHoaDonChiTietActivity.this);

        ds_sach = bookDAO.getMaSach();
        ArrayAdapter<Sach> dataAdapter = new ArrayAdapter<Sach>(this,
                android.R.layout.simple_spinner_item, ds_sach);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMaSach.setAdapter(dataAdapter);
    }

    public void ADDHoaDonCHITIET(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(AddHoaDonChiTietActivity.this);
        bookDAO = new SachDAO(AddHoaDonChiTietActivity.this);
        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                Sach sach = bookDAO.getSachByID(maSachX);
                if (sach != null) {
                    int pos = checkMaSach(dsHDCT, maSachX);
                    HoaDon hoaDon = new HoaDon(maHoaDonZ, new Date());
                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(1, hoaDon, sach, Integer.parseInt(edSoLuong.getText().toString()));
                    if (pos >= 0) {
                        int soluong = dsHDCT.get(pos).getSoLuongMua();
                        hoaDonChiTiet.setSoLuongMua(soluong + Integer.parseInt(edSoLuong.getText().toString()));
                        dsHDCT.set(pos, hoaDonChiTiet);
                    } else {
                        dsHDCT.add(hoaDonChiTiet);
                    }
                    adapter.changeDataset(dsHDCT);
                } else {
                    Toast.makeText(getApplicationContext(), "Mã sách không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(AddHoaDonChiTietActivity.this);
        //tinh tien
        thanhTien = 0;
        try {
            for (HoaDonChiTiet hd : dsHDCT) {
                hoaDonChiTietDAO.inserHoaDonChiTiet(hd);
                thanhTien = thanhTien + hd.getSoLuongMua() * hd.getSach().getGiaBia();
            }
            tvThanhTien.setText("Tổng tiền: " + thanhTien);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int checkMaSach(List<HoaDonChiTiet> lsHD, String maSach) {
        int pos = -1;
        for (int i = 0; i < lsHD.size(); i++) {
            HoaDonChiTiet hd = lsHD.get(i);
            if (hd.getSach().getMaSach().equalsIgnoreCase(maSach)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public int validation() {
        if (maSachX.isEmpty() || edSoLuong.getText().toString().isEmpty() || maHoaDonZ.isEmpty()) {
            return -1;
        }
        return 1;
    }
}