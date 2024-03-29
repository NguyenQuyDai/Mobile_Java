package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvSanPham;
    ArrayList<SanPham> listSP;
    SanPhamAdapter sanPhamAdapter;
    EditText editTextTenSP,editTextGiaSP;
    ImageView imageView,imageViewSua;
    int REQUEST_CODE = 456;
    int REQUEST_CODE_1 = 123;
    String DB_NAME = "SanPham.sqlite";
    String DB_PATH_SUFFIX = "/databases/";
    public static Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvSanPham = findViewById(R.id.lvSanPham);
        listSP = new ArrayList<>();
        sanPhamAdapter = new SanPhamAdapter(MainActivity.this,R.layout.san_pham);
        lvSanPham.setAdapter(sanPhamAdapter);
        database = new Database(this,DB_NAME,null,2);
        database.QueryData("DROP TABLE IF EXISTS SanPham");
        database.QueryData("CREATE TABLE IF NOT EXISTS SanPham(id integer primary key autoincrement,tenSP varchar(255),giaSP varchar(255), hinhanhSP blob)");
        loadDataSanPham();
    }
        public void loadDataSanPham() {
        Cursor dataSanPham = database.getData("SELECT * FROM SanPham");
        sanPhamAdapter.clear();
        SanPham sp;
         while (dataSanPham.moveToNext())
         {
            int idSP = dataSanPham.getInt(0);
            String tenSP = dataSanPham.getString(1);
            String giaSP = dataSanPham.getString(2);
            byte[] hinh  = dataSanPham.getBlob(3);
            sp = new SanPham(idSP,tenSP,giaSP,hinh);
            sanPhamAdapter.add(sp);
         }
         sanPhamAdapter.notifyDataSetChanged();
  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.diag_them_san_pham,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuAddSP)
        {
            ShowThemSP();
        }
        return super.onOptionsItemSelected(item);
    }
    public void ShowThemSP()
    {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_them_san_pham);
        dialog.setCanceledOnTouchOutside(false);
        editTextTenSP = dialog.findViewById(R.id.editTextTenSP);
        editTextGiaSP = dialog.findViewById(R.id.editTextGiaSP);
        Button btnChonAnh = dialog.findViewById(R.id.btnChonAnh);
        imageView = dialog.findViewById(R.id.imageView);
        Button btnThem = dialog.findViewById(R.id.btnThem);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        btnChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // chuyển image -->byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhanh = byteArrayOutputStream.toByteArray();
                database.INSERT_SANPHAM(
                        editTextTenSP.getText().toString().trim(),
                        editTextGiaSP.getText().toString().trim(),
                        hinhanh);
                Toast.makeText(MainActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                loadDataSanPham();
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK&&data!=null)
        {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if(requestCode==REQUEST_CODE_1 && resultCode==RESULT_OK&&data!=null)
        {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewSua.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void SuaSanPham(SanPham sp)
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.sua_san_pham);
        dialog.setCanceledOnTouchOutside(false);
        TextView textViewID = dialog.findViewById(R.id.textViewID);
        EditText editTextTenSP = dialog.findViewById(R.id.editTextTenSP);
        EditText editTextGiaSP = dialog.findViewById(R.id.editTextGiaSP);
        Button btnChonAnh = dialog.findViewById(R.id.btnChonAnh);
        imageViewSua = dialog.findViewById(R.id.imageViewSua);
        Button btnSua = dialog.findViewById(R.id.btnSua);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        textViewID.setText("ID:"+sp.getId());
        editTextTenSP.setText(sp.getTenSP());
        editTextGiaSP.setText(sp.getGiaSP());
        btnChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_1);
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenSPMoi = editTextTenSP.getText().toString().trim();
                String giaSPMoi = editTextGiaSP.getText().toString().trim();
                // chuyển image-->byte
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageViewSua.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhanh = byteArrayOutputStream.toByteArray();
                if(tenSPMoi.length()>0&&giaSPMoi.length()>0)
                {
                database.QueryData("UPDATE SanPham SET tenSP='"+tenSPMoi+"',giaSP='"+giaSPMoi+"',hinhanhSP='"+hinhanh+"' WHERE id='"+sp.getId()+"'");
                Toast.makeText(MainActivity.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                loadDataSanPham();
                }
                else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
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
    public void XoaSP(SanPham sp)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa sản phẩm này?");
        builder.setCancelable(false);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database.QueryData("DELETE FROM SanPham WHERE id='"+sp.getId()+"'");
                Toast.makeText(MainActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                loadDataSanPham();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}});
        builder.show();
    }
}