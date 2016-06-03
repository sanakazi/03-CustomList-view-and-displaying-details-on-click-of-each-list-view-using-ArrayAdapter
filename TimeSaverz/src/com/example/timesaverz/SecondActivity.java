package com.example.timesaverz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.timesaverz.MainActivity.AsyncWS;
import com.example.timesaverz.adapter.WorkAdapter;
import com.example.timesaverz.model.WorkModel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class SecondActivity extends Activity {
	TextView tv1,tv2,tv3;
	ArrayList<WorkModel> arr;
	String name,email,id1;
	public static final String ID= "id123";
	public static final String NAME= "name";
	public static final String EMAIL="email";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		tv1=(TextView)findViewById(R.id.tv1);
		tv2=(TextView)findViewById(R.id.tv2);
		tv3=(TextView)findViewById(R.id.tv3);
		id1=getIntent().getStringExtra("id").toString();
		Log.d("SE ID1 :",id1); 
		String name1=getIntent().getStringExtra("name").toString();
		Log.d("SE NAME1",name1);
		String email1=getIntent().getStringExtra("email").toString();
		Log.d("SE ENAIL1",email1);
	
		 tv3.setText(id1);
		 tv1.setText(name1);
		 tv2.setText(email1);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}
	
	
}
