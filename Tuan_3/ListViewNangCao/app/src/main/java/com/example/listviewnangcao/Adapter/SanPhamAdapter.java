package com.example.listviewnangcao.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listviewnangcao.Models.SanPham;
import com.example.listviewnangcao.R;

public class SanPhamAdapter extends ArrayAdapter {
    Activity context;
    int resource;

    public SanPhamAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = (Activity) context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customView = inflater.inflate(this.resource,null);
        TextView maSP = customView.findViewById(R.id.textView);
        TextView tenSP = customView.findViewById(R.id.textView2);
        TextView giaSP = customView.findViewById(R.id.textView3);
        ImageView hinhanhSP = customView.findViewById(R.id.imgSP);
        SanPham sp = (SanPham) getItem(position);
        hinhanhSP.setImageResource(sp.getHinhAnhSP());
        maSP.setText(sp.getMaSP());
        tenSP.setText(sp.getTenSP());
        giaSP.setText("Đơn giá:"+sp.getGiaSP()+" VNĐ");
        return customView;
    }
}
