package com.example.luyen_tap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvMonHoc;
    ArrayList<MonHoc> listMH;
    MonHocAdapter adapter;
    EditText editTextMaMH,editTextTenMH;
    String DB_NAME = "MonHoc.sqlite";
    String DB_PATH_SUFFIX = "/databases/";
    public static Database database;
    int REQUEST_CODE = 123,REQUEST_CODE_1=456;
    ImageView imgAnhThem,imgSua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMonHoc = findViewById(R.id.lvMonHoc);
        listMH = new ArrayList<>();
        adapter = new MonHocAdapter(MainActivity.this,R.layout.san_pham);
        lvMonHoc.setAdapter(adapter);
        database = new Database(this,DB_NAME,null,2);
        database.queryData("DROP TABLE IF EXISTS MonHoc");
        database.queryData("CREATE TABLE MonHoc(id integer primary key autoincrement,maMH varchar(255),tenMH varchar(255),hinhanhMH blob)");
        loadMonHoc();
    }
    public void loadMonHoc()
    {
        Cursor dataMonHoc = database.getData("SELECT * FROM MonHoc");
        adapter.clear();
        MonHoc MH;
        while (dataMonHoc.moveToNext())
        {
            int idMH = dataMonHoc.getInt(0);
            String maMH = dataMonHoc.getString(1);
            String tenMH = dataMonHoc.getString(2);
            byte[] hinhAnhSP = dataMonHoc.getBlob(3);
            MH = new MonHoc(idMH,maMH,tenMH,hinhAnhSP);
            adapter.add(MH);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them_sp,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_them)
        {
            showThemMonHoc();
        }
        return super.onOptionsItemSelected(item);
    }
    public void showThemMonHoc()
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.them_mon_hoc);
        dialog.setCanceledOnTouchOutside(false);
        editTextMaMH = dialog.findViewById(R.id.editTextMaMH);
        editTextTenMH = dialog.findViewById(R.id.editTextTenMH);
        imgAnhThem = dialog.findViewById(R.id.imgAnhThem);
        Button btnChonAnh = dialog.findViewById(R.id.btnChonAnh);
        btnChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        Button btnThem = dialog.findViewById(R.id.btnThem);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                 Chuyen image --> byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnhThem.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhanh = byteArrayOutputStream.toByteArray();
                database.INSERT_MONHOC(
                        editTextMaMH.getText().toString().trim(),
                        editTextTenMH.getText().toString().trim(),
                        hinhanh);
                Toast.makeText(MainActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                loadMonHoc();
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK && requestCode == REQUEST_CODE && data!=null)
        {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAnhThem.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if(resultCode==RESULT_OK && requestCode == REQUEST_CODE_1 && data!=null)
        {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgSua.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void SuaMonHoc(MonHoc mh)
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.sua_mon_hoc);
        dialog.setCanceledOnTouchOutside(false);
        TextView textViewID = dialog.findViewById(R.id.textViewID);
        EditText editTextMaMHoc = dialog.findViewById(R.id.editTextMaMHoc);
        EditText editTextTenMHoc = dialog.findViewById(R.id.editTextTenMHoc);
        imgSua = dialog.findViewById(R.id.imgSua);
        Button btnSua = dialog.findViewById(R.id.btnSua);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        Button btnChonAnhSua = dialog.findViewById(R.id.btnChonAnhSua);
        editTextMaMHoc.setText(mh.getMaMH());
        editTextTenMHoc.setText(mh.getTenMH());
        textViewID.setText("ID:"+mh.getId());
        btnChonAnhSua.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_1);
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maMHMoi = editTextMaMHoc.getText().toString().trim();
                String tenMHMoi = editTextTenMHoc.getText().toString().trim();
                // Chuyển image --> byte
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgSua.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhanh = byteArrayOutputStream.toByteArray();
                if(maMHMoi.length()>0 && tenMHMoi.length()>0)
                {
                    database.queryData("UPDATE MonHoc SET maMH='"+maMHMoi+"',tenMH='"+tenMHMoi+"',hinhanhMH='"+hinhanh+"' WHERE id='"+mh.getId()+"'");
                    Toast.makeText(MainActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    loadMonHoc();
                }
                else {
                    Toast.makeText(MainActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}