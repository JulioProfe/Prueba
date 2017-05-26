package com.example.ivandario.prueba;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.RippleDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import serial.Posicion;

public class Interaccion extends AppCompatActivity implements SensorEventListener, Observer {
    private SensorManager mSensor;

    private TextView nombre;

    private Typeface type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaccion);


        type = Typeface.createFromAsset(getAssets(), "fonts/FuturaExtraBoldIta.ttf");


        mSensor = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        nombre = (TextView) findViewById(R.id.name);

        nombre.setText(Comunicacion.getInstance().getName().toUpperCase());

        nombre.setTypeface(type);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        Posicion tempP = new Posicion(Comunicacion.getInstance().getName(), x);
        Comunicacion.getInstance().enviar(tempP);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensor.registerListener(this, mSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mSensor.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
