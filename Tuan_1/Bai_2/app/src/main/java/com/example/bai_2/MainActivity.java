package com.example.bai_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextSoA,editTextSoB;
    TextView textViewKQ;
    Button btnTinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextSoA = findViewById(R.id.editTextSoA);
        editTextSoB = findViewById(R.id.editTextSoB);
        btnTinh = findViewById(R.id.btnTinh);
        textViewKQ = findViewById(R.id.textViewKQ);
        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double a = Double.parseDouble(editTextSoA.getText().toString().trim());
                    double b = Double.parseDouble(editTextSoB.getText().toString().trim());
                    String kq = a+" + "+b+"="+(a+b)+"\n"+
                                a+" - "+b+"="+(a-b)+"\n"+
                                a+"*"+b+"="+(a*b)+"\n"+
                                a+"/"+b+"="+String.format("%1.2f",a/b)+"\n";
                    textViewKQ.setText(kq);
                    textViewKQ.setTextColor(Color.parseColor("#FF0000"));
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this,"Số a và b không hợp lệ!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}