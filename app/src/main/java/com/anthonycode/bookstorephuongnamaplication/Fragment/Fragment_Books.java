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

import com.anthonycode.bookstorephuongnamaplication.Adapter.Adapter_Sach;
import com.anthonycode.bookstorephuongnamaplication.DAO.SachDAO;
import com.anthonycode.bookstorephuongnamaplication.Dialog.BottomSheet_Insert_Books;
import com.anthonycode.bookstorephuongnamaplication.Model.Sach;
import com.anthonycode.bookstorephuongnamaplication.R;

import java.util.ArrayList;

public class Fragment_Books extends Fragment {
    public static Adapter_Sach adapter_sach;
    public static RecyclerView rcv_book;
    ArrayList<Sach> ds_gd;
    SachDAO sachDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books, container, false);
        rcv_book = view.findViewById(R.id.rcv_books);
        rcv_book.setLayoutManager(new LinearLayoutManager(getContext()));
        ds_gd = new ArrayList<>();
        sachDAO = new SachDAO(getContext());
        setHasOptionsMenu(true);

        ds_gd = sachDAO.getAllSach();
        adapter_sach = new Adapter_Sach(ds_gd, getContext());
        rcv_book.setAdapter(adapter_sach);

        return view;
    }

    private Menu menu = null;
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_books, menu);
        this.menu = menu;
        menu.findItem(R.id.user).setVisible(false);
        getActivity().invalidateOptionsMenu();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add_book:
                BottomSheet_Insert_Books bottom_sheet = new BottomSheet_Insert_Books();
                bottom_sheet.show(getFragmentManager(), "TAG");
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
