/*
 * Copyright 2014 IBM Corp. All Rights Reserved
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ibm.bluelist;


import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import bolts.Continuation;
import bolts.Task;

import com.ibm.mobile.services.data.IBMDataException;
import com.ibm.mobile.services.data.IBMDataObject;
import com.ibm.mobile.services.data.IBMObjectResult;

public class EditActivity extends Activity {

	String originalItem;
	int location;
	BlueListApplication blApplication;
	List<Item> itemList;
	public static final String CLASS_NAME="EditActivity";
	
	@Override
	/**
	 * onCreate called when edit activity is created.
	 * 
	 * Sets up the application, sets listeners, and gets intent info from calling activity.
	 *
	 * @param savedInstanceState
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* Get application context, item list. */
		blApplication = (BlueListApplication) getApplicationContext();
		itemList = blApplication.getItemList();
		setContentView(R.layout.activity_edit);
		
		/* Information required to edit item. */
		Intent intent = getIntent();
	    originalItem = intent.getStringExtra("ItemText");
	    location = intent.getIntExtra("ItemLocation", 0);
		EditText itemToEdit = (EditText) findViewById(R.id.itemToEdit);
		itemToEdit.setText(originalItem);
		
		/* Set key listener for edittext (done key to accept item to list). */
		itemToEdit.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId==EditorInfo.IME_ACTION_DONE){
                	finishedEdit(v);
                    return true;
                }
                return false;
            }
        });
	}

	/**
	 * On completion of edit, edit itemList, return to main activity with edit return code.
	 * @param View v
	 */
	public void finishedEdit(View v) {
		Item item = itemList.get(location);
		EditText itemToEdit = (EditText) findViewById(R.id.itemToEdit);
		String text = itemToEdit.getText().toString();
		item.setName(text);
		/**
		 * IBMObjectResult is used to handle the response from the server after 
		 * either creating or saving an object.
		 * 
		 * onResult is called if the object was successfully saved.
		 * onError is called if an error occurred saving the object. 
		 */
		item.save().continueWith(new Continuation<IBMDataObject, Void>() {

			@Override
			public Void then(Task<IBMDataObject> task) throws Exception {
				if(task.isCancelled()) {
                    Log.e(CLASS_NAME, "Exception : " + task.toString() + " was cancelled.");
                }

				else if (task.isFaulted()) {
					Log.e(CLASS_NAME, "Exception : " + task.getError().getMessage());
				}
				
				else {
                    Intent returnIntent = new Intent();
                    setResult(BlueListApplication.EDIT_ACTIVITY_RC, returnIntent);
                    finish();
				}
				return null;
			}
			
		},Task.UI_THREAD_EXECUTOR);

	}
}
