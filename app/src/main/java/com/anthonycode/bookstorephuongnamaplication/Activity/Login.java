package com.anthonycode.bookstorephuongnamaplication.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.anthonycode.bookstorephuongnamaplication.DAO.UserDAO;
import com.anthonycode.bookstorephuongnamaplication.Database.DatabaseHelper;
import com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_Home;
import com.anthonycode.bookstorephuongnamaplication.R;


public class Login extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btnLogin;
    TextView tvSignUp;
    ProgressBar pbLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pbLogin = findViewById(R.id.pbLogin);
        edtEmail = findViewById(R.id.edt_email_login);
        edtPassword = findViewById(R.id.edt_password_login);
        btnLogin = findViewById(R.id.btnSignIn);
        tvSignUp = findViewById(R.id.tv_signUp);
    }

    //check primarykey nó sẽ lấy ra những thằng nào trùng và sẽ update thằng đó
    //Cho nên chúng ta sẽ dùng nó để check login
}