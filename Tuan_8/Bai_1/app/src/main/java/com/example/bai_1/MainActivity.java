package com.example.bai_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvStatus;
    MyBroadcast broadcast = new MyBroadcast();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(broadcast,filter);
        tvStatus = findViewById(R.id.tvStatus);
    }
    protected void onDestroy() {

        super.onDestroy();
        unregisterReceiver(broadcast);
    }
}