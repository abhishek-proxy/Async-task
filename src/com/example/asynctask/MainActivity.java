package com.example.asynctask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	public TextView textview;
	public ProgressBar progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textview=(TextView) findViewById(R.id.txtName);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private class DownloadWebPageTask extends AsyncTask<String, Void, String>
	{

		@Override
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub
			String response="";
			for(String url:urls)
			{
				DefaultHttpClient client = new DefaultHttpClient();
		        HttpGet httpGet = new HttpGet(url);
		        try
		        {
		        	HttpResponse execute=client.execute(httpGet);
		        	InputStream content = execute.getEntity().getContent();
		        	BufferedReader buffreader= new BufferedReader(new InputStreamReader(content));
		        	String s = "";
		            while ((s = buffreader.readLine()) != null) {
		              response += s;
		            }
		        			
		        }catch (Exception e) {
		            e.printStackTrace();
		        }
			}
			return response;
		
		
		
		}
		@Override
	    protected void onPostExecute(String result) {
	      textview.setText(result);
	    }
	}

	  public void onClick(View view) {
	    DownloadWebPageTask task = new DownloadWebPageTask();
	    task.execute(new String[] { "http://www.google.co.in" });

	  }
	
	
}
