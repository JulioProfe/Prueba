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

import serial.Registrar;

public class Registro extends AppCompatActivity implements SensorEventListener {

    private static final float SHAKE_THRESHOLD = 1.5f;
    private static final int SHAKE_WAIT_TIME_MS = 250;
    private long mShakeTime = 0;

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

        type = Typeface.createFromAsset(getAssets(), "fonts/FuturaExtraBoldIta.ttf");

        name = (EditText) findViewById(R.id.userName);
        name.setTypeface(type);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        String nombre = name.getText().toString();

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            detectShake(sensorEvent, nombre);
        }

    }

    private void detectShake(SensorEvent event, String nameUser) {
        long now = System.currentTimeMillis();


        if ((now - mShakeTime) > SHAKE_WAIT_TIME_MS) {
            mShakeTime = now;

            float gX = event.values[0] / SensorManager.GRAVITY_EARTH;
            float gY = event.values[1] / SensorManager.GRAVITY_EARTH;
            float gZ = event.values[2] / SensorManager.GRAVITY_EARTH;

            // gForce will be close to 1 when there is no movement
            double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            // Change background color if gForce exceeds threshold;
            // otherwise, reset the color
            if (gForce > SHAKE_THRESHOLD) {

                if (!nameUser.isEmpty() && nameUser!=null) {
                    Registrar registro = new Registrar(nameUser);
                    Comunicacion.getInstance().enviar(registro);
                    Intent jugarIn = new Intent(Registro.this, Seleccion.class);
                    startActivity(jugarIn);


                } else {
                    aviso("Ingresa un nombre para jugar");
                }
            }
        }
    }

    private void aviso(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, acele, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}
