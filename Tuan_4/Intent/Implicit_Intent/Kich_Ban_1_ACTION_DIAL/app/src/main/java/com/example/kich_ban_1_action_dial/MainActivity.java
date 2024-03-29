package com.example.kich_ban_1_action_dial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextText;
    Button btnCall;
    String sdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCall = findViewById(R.id.btnCall);
        editTextText = findViewById(R.id.editTextText);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdt = editTextText.getText().toString().trim();
                if(!sdt.equals(""))
                {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+sdt));
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,"Vui lòng kiểm tra lại!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}