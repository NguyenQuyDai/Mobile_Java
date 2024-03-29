package com.example.test;

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

import java.util.ArrayList;

public class CongViecAdapter extends ArrayAdapter {

    Activity context;
    int resource;
    ArrayList<CongViec> listCV;

    public CongViecAdapter(@NonNull Context context, int resource,ArrayList<CongViec> listCV) {
        super(context, resource,listCV);
        this.context =(Activity) context;
        this.resource = resource;
        this.listCV = listCV;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(this.resource,null);
        TextView txtTieuDe = view.findViewById(R.id.txtTieuDe);
        ImageView imgEdit = view.findViewById(R.id.imgEdit);
        ImageView imgDelete = view.findViewById(R.id.imgDelete);
        CongViec cv =listCV.get(position);
        txtTieuDe.setText(cv.getMaCV()+" "+cv.getTenCV());
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "EDIT"+cv.getMaCV(), Toast.LENGTH_SHORT).show();
            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "DELETE"+cv.getMaCV(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
