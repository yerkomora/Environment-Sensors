package cl.infomatico.android.examples.environmentsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // AppCompatActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvData = findViewById(R.id.tvData);

        // Sensor Light
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (mPressure == null)
            tvData.setText(R.string.sensors_light_without);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mSensorManager != null)
            mSensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mSensorManager != null)
            mSensorManager.unregisterListener(this);
    }

    // MainActivity

    private AppCompatTextView tvData;

    // SensorEventListener

    private SensorManager mSensorManager;
    private Sensor mPressure = null;

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        float brightness = event.values[0];
        tvData.setText(String.format("%s lx.", String.valueOf(brightness)));
    }
}