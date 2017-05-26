package com.example.ivandario.prueba;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.util.Observable;
import java.util.Observer;

public class Inicio extends AppCompatActivity implements Observer, SensorEventListener {

    private static final float SHAKE_THRESHOLD = 1.5f;
    private static final int SHAKE_WAIT_TIME_MS = 250;
    private long mShakeTime = 0;

    private SensorManager sensorManager;
    private Sensor acele;
    private Vibrator v;
    //private long[] pattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        acele = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);




        Comunicacion.getInstance().addObserver(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {


        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            detectShake(sensorEvent);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void detectShake(SensorEvent event) {
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
                long[] pattern = {0, 100, 1000, 300, 200, 100, 500, 200, 100};
                v.vibrate(pattern, -1);
                Intent jugarIn = new Intent(Inicio.this, Registro.class);
                startActivity(jugarIn);

            }
        }
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
        //v.cancel();
    }


    @Override
    public void update(Observable observable, Object o) {

    }
}
