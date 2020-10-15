package com.anthonycode.bookstorephuongnamaplication.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anthonycode.bookstorephuongnamaplication.Dialog.BottomSheet_Insert_TheLoai;
import com.anthonycode.bookstorephuongnamaplication.R;

public class Fragment_MyAccount extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__my_account, container, false);
        setHasOptionsMenu(true);
        return view;
    }
    private Menu menu = null;
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu__my_account, menu);
        this.menu = menu;
        menu.findItem(R.id.user).setVisible(false);
        getActivity().invalidateOptionsMenu();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.myacc:
//                BottomSheet_Insert_TheLoai bottom_sheet = new BottomSheet_Insert_TheLoai();
//                bottom_sheet.show(getFragmentManager(), "TAG");
                Toast.makeText(getContext(), "Do something!", Toast.LENGTH_SHORT).show();
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
}