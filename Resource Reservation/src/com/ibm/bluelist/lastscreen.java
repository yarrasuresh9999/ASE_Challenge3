package com.ibm.bluelist;

import com.ibm.mobile.services.data.IBMDataObject;

import bolts.Continuation;
import bolts.Task;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class lastscreen extends Activity implements OnClickListener{
	TextView tv1,tv2;
	EditText et;
	Button b;
	public static final String CLASS_NAME="lastscreen";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lastscreen);
		System.out.println("Getting Text view and time ");
		Intent i=getIntent();
		tv1=(TextView) findViewById(R.id.resname);
		tv2=(TextView) findViewById(R.id.selectedtm);
		String p=i.getStringExtra("a");
		String q=i.getStringExtra("b");
		tv1.setText(p);
		tv2.setText(q);
		System.out.println("name:"+p);
		System.out.println("time:"+q);
		et=(EditText) findViewById(R.id.editText1);
		b=(Button) findViewById(R.id.button1);
		b.setOnClickListener(this);
		
	}
	public void onClick(View v)
	{
		System.out.print("Finished ");
		String e=et.getText().toString();
		
		if(e.equalsIgnoreCase("syvd3")||e.equalsIgnoreCase("16168343"))
		{
			AlertDialog alertDialog = new AlertDialog.Builder(
                    lastscreen.this).create();

	    // Setting Dialog Title
	    alertDialog.setTitle("Success");
	
	    // Setting Dialog Message
	    alertDialog.setMessage("Your Request was Submitted Thank you");

	    // Setting OK Button
	    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	            	Intent i=new Intent (getApplicationContext(),startscreen.class);
	    			startActivity(i);
            }
    });

    // Showing Alert Message
    alertDialog.show();
		ResourceData i=new ResourceData();
		i.setResName(tv1.getText().toString());
		i.setReqTime(tv2.getText().toString());
		i.setName(et.getText().toString());
		
		i.save().continueWith(new Continuation<IBMDataObject, Void>() {

			@Override
			public Void then(Task<IBMDataObject> task) throws Exception {
                // Log if the save was cancelled.
                if (task.isCancelled()){
                    Log.e(CLASS_NAME, "Exception : Task " + task.toString() + " was cancelled.");
                }
				 // Log error message, if the save task fails.
				else if (task.isFaulted()) {
					Log.e(CLASS_NAME, "Exception : " + task.getError().getMessage());
				}
                System.out.print("saved succesfully");
				return null;
			}

		});
			
		}
		else
			et.setError("Invalid Credentials");
	}
	
}
