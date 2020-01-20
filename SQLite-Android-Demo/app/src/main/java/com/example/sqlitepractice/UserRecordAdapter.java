package com.example.sqlitepractice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserRecordAdapter extends ArrayAdapter<UserRecord> {
    Context context;
    List<UserRecord> list;

    public UserRecordAdapter(Activity context, int resource, List<UserRecord> list) {
        super(context, resource, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final UserRecord userRecord = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.user_record,
                            parent,
                            false);
        }


        TextView textViewPhone, textViewName, textViewEmail;
        textViewPhone = (TextView) convertView.findViewById(R.id.textViewPhone);
        textViewName = (TextView) convertView.findViewById(R.id.textViewName);
        textViewEmail = (TextView) convertView.findViewById(R.id.textViewEmail);
        textViewPhone.setText("Phone : " +
                userRecord.getPhone());
        textViewName.setText(userRecord.getName());
        textViewEmail.setText("Email : " +
                userRecord.getEmail());



        Button deleteBtn = (Button) convertView.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(userRecord);
            }
        });

        Button editBtn = (Button) convertView.findViewById(R.id.edit_btn);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditActivity.class);
                intent.putExtra("UserRecord", userRecord);
                v.getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public void remove(@Nullable UserRecord object) {
        super.remove(object);
        UserSQLHelper helper = new UserSQLHelper(context);
        helper.deleteRecord(object);
//        notifyDataSetChanged();
    }
}
