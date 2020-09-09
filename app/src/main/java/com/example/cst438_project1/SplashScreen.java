package com.example.cst438_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    TextView mTopText;
    TextView mBottomText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mTopText = findViewById(R.id.AppName);
        mBottomText = findViewById(R.id.AppName2);

        mTopText.setTextColor(Color.rgb(246,244,241));
        mBottomText.setTextColor(Color.rgb(246,244,241));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);

                finish();
            }
        },3000);
    }
}