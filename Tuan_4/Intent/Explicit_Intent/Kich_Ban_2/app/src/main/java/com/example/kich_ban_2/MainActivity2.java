package com.example.kich_ban_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textViewKQ;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textViewKQ = findViewById(R.id.textViewKQ);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();                // Lấy intent được start từ Main 1
        Bundle bundle = intent.getExtras();         // Lấy dữ liệu
        double a = bundle.getDouble("soA");
        double b = bundle.getDouble("soB");
        String kq = a+" + "+b +" = "+(a+b)+"\n"+
                    a+" - "+b +" = "+(a-b)+"\n"+
                    a+" * "+b +" = "+(a*b)+"\n"+
                    a+" / "+b +" = "+String.format("%1.2f",a/b)+"\n";
        textViewKQ.setText(kq);
    }

}