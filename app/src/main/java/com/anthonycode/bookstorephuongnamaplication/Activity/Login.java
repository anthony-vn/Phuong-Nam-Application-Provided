package com.anthonycode.bookstorephuongnamaplication.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anthonycode.bookstorephuongnamaplication.DAO.UserDAO;
import com.anthonycode.bookstorephuongnamaplication.HomeActivity;
import com.anthonycode.bookstorephuongnamaplication.Model.User;
import com.anthonycode.bookstorephuongnamaplication.R;

import java.util.ArrayList;
import java.util.List;


public class Login extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView tvSignUp, tvForgotPass;
    ProgressBar pbLogin;
    UserDAO userDAO;
    List<User> datauser;
    CheckBox chkRememberPass;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ///innit view
        pbLogin = (ProgressBar) findViewById(R.id.pbLogin);
        edtUsername = (EditText) findViewById(R.id.edt_username_login);
        edtPassword = (EditText) findViewById(R.id.edt_password_login);
        btnLogin = (Button) findViewById(R.id.btnSignIn);
        tvSignUp = (TextView) findViewById(R.id.tv_signUp);
        tvForgotPass = (TextView) findViewById(R.id.tv_forgotPassword);


        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ForgotPassword.class));
                finish();
            }
        });

        //Button set on click listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbLogin.setVisibility(View.VISIBLE);
                final String getUsername = edtUsername.getText().toString().trim();
                final String getPassword = edtPassword.getText().toString().trim();
                if (getUsername.isEmpty()) {
                    pbLogin.setVisibility(View.INVISIBLE);
                    edtUsername.setError("The username is empty!");
                } else if (getPassword.isEmpty()) {
                    pbLogin.setVisibility(View.INVISIBLE);
                    edtPassword.setError("The password is empty!");
                } else {

                    try {

                        //Check user with loop for
                        datauser = new ArrayList<>();
                        userDAO = new UserDAO(Login.this);
                        datauser = userDAO.getAllUser();
                        User user = new User(getUsername, getPassword);

                        if (userDAO.Authenticate(user) != null) {

                            Toast.makeText(Login.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, HomeActivity.class));
                            finish();
                        } else {
                            pbLogin.setVisibility(View.INVISIBLE);
                            Toast.makeText(Login.this, "Username hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.d("TAG", "Error login: " + e.toString());
                    }

//                    Toast.makeText(context, "size" + datauser.size(), Toast.LENGTH_SHORT).show();
//                    for (int i = 0; i < datauser.size(); i++) {
//                        if (datauser.get(i).getUserName().matches(getUsername) && datauser.get(i).getPassword().matches(getPassword)) {
//                            Toast.makeText(Login.this, "Login successfully!", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(Login.this, HomeActivity.class));
//                            finish();
//                        }else {
//                            Toast.makeText(Login.this, "Login failed!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
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
    public void rememberUser(String u, String p) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("USERNAME_X", u);
        edit.putString("PASSWORD_X", p);
        //luu lai toan bo
        edit.commit();
    }

}