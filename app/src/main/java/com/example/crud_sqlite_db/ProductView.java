package com.example.crud_sqlite_db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ProductView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_view);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DbHelper(this);

        List<Product> productList = getProductsFromDatabase();

        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);
    }

    private List<Product> getProductsFromDatabase() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Product> productList = new ArrayList<>();

        Cursor cursor = db.query("tbl_products", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String productId = cursor.getString(cursor.getColumnIndex("PRODUCT_ID"));
                String productName = cursor.getString(cursor.getColumnIndex("PRODUCT_NAME"));
                String productCategory = cursor.getString(cursor.getColumnIndex("PRODUCT_CATEGORY"));
                String productQuantity = cursor.getString(cursor.getColumnIndex("PRODUCT_QUANTITY"));
                String productAvailability = cursor.getString(cursor.getColumnIndex("PRODUCT_AVAILABILITY"));
                int productPrice = cursor.getInt(cursor.getColumnIndex("PRODUCT_PRICE"));

                Product product = new Product(productId, productName, productCategory,
                        productQuantity, productAvailability, productPrice);

                productList.add(product);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();

        return productList;
    }
}
