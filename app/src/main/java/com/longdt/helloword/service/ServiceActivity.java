package com.longdt.helloword.service;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.longdt.helloword.Home;
import com.longdt.helloword.R;

public class ServiceActivity extends AppCompatActivity {
    private Button btnStartService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        btnStartService = findViewById(R.id.btnStartService);

        btnStartService.setOnClickListener(view -> {
            Intent intent = new Intent(ServiceActivity.this, MyService.class);
            startService(intent);
        });
    }
}
