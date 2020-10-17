package com.anthonycode.bookstorephuongnamaplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anthonycode.bookstorephuongnamaplication.DAO.UserDAO;
import com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_Home;
import com.anthonycode.bookstorephuongnamaplication.Model.User;
import com.anthonycode.bookstorephuongnamaplication.R;


public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText edtUsername, edtPassword, edtConfirmPass, edtPhone, edtFullname;
    private Button btnSignUp;
    private ProgressBar pbSignUp;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        edtUsername = findViewById(R.id.edUsername);
        edtPassword = findViewById(R.id.edPassword);
        edtConfirmPass = findViewById(R.id.edConFirmPassword);
        edtPhone = findViewById(R.id.edPhone);
        edtFullname = findViewById(R.id.edFullname);
        btnSignUp = findViewById(R.id.btnSignUp);
        //Firebase
        pbSignUp = findViewById(R.id.ProgressBarSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDAO = new UserDAO(Register.this);
                try {
                    final String username_ = edtUsername.getText().toString().trim();
                    final String phone_ = edtPhone.getText().toString().trim();
                    final String password_ = edtPassword.getText().toString().trim();
                    final String confrimPassword_ = edtConfirmPass.getText().toString().trim();
                    final String fullname_ = edtFullname.getText().toString().trim();

                    pbSignUp.setVisibility(View.VISIBLE);
                    if (username_.isEmpty() || phone_.isEmpty() || password_.isEmpty() || confrimPassword_.isEmpty() || fullname_.isEmpty()){
                        edtUsername.setError("Không được để trống!");
                        edtPhone.setError("Không được để trống!");
                        edtPassword.setError("Không được để trống!");
                        edtConfirmPass.setError("Không được để trống!");
                        edtFullname.setError("Không được để trống!");
                        pbSignUp.setVisibility(View.INVISIBLE);
                    }else {
                        if (password_.equals(confrimPassword_)){
                            User user = new User(username_, password_, phone_, fullname_);
                            userDAO.inserNguoiDung(user);
                            Toast.makeText(Register.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Get all user : " + userDAO.getAllUser());
                            startActivity(new Intent(Register.this, Login.class));
                            finish();
                        }else {
                            Toast.makeText(Register.this, "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
                            pbSignUp.setVisibility(View.INVISIBLE);
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(Register.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Error sign up == " + e.toString());
                    Log.d(TAG, "Get all user : " + userDAO.getAllUser());
                    pbSignUp.setVisibility(View.INVISIBLE);

                }
            }
        });
    }

    public void retrunSignUp(View view) {
        startActivity(new Intent(this, Login.class));
        finish();
    }

}