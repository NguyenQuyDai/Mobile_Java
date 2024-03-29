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

public class FragmentA extends Fragment {
    static TextView textViewA;
    EditText editTextA;
    Button buttonA;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a,container,false);
        textViewA = (TextView) view.findViewById(R.id.textViewA);
        editTextA = (EditText) view.findViewById(R.id.editTextA);
        buttonA = (Button) view.findViewById(R.id.buttonA);
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textViewMain = getActivity().findViewById(R.id.textViewMain);
                textViewMain.setText(editTextA.getText().toString().trim());
            }
        });
        return view;
    }
    public static void ganNoiDung(String noiDung) {
        textViewA.setText(noiDung);
    }
}
