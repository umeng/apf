package com.example.host;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.umeng.analytics.IMobClickAgent;
import com.umeng.apf.ApfException;
import com.umeng.apf.PluginManager;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getName();
	private IMobClickAgent agent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		//
		try {
			PluginManager.registerPlugin("com.umeng.common");
			PluginManager.registerPlugin("com.umeng.analytics");
			PluginManager.loadPlugins(this.getApplicationContext());
			agent = (IMobClickAgent) PluginManager
					.newInstance("com.umeng.analytics.internal.MobclickProxy");
			if (agent == null)
				throw new ApfException("unable to initialize plugin");
		} catch (ApfException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
		agent.onPause(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
		agent.setSessionContinueMillis(10000);
		agent.onResume(this);
	}

	public void testProxy() {
		InvocationHandler handler = new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				// do some processing before the method invocation
				Log.d(TAG, proxy.getClass().getCanonicalName());
				// invoke the method
				Object result = method.invoke(proxy, args);

				// do some processing after the method invocation
				return result;
			}

		};

		Object obj = Proxy.newProxyInstance(this.getClassLoader(),
				new Class[] { Context.class }, handler);
		obj.toString();
	}
}