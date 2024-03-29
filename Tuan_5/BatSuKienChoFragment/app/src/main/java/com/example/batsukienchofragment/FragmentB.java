package com.example.batsukienchofragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class FragmentB extends Fragment {
    TextView textViewB;
    EditText editTextB;
    Button buttonB;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b,container,false);
        textViewB = view.findViewById(R.id.textViewB);
        editTextB = view.findViewById(R.id.editTextB);
        buttonB = view.findViewById(R.id.buttonB);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),editTextB.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
