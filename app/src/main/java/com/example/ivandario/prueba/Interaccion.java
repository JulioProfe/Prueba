package com.example.ivandario.prueba;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class Interaccion extends AppCompatActivity implements SensorEventListener, Observer{
    private SensorManager mSensor;
    private TextView xd, yd, zd;
    private static final float ROTATION_THRESHOLD = 2.0f;
    private static final int ROTATION_WAIT_TIME_MS = 100;
    private long mRotationTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaccion);

        mSensor = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        xd = (TextView) findViewById(R.id.x);
        yd = (TextView) findViewById(R.id.y);
        zd = (TextView) findViewById(R.id.z);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];

        if (x <= 3) {
            Comunicacion.getInstance().enviar("izquierda");
        } else if (x > -2.5f) {
            Comunicacion.getInstance().enviar("derecha");
        }

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
