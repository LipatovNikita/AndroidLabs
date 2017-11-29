package android.lab4;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private TextView accelerometerXResult;
    private TextView accelerometerYResult;
    private TextView accelerometerZResult;
    private TextView magneticXResult;
    private TextView magneticYResult;
    private TextView magneticZResult;
    private TextView proximityResult;
    private TextView lightResult;
    private ImageView image;
    private View view;
    private Button reloadButton;

    private SensorManager sensorManager;
    private Sensor accelerometer = null;
    private Sensor magnetic = null;
    private Sensor proximity = null;
    private Sensor light = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        accelerometerXResult = (TextView) findViewById(R.id.accelerometerXResult);
        accelerometerYResult = (TextView) findViewById(R.id.accelerometerYResult);
        accelerometerZResult = (TextView) findViewById(R.id.accelerometerZResult);
        magneticXResult = (TextView) findViewById(R.id.magneticXResult);
        magneticYResult = (TextView) findViewById(R.id.magneticYResult);
        magneticZResult = (TextView) findViewById(R.id.magneticZResult);
        proximityResult = (TextView) findViewById(R.id.proximityResult);
        lightResult = (TextView) findViewById(R.id.lightResult);
        image = (ImageView) findViewById(R.id.image);
        view = getWindow().getDecorView().getRootView();
        reloadButton = (Button) findViewById(R.id.reload);
        reloadButton.setVisibility(View.INVISIBLE);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
            proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometerXResult.setText(Double.toString(new BigDecimal(event.values[0]).setScale(2, RoundingMode.UP).doubleValue()));
            accelerometerYResult.setText(Double.toString(new BigDecimal(event.values[1]).setScale(2, RoundingMode.UP).doubleValue()));
            accelerometerZResult.setText(Double.toString(new BigDecimal(event.values[2]).setScale(2, RoundingMode.UP).doubleValue()));
            image.setX(image.getX() + Math.round(event.values[0])*(-2));
            image.setY(image.getY() + Math.round(event.values[1])*2);
            if ((image.getX() < -view.getRight()) || (image.getX() > view.getRight()) ||
                    (image.getY() < -view.getTop()) || (image.getY() > view.getBottom())) {
                reloadButton.setVisibility(View.VISIBLE);
                image.setImageDrawable(null);
            }
        }

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magneticXResult.setText(Double.toString(new BigDecimal(event.values[0]).setScale(1, RoundingMode.UP).doubleValue()));
            magneticYResult.setText(Double.toString(new BigDecimal(event.values[1]).setScale(1, RoundingMode.UP).doubleValue()));
            magneticZResult.setText(Double.toString(new BigDecimal(event.values[2]).setScale(1, RoundingMode.UP).doubleValue()));
        }
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            proximityResult.setText(Double.toString(new BigDecimal(event.values[0]).setScale(1, RoundingMode.UP).doubleValue()));
        }
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lightResult.setText(Double.toString(new BigDecimal(event.values[0]).setScale(1, RoundingMode.UP).doubleValue()));
        }
    }

    public void onReloadClick(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onStart(){
        super.onStart();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, sensorManager.SENSOR_DELAY_GAME);
        } else {
            accelerometerXResult.setText("Такой датчик не поддерживается");
            accelerometerYResult.setText("Такой датчик не поддерживается");
            accelerometerZResult.setText("Такой датчик не поддерживается");
        }
        if (magnetic != null) {
            sensorManager.registerListener(this, magnetic, sensorManager.SENSOR_DELAY_NORMAL);
        } else {
            magneticXResult.setText("Такой датчик не поддерживается");
            magneticYResult.setText("Такой датчик не поддерживается");
            magneticZResult.setText("Такой датчик не поддерживается");
        }
        if (proximity != null) {
            sensorManager.registerListener(this, proximity, sensorManager.SENSOR_DELAY_NORMAL);
        } else {
            proximityResult.setText("Такой датчик не поддерживается");
            proximityResult.setText("Такой датчик не поддерживается");
            proximityResult.setText("Такой датчик не поддерживается");
        }
        if (light != null) {
            sensorManager.registerListener(this, light, sensorManager.SENSOR_DELAY_NORMAL);
        } else {
            lightResult.setText("Такой датчик не поддерживается");
            lightResult.setText("Такой датчик не поддерживается");
            lightResult.setText("Такой датчик не поддерживается");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this, accelerometer);
        sensorManager.unregisterListener(this, magnetic);
        sensorManager.unregisterListener(this, proximity);
        sensorManager.unregisterListener(this, light);
    }
}
