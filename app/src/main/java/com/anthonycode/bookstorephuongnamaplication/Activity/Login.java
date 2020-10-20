package com.anthonycode.bookstorephuongnamaplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
    TextView tvSignUp;
    ProgressBar pbLogin;
    UserDAO userDAO;
    List<User> datauser;
    Context context;
    CheckBox chkRememberPass;
    public static final String KEY_LOGIN = "show_what";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ///innit view
        pbLogin = (ProgressBar) findViewById(R.id.pbLogin);
        edtUsername = (EditText) findViewById(R.id.edt_username_login);
        edtPassword = (EditText) findViewById(R.id.edt_password_login);
        btnLogin = (Button) findViewById(R.id.btnSignIn);
        tvSignUp = (TextView) findViewById(R.id.tv_signUp);


        //Button set on click listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUsername = edtUsername.getText().toString().trim();
                String getPassword = edtPassword.getText().toString().trim();
                if (getUsername.isEmpty()) {
                    edtUsername.setError("The username is empty!");
                } else if (getPassword.isEmpty()) {
                    edtPassword.setError("The password is empty!");
                } else {

                    //Check user with loop for
                    datauser = new ArrayList<>();
                    userDAO = new UserDAO(Login.this);
                    datauser = userDAO.getAllUser();
                    User user = new User(getUsername, getPassword);
                    if (userDAO.Authenticate(user) != null) {

                        Bundle args = new Bundle();
                        args.putString("USN", getUsername);
                        args.putString("PSW", getPassword);

                        Toast.makeText(Login.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, HomeActivity.class));
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Login failed!", Toast.LENGTH_SHORT).show();
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
    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            //xoa tinh trang luu tru truoc do
            edit.clear();
        } else {
            //luu du lieu
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        //luu lai toan bo
        edit.commit();
    }

}