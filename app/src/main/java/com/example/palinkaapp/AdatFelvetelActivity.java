package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdatFelvetelActivity extends AppCompatActivity {
    private EditText editTextManufact;
    private EditText editTextFruit;
    private EditText editTextAlcohol;
    private Button buttonRecordData;
    private Button buttonRecordBack;
    private DBHelper dbHelper;

    public void init() {
        editTextManufact = findViewById(R.id.editTextManufact);
        editTextFruit = findViewById(R.id.editTextFruit);
        editTextAlcohol = findViewById(R.id.editTextAlcohol);
        buttonRecordData = findViewById(R.id.buttonRecordData);
        buttonRecordBack = findViewById(R.id.buttonRecordBack);
        dbHelper = new DBHelper(AdatFelvetelActivity.this);
    }

    public void editTextReset() {
        editTextManufact.setText(null);
        editTextFruit.setText(null);
        editTextAlcohol.setText(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_felvetel);
        init();

        buttonRecordData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String manufact = editTextManufact.getText().toString();
                String fruit = editTextFruit.getText().toString();
                String alcohol = editTextAlcohol.getText().toString();

                if (manufact.isEmpty() || fruit.isEmpty() || alcohol.isEmpty()) {
                    Toast.makeText(AdatFelvetelActivity.this, "Kitöltetlen adatmezők", Toast.LENGTH_SHORT).show();
                } else {
                    int alcoholInt = Integer.parseInt(alcohol);
                    if (dbHelper.dataRecord(manufact, fruit, alcoholInt)) {
                        Toast.makeText(AdatFelvetelActivity.this,
                                "Sikeres adatfelvétel", Toast.LENGTH_SHORT).show();

                        editTextReset();
                    } else {
                        Toast.makeText(AdatFelvetelActivity.this,
                                "Sikertelen adatfelvétel", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonRecordBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdatFelvetelActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}