package com.example.android.mysqldemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button login,register;
    EditText Et_Name,Et_pass;
    String login_name,login_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.btn_login);
        register=findViewById(R.id.btn_register);

        Et_Name=findViewById(R.id.et_username);
        Et_pass=findViewById(R.id.et_password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_name=Et_Name.getText().toString();
                login_pass=Et_pass.getText().toString();

                String method="login";

                BackgroundTask backgroundTask=new BackgroundTask(MainActivity.this);
                backgroundTask.execute(method,login_name,login_pass);

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

    }
}
