package com.example.crud_sqlite_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText registerName, registerEmail, registerMobile, registerUsername, registerPassword, registerConfirmPassword;
    Button registerButton;
    TextView loginRedirectText;
    DbAssist assist = new DbAssist(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerName = findViewById(R.id.signup_name);
        registerEmail = findViewById(R.id.signup_email);
        registerMobile = findViewById(R.id.signup_mobile);
        registerUsername = findViewById(R.id.signup_username);
        registerPassword = findViewById(R.id.signup_password);
        registerConfirmPassword = findViewById(R.id.signup_confirmPassword);

        registerButton = findViewById(R.id.signup_button);

        loginRedirectText = findViewById(R.id.loginRedirectText);

        db = assist.getWritableDatabase();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm = registerName.getText().toString();
                String em = registerEmail.getText().toString();
                String mb = registerMobile.getText().toString();
                String un = registerUsername.getText().toString();
                String pw = registerPassword.getText().toString();
                String cp = registerConfirmPassword.getText().toString();

                if (nm.length() <= 4 || nm.matches(".*\\d.*")) {
                    registerName.setError("Name should be atleast 5 characters long");
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(em).matches()) {
                    registerEmail.setError("Enter a valid email address");
                } else if (mb.length() != 10 || !TextUtils.isDigitsOnly(mb)) {
                    registerMobile.setError("Enter a valid 10 digit mobile number");
                } else if (un.length() <= 4 || !un.matches("^[a-zA-Z0-9_]*$")) {
                    registerUsername.setError("Username should be minimum 5 characters long");
                } else if (!pw.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$")) {
                    registerPassword.setError("Password must be 8 characters long, should contain atleast one uppercase, one lowercase," +
                            "one number and a special characters");
                } else if (!TextUtils.equals(pw, cp)) {
                    registerConfirmPassword.setError("Password do not match");
                } else {
                    ContentValues data = new ContentValues();
                    data.put("NAME", nm);
                    data.put("EMAIL", em);
                    data.put("MOBILE", mb);
                    data.put("USERNAME", un);
                    data.put("PASSWORD", pw);
                    data.put("CONFIRM_PASSWORD", cp);

                    db.insert("tbl_users",null,data);
                    Toast.makeText(RegisterActivity.this, "Glad to see you joined us!", Toast.LENGTH_SHORT).show();

                    registerName.setText("");
                    registerEmail.setText("");
                    registerMobile.setText("");
                    registerUsername.setText("");
                    registerPassword.setText("");
                    registerConfirmPassword.setText("");

                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}