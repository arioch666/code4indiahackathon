package org.arioch.emergencyalert;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SensorDisplay extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_display);
		buildAndBind();
		
	}

	private void buildAndBind()
	{
		Button start = (Button) findViewById(R.id.startButton);
		Button stop = (Button) findViewById(R.id.stopButton);
		initializeClickListeners(start, stop);
		new HandleAccelerometerChange(this);
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

	

}
