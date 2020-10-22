package com.anthonycode.bookstorephuongnamaplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.anthonycode.bookstorephuongnamaplication.Adapter.AdapterHoaDon;
import com.anthonycode.bookstorephuongnamaplication.DAO.HoaDonDAO;
import com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_Home;
import com.anthonycode.bookstorephuongnamaplication.Model.HoaDon;
import com.anthonycode.bookstorephuongnamaplication.R;

import java.util.ArrayList;
import java.util.List;

public class ListHoaDonActivity extends AppCompatActivity {
    public List<HoaDon> dsHoaDon = new ArrayList<>();
    ListView lvHoaDon;
    AdapterHoaDon adapter = null;
    HoaDonDAO hoaDonDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don);
        setTitle("HOÁ ĐƠN");
        lvHoaDon = (ListView) findViewById(R.id.lvHoaDon);
        hoaDonDAO = new HoaDonDAO(ListHoaDonActivity.this);
        try {
            dsHoaDon = hoaDonDAO.getAllHoaDon();
        } catch (Exception e) {
            Log.d("Error: ", e.toString());
        }

        adapter = new AdapterHoaDon(this, dsHoaDon);
        lvHoaDon.setAdapter(adapter);

        lvHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HoaDon hoaDon = (HoaDon) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListHoaDonActivity.this, ListHoaDonChiTietActivity.class);
                Bundle b = new Bundle();
                b.putString("MAHOADON", hoaDon.getMaHoaDon());
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        // TextFilter
        lvHoaDon.setTextFilterEnabled(true);
        EditText edSeach = (EditText) findViewById(R.id.edSearch_hoadon);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    adapter.resetData();
                }
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    public void OpenAddHoaDon(View view) {
        Intent intent = new Intent(ListHoaDonActivity.this, AddHoaDon.class);
        startActivity(intent);
    }

    public void Close(View view) {
    }
}