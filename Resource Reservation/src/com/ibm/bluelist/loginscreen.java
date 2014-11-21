package com.ibm.bluelist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class loginscreen extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginscreen);
		Button b=(Button) findViewById(R.id.sub);
		final EditText et1=(EditText) findViewById(R.id.ssoid);
		final EditText et2=(EditText) findViewById(R.id.passw);
		
		b.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
			System.out.println("Button clicked");
			
			final String x=et1.getText().toString();
			final String y=et2.getText().toString();
			System.out.println("Entered username: " + x);
			System.out.println("Entered pwd: "+y );
			if(x.equalsIgnoreCase("syvd3") && y.equalsIgnoreCase("1hserus2"))
			{
			Intent i=new Intent (getApplicationContext(),startscreen.class);
			startActivity(i);
			}
			else
				et2.setError("Invalid Credentials");
		}
		}
	);
		

	}
	
}
