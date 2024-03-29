package com.example.kich_ban_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextSoA,editTextSoB;
    Button btnGuiDL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view();
        Event();
    }
    private  void view()
    {
        this.btnGuiDL = findViewById(R.id.btnGuiDL);
        this.editTextSoA = findViewById(R.id.editTextSoA);
        this.editTextSoB = findViewById(R.id.editTextSoB);
    }
    private void Event()
    {
        btnGuiDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentKQ = new Intent(MainActivity.this, MainActivity2.class);
                try {
                        double a = Double.parseDouble(editTextSoA.getText().toString().trim());
                        double b = Double.parseDouble(editTextSoB.getText().toString().trim());
                        Bundle dulieu = new Bundle();   // Dùng Bundle để lưu dữ liệu
                        dulieu.putDouble("soA",a);      // Nén dữ liệu vào Bundle
                        dulieu.putDouble("soB",b);
                        intentKQ.putExtras(dulieu);     // Lưu Bundle vào Intent
                        startActivity(intentKQ);
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this,"Vui lòng kiểm tra lại số a và số b!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }
}