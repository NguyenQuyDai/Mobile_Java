package com.example.rss;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaiBaoAdapter extends ArrayAdapter<BaiBao> {
    Activity context;
    int resource;
    ArrayList<BaiBao> listBaiBao;

    public BaiBaoAdapter(@NonNull Context context, int resource, ArrayList<BaiBao> listBaiBao) {
        super(context, resource, listBaiBao);
        this.context = (Activity) context;
        this.resource = resource;
        this.listBaiBao = listBaiBao;
    }

    @Override
    public int getCount() {
        return listBaiBao.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(this.resource, null);
        ImageView imgPostThumd = view.findViewById(R.id.imgPostThumd);
        TextView tvPostTitle = view.findViewById(R.id.tvPostTitle);
        TextView tvPostDescrip = view.findViewById(R.id.tvPostDescrip);
        TextView tvPostPubDate = view.findViewById(R.id.tvPostPubDate);

        BaiBao baiBao = listBaiBao.get(position);

        tvPostTitle.setText(baiBao.getTenBaiBao());
        tvPostDescrip.setText(baiBao.getTomTatBaiBao());
        tvPostPubDate.setText(baiBao.getNgayDangBai());
        if (baiBao.getLinkBaiBao().trim().length() > 0) {
            Handler handler = new Handler(Looper.getMainLooper());
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Bitmap bitmap = null;
                        URL imageURL = new URL(baiBao.getLinkAnhBaiBao());
                        InputStream inputStream = imageURL.openStream();
                        bitmap = BitmapFactory.decodeStream(inputStream);

                        Bitmap finalBitmap = bitmap;
                        // post thay đổi luồng chính của đối tượng
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                imgPostThumd.setImageBitmap(finalBitmap);
                            }
                        });
                    } catch (Exception e) {
                        System.out.println("Lỗi đọc file Ảnh" + e.toString());
                        Toast.makeText(context, "Lỗi đọc file Ảnh" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        imgPostThumd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openLinkIntent = new Intent(Intent.ACTION_VIEW);
                openLinkIntent.setData(Uri.parse(baiBao.getLinkBaiBao()));
                context.startActivity(openLinkIntent);
            }
        });
        return view;
    }
}
