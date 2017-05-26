package com.example.ivandario.prueba;

import android.content.Intent;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import serial.Registrar;
import serial.Select;


public class Seleccion extends AppCompatActivity implements View.OnClickListener {


    private ImageButton shoe1;
    private ImageButton shoe2;
    private ImageButton shoe3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);

        shoe1 = (ImageButton) findViewById(R.id.shoe1);
        shoe2 = (ImageButton) findViewById(R.id.shoe2);
        shoe3 = (ImageButton) findViewById(R.id.shoe3);

        shoe1.setOnClickListener(this);
        shoe2.setOnClickListener(this);
        shoe3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == shoe1.getId()) {

            // MENSAJE


            // SELECCIÓN
            Select eleccion = new Select(Comunicacion.getInstance().getName(), 0);
            Comunicacion.getInstance().enviar(eleccion);

            // CAMBIO DE INTENTO
            Intent jugarIn = new Intent(Seleccion.this, Instrucciones.class);
            startActivity(jugarIn);
        }
        if (v.getId() == shoe2.getId()) {

            // MENSAJE


            // SELECCIÓN
            Select eleccion = new Select(Comunicacion.getInstance().getName(), 1);
            Comunicacion.getInstance().enviar(eleccion);

            // CAMBIO DE INTENTO
            Intent jugarIn = new Intent(Seleccion.this, Instrucciones.class);
            startActivity(jugarIn);
        }
        if (v.getId() == shoe3.getId()) {

            // MENSAJE


            // SELECCIÓN
            Select eleccion = new Select(Comunicacion.getInstance().getName(), 2);
            Comunicacion.getInstance().enviar(eleccion);

            // CAMBIO DE INTENTO
            Intent jugarIn = new Intent(Seleccion.this, Instrucciones.class);
            startActivity(jugarIn);
        }
    }
}
