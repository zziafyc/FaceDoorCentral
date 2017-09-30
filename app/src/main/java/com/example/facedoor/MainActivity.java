package com.example.facedoor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.example.facedoor.util.Moniter;
import com.example.facedoor.util.ToastShow;

public class MainActivity extends Activity implements OnClickListener {

	private static final String PASS_WORD = "a";
	// public static DBManage db;

	private Toast mToast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

		EditText editText = (EditText) findViewById(R.id.admin);
		editText.setKeyListener(null);
		findViewById(R.id.btn_admin).setOnClickListener(this);
		findViewById(R.id.btn_start).setOnClickListener(this);

		startMoniter();

		MyApp myApp = (MyApp) MainActivity.this.getApplication();
		myApp.addActivity(this);

		Intent intent = new Intent();
		intent.setClass(MainActivity.this, VideoDetect.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		MyApp myApp = (MyApp) MainActivity.this.getApplication();
		myApp.removeActivity(this);
		stopMoniter();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btn_admin:
			EditText pwdView = (EditText) findViewById(R.id.password);
			if (pwdView.getText().toString().equals(PASS_WORD)) {
				Intent intent = new Intent(this, AdminActivity.class);
				startActivity(intent);
			} else {
				ToastShow.showTip(mToast, R.string.error_pwd);
			}
			break;
		case R.id.btn_start:
			Intent intent = new Intent(this, VideoDetect.class);
			startActivity(intent);
			break;
		}
	}

	private void startMoniter() {
		Intent intent = new Intent(MainActivity.this, Moniter.class);
		startService(intent);
	}
	private void stopMoniter() {
		Intent intent = new Intent(MainActivity.this, Moniter.class);
		stopService(intent);
	}
}
