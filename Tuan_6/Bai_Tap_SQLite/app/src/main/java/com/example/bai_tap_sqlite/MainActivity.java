package com.example.bai_tap_sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<CongViec> listCV;
    ListView lvCongViec;
    CongViecAdapter adapter;
    String DB_NAME = "mydatabase.sqlite";
    String DB_PATH_SUFFIX = "/databases/";
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvCongViec = findViewById(R.id.lvCongViec);
        listCV = new ArrayList<>();
        adapter = new CongViecAdapter(MainActivity.this,R.layout.cong_viec);
        lvCongViec.setAdapter(adapter);
        database = new Database(this,DB_NAME,null,2);

        // Xóa bảng nếu nó đã tồi tại
        database.queryData("drop table if exists CongViec");
        // Tạo bảng
        database.queryData("CREATE TABLE IF NOT EXISTS CongViec(id integer primary key autoincrement,tencv varchar(255))");

        // Thêm dữ liệu
        database.queryData("INSERT INTO CongViec VALUES(null,'Lap Trinh Android')");
        database.queryData("INSERT INTO CongViec VALUES(null,'Cai dat moi truong lap trinh Android')");
        // Đọc dữ liệu và thêm vào listView
        loadDataCongViec();
    }
    private void loadDataCongViec()
    {
        Cursor dataCongViec = database.getData("SELECT * FROM CongViec");
        adapter.clear();
        CongViec cv;
        while (dataCongViec.moveToNext())
        {
            int idCV = dataCongViec.getInt(0);
            String tenCV = dataCongViec.getString(1);
            cv = new CongViec(idCV,tenCV);
            adapter.add(cv);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cong_viec_mnu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuAddCV)
        {
            ShowThemCVDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void ShowThemCVDialog()
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_cong_viec);
        dialog.setCanceledOnTouchOutside(false);
        EditText edtTenCV = dialog.findViewById(R.id.edtTenCV);
        Button btnThem = dialog.findViewById(R.id.btnThem);
        Button btnHuyThemCV = dialog.findViewById(R.id.btnHuyThemCV);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenCV = edtTenCV.getText().toString().trim();
                if(tenCV.length()>0)
                {
                    database.queryData("INSERT INTO CongViec VALUES (null,'"+tenCV+"')");
                    Toast.makeText(MainActivity.this, "Thêm cv "+tenCV+ " thành công!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    loadDataCongViec();
                }
                else {
                    Toast.makeText(MainActivity.this, "Vui lòng ngập tên môn học", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuyThemCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void SuaCVDialog(CongViec cv)
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sua_cong_viec);
        dialog.setCanceledOnTouchOutside(false);
        TextView txtIdCV = dialog.findViewById(R.id.txtIdCV);
        EditText edtSuaTenCV = dialog.findViewById(R.id.edtSuaTenCV);
        Button btnSuaCV = dialog.findViewById(R.id.btnSuaCV);
        Button btnHuySuaCV = dialog.findViewById(R.id.btnHuySuaCV);
        txtIdCV.setText("ID:"+cv.getIdCV());
        edtSuaTenCV.setText(cv.getTenCV());
        btnSuaCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenCVMoi = edtSuaTenCV.getText().toString().trim();
                if(tenCVMoi.length()>0)
                {
                    database.queryData("UPDATE CongViec SET tenCV='"+tenCVMoi+"' WHERE id='"+cv.getIdCV()+"'");
                    Toast.makeText(MainActivity.this, "Sửa "+cv.toString()+" thành công!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    loadDataCongViec();
                }
                else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên công việc mới", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuySuaCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void ShowXoaCVDialog(CongViec cv)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn thật sự muốn xóa"+cv.toString()+"?");
        builder.setCancelable(false);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database.queryData("DELETE FROM CongViec WHERE id='"+cv.getIdCV()+"'");
                Toast.makeText(MainActivity.this, "Xóa"+cv.toString()+" thành công", Toast.LENGTH_SHORT).show();
                loadDataCongViec();
            }
        });
        builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        builder.show();
    }

}