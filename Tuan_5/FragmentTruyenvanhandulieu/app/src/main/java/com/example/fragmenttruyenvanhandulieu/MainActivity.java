package com.example.fragmenttruyenvanhandulieu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout lnLayout;
    Button btnAdd;
    EditText editTextText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lnLayout = findViewById(R.id.lnLayout);
        btnAdd = findViewById(R.id.btnAdd);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentA fragmentA = new FragmentA();
                Bundle bundle = new Bundle();
                bundle.putString("hoVaTen","Nguyen Van A");
                fragmentA.setArguments(bundle);
                fragmentTransaction.add(R.id.lnLayout,fragmentA);
                fragmentTransaction.commit();
            }
        });
    }
}