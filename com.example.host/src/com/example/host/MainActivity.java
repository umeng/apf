package com.example.host;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.example.plugin1.ifs.ExampleApfService;
import com.umeng.apf.ApfException;
import com.umeng.apf.PluginManager;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getName();
	private ExampleApfService service;

	EditText edit_a;
	EditText edit_b;

	TextView result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.d(TAG, "onCreate");
		//
		new Thread() {
			@Override
			public void run() {
				try {
					PluginManager.registerPlugin("com.example.plugin1");
					PluginManager.loadPlugins(getApplicationContext());
					service = (ExampleApfService) PluginManager
							.newInstance("com.example.plugin1.ExampleApfServiceImpl");
					if (service == null)
						throw new ApfException("unable to initialize plugin");
				} catch (ApfException e) {
					e.printStackTrace();
				}

			}
		}.start();

		edit_a = (EditText) findViewById(R.id.edit_a);
		edit_b = (EditText) findViewById(R.id.edit_b);
		result = (TextView) findViewById(R.id.text_result);
		this.findViewById(R.id.button_add).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						int a = Integer.parseInt(edit_a.getText().toString());
						int b = Integer.parseInt(edit_b.getText().toString());

						if (service != null) {
							result.setText(service.add(a, b));
						}
					}

				});

		this.findViewById(R.id.button_minus).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						int a = Integer.parseInt(edit_a.getText().toString());
						int b = Integer.parseInt(edit_b.getText().toString());

						if (service != null) {
							result.setText(service.minus(a, b));
						}
					}

				});
		this.findViewById(R.id.button_multiply).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						int a = Integer.parseInt(edit_a.getText().toString());
						int b = Integer.parseInt(edit_b.getText().toString());

						if (service != null) {
							result.setText(service.multiply(a, b));
						}
					}

				});
		this.findViewById(R.id.button_divide).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						int a = Integer.parseInt(edit_a.getText().toString());
						int b = Integer.parseInt(edit_b.getText().toString());

						if (service != null) {
							result.setText(service.divide(a, b));
						}
					}

				});
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
	}
}