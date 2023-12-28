package com.example.crud_sqlite_db;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProductDelete extends AppCompatActivity {

    Button btnProductDelete;
    EditText productId;
    DbHelper helper = new DbHelper(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_delete);

        btnProductDelete = findViewById(R.id.product_delete_button);

        productId = findViewById(R.id.product_id);

        db = helper.getWritableDatabase();

        btnProductDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pid = productId.getText().toString();

                if (!isProductExists(pid)) {
                    Toast.makeText(ProductDelete.this, "Product ID not found", Toast.LENGTH_SHORT).show();
                    return;
                }else if (pid.isEmpty()){
                    Toast.makeText(ProductDelete.this, "Please Enter the Product Id", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(ProductDelete.this);
                    alert.setTitle("Delete Confirmation");
                    alert.setMessage("Are you sure to delete this product from the mart?");
                    alert.setPositiveButton("Yes, Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.delete("tbl_products", "PRODUCT_ID=?", new String[]{pid});
                            Toast.makeText(ProductDelete.this, "Product Deleted Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ProductDelete.this, Homepage.class));
                        }
                    });

                    alert.setNegativeButton("No, Go Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog obj = alert.create();
                    obj.show();
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