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

import com.anthonycode.bookstorephuongnamaplication.Adapter.Adapter_User;
import com.anthonycode.bookstorephuongnamaplication.DAO.UserDAO;
import com.anthonycode.bookstorephuongnamaplication.Model.User;
import com.anthonycode.bookstorephuongnamaplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import static com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_User.rcv_user;


public class BottomSheet_Update_User extends BottomSheetDialogFragment {
    EditText edtUsername, edtPassword, edtPhone, edtFullname;
    Button btnUpdateUser;
    UserDAO userDAO;
    List<User> ds_gd;
    int id;
    Adapter_User adapter_category;

    public BottomSheet_Update_User() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_update_user, container, false);
        edtUsername = view.findViewById(R.id.edtUpdate_username);
        edtPassword = view.findViewById(R.id.edtUpdate_password);
        edtPhone = view.findViewById(R.id.edtUpdate_phone);
        edtFullname = view.findViewById(R.id.edtUpdate_fullname);
        btnUpdateUser = view.findViewById(R.id.btnUpdate_user);

        // Get Bundle
        Bundle mArgs = getArguments();
        id = mArgs.getInt("id_user");
        final String username_x = mArgs.getString("username_user");
        final String password_x = mArgs.getString("password_user");
        final String phone_x = mArgs.getString("phone_user");
        final String fullname_x = mArgs.getString("fullname_user");

        // Hiển thị lên bottomsheet Update
        edtUsername.setText(username_x);
        edtPassword.setText(password_x);
        edtPhone.setText(phone_x);
        edtFullname.setText(fullname_x);

        //Update user
        userDAO = new UserDAO(getContext());

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_ = edtUsername.getText().toString().trim();
                String password_ = edtPassword.getText().toString().trim();
                String phone_ = edtPhone.getText().toString().trim();
                String fullname_ = edtFullname.getText().toString().trim();

                User user = new User(id, username_, password_, phone_, fullname_);
                userDAO = new UserDAO(getContext());
                userDAO.updateUser(user);

                capnhat();
                Toast.makeText(getContext(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        return view;
    }

    public void capnhat() {
        ds_gd = userDAO.getAllUser();
        adapter_category = new Adapter_User(ds_gd, getContext());
        rcv_user.setAdapter(adapter_category);
    }

}
