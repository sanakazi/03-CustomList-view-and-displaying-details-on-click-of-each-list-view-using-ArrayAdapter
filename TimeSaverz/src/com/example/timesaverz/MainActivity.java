package com.example.timesaverz;

import com.example.timesaverz.adapter.*;
import com.example.timesaverz.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
  ListView list;
  WorkAdapter adapter;
  ArrayList<WorkModel> arr;
  String strJson;
  String id, name, email;
  JSONArray jsonArray;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    list= (ListView)findViewById(R.id.list);
	    arr = new ArrayList<WorkModel>();
	    AsyncWS task = new AsyncWS();
	    task.execute();
	    list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id1) {
				// TODO Auto-generated method stub
				long data = list.getItemIdAtPosition(position);
				String idx=String.valueOf(data);
				Log.d("position", idx);
				//Toast.makeText(MainActivity.this, "item click id "+ id1, Toast.LENGTH_SHORT).show();
				//Toast.makeText(MainActivity.this, "item click position "+ position, Toast.LENGTH_SHORT).show();
				String name1=list.getItemAtPosition(position).toString();
				Log.d("GetItemAtPosition", name1.toString());
				Intent intent = new Intent(MainActivity.this,SecondActivity.class);
			    // intent.putExtra("id", id);
				try {
					intent.putExtra("id", jsonArray.optJSONObject(position).getString("id"));
					intent.putExtra("name", jsonArray.optJSONObject(position).getString("name"));
					intent.putExtra("email", jsonArray.optJSONObject(position).getString("email"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	             
	            startActivity(intent);
			
			}
		});
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	class AsyncWS extends AsyncTask
	{
		ProgressDialog dialog;
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = new ProgressDialog(MainActivity.this);
			//dialog.setIndeterminate(true);
		    dialog.setCancelable(false);
			dialog.setMessage("Please wait...");

			dialog.show();

		}

		@Override
		protected Object doInBackground(Object... arg0) {
			// TODO Auto-generated method stub
			setData();
			return null;
		}
		
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			

			dialog.dismiss();
			  adapter=new WorkAdapter( MainActivity.this, arr );
	          list.setAdapter( adapter );
			   
			}
		
	}
	
	public void setData()
	{
		 HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost = new HttpPost("http://api.androidhive.info/contacts/");
		
		   try{
		 HttpResponse response = httpclient.execute(httppost);
               // write response to log
		    String str = inputStreamToString(response.getEntity().getContent()).toString();
		     strJson=str.toString();  
		     JSONObject  jsonRootObject = new JSONObject(strJson);
			   //Get the instance of JSONArray that contains JSONObjects
		        jsonArray = jsonRootObject.optJSONArray("contacts");	
		        Log.d("Json array", jsonArray.toString());
//Iterate the jsonArray and print the info of JSONObjects
		         for(int i=0; i < jsonArray.length(); i++)
		         {
		            JSONObject jsonObject = jsonArray.getJSONObject(i);
		        
		        //     int id = Integer.parseInt(jsonObject.optString("id").toString());
		            id = jsonObject.optString("id").toString();
		            Log.d("id",id.toString());
		             name = jsonObject.optString("name").toString();
		             Log.d("name:", name);
		           email = jsonObject.optString("email").toString();
		       

		              //******* Firstly take data in model object ******/
		              WorkModel sched = new WorkModel();
		               sched.setName(name);
		               sched.setEmail(email);
		  
		            /******** Take Model Object in ArrayList **********/
		          arr.add( sched);
		          
		         }
		       
		       /**************** Create Custom Adapter *********/
		    
		   }
		   
		   catch (ClientProtocolException e) {
			    // TODO Auto-generated catch block
			} catch (IOException e) {
			    // TODO Auto-generated catch block
			}
			 catch (JSONException e) {e.printStackTrace();}
	}
	
	
	 private StringBuilder inputStreamToString(InputStream is) {
	     String line = "";
	     StringBuilder total = new StringBuilder();
	     // Wrap a BufferedReader around the InputStream
	     BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	     // Read response until the end
	     try {
	      while ((line = rd.readLine()) != null) { 
	        total.append(line); 
	      }
	     } catch (IOException e) {
	      e.printStackTrace();
	     }
	     // Return full string
	     return total;
	    }

	/* 
	 //When clicked from main Activity
	public void onItemClick(int mPosition) {
		// TODO Auto-generated method stub
		
		Toast.makeText(MainActivity.this, "You Clicked" + mPosition, Toast.LENGTH_SHORT).show();
		 
	}
    
	 */
	 
}
