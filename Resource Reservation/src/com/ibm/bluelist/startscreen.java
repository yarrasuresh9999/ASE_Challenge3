package com.ibm.bluelist;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;

public class startscreen extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startscreen);
		Button mybutton=(Button) findViewById(R.id.button1);
		Button mybut2=(Button) findViewById(R.id.button2);

		mybutton.setOnClickListener(this);
		mybut2.setOnClickListener(this);
	}
	
	public void onClick(View v)
	{
		System.out.println("Button clicked");
		if(v.getId() == R.id.button1)
		{
			System.out.println("Button 1 clicked");
			Intent i=new Intent (getApplicationContext(),listofresources.class);
			startActivity(i);
		}
		else if(v.getId() == R.id.button2)
		{
			System.out.println("Button 2 clicked");
			Intent i=new Intent (getApplicationContext(),howitworks.class);
			startActivity(i);
		}
	}
}
