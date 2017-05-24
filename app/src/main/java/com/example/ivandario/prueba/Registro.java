package com.example.ivandario.prueba;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

public class Registro extends AppCompatActivity implements Observer, SensorEventListener {

    private SensorManager sensorManager;
    private Sensor acele;
    private EditText name;

    private Typeface type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        acele = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        name = (EditText) findViewById(R.id.userName);

        Comunicacion.getInstance().addObserver(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float valores = sensorEvent.values[0];
        String nombre = name.getText().toString();
        if ((valores < -2 || valores > 2) && !nombre.isEmpty()) {
                Intent jugarIn = new Intent(Registro.this, Seleccion.class);
                startActivity(jugarIn);
        }

    }

    private void aviso(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, acele, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
