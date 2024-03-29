package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.myapplication.Adapter.ThietBiAdapter;
import com.example.myapplication.SQL.Database;
import com.example.myapplication.models.ThietBi;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvThietBi;
    ArrayList<ThietBi> listThietBi;
    ArrayAdapter adapter;
    private Bitmap newImageBitmap = null;
    String DB_NAME = "ThietBi.db";
    String DB_PATH_SUFFIX = "/databases/";
    public static Database database;
    ImageView imgAnhThem;
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = findViewById(R.id.viewFlipper);

        // Thiết lập chuyển đổi tự động giữa các view
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000); // Thời gian chờ giữa các chuyển đổi (2 giây)

        // Khai báo các sự kiện chuyển đổi
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);

        lvThietBi = findViewById(R.id.lvThietBi);
        listThietBi = new ArrayList<>();
        adapter = new ThietBiAdapter(MainActivity.this,R.layout.thiet_bi,listThietBi);
        lvThietBi.setAdapter(adapter);
        database = new Database(this,2);
        database.getWritableDatabase(); // Tạo hoặc mở cơ sở dữ liệu
        loadThietBi();
    }

    public void previousView(View view) {
        viewFlipper.showPrevious();
    }

    public void nextView(View view) {
        viewFlipper.showNext();
    }
    public void loadThietBi(){
        Cursor cursor = database.getData("SELECT * FROM ThietBi");
        adapter.clear();
        ThietBi thietBi;
        while (cursor.moveToNext()){
            int ID = cursor.getInt(0);
            String maTB = cursor.getString(1);
            String tenTB = cursor.getString(2);
            double giaTB = cursor.getDouble(3);
            byte[] hinhAnhTB = cursor.getBlob(4);
            thietBi = new ThietBi(ID,maTB,tenTB,giaTB,hinhAnhTB);
            adapter.add(thietBi);
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.them_thiet_bi,menu);
        MenuItem menuItem = menu.findItem(R.id.timKiem);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchView.clearFocus();
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.themThietBi){
            showThemSP();
        }
        return super.onOptionsItemSelected(item);
    }
    public void showThemSP(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.them_thiet_bi);
        dialog.setCanceledOnTouchOutside(false);
        EditText edtMaTB = dialog.findViewById(R.id.edtMaTB);
        EditText edtTenTB = dialog.findViewById(R.id.edtTenTB);
        EditText edtGiaTB = dialog.findViewById(R.id.edtGiaTB);
        imgAnhThem = dialog.findViewById(R.id.imgAnhThem);
        ImageButton btnChupAnh = dialog.findViewById(R.id.btnChupAnh);
        btnChupAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,123);
            }
        });
        ImageButton btnChonAnh = dialog.findViewById(R.id.btnChonAnh);
        btnChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,456);
            }
        });
        Button btnThem = dialog.findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển từ image --> byte
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgAnhThem.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                String maTB = edtMaTB.getText().toString().trim();
                String tenTB = edtTenTB.getText().toString().trim();
                double giaTB = Double.parseDouble(edtGiaTB.getText().toString().trim());
                byte[] hinhanh = byteArrayOutputStream.toByteArray();
                if(maTB.isEmpty()||tenTB.isEmpty()||giaTB<0){
                    Toast.makeText(MainActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    long id =  database.INSERT_THIETBI(maTB,tenTB, giaTB, hinhanh);
                    if(id==-1){
                        Toast.makeText(MainActivity.this, "Mã thiết bị đã tồn tại! Vui lòng thử lại", Toast.LENGTH_SHORT).show();}
                    else {
                        Toast.makeText(MainActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    }
                }
                dialog.dismiss();
                loadThietBi();
            }
        });
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==123 && resultCode ==RESULT_OK && data!=null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgAnhThem.setImageBitmap(bitmap);
            newImageBitmap = bitmap;
        }
        if (requestCode==456 && resultCode ==RESULT_OK && data!=null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAnhThem.setImageBitmap(bitmap);
                newImageBitmap = bitmap;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void xoaThietBi(ThietBi tb){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa Thiết Bị");
        builder.setMessage("Bạn có muốn xóa thiết bị này?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("DELETE FROM ThietBi Where id ='"+tb.getID()+"'");
                Toast.makeText(MainActivity.this, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                loadThietBi();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        builder.show();
    }
    public void suaThietBi(ThietBi tb) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.sua_thiet_bi);
        TextView txtID = dialog.findViewById(R.id.txtID);
        EditText edtMaTB = dialog.findViewById(R.id.edtMaTB);
        EditText edtTenTB = dialog.findViewById(R.id.edtTenTB);
        EditText edtGiaTB = dialog.findViewById(R.id.edtGiaTB);
        ImageButton btnChupAnh = dialog.findViewById(R.id.btnChupAnh);
        ImageButton btnChonAnh = dialog.findViewById(R.id.btnChonAnh);
        imgAnhThem = dialog.findViewById(R.id.imgAnhThem);
        txtID.setText("ID:" + tb.getID());
        edtMaTB.setText(tb.getMaTB());
        edtTenTB.setText(tb.getTenTB());
        edtGiaTB.setText("" + tb.getGiaTB());
        byte[] hinhanh = tb.getHinhAnhTB();
        // BitmapFactory.decodeByteArray: Phương thức này được sử dụng để giải mã một mảng byte thành một
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
        imgAnhThem.setImageBitmap(bitmap);

        btnChupAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 123);
            }
        });
        btnChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 456);
            }
        });
        Button btnThem = dialog.findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maTB = edtMaTB.getText().toString().trim();
                String tenTB = edtTenTB.getText().toString().trim();
                float giaTB = Float.parseFloat(edtGiaTB.getText().toString().trim());

                if (maTB.length() > 0 && tenTB.length() > 0 && giaTB > 0) {
                    byte[] newHinhanh = null;
                    // Nếu newImageBitmap có ảnh mới, cập nhật hình ảnh mới vào cơ sở dữ liệu
                    if (newImageBitmap != null) {
                        // Chuyển từ image --> byte
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        newImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        newHinhanh = byteArrayOutputStream.toByteArray();
                    }

                    // Cập nhật dữ liệu trong cơ sở dữ liệu
                    String updateQuery = "UPDATE ThietBi SET maTB = '" + maTB + "', tenTB = '" + tenTB + "', giaTB = " + giaTB;
                    if (newHinhanh != null) {
                        updateQuery += ", hinhanhTB = ?";
                    }
                    updateQuery += " WHERE id = " + tb.getID();

                    if (newHinhanh != null) {
                        database.UpdateWithImage(updateQuery, newHinhanh);
                    } else {
                        database.QueryData(updateQuery);
                    }

                    // Cập nhật đối tượng ThietBi tương ứng
                    tb.setMaTB(maTB);
                    tb.setTenTB(tenTB);
                    tb.setGiaTB(giaTB);
                    if (newHinhanh != null) {
                        tb.setHinhAnhTB(newHinhanh);
                    }

                    adapter.notifyDataSetChanged();
                    // Cập nhật ListView và thông báo thành công
                    Toast.makeText(MainActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
