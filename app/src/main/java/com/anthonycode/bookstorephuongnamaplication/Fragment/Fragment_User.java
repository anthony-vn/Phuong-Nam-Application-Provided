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

import com.anthonycode.bookstorephuongnamaplication.Adapter.Adapter_User;
import com.anthonycode.bookstorephuongnamaplication.DAO.UserDAO;
import com.anthonycode.bookstorephuongnamaplication.Dialog.BottomSheet_Insert_User;
import com.anthonycode.bookstorephuongnamaplication.Model.User;
import com.anthonycode.bookstorephuongnamaplication.R;

import java.util.ArrayList;

public class Fragment_User extends Fragment {
    public static Adapter_User adapter_user;
    public static RecyclerView rcv_user;
    ArrayList<User> ds_user;
    UserDAO userDAO;
    private Menu menu = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__user, container, false);
        rcv_user = view.findViewById(R.id.rcv_user);
        rcv_user.setLayoutManager(new LinearLayoutManager(getContext()));
        ds_user = new ArrayList<>();
        userDAO = new UserDAO(getContext());
        setHasOptionsMenu(true);

        ds_user = userDAO.getAllUser();
        adapter_user = new Adapter_User(ds_user, getContext());
        rcv_user.setAdapter(adapter_user);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_user, menu);
        this.menu = menu;
        menu.findItem(R.id.user).setVisible(false);
        getActivity().invalidateOptionsMenu();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_user:
                BottomSheet_Insert_User bottom_sheet = new BottomSheet_Insert_User();
                bottom_sheet.show(getFragmentManager(), "TAG");
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }


}