package com.ibm.bluelist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class firstscreen extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstscrren);
		Button bu=(Button) findViewById(R.id.loj);
		bu.setOnClickListener(this);
	}
	public void onClick(View v)
	{
		System.out.println("Button clicked");
			
			Intent i=new Intent (getApplicationContext(),loginscreen.class);
			startActivity(i);
		
	}
}
