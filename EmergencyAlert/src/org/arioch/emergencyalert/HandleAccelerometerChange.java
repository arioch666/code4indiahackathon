package org.arioch.emergencyalert;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

class HandleAccelerometerChange implements SensorEventListener
{
	Sensor accelerometer, proximitySensor;
	Context context;

	public HandleAccelerometerChange(Context context)
	{
		this.context = context;
		initilizeSensors();
	}

	private void initilizeSensors()
	{
		if(initializeAccelerometer())
		{
			if(initializeProximitySensor())
			{
				Toast complete = new Toast(context);
				complete.setText("Completed Initilization");
				complete.show();
			}
			else
			{
				Toast incomplete = new Toast(context);
				incomplete.setText("Initilization failed");
				incomplete.show();
			}
		}
		
		
	}

	private boolean initializeProximitySensor()
	{
		Sensor defaultProximitySensor = ((SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE)).getDefaultSensor(Sensor.TYPE_PROXIMITY);
		
		if (defaultProximitySensor == null)
		{
			Toast noAccelerometer = new Toast(context);
			noAccelerometer.setText("Your Device Does not have an Proximity Sensor. Buy a better Device");
			noAccelerometer.show();
			return false;
		} else
		{
			accelerometer = defaultProximitySensor;
			Toast setAccelerometer = new Toast(context);
			setAccelerometer.setText("Accelerometer Set");
			setAccelerometer.show();
			return true;
		}
	}

	private Boolean initializeAccelerometer()
	{
		Sensor defaultAccelerometer = ((SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE)).getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		if (defaultAccelerometer == null)
		{
			Toast noAccelerometer = new Toast(context);
			noAccelerometer.setText("Your Device Does not have an accelerometer. Buy a better Device");
			noAccelerometer.show();
			return false;
		} else
		{
			accelerometer = defaultAccelerometer;
			Toast setAccelerometer = new Toast(context);
			setAccelerometer.setText("Accelerometer Set");
			setAccelerometer.show();
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
	public void onSensorChanged(SensorEvent arg0)
	{
		// TODO Auto-generated method stub
	}

}
