package com.example.inforapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Button avt = findViewById(R.id.button_avtt);
        TextView name = findViewById(R.id.text_name1);
        TextView username = findViewById(R.id.text_username1);
        TextView email = findViewById(R.id.text_email1);
        TextView phone = findViewById(R.id.text_phone1);
        TextView address = findViewById(R.id.text_address1);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        String lat = data.getString("lat");
        String lng = data.getString("lng");
        SpannableString address_text = new SpannableString(data.getString("address"));
        address_text.setSpan(new UnderlineSpan(), 0, address_text.length(), 0);

        avt.setText(data.getString("avatar"));
        name.setText(data.getString("name"));
        username.setText(data.getString("username"));
        email.setText(data.getString("email"));
        phone.setText(data.getString("phone"));
        address.setText(address_text);

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mapString = "geo:" + lat + ',' + lng;
                Log.v("TAG", mapString);
                Uri gmmIntentUri = Uri.parse(mapString);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

    }
}