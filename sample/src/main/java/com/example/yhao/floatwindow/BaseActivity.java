package com.example.yhao.floatwindow;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yhao.fixedfloatwindow.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);
    }
}
