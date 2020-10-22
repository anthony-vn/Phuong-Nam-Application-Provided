package com.anthonycode.bookstorephuongnamaplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anthonycode.bookstorephuongnamaplication.Adapter.Adapter_TheLoai;
import com.anthonycode.bookstorephuongnamaplication.DAO.TheLoaiDAO;
import com.anthonycode.bookstorephuongnamaplication.Dialog.BottomSheet_Insert_TheLoai;
import com.anthonycode.bookstorephuongnamaplication.Model.TheLoai;
import com.anthonycode.bookstorephuongnamaplication.R;

import java.util.ArrayList;

public class Fragment_Category extends Fragment {
    public static Adapter_TheLoai adapter_theLoai;
    public static RecyclerView rcv_theloai;
    ArrayList<TheLoai> ds_gd;
    TheLoaiDAO khoanChi_dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        rcv_theloai = view.findViewById(R.id.rcv_theloai);
        rcv_theloai.setLayoutManager(new LinearLayoutManager(getContext()));
        ds_gd = new ArrayList<>();
        khoanChi_dao = new TheLoaiDAO(getContext());
        setHasOptionsMenu(true);

        ds_gd = khoanChi_dao.getAllTheLoai();
        adapter_theLoai = new Adapter_TheLoai(ds_gd, getContext());
        rcv_theloai.setAdapter(adapter_theLoai);

        return view;
    }

    private Menu menu = null;
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_theloai, menu);
        this.menu = menu;
        menu.findItem(R.id.user).setVisible(false);
        getActivity().invalidateOptionsMenu();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add_theloai:
                BottomSheet_Insert_TheLoai bottom_sheet = new BottomSheet_Insert_TheLoai();
                bottom_sheet.show(getFragmentManager(), "TAG");
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
