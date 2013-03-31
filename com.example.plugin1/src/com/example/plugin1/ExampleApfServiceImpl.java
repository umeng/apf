package com.example.plugin1;

import android.util.Log;

/**
 * Implementation of plugin interface
 * {@link com.example.plugin1.ifs.ExampleApfService}
 * 
 * @author lucas
 * 
 */
public class ExampleApfServiceImpl implements
		com.example.plugin1.ifs.ExampleApfService {
	private static final String TAG = ExampleApfServiceImpl.class.getName();

	/**
	 * Flawed implementation, mind overflow.
	 */
	@Override
	public int add(int a, int b) {
		Log.d(TAG, "calling implementation add.");
		return a + b;
	}

	@Override
	public int minus(int a, int b) {
		Log.d(TAG, "calling implementation minus.");
		return a - b;
	}

	/**
	 * Flawed implementation of {@link #multiply(int, int)}. Mind overflow.
	 */
	@Override
	public int multiply(int a, int b) {
		Log.d(TAG, "calling implementation multiply.");
		return a * b;
	}

	/**
	 * Flawed implementation, mind divided by zero.
	 */
	@Override
	public int divide(int a, int b) {
		Log.d(TAG, "calling implementation divide.");
		return a / b;
	}
}
