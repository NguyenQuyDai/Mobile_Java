package com.example.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.models.ThietBi;

import java.util.ArrayList;

public class ThietBiAdapter extends ArrayAdapter<ThietBi> implements Filterable {

    Activity context;
    int resource;

    ArrayList<ThietBi> listThietBi, listThietBiBackup, listThietBiFilter;
    public ThietBiAdapter(@NonNull Context context, int resource,ArrayList<ThietBi> listThietBi) {
        super(context, resource, listThietBi);
        this.context =(Activity) context;
        this.resource = resource;
        this.listThietBi = this.listThietBiBackup = listThietBi;
    }


    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString().trim();
                if (query.length() < 1) {
                    listThietBiFilter = listThietBiBackup;
                } else {
                    listThietBiFilter = new ArrayList<>();
                    for (ThietBi tb : listThietBiBackup) {
                        if (tb.getTenTB().toLowerCase().contains(query) || tb.getMaTB().toLowerCase().contains(query)) {
                            listThietBiFilter.add(tb);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listThietBiFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listThietBi = (ArrayList<ThietBi>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getCount() {
        return listThietBi.size();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(this.resource,null);
        TextView textViewMaTB = view.findViewById(R.id.textViewMaTB);
        TextView textViewTenTB = view.findViewById(R.id.textViewTenTB);
        TextView textViewGiaTB = view.findViewById(R.id.textViewGiaTB);
        ImageView imgAnhTB = view.findViewById(R.id.imgAnhTB);
        ImageView imgEdit = view.findViewById(R.id.imgEdit);
        ImageView imgDelete = view.findViewById(R.id.imgDelete);
        final  ThietBi tb = this.listThietBi.get(position);
        textViewMaTB.setText(tb.getMaTB());
        textViewTenTB.setText(tb.getTenTB());
        textViewGiaTB.setText("Đơn giá:"+tb.getGiaTB()+" VNĐ");
        // chuyen tu byte --> bitmap
        byte[] hinhanh = tb.getHinhAnhTB();
        //BitmapFactory.decodeByteArray:Phương thức này được sử dụng để giải mã một mảng byte thành một
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh,0,hinhanh.length);
        imgAnhTB.setImageBitmap(bitmap);
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).suaThietBi(listThietBi.get(position));
                Toast.makeText(context, "Đã sửa", Toast.LENGTH_SHORT).show();
            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).xoaThietBi(listThietBi.get(position));
                Toast.makeText(context, "xóa", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
