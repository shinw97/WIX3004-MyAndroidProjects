package com.example.ourteam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                if (v == findViewById(R.id.ButtonLY)) {
                    fragment = new FragmentLayyan();
                } else if (v == findViewById(R.id.ButtonSW)) {
                    fragment = new FragmentShinwei();
                } else if (v == findViewById(R.id.ButtonSyaz)) {
                    fragment = new FragmentSyazwan();
                } else {
                    fragment = new FragmentXunwei();
                }
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentDisplay, fragment);
                transaction.commit();
            }
        };
        Button BtnLY = (Button) findViewById(R.id.ButtonLY);
        BtnLY.setOnClickListener(listener);
        Button BtnSW = (Button) findViewById(R.id.ButtonSW);
        BtnSW.setOnClickListener(listener);
        Button BtnSZ = (Button) findViewById(R.id.ButtonSyaz);
        BtnSZ.setOnClickListener(listener);
        Button BtnXW = (Button) findViewById(R.id.ButtonXW);
        BtnXW.setOnClickListener(listener);
    }
}
