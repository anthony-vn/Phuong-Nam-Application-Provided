package com.anthonycode.bookstorephuongnamaplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anthonycode.bookstorephuongnamaplication.DAO.UserDAO;
import com.anthonycode.bookstorephuongnamaplication.Database.DatabaseHelper;
import com.anthonycode.bookstorephuongnamaplication.HomeActivity;
import com.anthonycode.bookstorephuongnamaplication.R;


public class Login extends AppCompatActivity{
    private final AppCompatActivity activity = Login.this;
    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView tvSignUp;
    ProgressBar pbLogin;
    UserDAO userDAO;
    Context context;
    DatabaseHelper dbhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pbLogin = findViewById(R.id.pbLogin);
        edtUsername = findViewById(R.id.edt_username_login);
        edtPassword = findViewById(R.id.edt_password_login);
        btnLogin = findViewById(R.id.btnSignIn);
        tvSignUp = findViewById(R.id.tv_signUp);

        dbhelper = new DatabaseHelper(context);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbLogin.setVisibility(View.VISIBLE);
                //checkUserLogin
                String getUsername = edtUsername.getText().toString().trim();
                String getPassword = edtPassword.getText().toString().trim();

                try {
                    if (getUsername.isEmpty()) {
                        edtUsername.setError("Is empty!");
                        pbLogin.setVisibility(View.INVISIBLE);
                    }
                    if (getPassword.isEmpty()) {
                        edtPassword.setError("Is empty!");
                        pbLogin.setVisibility(View.INVISIBLE);
                    } else {
                        if (userDAO.checkUser(getUsername, getPassword)) {
                            Toast.makeText(Login.this, "login successfully!", Toast.LENGTH_SHORT).show();
//                            Intent i = new Intent(Login.this, Fragment_Home.class);
//                            Bundle bundle = new Bundle();
//                            bundle.putString("USERNAME", getUsername);
//                            i.putExtras(bundle);
//                            startActivity(i);
                            edtUsername.setText(null);
                            edtPassword.setText(null);
                            startActivity(new Intent(context, HomeActivity.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Username hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                            pbLogin.setVisibility(View.INVISIBLE);
                        }
                    }
                } catch (Exception e) {
                    pbLogin.setVisibility(View.INVISIBLE);
                    Log.d("TAG", "Error login == " + e.toString());
                }
            }

        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
                finish();
            }
        });
    }

    //check primarykey nó sẽ lấy ra những thằng nào trùng và sẽ update thằng đó
    //Cho nên chúng ta sẽ dùng nó để check login


}