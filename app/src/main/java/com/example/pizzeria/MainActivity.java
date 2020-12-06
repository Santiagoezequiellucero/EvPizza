package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edUser, edPassword;
    boolean acces(String user, String password){
        return user.equalsIgnoreCase("android") && password.equalsIgnoreCase("123");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edUser = (EditText)findViewById(R.id.edUser);
        edPassword = (EditText)findViewById(R.id.edPassword);
    }
    public void login(View view){

            Intent i = new Intent(this, Menu_act.class);
            startActivity(i);

    }
}