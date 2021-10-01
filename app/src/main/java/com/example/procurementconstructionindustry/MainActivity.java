package com.example.procurementconstructionindustry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.procurementconstructionindustry.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper mydb = new DatabaseHelper(this);
    }
}