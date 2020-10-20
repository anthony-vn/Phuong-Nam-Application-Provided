package com.anthonycode.bookstorephuongnamaplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.anthonycode.bookstorephuongnamaplication.R;

public class MyAccount extends AppCompatActivity {
    TextView tvFullname, tvFullname1, tvUsername, tvPassword, tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        tvFullname = findViewById(R.id.tv_InforFullname);
        tvFullname1 = findViewById(R.id.tv_InforFullname1);
        tvUsername = findViewById(R.id.tv_InforUsername);
        tvPassword = findViewById(R.id.tv_InforPassword);
        tvPhone = findViewById(R.id.tv_InforPhone);

        try {
            //Get bundle
            Intent i = getIntent();
            Bundle mArgs = i.getExtras();
            String usn = mArgs.get("username_user").toString();
            String phone = mArgs.get("phone_user").toString();
            String pass = mArgs.get("password_user").toString();
            String fullname = mArgs.get("fullname_user").toString();
            tvFullname.setText(fullname);
            tvFullname1.setText(fullname);
            tvUsername.setText(usn);
            tvPassword.setText(pass);
            tvPhone.setText(phone);
        } catch (Exception e) {
            Log.d("TAG", "Error show account == " + e.toString());
        }
    }

    public void back(View view) {
        finish();
    }
}