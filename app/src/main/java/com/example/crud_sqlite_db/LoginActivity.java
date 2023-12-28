package com.example.crud_sqlite_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView registerRedirectText;
    DbAssist assist = new DbAssist(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);

        registerRedirectText = findViewById(R.id.registerRedirectText);

        db = assist.getReadableDatabase();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = loginUsername.getText().toString();
                String pw = loginPassword.getText().toString();

                if (un.isEmpty() || pw.isEmpty()){
                    Toast.makeText(LoginActivity.this, "All Fields are Mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    String[] columns = {"USERNAME"};
                    String selection = "USERNAME = ?";
                    String[] selectionArgs = {un};

                    Cursor cursor = db.query("tbl_users", columns, selection, selectionArgs, null, null, null);
                    int count = cursor.getCount();
                    cursor.close();

                    if (count > 0) {
                        String selectionWithPassword = "USERNAME = ? AND PASSWORD = ?";
                        String[] selectionArgsWithPassword = {un, pw};

                        Cursor passwordCursor = db.query("tbl_users", columns, selectionWithPassword, selectionArgsWithPassword, null, null, null);
                        int passwordCount = passwordCursor.getCount();
                        passwordCursor.close();

                        if (passwordCount > 0) {
                            Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, Homepage.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registerRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class ));
            }
        });
    }
}