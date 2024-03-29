package com.example.baitapnhom;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import com.example.baitapnhom.adapter.SanPhamAdapter;
import com.example.baitapnhom.models.SanPham;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    ListView lvSanPham;
    SanPhamAdapter adapter;
    ArrayList<SanPham> listSanPham;
    Button btnThemSP;
    ImageView imgAnhThem,imgAnhCu;
    int REQUEST_CODE = 123,REQUEST_CODE_1=456;
    private String imgPath,imgPath1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvSanPham = findViewById(R.id.lvSanPham);
        listSanPham = new ArrayList<>();
        listSanPham.add(new SanPham("SP01", "Vịt Quay", 200000, "android.resource://com.example.baitapnhom/drawable/vitquay"));
        listSanPham.add(new SanPham("SP02", "Phở", 40000, "android.resource://com.example.baitapnhom/drawable/pho"));
        listSanPham.add(new SanPham("SP03", "Mì", 99000, "android.resource://com.example.baitapnhom/drawable/mi"));
        adapter = new SanPhamAdapter(MainActivity.this, R.layout.san_pham,listSanPham);
        lvSanPham.setAdapter(adapter);
        btnThemSP = findViewById(R.id.btnThemSP);
        btnThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themSanPham();
            }
        });
    }
    public void themSanPham() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.them_san_pham);
        dialog.setCanceledOnTouchOutside(false);
        Button btnChonAnh = dialog.findViewById(R.id.btnChonAnh);
        imgAnhThem = dialog.findViewById(R.id.imgAnhThem);
        btnChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        EditText edtMaSP = dialog.findViewById(R.id.edtMaSP);
        EditText edtTenSP = dialog.findViewById(R.id.edtTenSP);
        EditText edtGiaSP = dialog.findViewById(R.id.edtGiaSP);
        Button btnThem = dialog.findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maSP = edtMaSP.getText().toString().trim();
                String tenSP = edtTenSP.getText().toString().trim();
                float giaSP = Float.parseFloat(edtGiaSP.getText().toString().trim());
                if(imgPath!=null)
                {

                    listSanPham.add(new SanPham(maSP,tenSP,giaSP,imgPath));
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập ảnh để thêm vào danh sách", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data!=null)
        {
            Uri uri = data.getData();
            imgAnhThem.setImageURI(uri);
            imgPath = uri.toString();
        }
        if(requestCode == REQUEST_CODE_1 && resultCode == RESULT_OK && data!=null)
        {
            Uri uri = data.getData();
            imgAnhCu.setImageURI(uri);
            imgPath1 = uri.toString();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tim_kiem,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
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
    public void suaSanPham(SanPham sp)
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.sua_san_pham);
        dialog.setCanceledOnTouchOutside(false);
        EditText edtMaSPSua = dialog.findViewById(R.id.edtMaSPSua);
        EditText edtTenSPSua = dialog.findViewById(R.id.edtTenSPSua);
        EditText edtGiaSPSua = dialog.findViewById(R.id.edtGiaSPSua);
        Button btnChonAnhMoi = dialog.findViewById(R.id.btnChonAnhMoi);
        Button btnSua = dialog.findViewById(R.id.btnSua);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        imgAnhCu = dialog.findViewById(R.id.imgAnhCu);
        edtMaSPSua.setText(sp.getMaSP());
        edtTenSPSua.setText(sp.getTenSP());
        edtGiaSPSua.setText(""+sp.getGiaSP());
        Uri uri = Uri.parse(sp.getHinhAnhSP());
        imgAnhCu.setImageURI(uri);
        imgPath1 = sp.getHinhAnhSP();
        btnChonAnhMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_1);
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maSPSua = edtMaSPSua.getText().toString().trim();
                String tenSPSua = edtTenSPSua.getText().toString().trim();
                float giaSPSua = Float.parseFloat(edtGiaSPSua.getText().toString().trim());
                sp.setMaSP(maSPSua);
                sp.setTenSP(tenSPSua);
                sp.setGiaSP(giaSPSua);
                if(imgPath1!=null)
                {
                    sp.setHinhAnhSP(imgPath1);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
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
