package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ListView lvCongViec;
    ArrayList<CongViec> listCV;
    CongViecAdapter congViecAdapter;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvCongViec = findViewById(R.id.lvCongViec);
        listCV = new ArrayList<>();
        database = new Database(getBaseContext(),getString(R.string.db_name),null,1);
        String query = "CREATE TABLE IF NOT EXISTS congviec (" +
                "    [key]     INTEGER  PRIMARY KEY AUTOINCREMENT," +
                "    maCV      CHAR," +
                "    tenCV     CHAR," +
                "    noiDungCV CHAR," +
                "    thoiHan   DATETIME" +
                ")";
        database.queryData(query);

        query = "INSERT INTO congviec (maCV, tenCV, noiDungCV, thoiHan) VALUES ('CV001', 'Học Tiếng Anh', 'Luyện Thi VSTEP', '2023-10-18 16:50:05');" ;
        database.queryData(query);
        congViecAdapter = new CongViecAdapter(MainActivity.this,R.layout.lv_congviec,listCV);
        lvCongViec.setAdapter(congViecAdapter);
        loadDataCongViec();
        lvCongViec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(),xemchitiet.class);
                Bundle bundle = new Bundle();
                CongViec cv = listCV.get(position);
                bundle.putSerializable("cv_value",cv);
                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(MainActivity.this, listCV.get(position).getTenCV(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadDataCongViec(){
        String sql = "SELECT * FROM congviec";
        Cursor cursor = database.getData(sql);
        listCV.clear();
        while (cursor.moveToNext()){
            try {
                int key = cursor.getInt(0);
                String maCV = cursor.getString(1);
                String tenCV = cursor.getString(2);
                String noiDungCV = cursor.getString(3);
                String thoiHan = cursor.getString(4);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dateThoiHan = dateFormat.parse(thoiHan);
                CongViec cv = new CongViec(key,maCV,tenCV,noiDungCV,dateThoiHan);
                listCV.add(cv);
            } catch (ParseException e) {
                Log.d("Lỗi đọc dữ liệu",e.toString());
                Toast.makeText(this, ""+e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        congViecAdapter.notifyDataSetChanged();
    }
}
