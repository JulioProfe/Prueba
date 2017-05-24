package com.example.ivandario.prueba;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class Inicio extends AppCompatActivity implements Observer, SensorEventListener{

    private SensorManager sensorManager;
    private Sensor acele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        acele = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Comunicacion.getInstance().addObserver(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float valores = sensorEvent.values[0];

        if (valores < -2){
            Intent jugarIn = new Intent(Inicio.this, Registro.class);
            startActivity(jugarIn);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

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
    public void update(Observable observable, Object o) {

    }
}
