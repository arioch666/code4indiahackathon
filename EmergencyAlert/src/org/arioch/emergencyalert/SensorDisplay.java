package org.arioch.emergencyalert;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SensorDisplay extends Activity implements SensorEventListener
{
	Sensor accelerometer, proximitySensor;
	SensorManager sensorManager;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_display);
		buildAndBind();
		initilizeSensors();
	}

	private void buildAndBind()
	{
		Button start = (Button) findViewById(R.id.startButton);
		Button stop = (Button) findViewById(R.id.stopButton);
		initializeClickListeners(start, stop);
		
	}

	private void initializeClickListeners(Button start, Button stop)
	{	
		start.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				findViewById(R.id.stopButton).setVisibility(v.VISIBLE);
				findViewById(R.id.startButton).setVisibility(v.INVISIBLE);
				
			}
		});

		stop.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				findViewById(R.id.stopButton).setVisibility(v.INVISIBLE);
				findViewById(R.id.startButton).setVisibility(v.VISIBLE);
			}
		});
	}

	private void initilizeSensors()
	{
		if(initializeAccelerometer())
		{
			
			if(initializeProximitySensor())
			{
				Toast.makeText(this, "Initilization Complete", Toast.LENGTH_SHORT).show();		
				
			}
			else
			{
				Toast.makeText(this, "initilization failed", Toast.LENGTH_SHORT).show();
			}
		}
	}

	private boolean initializeProximitySensor()
	{
		Sensor defaultProximitySensor = ((SensorManager) 
				getSystemService(Context.SENSOR_SERVICE)).getDefaultSensor(Sensor.TYPE_PROXIMITY);
		
		if (defaultProximitySensor == null)
		{
			Toast.makeText(this, "Your Device Does not have a proximity sensor. Buy a better Device", Toast.LENGTH_SHORT).show();
			return false;
		} else
		{
			proximitySensor = defaultProximitySensor;
			Toast.makeText(this, "Proximity Sensor set", Toast.LENGTH_SHORT).show();
			return true;
		}
	}

	private Boolean initializeAccelerometer()
	{
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		
		Sensor defaultAccelerometer = ((SensorManager) 
				getSystemService(Context.SENSOR_SERVICE)).getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		if (defaultAccelerometer == null)
		{
			Toast.makeText(this, "Your Device Does not have an accelerometer. Buy a better Device", Toast.LENGTH_SHORT).show();
			return false;
		} else
		{
			accelerometer = defaultAccelerometer;
			Toast.makeText(this, "Accelerometer Set", Toast.LENGTH_SHORT).show();
			return true;
		}
//		TextView stream = (TextView) context.findViewById(R.id.stream);
//		stream.setText(displaySensorList.toString());
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1)
	{

	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		if(accelerometer!= null && event.sensor.getType() == accelerometer.getType())
		{	
			shakeDetect(event);
		}
		else if(proximitySensor!=null && event.sensor.getType() == proximitySensor.getType())
		{
			detectProximity(event);
		}
	}

	private void detectProximity(SensorEvent event)
	{
		// TODO Auto-generated method stub
		((TextView) findViewById(R.id.stream)).setText("x:"+event.values[0]+" y:"+event.values[1]+" z:"+event.values[2]);
	}

	private void shakeDetect(SensorEvent event)
	{
			((TextView) findViewById(R.id.state)).setText("x:"+event.values[0]+" y:"+event.values[1]+" z:"+event.values[2]);
	}

	@Override
	protected void onResume()
	{
			super.onResume();
			if(accelerometer!=null)
			{
				sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
			}
			if(proximitySensor!=null)
			{
				sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
			}			
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		sensorManager.unregisterListener(this);
	}
}
