package com.example.listviewnangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listviewnangcao.Adapter.SanPhamAdapter;
import com.example.listviewnangcao.Models.SanPham;

public class MainActivity extends AppCompatActivity {

    ListView lvSanPham;
    SanPhamAdapter sanPhamAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvSanPham = findViewById(R.id.lvSanPham);
        sanPhamAdapter = new SanPhamAdapter(MainActivity.this, R.layout.sanpham);
        sanPhamAdapter.add(new SanPham("SP001","Đồng hồ",300000,R.drawable.donghoc));
        sanPhamAdapter.add(new SanPham("SP002","Đèn học",15000, R.drawable.denhoc));
        sanPhamAdapter.add(new SanPham("SP003","Ti Vi",19000000,R.drawable.tv));
        sanPhamAdapter.add(new SanPham("SP004","Quạt",500000,R.drawable.quat));
        sanPhamAdapter.add(new SanPham("SP005","Nồi Cơm điện",700000,R.drawable.noicomdien));
        lvSanPham.setAdapter(sanPhamAdapter);
        lvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
