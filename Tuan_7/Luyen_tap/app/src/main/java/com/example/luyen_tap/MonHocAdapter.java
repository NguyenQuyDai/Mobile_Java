package com.example.luyen_tap;

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

public class MonHocAdapter extends ArrayAdapter<MonHoc> {
    Activity context;
    int resource;

    public MonHocAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = (Activity) context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customerView = inflater.inflate(this.resource,null);
        ImageView imgMH = customerView.findViewById(R.id.imgMH);
        TextView txtViewMaMH = customerView.findViewById(R.id.txtViewMaMH);
        TextView txtViewTenMH = customerView.findViewById(R.id.txtViewTenMH);
        ImageView imgEdit = customerView.findViewById(R.id.imgEdit);
        ImageView imgDelete = customerView.findViewById(R.id.imgDelete);
        MonHoc monHoc = getItem(position);
        txtViewMaMH.setText(monHoc.getMaMH());
        txtViewTenMH.setText("Tên môn học:"+monHoc.getTenMH());
        // Chuyển byte[] thành bitmap
        byte[] hinhanh = monHoc.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh,0,hinhanh.length);
        imgMH.setImageBitmap(bitmap);
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Edit môn học có id:"+getItem(position), Toast.LENGTH_SHORT).show();
                ((MainActivity)context).SuaMonHoc(getItem(position));
            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Delete môn học có id:"+getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
        return customerView;
    }
}
