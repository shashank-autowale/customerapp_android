package com.autowale;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class Book extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.book);
		setReferenes();
		setListeners();

	}

	private void setListeners() {
		// TODO Auto-generated method stub
		
	}

	private void setReferenes() {
		// TODO Auto-generated method stub
		
	}

}
