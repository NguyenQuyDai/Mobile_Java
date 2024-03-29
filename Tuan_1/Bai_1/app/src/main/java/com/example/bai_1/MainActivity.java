package com.example.bai_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    LinearLayout lnLayout;
    Switch swtBG;
    TextView textView;
    EditText editText;
    Button btnClickMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swtBG = findViewById(R.id.swtBG);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        lnLayout = findViewById(R.id.lnLayout);
        swtBG.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(swtBG.isChecked())
                {
                   lnLayout.setBackgroundColor(Color.parseColor("#FFFF00"));
                   textView.setText("Vui lòng nhập tên của bạn!");
                   textView.setTextColor(Color.parseColor("#00FF00"));
                }
                else {
                    lnLayout.setBackgroundColor(Color.parseColor("white"));
                    textView.setText("Hello World");
                    textView.setTextColor(Color.parseColor("#F44336"));
                }
            }
        });
        btnClickMe = findViewById(R.id.btnClickMe);
        btnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString().trim();
                if(!name.equals(""))
                {
                    textView.setText("Xin chào "+name);
                }
                else {
                    Toast.makeText(MainActivity.this,"Vui lòng nhập tên!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}