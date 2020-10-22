package com.anthonycode.bookstorephuongnamaplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class VerifyPassword extends AppCompatActivity {
    EditText edtVerifyNew, getEdtVerifyPass;
    ProgressBar bar;
    Button btnFinish;
    UserDAO userDAO;
    List<User> ds_gd;
    Adapter_User adapter_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_verify_password);
        btnFinish = findViewById(R.id.btn_verifyPassword);
        edtVerifyNew = findViewById(R.id.verifyNewPass);
        getEdtVerifyPass = findViewById(R.id.verifyPass);
        bar = findViewById(R.id.progressabr_verify);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.setVisibility(View.VISIBLE);
                String getNewPass = edtVerifyNew.getText().toString().trim();
                String getPass = getEdtVerifyPass.getText().toString().trim();
                try {
                    if (getNewPass.equalsIgnoreCase(getPass)) {
                        //Get bundle
                        Bundle bundle = getIntent().getExtras();
                        String getUsernameX = bundle.getString("UsernameForgot");

                        userDAO = new UserDAO(VerifyPassword.this);
                        User user = new User(getUsernameX, getPass);
                        userDAO.updateUserWithUsername(user);
                        capnhat();
                        Toast.makeText(VerifyPassword.this, "Thành công!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(VerifyPassword.this, Login.class));
                        finish();
                    } else {
                        Toast.makeText(VerifyPassword.this, "Không thành công!", Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.INVISIBLE);
                    }
                } catch (Exception e) {
                    Toast.makeText(VerifyPassword.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                    bar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }


    public void capnhat() {
        ds_gd = userDAO.getAllUser();
        adapter_category = new Adapter_User(ds_gd, VerifyPassword.this);
        rcv_user.setAdapter(adapter_category);
    }
}