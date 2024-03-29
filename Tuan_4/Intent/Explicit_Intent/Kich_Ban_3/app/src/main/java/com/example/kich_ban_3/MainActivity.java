package com.example.kich_ban_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextSoN;
    Button btnGui;
    TextView textViewKQ;
    int n = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextSoN = findViewById(R.id.editTextSoN);
        btnGui = findViewById(R.id.btnGui);
        textViewKQ = findViewById(R.id.textViewKQ);
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTinhSoUoc = new Intent(MainActivity.this, MainActivity2.class);
                try {
                    n = Integer.parseInt(editTextSoN.getText().toString().trim());
                    Bundle dulieu = new Bundle();
                    dulieu.putInt("soN", n);
                    intentTinhSoUoc.putExtras(dulieu);
                    startActivityForResult(intentTinhSoUoc, 100);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Vui lòng kiểm tra lại số N", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==101)
        {
            Bundle dulieu = data.getExtras();
            ArrayList<Integer> dsUocSo = dulieu.getIntegerArrayList("dsUocSo");
            textViewKQ.setText("Danh sách ước số của "+n+" là: " + dsUocSo.toString());
        }
    }
}