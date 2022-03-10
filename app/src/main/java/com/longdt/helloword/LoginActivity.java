package com.longdt.helloword;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.longdt.helloword.entity.Account;
import com.longdt.helloword.menu.MenuOptions;

import java.io.Serializable;
import java.util.Scanner;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;
    private CheckBox cbRemember;
    private Button btnLogin;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private void bindingView() {
        txtUsername = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        cbRemember = findViewById(R.id.cbRem);
        btnLogin = findViewById(R.id.btnLogin);

        preferences = getApplicationContext().getSharedPreferences("account", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    private void bindingAction() {
        btnLogin.setOnClickListener(view -> {
            btnLoginAction(view);
        });

        btnLogin.setOnLongClickListener(view -> {
            Toast.makeText(this, txtUsername.getText().toString() + " - " + txtPassword.getText().toString(), Toast.LENGTH_LONG).show();
            return true;
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            bindingView();
            bindingAction();
    }

    public void btnLoginAction(View view) {

        String userName = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        String message = checkLogin(userName, password) ? "Login successfully :D" : "Login Fail :(";
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        if (checkLogin(userName, password)) {
//            Uri uri = Uri.parse("http://fb.com");
//            Intent it = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(it);
//            Uri uri = Uri.parse("tel:0866968198");
//            Intent it = new Intent(Intent.ACTION_DIAL, uri);
//            startActivity(it);

            Account account = new Account(userName, password, "longdt");
            if(cbRemember.isChecked()){
                editor.putString("username", userName);
                editor.putString("password", password);
                editor.apply();
            }
            Intent intent = new Intent(LoginActivity.this, Home.class);
            intent.putExtra("account", account);
            startActivity(intent);
//            finish();
        } else {

        }
//        txtResult.setText(txtUsername + " - " + txtPassword);

//        Log.d("btnLoginAction","txtUsername + \" - \" + txtPassword");
    }

    @Override
    protected void onResume() {
        super.onResume();
        preferences = getSharedPreferences("account", Context.MODE_PRIVATE);
        String userName = preferences.getString("username", null);
        String password = preferences.getString("password", null);
        if(userName != null && password != null){
            Intent intent = new Intent(LoginActivity.this, Home.class);
            intent.putExtra("account", new Account(userName, password, "longdt"));
            startActivity(intent);
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 1){
//            String name = data.getStringExtra("result");
//            Log.i("name", name);
//            Toast.makeText(this,name + " logout successfull", Toast.LENGTH_LONG).show();
//        }
//    }

    private boolean checkLogin(String userName, String password) {
        return userName.equals("a") && password.equals("a");
    }

}