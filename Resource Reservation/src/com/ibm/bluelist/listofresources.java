package com.ibm.bluelist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

import com.ibm.mobile.services.data.IBMDataException;
import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMQuery;

import android.app.Activity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class listofresources extends Activity{
	protected static final String CLASS_NAME = "ListOfResources";
	protected List<ResourceData> itemList = new ArrayList<ResourceData>();

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
			public void onItemClick(AdapterView<?> parent,final View view,int position,long id)
			{
				listItems(((TextView) view).getText().toString());
			}
		});
	}

		public void listItems(final String itemSelected) {
			try {
				IBMQuery<ResourceData> query = IBMQuery.queryForClass(ResourceData.class);
				// Query all the Item objects from the server.
				query.find().continueWith(new Continuation<List<ResourceData>, Void>() {
					
					@Override
					public Void then(Task<List<ResourceData>> task) throws Exception {
						final List<ResourceData> objects = task.getResult();
						// Log if the find was cancelled.
						if (task.isCancelled()){
							Log.e(CLASS_NAME, "Exception : Task " + task.toString() + " was cancelled.");
						}
						// Log error message, if the find task fails.
						else if (task.isFaulted()) {
							Log.e(CLASS_NAME, "Exception : " + task.getError().getMessage());
						}
						
						
						// If the result succeeds, load the list.
						else {
							// Clear local itemList.
							// We'll be reordering and repopulating from DataService.
//							itemList.clear();
							for(IBMDataObject item:objects) {
								itemList.add((ResourceData) item);
							}
//	                        sortItems(itemList);
//	                        lvArrayAdapter.notifyDataSetChanged();

							AlertDialog alertDialog = new AlertDialog.Builder(
				                    listofresources.this).create();
					    
							alertDialog.setTitle("Count");
							int numberOfInstances = 0;
							for(ResourceData data: itemList)
							{
								if(data.getResName().equalsIgnoreCase(itemSelected))
									numberOfInstances++;		
							}
							
							if(numberOfInstances >= 4)
							{
								alertDialog.setMessage("This Resource has reached maximux allocations.");
								
								alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int which) {
												Toast.makeText(getApplicationContext(),
														"Please Choose another resource",
														Toast.LENGTH_SHORT).show();
												itemList.clear();
									}
								});
							}
							else
							{
								alertDialog.setMessage("This Resource was Requested Previously: " + numberOfInstances);
								
								alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int which) {
//										String product =((TextView) view).getText().toString();
										Intent i=new Intent(getApplicationContext(),selecttime.class);
										i.putExtra("product",itemSelected);
										startActivity(i);        
										itemList.clear();
									}
								});
							}
					    alertDialog.show();
					    
						}
						return null;
					}
				},Task.UI_THREAD_EXECUTOR);
				
			}  catch (IBMDataException error) {
				Log.e(CLASS_NAME, "Exception : " + error.getMessage());
			}
		}

}
