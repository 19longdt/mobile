package com.longdt.helloword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.longdt.helloword.ProductC.ProductDetail;
import com.longdt.helloword.ProductC.ProductHome;
import com.longdt.helloword.entity.Account;
import com.longdt.helloword.menu.MenuOptions;
import com.longdt.helloword.mutilThread.MutilThread;

public class Home extends AppCompatActivity {

    private Account account;
    private Button btnAS;
    private Button btnLogout;
    private Button btnMT;


    private void bindingView() {
        btnAS = findViewById(R.id.btnAS);
        btnLogout = findViewById(R.id.btnLogout);
        btnMT = findViewById(R.id.btnMThread);
    }


    private void bindingDataFromIntent() {
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");

    }

    private void bindingAction() {
        bindingDataFromIntent();
        btnLogout.setOnClickListener(view -> {
            btnLogoutAction(view);
        });
        btnAS.setOnClickListener(view -> {
            bindingAction(view);
        });
        btnMT.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, MutilThread.class);
            startActivity(intent);
        });
    }

    private void bindingAction(View view){
        Intent intent = new Intent(Home.this, ProductHome.class);
        startActivity(intent);
    }

    private void btnLogoutAction(View view){
        if(account != null){
            SharedPreferences preferences = getSharedPreferences("account", 0);
            preferences.edit().remove("username").commit();
            preferences.edit().remove("password").commit();
            Intent intent = new Intent(Home.this, LoginActivity.class);
            startActivity(intent);
        }else{

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bindingView();
        bindingAction();
    }

}