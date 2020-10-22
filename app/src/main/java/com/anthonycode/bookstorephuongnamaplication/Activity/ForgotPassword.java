package com.anthonycode.bookstorephuongnamaplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anthonycode.bookstorephuongnamaplication.Adapter.Adapter_User;
import com.anthonycode.bookstorephuongnamaplication.DAO.UserDAO;
import com.anthonycode.bookstorephuongnamaplication.Model.User;
import com.anthonycode.bookstorephuongnamaplication.R;

import java.util.List;

import static com.anthonycode.bookstorephuongnamaplication.Fragment.Fragment_User.rcv_user;

public class ForgotPassword extends AppCompatActivity {

    EditText edtUsername;
    ProgressBar bar;
    Button btnNext;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity__forgot_password);
        //innit view
        edtUsername = findViewById(R.id.tv_invalidate_username);
        bar = findViewById(R.id.progressabr_forgot);
        btnNext = findViewById(R.id.btn_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bar.setVisibility(View.VISIBLE);
                    userDAO = new UserDAO(ForgotPassword.this);
                    User user = new User(edtUsername.getText().toString());
                    if (userDAO.AuthenticateUsename(user) != null){
                        Intent intent = new Intent(ForgotPassword.this, VerifyPassword.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("UsernameForgot", edtUsername.getText().toString());
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(ForgotPassword.this, "Username không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(ForgotPassword.this, "Không thành công!", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Lỗi đổi mật khẩu: " + e.toString());
                }
            }
        });

    }


}