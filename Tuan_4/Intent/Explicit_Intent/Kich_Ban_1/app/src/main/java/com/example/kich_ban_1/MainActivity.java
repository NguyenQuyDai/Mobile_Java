package com.example.kich_ban_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonManHinh2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonManHinh2 = findViewById(R.id.btnManHinh2);
        buttonManHinh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentManHinh2 = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intentManHinh2);
            }
        });
    }
}