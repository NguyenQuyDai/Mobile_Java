package com.example.bai_4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextChieuCao,editTextCanNang;
    Button button;
    TextView textViewKQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextChieuCao = findViewById(R.id.editTextChieuCao);
        editTextCanNang = findViewById(R.id.editTextCanNang);
        button = findViewById(R.id.button);
        textViewKQ = findViewById(R.id.textViewKQ);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double chieuCao = Double.parseDouble(editTextChieuCao.getText().toString().trim());
                    double canNang = Double.parseDouble(editTextCanNang.getText().toString().trim());
                    double bmi = canNang/(chieuCao*chieuCao);
                    textViewKQ.setText("BMI của bạn là:"+String.format("%1,2f,bmi"));
                    if(bmi<18.5)
                    {
                        textViewKQ.setText(textViewKQ.getText().toString()+"\nHơi gầy");
                        textViewKQ.setTextColor(Color.parseColor("#66FFFF"));
                    }
                    else if(bmi>=18.5&&bmi<25)
                    {
                        textViewKQ.setText(textViewKQ.getText().toString()+"\nBình thường");
                        textViewKQ.setTextColor(Color.parseColor("#99FF99"));
                    }
                    else if(bmi>=25&&bmi<30)
                    {
                        textViewKQ.setText(textViewKQ.getText().toString()+"\nThừa cân");
                        textViewKQ.setTextColor(Color.parseColor("FFFFEB3B"));
                    }
                    else if(bmi>=30&&bmi<35)
                    {
                        textViewKQ.setTextColor(Color.parseColor("#FF7F00"));
                        textViewKQ.setText(textViewKQ.getText().toString()+"\nBéo phì");
                    }
                    else {
                        textViewKQ.setText(textViewKQ.getText().toString()+"Quá béo");
                        textViewKQ.setTextColor(Color.parseColor("#FF0000"));
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this,"Vui lòng nhập cân nặng,chiều cao!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}