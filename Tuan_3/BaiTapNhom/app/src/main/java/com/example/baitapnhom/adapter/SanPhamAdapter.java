package com.example.baitapnhom.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.baitapnhom.MainActivity;
import com.example.baitapnhom.R;
import com.example.baitapnhom.models.SanPham;

import java.util.ArrayList;

public class SanPhamAdapter extends ArrayAdapter<SanPham> implements Filterable {
    Activity context;
    int resource;

    ArrayList<SanPham> listSanPham, listSanPhamBackup, listSanPhamFilter;

    public SanPhamAdapter(@NonNull Context context, int resource, ArrayList<SanPham> listSanPham) {
        super(context, resource);
        this.context = (Activity) context;
        this.resource = resource;
        this.listSanPham = this.listSanPhamBackup = listSanPham;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString().trim();
                if (query.length() < 1) {
                    listSanPhamFilter = listSanPhamBackup;
                } else {
                    listSanPhamFilter = new ArrayList<>();
                    for (SanPham sp : listSanPhamBackup) {
                        if (sp.getTenSP().toLowerCase().contains(query) || sp.getMaSP().toLowerCase().contains(query)) {
                            listSanPhamFilter.add(sp);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listSanPhamFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listSanPham = (ArrayList<SanPham>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getCount() {
        return listSanPham.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(this.resource, null);
        TextView tvMaSP = view.findViewById(R.id.tvMaSP);
        TextView tvTenSP = view.findViewById(R.id.tvTenSP);
        TextView tvGiaSP = view.findViewById(R.id.tvGiaSP);
        ImageView imgLogo = view.findViewById(R.id.imgLogo);
        ImageButton imgDelete = view.findViewById(R.id.imgDelete);
        ImageView imgEdit = view.findViewById(R.id.imgEdit);
        final SanPham sp = this.listSanPham.get(position);
        tvMaSP.setText("Mã SP:" + sp.getMaSP());
        tvTenSP.setText("Tên SP:" + sp.getTenSP());
        tvGiaSP.setText("Giá SP:" + sp.getGiaSP() + "VNĐ");
        Uri uri = Uri.parse(sp.getHinhAnhSP());
        imgLogo.setImageURI(uri);
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).suaSanPham(sp);
            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xoaSanPham(sp);
            }
        });
        return view;
    }

    public void xoaSanPham(SanPham sp) {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setTitle("Xóa Sản Phẩm");
        b.setMessage("Bạn có muốn xóa sản phẩm này không?");
        b.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listSanPham.remove(sp);
                notifyDataSetChanged();
            }
        });
        b.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        b.create().show();
    }

}
