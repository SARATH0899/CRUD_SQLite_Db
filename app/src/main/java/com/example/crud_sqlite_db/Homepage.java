package com.example.crud_sqlite_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage extends AppCompatActivity {

    Button btnProductAdd, btnProductUpdate, btnProductDelete, btnProductPreview, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        btnProductAdd = findViewById(R.id.button_productAdd);
        btnProductUpdate = findViewById(R.id.button_productUpdate);
        btnProductDelete = findViewById(R.id.button_productDelete);
        btnProductPreview = findViewById(R.id.button_productView);
        btnLogout = findViewById(R.id.button_logout);

        btnProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homepage.this, ProductAdd.class));
            }
        });

        btnProductUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homepage.this, ProductUpdate.class));
            }
        });

        btnProductDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homepage.this, ProductDelete.class));
            }
        });

        btnProductPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homepage.this, ProductView.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homepage.this, LoginActivity.class));
                finish();
            }
        });
    }
}