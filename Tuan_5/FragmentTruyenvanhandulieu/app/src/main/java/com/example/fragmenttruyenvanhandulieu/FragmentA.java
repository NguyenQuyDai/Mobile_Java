package com.example.fragmenttruyenvanhandulieu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FragmentA extends Fragment {
    TextView textViewA;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a,container,false);
        textViewA = view.findViewById(R.id.textViewA);
        Bundle bundle = getArguments();
        if(bundle!=null)
        {
            textViewA.setText(bundle.getString("hoVaTen").toString());
        }
        else {
            Toast.makeText(getActivity(),"Khong nhan dc gia tri!",Toast.LENGTH_LONG).show();
        }
        return view;
    }
    public boolean kiemTraSnt(int n)
    {
        if(n==1||n==2)
            return true;
        for(int i = 2 ; i<=n ;i++)
        {
            if(n%i==0)
                return false;
        }
        return true;
    }
}
