package com.example.bai_tap_sqlite;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CongViecAdapter extends ArrayAdapter<CongViec> {
    Activity context;
    int resource;

    public CongViecAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context =(Activity) context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customView = inflater.inflate(this.resource,null);
        TextView txtTenCV = customView.findViewById(R.id.txtTenCV);
        ImageView imgEdit = customView.findViewById(R.id.imgEdit);
        ImageView imgDelete = customView.findViewById(R.id.imgDelete);
        CongViec cv = getItem(position);
        txtTenCV.setText(cv.getTenCV());
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Edit cong viec id="+getItem(position), Toast.LENGTH_SHORT).show();
                ((MainActivity)context).SuaCVDialog(getItem(position));
            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Delete cong viec id="+getItem(position),
                        Toast.LENGTH_SHORT).show();
                ((MainActivity)context).ShowXoaCVDialog(getItem(position));
            }
        });
        return customView;
    }
}
