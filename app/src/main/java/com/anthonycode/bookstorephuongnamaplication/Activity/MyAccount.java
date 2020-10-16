package com.anthonycode.bookstorephuongnamaplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.anthonycode.bookstorephuongnamaplication.R;

public class MyAccount extends AppCompatActivity {
    TextView tvUsername1, tvUsername2, tvPassword, tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        tvUsername1 = findViewById(R.id.tv_InforUsername);
        tvUsername2 = findViewById(R.id.tv_InforUsername2);
        tvPassword = findViewById(R.id.tv_InforPassword);
        tvPhone = findViewById(R.id.tv_InforPhone);

        //Get bundle
        Intent i = getIntent();
        Bundle mArgs = i.getExtras();
        String user1 = mArgs.get("username_user").toString();
        String phone = mArgs.get("phone_user").toString();
        String pass = mArgs.get("password_user").toString();
        tvUsername1.setText(user1);
        tvUsername2.setText(user1);
        tvPassword.setText(pass);
        tvPhone.setText(phone);
    }

    public void back(View view) {
        finish();
    }
}