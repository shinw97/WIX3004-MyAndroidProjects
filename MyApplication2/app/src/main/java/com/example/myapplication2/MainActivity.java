package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.MainLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return false;
            }
        });
    }

    public void addition(View view){
        EditText ETNum1 = findViewById(R.id.ETFirstNumber);
        EditText ETNum2 = findViewById(R.id.ETSecondNumber);
        int result = Integer.parseInt(ETNum1.getText().toString()) + Integer.parseInt(ETNum2.getText().toString());
        System.out.println(result);
        TextView TVResult = findViewById(R.id.TVResult);
        TVResult.setText(Integer.toString(result));
    }
}
