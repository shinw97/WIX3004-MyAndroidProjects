package com.example.multipleact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BtnRLOnClick(View view) {
        Intent RLAct = new Intent(this, RLActivity.class);
        startActivityForResult(RLAct, 0);
//        startActivity(RLAct);
    }

    public void BtnCLOnClick(View view) {
        Intent CLAct = new Intent(this, CLActivity.class);
//        startActivityForResult(RLAct, 0);
        startActivity(CLAct);
    }


    public void BtnGLOnClick(View view) {
        Intent GLAct = new Intent(this, GLActivity.class);
//        startActivityForResult(RLAct, 0);
        startActivity(GLAct);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("Name");
                Toast.makeText(getApplicationContext(), "Welcome Back! " + name + ".", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
