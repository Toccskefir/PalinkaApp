package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonRecord;
    private Button buttonSearch;
    private Button buttonList;
    private TextView textViewList;
    private DBHelper dbHelper;

    public void init() {
        buttonRecord = findViewById(R.id.buttonRecord);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonList = findViewById(R.id.buttonList);
        textViewList = findViewById(R.id.textViewList);
        textViewList.setMovementMethod(new ScrollingMovementMethod());
        dbHelper = new DBHelper(MainActivity.this);
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
                    StringBuilder sb = new StringBuilder();
                    while (datas.moveToNext()) {
                        sb.append("ID: ").append(datas.getInt(0)).append("\n");
                        sb.append("Főző: ").append(datas.getString(1)).append("\n");
                        sb.append("Gyümölcs: ").append(datas.getString(2)).append("\n");
                        sb.append("Alkohol tartalom: ").append(datas.getInt(3)).append("\n\n");
                    }
                    textViewList.setText(sb);
                }
            }
        });
    }
}