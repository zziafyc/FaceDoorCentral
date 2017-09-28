package com.example.facedoor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);

		((Button) findViewById(R.id.btn_register)).setOnClickListener(this);
		((Button) findViewById(R.id.btn_manage_group)).setOnClickListener(this);
		findViewById(R.id.btn_query).setOnClickListener(this);
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
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btn_register:
			Intent intentReg = new Intent(this, RegisterActivity.class);
			startActivity(intentReg);
			break;
		case R.id.btn_manage_group:
			Intent intentGroup = new Intent(this, GroupManageActivity.class);
			startActivity(intentGroup);
			break;
		case R.id.btn_query:
			Intent intentQuery = new Intent(this, QueryActivity.class);
			startActivity(intentQuery);
			break;
		}
	}

}
