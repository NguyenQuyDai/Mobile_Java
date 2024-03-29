package com.example.bai_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton rdtNam,rdtNu;
    EditText editTextName,editTextDate,editTextPhone;
    CheckBox checkBox,checkBox2,checkBox3;
    TextView txtViewThanhTien,txtViewKQ;
    Button button;
    String gioitinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
        addEvent();
    }
    public  void addView()
    {
        radioGroup =findViewById(R.id.radioGroup);
        rdtNam = findViewById(R.id.rdtNam);
        rdtNu = findViewById(R.id.rdtNu);
        editTextName = findViewById(R.id.editTextName);
        editTextDate = findViewById(R.id.editTextDate);
        editTextPhone = findViewById(R.id.editTextPhone);
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        txtViewThanhTien = findViewById(R.id.txtViewThanhTien);
        txtViewKQ = findViewById(R.id.txtViewKQ);
        button = findViewById(R.id.button);
    }
    public  void addEvent()
    {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(rdtNam.isChecked())
                {
                    gioitinh = "Nam";
                }
                else if(rdtNu.isChecked())
                {
                    gioitinh = "Nữ";
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();
                double tien=0;
                if(checkBox.isChecked())
                {
                    tien=tien+200000;
                }
                if(checkBox2.isChecked())
                {
                    tien=tien+350000;
                }
                if(checkBox3.isChecked())
                {
                    tien=tien+1000000;
                }
                txtViewThanhTien.setText("Thành tiền:"+tien+" VNĐ");
                txtViewKQ.setText("Kết quả:"+"\nHọ tên:"+name+"\nNgày sinh:"+date+"\nSố điện thoại:"+phone+"\nGiới tính:"+gioitinh);
                txtViewKQ.setTextColor(Color.parseColor("red"));
            }
        });

    }
}