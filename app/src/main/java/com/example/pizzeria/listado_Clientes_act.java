package com.example.pizzeria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Modelos.Clientes;

public class listado_Clientes_act extends AppCompatActivity {

    private ListView list;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    private ArrayList<Clientes> listaClientes = new ArrayList<Clientes>();

    Clientes clientesSeleccionados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado__clientes_act);

        list = (ListView)findViewById(R.id.list);

        inicializarBase();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                clientesSeleccionados = (Clientes) parent.getItemAtPosition(position);
            }
        });

        databaseReference.child("Clientes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot objSnapshot: snapshot.getChildren())
                {
                    Clientes c = objSnapshot.getValue(Clientes.class);
                    listaClientes.add(c);

                    ArrayAdapter adapt = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, listaClientes);

                    list.setAdapter(adapt);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Eliminar(View view)
    {
        Clientes c = new Clientes();

        c.setNombre(clientesSeleccionados.getNombre());
        databaseReference.child("Clientes").child(c.getNombre()).removeValue();
        Toast.makeText(getBaseContext(), "Ha eliminado cliente", Toast.LENGTH_LONG).show();
    }

    public void inicializarBase()
    {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }
}