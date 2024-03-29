package com.example.kich_ban_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton imgBtnShareImg,imgBtnShareVanBan;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgBtnShareImg = findViewById(R.id.imgBtnShareImg);
        imgBtnShareVanBan = findViewById(R.id.imgBtnShareVanBan);
        textView = findViewById(R.id.textView);
        imgBtnShareVanBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,textView.getText().toString());
                intent.setType("text/plain");

                Intent intent1 = Intent.createChooser(intent,null);
                startActivity(intent1);
            }
        });
        imgBtnShareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri path = Uri.parse("android.resource://com.example.kich_ban_4/"+R.drawable.fit);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_STREAM,path);
                intent.setType("image/jpg");
                startActivity(Intent.createChooser(intent,null));
            }
        });
    }
}