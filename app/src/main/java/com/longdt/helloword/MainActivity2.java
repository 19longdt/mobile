package com.longdt.helloword;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        int startColor = 0xffB762C1;
        int endColor = 0xffF2789F;
        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {startColor, endColor});

        View myView = findViewById(R.id.view);
        myView.setBackgroundDrawable(gradientDrawable);
    }
}