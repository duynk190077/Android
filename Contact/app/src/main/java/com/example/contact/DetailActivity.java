package com.example.contact;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_detail);

        TextView idText = findViewById(R.id.text_id);
        TextView nameText = findViewById(R.id.text_name);
        TextView phoneText = findViewById(R.id.text_phone);
        TextView emailText = findViewById(R.id.text_email);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        idText.setText(String.valueOf(data.getInt("id")));
        nameText.setText(data.getString("name"));
        phoneText.setText(data.getString("phone"));
        emailText.setText(data.getString("email"));
    }
}
