package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdatKeresActivity extends AppCompatActivity {
    private EditText editTextManufactSearch;
    private EditText editTextFruitSearch;
    private Button buttonSearchData;
    private Button buttonSearchBack;
    private TextView textViewSearch;
    private DBHelper dbHelper;

    public void init() {
        editTextManufactSearch = findViewById(R.id.editTextManufactSearch);
        editTextFruitSearch = findViewById(R.id.editTextFruitSearch);
        buttonSearchData = findViewById(R.id.buttonSearchData);
        buttonSearchBack = findViewById(R.id.buttonSearchBack);
        textViewSearch = findViewById(R.id.textViewSearch);
        dbHelper = new DBHelper(AdatKeresActivity.this);
    }

    public void editTextReset() {
        editTextManufactSearch.setText(null);
        editTextFruitSearch.setText(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_keres);
        init();

        buttonSearchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String manufact = editTextManufactSearch.getText().toString();
                String fruit = editTextFruitSearch.getText().toString();
                
                if (manufact.isEmpty() || fruit.isEmpty()) {
                    Toast.makeText(AdatKeresActivity.this,
                "Kitöltetlen adastmezők", Toast.LENGTH_SHORT).show();
                } else {
                    editTextReset();
                    Cursor datas = dbHelper.dataGet(manufact, fruit);
                    if (datas.getCount() == 0) {
                        textViewSearch.setText("Nem található ilyen adat!");
                    } else {
                        StringBuilder sb = new StringBuilder();
                        while (datas.moveToNext()) {
                            sb.append("Alkohol tartalom: ").append(datas.getInt(0)).append("%");
                        }
                        textViewSearch.setText(sb);
                    }
                }
            }
        });

        buttonSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdatKeresActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}