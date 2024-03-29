package com.example.kich_ban_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    TextView txtSoN;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtSoN = findViewById(R.id.txtSoN);
        btnBack = findViewById(R.id.btnBack);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int n = bundle.getInt("soN",0);
        txtSoN.setText(n+"");
        btnBack.setText("Trả về ước số của "+n);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> listUS = new ArrayList<>();
                if(n>0)
                {
                    for(int i = 1; i <= n ; i ++)
                    {
                        if(n%i==0)
                        {
                            listUS.add(i);
                        }
                    }
                }
                else {
                    Toast.makeText(MainActivity2.this,"Số N không hợp lệ!",Toast.LENGTH_LONG).show();
                }
                Toast.makeText(MainActivity2.this,listUS.toString(),Toast.LENGTH_LONG).show();
                bundle.putIntegerArrayList("dsUocSo",listUS);
                intent.putExtras(bundle);
                setResult(101,intent);
                finish();
            }
        });
    }
}