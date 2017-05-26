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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaccion);

        mSensor = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];

        if (x <= 5) {
            Comunicacion.getInstance().enviar("derecha");
        } else if (x >= -5) {
            Comunicacion.getInstance().enviar("izquierda");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensor.registerListener(this, mSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    public static void setImage(String shoeNumber){
//        if ()
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
