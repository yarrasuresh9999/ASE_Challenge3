package com.ibm.bluelist;

import android.app.Activity;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class selecttime extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selecttime);
System.out.println("Getting Text view");		
		TextView itv=(TextView) findViewById(R.id.game);
		
		Intent i=getIntent();
		String product=i.getStringExtra("product");
		System.out.println("Selected product: " + product);
		itv.setText(product);
	}
}
