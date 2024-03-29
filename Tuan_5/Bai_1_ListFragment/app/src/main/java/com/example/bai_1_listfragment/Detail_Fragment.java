package com.example.bai_1_listfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Detail_Fragment extends Fragment {
    TextView tvName,tvContent;
    ImageView imgLogo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.detail_item,container,false);
        tvName =(TextView) view.findViewById(R.id.tvName);
        tvContent =(TextView) view.findViewById(R.id.tvContent);
        imgLogo =(ImageView) view.findViewById(R.id.imgLogo);
        return view;
    }
    public void changeData(String name, int logo,String content)
    {
        tvName.setText(name);
        imgLogo.setImageResource(logo);
        tvContent.setText(content);
    }
}
