package com.example.fragment_replace;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void AddFragment(View view)
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        if(view.getId()==R.id.buttonAddA)
        {
            fragment = new FragmentA();
        }
        if(view.getId()==R.id.buttonAddB)
        {
            fragment = new FragmentB();
        }
        fragmentTransaction.replace(R.id.frameContent,fragment);
        fragmentTransaction.commit();
    }
}