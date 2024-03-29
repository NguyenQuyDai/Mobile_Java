package com.example.bai_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton rdtA,rdtB,rdtC,rdtD;
    Button  buttonTraLoi,buttonDapAn;
    TextView textViewKQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View();
        Event();
    }
    public  void View()
    {
        radioGroup = findViewById(R.id.radioGroup);
        rdtA = findViewById(R.id.rdtA);
        rdtB = findViewById(R.id.rdtB);
        rdtC = findViewById(R.id.rdtC);
        rdtD = findViewById(R.id.rdtD);
        buttonTraLoi = findViewById(R.id.buttonTraLoi);
        buttonDapAn = findViewById(R.id.buttonDapAn);
        textViewKQ = findViewById(R.id.textViewKQ);
    }
    public void Event() {
        buttonTraLoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdtA.isChecked())
                {
                    textViewKQ.setText("Bạn chọn :BangKok");
                }
                if(rdtB.isChecked())
                {
                    textViewKQ.setText("Bạn chọn : Hà Nội");
                }
                if(rdtC.isChecked())
                {
                    textViewKQ.setText("Bạn chọn : Thành Phố Hồ Chí Minh");
                }
                if(rdtD.isChecked())
                {
                    textViewKQ.setText("Bạn chọn : LonDon");
                }
            }
        });
        buttonDapAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdtB.isChecked())
                {
                    textViewKQ.setText("Bạn đã chọn đúng!");
                    textViewKQ.setTextColor(Color.parseColor("#4CAF50"));
                    rdtB.setBackgroundColor(Color.parseColor("#4CAF50"));
                }
                if(rdtA.isChecked()||rdtC.isChecked()||rdtD.isChecked())
                {
                    textViewKQ.setText("Bạn đã chọn Sai!");
                    textViewKQ.setTextColor(Color.parseColor("#F44336"));
                    rdtB.setBackgroundColor(Color.parseColor("#4CAF50"));
                }
            }
        });
    }
}