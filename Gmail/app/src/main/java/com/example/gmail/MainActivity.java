package com.example.gmail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    List<InboxModel> items;

    List<Integer> colors;

    private InboxModel random(int i) {
        Random generator = new Random();
        int index = generator.nextInt(colors.size());
        return new InboxModel(colors.get(index), "Momo " + i, "Chuyen tien", "Ban nhan duoc 120.000.000 VND tu momo", "12:34 PM");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView inboxView = (RecyclerView) findViewById(R.id.recycler_view);
        inboxView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        inboxView.setLayoutManager(layoutManager);
        colors = new ArrayList<>();
        colors.add(0xffacbfd0);
        colors.add(0xff8fbea5);
        colors.add(0xffb0c8ed);
        colors.add(0xffd6c2ac);
        colors.add(0xffffc1cc);
        colors.add(0xff129900);
        colors.add(0xffd6c2ac);
        colors.add(0xffaa9786);
        items = new ArrayList<>();
        for (int i = 0; i < 10; i++) items.add(random(i));
        InboxAdapter adapter = new InboxAdapter(items);
        inboxView.setAdapter(adapter);
    }
}