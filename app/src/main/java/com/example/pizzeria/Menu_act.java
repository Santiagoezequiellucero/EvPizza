package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

public class Menu_act extends AppCompatActivity {
    private VideoView videoView;
    private TextView tvInfoMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_act);
        tvInfoMenu = (TextView)findViewById(R.id.tvInfoMenu);
        tvInfoMenu.setText("Agradecemos que quieras trabajar con nosotros en pizza's");
        videoView = (VideoView)findViewById(R.id.vd);
        String ruta = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(ruta);
        videoView.setVideoURI(uri);
        MediaController media = new MediaController(this);
        videoView.setMediaController(media);
        videoView.start();


    }
    public void gestionClientes(View view){

        Intent i =  new Intent(this, Firebase_act.class);
        startActivity(i);
    }


    public void promociones(View view){

        ArrayList<String> clientes = new ArrayList<String>();
        Intent i = new Intent(this, promociones_act.class);

        clientes.add("(elija un cliente)");
        clientes.add("Ramiro");
        clientes.add("Rosa");
        clientes.add("Robert");

        i.putExtra("listaClientes", clientes);

        startActivity(i);
    }
}