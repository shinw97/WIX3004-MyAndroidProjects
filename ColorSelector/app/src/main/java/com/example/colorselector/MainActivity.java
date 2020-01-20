package com.example.colorselector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ConstraintLayout MainActivity = findViewById(R.id.MainLayout);
        ((Button) findViewById(R.id.BtnSelect)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelect(v);
            }
        });

        ((CheckBox) findViewById(R.id.CBRed)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
//                    Toast.makeText(getApplicationContext(), "You have chosen Red Color.", Toast.LENGTH_SHORT).show();
                    MainActivity.setBackgroundColor(getResources().getColor(R.color.colorLightRed));
                    return;
                }
//                Toast.makeText(getApplicationContext(), "You have unselected Red Color.", Toast.LENGTH_SHORT).show();
                MainActivity.setBackgroundColor(0x00000000);
            }
        });

        ((CheckBox) findViewById(R.id.CBBlue)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
//                    Toast.makeText(getApplicationContext(), "You have chosen Blue Color.", Toast.LENGTH_SHORT).show();
                    MainActivity.setBackgroundColor(getResources().getColor(R.color.colorLightBlue));
                    return;
                }
//                Toast.makeText(getApplicationContext(), "You have unselected Blue Color.", Toast.LENGTH_SHORT).show();
                MainActivity.setBackgroundColor(0x00000000);
            }
        });

        ((CheckBox) findViewById(R.id.CBGreen)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
//                    Toast.makeText(getApplicationContext(), "You have chosen Green Color.", Toast.LENGTH_SHORT).show();
                    MainActivity.setBackgroundColor(getResources().getColor(R.color.colorLightGreen));
                    return;
                }
//                Toast.makeText(getApplicationContext(), "You have unselected Green Color.", Toast.LENGTH_SHORT).show();
                MainActivity.setBackgroundColor(0x00000000);
            }
        });
    }

    public void onSelect(View view) {
        final ConstraintLayout MainActivity = findViewById(R.id.MainLayout);
        HashMap<String, Boolean> hm = new HashMap<>();
        hm.put("Red", ((CheckBox) findViewById(R.id.CBRed)).isChecked());
        hm.put("Blue", ((CheckBox) findViewById(R.id.CBBlue)).isChecked());
        hm.put("Green", ((CheckBox) findViewById(R.id.CBGreen)).isChecked());

        String[] colors = {"Red", "Blue", "Green"};

        String message = "Colors chosen: ";

        boolean noneChosen = true;

        StringBuilder colorPattern = new StringBuilder("000");

        for (int i = 0; i < colors.length; i++) {
            if (hm.get(colors[i])) {
                colorPattern.setCharAt(i, '1');
                noneChosen = false;
                message += colors[i] + " ";
            }
        }

        if (noneChosen) {
            message += "None";
        }

        switch (colorPattern.toString()) {
            case "000":
                MainActivity.setBackgroundColor(0x00000000);
                break;
            case "001":
                MainActivity.setBackgroundResource(R.drawable.green);
                break;
            case "010":
                MainActivity.setBackgroundResource(R.drawable.blue);
                break;
            case "011":
                MainActivity.setBackgroundResource(R.drawable.bluegreen);
                break;
            case "100":
                MainActivity.setBackgroundResource(R.drawable.red);
                break;
            case "101":
                MainActivity.setBackgroundResource(R.drawable.redgreen);
                break;
            case "110":
                MainActivity.setBackgroundResource(R.drawable.redblue);
                break;
            case "111":
                MainActivity.setBackgroundResource(R.drawable.redgreenblue);
                break;
            default:
                break;
        }


        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();


    }

}
