package com.ibm.bluelist;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class listofresources extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listofresources);
		ListView lv=(ListView) findViewById(R.id.listView1);
		String[] res=new String[] {"X-Box", "Pool","Table Tennis", "Football Court","Rocket Ball Court","Batmenton Court","Dancing Room","Swiming"};
		ArrayList<String> reslist=new ArrayList<String>();
		reslist.addAll(Arrays.asList(res));
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,R.layout.simplerow,reslist);
		listAdapter.add("Womens Football");
		listAdapter.add("Basket Ball Court");
		lv.setAdapter(listAdapter);
		
		lv.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent,View view,int position,long id)
			{
				String product =((TextView) view).getText().toString();
				Intent i=new Intent(getApplicationContext(),selecttime.class);
				i.putExtra("product",product);
				startActivity(i);
			}
		}
				);
		
	}


}
