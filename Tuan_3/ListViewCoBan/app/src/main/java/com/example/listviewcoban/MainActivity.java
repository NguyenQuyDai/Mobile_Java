package com.example.listviewcoban;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextText;
    Button buttonThem,buttonXoa,buttonSua;
    ListView lvMonHoc;
    ArrayList<String> listMonHoc;
    ArrayAdapter adapter;
    int viTri=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMonHoc = findViewById(R.id.lvMonHoc);
        listMonHoc = new ArrayList<>();
        listMonHoc.add("Toán");
        listMonHoc.add("Văn");
        listMonHoc.add("Tiếng Anh");
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,listMonHoc);
        lvMonHoc.setAdapter(adapter);
        editTextText = findViewById(R.id.editTextText);
        buttonThem = findViewById(R.id.buttonThem);
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenMon = editTextText.getText().toString().trim();
                if(!tenMon.equals(""))
                {
                    listMonHoc.add(tenMon);
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this,"Vui lòng tên môn học cần thêm!",Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonSua = findViewById(R.id.buttonSua);
        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editTextText.setText(listMonHoc.get(i));
                viTri = i;
            }
        });
        buttonSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listMonHoc.set(viTri,editTextText.getText().toString().trim());
                adapter.notifyDataSetChanged();
                editTextText.setText("");
            }
        });
        buttonXoa = findViewById(R.id.buttonXoa);
        buttonXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder b =new  AlertDialog.Builder(MainActivity.this);
                b.setTitle("Xóa");
                b.setMessage("Bạn có muốn xóa môn học này?");
                b.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listMonHoc.remove(viTri);
                        adapter.notifyDataSetChanged();
                        editTextText.setText("");
                    }
                });
                b.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        editTextText.setText("");
                    }
                });
                b.create().show();
            }
        });
    }
}