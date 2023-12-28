package com.example.crud_sqlite_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class ProductUpdate extends AppCompatActivity {
    Button btnProductUpdate;
    EditText productId, productName, productPrice;
    Spinner spinnerProductQuantity, spinnerProductCategory;
    Switch productAvailability;
    DbHelper helper = new DbHelper(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_update);

        btnProductUpdate = findViewById(R.id.product_update_button);

        productId = findViewById(R.id.product_id);
        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);

        spinnerProductCategory = findViewById(R.id.product_category);
        spinnerProductQuantity = findViewById(R.id.product_quantity);

        productAvailability = findViewById(R.id.product_availability);

        db = helper.getWritableDatabase();

        btnProductUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pid = productId.getText().toString();
                String pnm = productName.getText().toString();
                String pc = spinnerProductCategory.getSelectedItem().toString();
                String pq = spinnerProductQuantity.getSelectedItem().toString();
                boolean pa = productAvailability.isChecked();
                String pp = productPrice.getText().toString();

                if (!isProductExists(pid)) {
                    Toast.makeText(ProductUpdate.this, "Product ID not found", Toast.LENGTH_SHORT).show();
                    return;
                } else if (pid.isEmpty() || pnm.isEmpty() || pc.isEmpty() || pq.isEmpty() || pp.isEmpty()) {
                    Toast.makeText(ProductUpdate.this, "All Fields are Mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues data = new ContentValues();
                    data.put("PRODUCT_ID", pid);
                    data.put("PRODUCT_NAME", pnm);
                    data.put("PRODUCT_CATEGORY", pc);
                    data.put("PRODUCT_QUANTITY", pq);
                    data.put("PRODUCT_AVAILABILITY", pa ? "Available" : "Not Available");
                    data.put("PRODUCT_PRICE", pp);

                    db.update("tbl_products", data, "PRODUCT_ID=?", new String[]{pid});
                    Toast.makeText(ProductUpdate.this, "Product Updated Successfully", Toast.LENGTH_SHORT).show();

                    productId.setText("");
                    productName.setText("");
                    productPrice.setText("");
                    spinnerProductCategory.setSelection(0);
                    spinnerProductQuantity.setSelection(0);

                    startActivity(new Intent(ProductUpdate.this, Homepage.class));
                    finish();
                }
            }

            private boolean isProductExists(String pid) {
                String[] columns = {"PRODUCT_ID"};
                String selection = "PRODUCT_ID = ?";
                String[] selectionArgs = {pid};
                Cursor cursor = db.query("tbl_products", columns, selection, selectionArgs, null, null, null);
                boolean exists = cursor.getCount() > 0;
                cursor.close();
                return exists;
            }
        });
    }
}