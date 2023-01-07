package com.example.contact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity implements ItemOnClick {

    List<ContactModel> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Faker faker = new Faker();

        items = new ArrayList<>();

        for (int i = 0 ; i < 50; i++) {
            items.add(new ContactModel(i, faker.name.name(), faker.phoneNumber.phoneNumber(), faker.internet.email()));
        }

        ContactAdapter adapter = new ContactAdapter(items, this);
        ListView listContact = findViewById(R.id.list_view);

        listContact.setAdapter(adapter);
        registerForContextMenu(listContact);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        int pos = ((AdapterView.AdapterContextMenuInfo)item.getMenuInfo()).position;
        ContactModel contact = items.get(pos);
        if (id == R.id.button_call) {
            String phoneData = "tel:" + contact.getPhoneNumber();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneData));
            startActivity(intent);
        }
        else if (id == R.id.button_sms) {
            String smsData = "smsto:" + contact.getPhoneNumber();
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(smsData));
            startActivity(intent);
        } else if (id == R.id.button_email) {
            String[] emailReceiverList = { contact.getEmail() };
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("vnd.android.cursor.dir/email");
            intent.putExtra(Intent.EXTRA_EMAIL, emailReceiverList);
            startActivity(intent);
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void OnItemClick(int position) {
        ContactModel contact = items.get(position);
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        Bundle data = new Bundle();
        data.putInt("id", contact.getID());
        data.putString("name", contact.getFullname());
        data.putString("phone", contact.getPhoneNumber());
        data.putString("email", contact.getEmail());
        intent.putExtras(data);
        startActivity(intent);
    }
}