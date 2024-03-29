package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class xemchitiet extends AppCompatActivity {

    TextView tvTenCV,tvMaCV , tvDate,tvNoiDungCV;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemchitiet);
        tvTenCV = findViewById(R.id.tvTenCV);
        tvMaCV = findViewById(R.id.tvMaCV);
        tvNoiDungCV = findViewById(R.id.tvNoiDungCV);
        tvDate = findViewById(R.id.tvDate);
        button = findViewById(R.id.button);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        CongViec cv = (CongViec) bundle.get("cv_value");
        tvMaCV.setText(cv.getMaCV());
        tvTenCV.setText(cv.getTenCV());
        tvNoiDungCV.setText(cv.getNoiDungCV());
        tvDate.setText(dateFormat.format(cv.getThoiHan()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}