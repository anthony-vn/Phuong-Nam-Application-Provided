package com.anthonycode.bookstorephuongnamaplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.anthonycode.bookstorephuongnamaplication.R;


public class Fragment_Login extends Fragment {
    EditText edtEmail, edtPassword;
    Button btnLogin;
    TextView tvSignUp;
    ProgressBar pbLogin;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        pbLogin = view.findViewById(R.id.pbLogin);
        edtEmail = view.findViewById(R.id.edt_email_login);
        edtPassword = view.findViewById(R.id.edt_password_login);
        btnLogin = view.findViewById(R.id.btnSignIn);
        tvSignUp = view.findViewById(R.id.tv_signUp);

        return view;
    }

}