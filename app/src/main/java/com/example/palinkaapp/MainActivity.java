package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button buttonRecord;
    private Button buttonSearch;
    private Button buttonList;
    private ArrayAdapter<String> adapter;
    private List<String> entryList;
    private ListView listView;
    private DBHelper dbHelper;

    public void init() {
        buttonRecord = findViewById(R.id.buttonRecord);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonList = findViewById(R.id.buttonList);
        listView = findViewById(R.id.listView);
        dbHelper = new DBHelper(MainActivity.this);
        entryList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, entryList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        buttonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdatFelvetelActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdatKeresActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor datas = dbHelper.dataGet();
                if (datas.getCount() == 0) {
                    Toast.makeText(MainActivity.this,
                "Nincs lekérdezhető adat", Toast.LENGTH_SHORT).show();
                } else {
                    entryList.clear();
                    while (datas.moveToNext()) {
                        String entry = "ID: " + datas.getInt(0) + "\n" +
                                "Főző: " + datas.getString(1) + "\n" +
                                "Gyümölcs: " + datas.getString(2) + "\n" +
                                "ALkoholmérték: " + datas.getInt(3) + "\n\n";
                        entryList.add(entry);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}