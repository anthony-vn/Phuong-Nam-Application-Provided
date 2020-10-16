package com.anthonycode.bookstorephuongnamaplication.Fragment;

import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import com.anthonycode.bookstorephuongnamaplication.R;


public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText edtUsername, edtPassword, edtConfirmPass, edtEmail;
    private Button btnSignUp;
    private ProgressBar pbSignUp;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        edtUsername = findViewById(R.id.edUsername);
        edtPassword = findViewById(R.id.edPassword);
        edtConfirmPass = findViewById(R.id.edConFirmPassword);
        edtEmail = findViewById(R.id.edEmail);
        btnSignUp = findViewById(R.id.btnSignUp);
        //Firebase
        pbSignUp = findViewById(R.id.ProgressBarSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username_ = edtUsername.getText().toString().trim();
                final String password_ = edtPassword.getText().toString().trim();
                final String email_ = edtEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email_)) {
                    edtEmail.setError("Email is empty!");
                    return;
                }
                if (TextUtils.isEmpty(password_)) {
                    edtPassword.setError("Password is empty!");
                    return;
                }
                if (password_.length() < 6) {
                    edtPassword.setError("Password must be >= 6 characters.");
                    return;
                }

                pbSignUp.setVisibility(View.VISIBLE);

            }
        });
    }

    public void retrunSignUp(View view) {
        finish();
    }

}