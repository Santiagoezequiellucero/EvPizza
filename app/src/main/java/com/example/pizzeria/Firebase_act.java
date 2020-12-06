package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Modelos.Clientes;

public class Firebase_act extends AppCompatActivity {


    private EditText txtNombre, txtDestino, txtPromocion;
    private Button btnGuardar, btnListado;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_act);
        txtNombre =(EditText)findViewById(R.id.txtNombre);
        txtDestino =(EditText)findViewById(R.id.txtDestino);
        txtPromocion =(EditText)findViewById(R.id.txtPromocion);

        btnGuardar =(Button)findViewById(R.id.btnGuardar);
        btnListado =(Button)findViewById(R.id.btnListado);

        inicializarBase();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!txtNombre.getText().equals(""))
                {
                    Clientes c = new Clientes();

                    c.setNombre(txtNombre.getText().toString());
                    c.setDestino(txtDestino.getText().toString());
                    c.setPromocion(txtPromocion.getText().toString());

                    databaseReference.child("Clientes").child(c.getNombre()).setValue(c);
                    Toast.makeText(getBaseContext(), "Ha guardado cliente", Toast.LENGTH_LONG).show();

                }else
                {
                    Toast.makeText(getBaseContext(), "No ha guardado cliente", Toast.LENGTH_LONG).show();
                }
                Limpiar();
            }
        });

    }

    public void inicializarBase()
    {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }



    public void listadoClientes (View view){
        Intent i = new Intent(this,listado_Clientes_act.class);
        startActivity(i);
    }

    public void Limpiar()
    {
        txtNombre.setText("");
        txtDestino.setText("");
        txtPromocion.setText("");

    }
}