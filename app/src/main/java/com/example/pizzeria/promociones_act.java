package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Modelos.Promociones;

public class promociones_act extends AppCompatActivity {

    private Spinner spinner;
    private EditText txtPromo, txtEnvio;
    private TextView txtResultado, txtTotal;
    int promo,  total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promociones_act);

        spinner = (Spinner)findViewById(R.id.spinner);
        txtPromo = (EditText)findViewById(R.id.txtPromo);
        txtEnvio = (EditText)findViewById(R.id.txtEnvio);
        txtResultado = (TextView)findViewById(R.id.txtResultado);
        txtTotal = (TextView)findViewById(R.id.txtTotal);

        ArrayList<String > listaClientes = (ArrayList<String>)getIntent().getSerializableExtra("listaClientes");
        ArrayAdapter<String> adapt = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaClientes);
        spinner.setAdapter(adapt);

    }

    public void Calcular(View v)
    {
        Promociones p = new Promociones();


        String cliente = spinner.getSelectedItem().toString();

        String promocion= txtPromo.getText().toString();
        int envio = Integer.parseInt(txtEnvio.getText().toString());

        if (promocion.isEmpty()){
            Toast.makeText(this, "No ha escrito una promoción", Toast.LENGTH_LONG).show();
        }else {

            switch (promocion) {
                case "Pizzas promo":
                    promo = p.getPizzaPromo();
                    total = promo + envio;
                    txtTotal.setText(total);
                    break;
                case "Master pizza":
                    promo = p.getMasterPizza();
                    total = promo + envio;
                    txtTotal.setText(total);
                    break;
                case "Pizzas max":
                    promo = p.getPizzaMax();
                    total = promo + envio;
                    txtTotal.setText(total);
                    break;

            }
        }

        txtResultado.setText("Estimado "+cliente+" el total segun la promocion y envio es: ");

    }
}