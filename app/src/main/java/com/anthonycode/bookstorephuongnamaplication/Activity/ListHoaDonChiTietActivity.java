package com.anthonycode.bookstorephuongnamaplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.anthonycode.bookstorephuongnamaplication.Adapter.CartAdapter;
import com.anthonycode.bookstorephuongnamaplication.DAO.HoaDonChiTietDAO;
import com.anthonycode.bookstorephuongnamaplication.Model.HoaDonChiTiet;
import com.anthonycode.bookstorephuongnamaplication.R;

import java.util.ArrayList;
import java.util.List;

public class ListHoaDonChiTietActivity extends AppCompatActivity {
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoadonchitiet);
        setTitle("HOÁ ĐƠN CHI TIẾT");
        lvCart = (ListView) findViewById(R.id.lvHoaDonChiTiet);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(ListHoaDonChiTietActivity.this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            dsHDCT = hoaDonChiTietDAO.getAllHoaDonChiTietByID(b.getString("MAHOADON"));
        }

        adapter = new CartAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);
    }
}