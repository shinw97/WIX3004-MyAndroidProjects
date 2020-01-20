package com.example.sqlitepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    private EditText editTextPhone, editTextName, editTextEmail;
    private UserRecord oldUserRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        oldUserRecord = getIntent().getParcelableExtra("UserRecord");
        editTextPhone = (EditText) findViewById(R.id.editTextPhone_edit);
        editTextName = (EditText) findViewById(R.id.editTextName_edit);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail_edit);

        editTextPhone.setText(oldUserRecord.getPhone());
        editTextName.setText(oldUserRecord.getName());
        editTextEmail.setText(oldUserRecord.getEmail());

        Button saveBtn = findViewById(R.id.buttonSave_edit);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecord(v);
            }
        });

        Button cancelBtn = findViewById(R.id.buttonCancel_edit);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void saveRecord(View v) {
        String phone, name, email;
        phone = editTextPhone.getText().toString();
        if (phone.isEmpty()) {
            editTextPhone.setError(getString(R.string.error_phone));
            return;
        }
        name = editTextName.getText().toString();
        if (name.isEmpty()) {
            editTextName.setError(getString(R.string.error_name));
            return;
        }
        email = editTextEmail.getText().toString();
        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.error_email));
            return;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.error_invalid_email));
            return;
        }
        UserRecord newUserRecord = new UserRecord();
        newUserRecord.setPhone(phone);
        newUserRecord.setName(name);
        newUserRecord.setEmail(email);
        UserSQLHelper userDataSource = new UserSQLHelper(this);
        userDataSource.updateRecord(oldUserRecord, newUserRecord);
        System.out.println("hey");
        this.finish(); //Terminate this Activity
    }
}
