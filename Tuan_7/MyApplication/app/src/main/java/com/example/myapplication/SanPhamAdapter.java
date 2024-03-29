package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    Activity context;
    int resource;

    public SanPhamAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context =(Activity) context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(this.resource,null);
        TextView textViewTenSP = view.findViewById(R.id.textViewTenSP);
        TextView textViewGiaSP = view.findViewById(R.id.textViewGiaSP);
        ImageView imgAnhSP = view.findViewById(R.id.imgAnhSP);
        ImageView imgEdit = view.findViewById(R.id.imgEdit);
        ImageView imgDelete = view.findViewById(R.id.imgDelete);
        SanPham sp = getItem(position);
        textViewTenSP.setText(sp.getTenSP());
        textViewGiaSP.setText("Đơn giá: "+sp.getGiaSP()+" VNĐ");
        // Chuyen byte--> bitmap
        byte[] hinhanh = sp.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh,0,hinhanh.length);
        imgAnhSP.setImageBitmap(bitmap);
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Edit cong viec id="+getItem(position), Toast.LENGTH_SHORT).show();
                ((MainActivity)context).SuaSanPham(getItem(position));
            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Delete cong viec id="+getItem(position), Toast.LENGTH_SHORT).show();
                ((MainActivity)context).XoaSP(getItem(position));
            }
        });
        return view;
    }
}
