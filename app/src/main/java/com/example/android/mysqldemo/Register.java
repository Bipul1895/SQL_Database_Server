package com.example.android.mysqldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText Et_Name,Et_Username,Et_Password;
    String name,user_name,user_pass;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Et_Name=findViewById(R.id.et_register_name);
        Et_Username=findViewById(R.id.et_register_username);
        Et_Password=findViewById(R.id.et_register_password);

        register=findViewById(R.id.btn_register_user);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=Et_Name.getText().toString();
                user_name=Et_Username.getText().toString();
                user_pass=Et_Password.getText().toString();
                String method="register";

                BackgroundTask backgroundTask=new BackgroundTask(Register.this);
                backgroundTask.execute(method,name,user_name,user_pass);

                finish();

            }
        });


    }
}
