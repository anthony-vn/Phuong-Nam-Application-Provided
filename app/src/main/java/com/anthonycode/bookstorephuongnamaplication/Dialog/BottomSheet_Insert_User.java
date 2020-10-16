package com.anthonycode.bookstorephuongnamaplication.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.anthonycode.bookstorephuongnamaplication.Adapter.Adapter_NguoiDung;
import com.anthonycode.bookstorephuongnamaplication.DAO.UserDAO;
import com.anthonycode.bookstorephuongnamaplication.Model.User;
import com.anthonycode.bookstorephuongnamaplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import static com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_Category.adapter_category;
import static com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_User.rcv_user;

public class BottomSheet_Insert_User extends BottomSheetDialogFragment {
    EditText edtUsername, edtPassword, edtPhone, edtFullname;
    Button btnAddUser;
    UserDAO userDAO;
    ArrayList<User> ds_tl;
    Adapter_NguoiDung adapter_user;

    public BottomSheet_Insert_User() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_insert_user, container, false);
        edtUsername = view.findViewById(R.id.edtInsert_username);
        edtPassword = view.findViewById(R.id.edtInsert_password);
        edtPhone = view.findViewById(R.id.edtInsert_phone);
        edtFullname = view.findViewById(R.id.edtInsert_fullname);
        btnAddUser = view.findViewById(R.id.btnAdd_user);

        userDAO = new UserDAO(getContext());
        ds_tl = new ArrayList<>();

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_ = edtUsername.getText().toString().trim();
                String password_ = edtPassword.getText().toString().trim();
                String phone_ = edtPhone.getText().toString().trim();
                String fullname_ = edtFullname.getText().toString().trim();

                if (username_.isEmpty() || password_.isEmpty() || phone_.isEmpty() || fullname_.isEmpty()) {
                    edtUsername.setError("Is empty!");
                    edtPassword.setError("Is empty!");
                    edtPhone.setError("Is empty!");
                    edtFullname.setError("Is empty!");
                } else {
                    //Add user
                    userDAO = new UserDAO(getContext());
                    User user = new User(username_, password_, phone_, fullname_);
                    userDAO.inserNguoiDung(user);

                    capnhat();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }
        });
        return view;
    }

    public void capnhat() {
        ds_tl = userDAO.getAllUser();
        adapter_user = new Adapter_NguoiDung(ds_tl, getContext());
        rcv_user.setAdapter(adapter_category);
    }
}
