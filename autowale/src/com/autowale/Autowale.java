package com.autowale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

import alertdialog.CustomProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Autowale extends Activity implements OnClickListener,
		OnTouchListener {

	private EditText number_et;
	private ImageButton ver_code;
	private String[] echo;
	private CustomProgressDialog d;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.autowale);
		setReferenes();
		setListeners();

	}

	private void setListeners() {
		// TODO Auto-generated method stub
		ver_code.setOnClickListener(this);
		ver_code.setOnTouchListener(this);

	}

	private void setReferenes() {
		// TODO Auto-generated method stub
		number_et = (EditText) findViewById(R.id.number_et);
		ver_code = (ImageButton) findViewById(R.id.ver_code);
	}

	class MyAsyncTask_check extends AsyncTask<Void, Void, Integer> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			Log.i("MyAsyncTask_check", "in pre execute");
		}

		protected Integer doInBackground(Void... arg) {
			// TODO Auto-generated method stub
			Log.i("MyAsyncTask_check", "in do in background");
			// Todays Date

			HttpParams params = new BasicHttpParams();
			params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
					HttpVersion.HTTP_1_1);
			HttpClient httpclient = new DefaultHttpClient(params);
			HttpResponse response;

			// Fare and Distance
			HttpPost httppost = new HttpPost(
					"http://indiacommutes.com/DriverMobileApp/ .php");
			try {
				Log.i("try", "in try block");
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("number", number_et.getText().toString()));
				nameValuePairs.add(new BasicNameValuePair("a", "10"));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				response = httpclient.execute(httppost);

				HttpEntity entity = response.getEntity();
				InputStream is = null;

				is = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				sb.append(reader.readLine());
				String line = "0";
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				reader.close();
				String respo = sb.toString();
				Log.i("Todays records", respo);
				echo = respo.split("\\+");

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				Log.i("Excecption", "caught in exception");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.i("Excecption", "caught in exception");
				e.printStackTrace();
			} catch (Exception e) {
				Log.i("Excecption", "caught in exception");
				e.printStackTrace();
			}

			return 1;
		}

		protected void onPostExecute(Integer res) {
			// TODO Auto-generated method stub
			super.onPostExecute(null);
			Log.i("MyAsyncTask_check", "in post execute");
			d.dismiss();
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ver_code:
			if (number_et.getText().toString().length() == 10) {

				System.out.println(number_et.getText().toString().length());
				startActivity(new Intent(Autowale.this, Book.class));

			} else
				toast("Please enter a valid phone number");
			break;
		default:
			break;
		}
	}

	private void toast(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stubcase
		switch (v.getId()) {

		case R.id.ver_code:
			if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
				ver_code.setImageResource(R.drawable.book_freq_button_yellow);
			} else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
				ver_code.setImageResource(R.drawable.book_freq_button_up_yellow);
			}

			break;

		default:
			break;
		}

		return false;
	}

}
