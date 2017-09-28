package com.example.facedoor;

import java.util.ArrayList;
import java.util.List;

import com.example.facedoor.db.DBManage;
import com.example.facedoor.exception.CrashHandler;
import com.iflytek.cloud.SpeechUtility;

import android.R.string;
import android.app.Activity;
import android.app.Application;

public class MyApp extends Application {

	public static final String adminId = "admin1";
	public static final String DBIP_KEY = "dbip";
	public static final String DOORIP_KEY = "doorip";
	public static final String DOOR_CONTROLLER = "doorcontroller";
	public static final String OPEN_TIME = "opentime";
	public static final String PLATFORM_IP = "platformip";
	public static final String DOOR_NUM = "doornum";
	public static final String DB_AGENT = "dbagent";
	public static final String FACEONLY = "faceonly";
	public static final String CONFIG = "config";
	public static final String PERSONNAL = "personnal";
	public static final String APPID = "pc20onli";

	private DBManage db;
	private List<Activity> activities = new ArrayList<Activity>();

	@Override
	public void onCreate() {
		super.onCreate();
		SpeechUtility.createUtility(this, "appid=" + getString(R.string.appid));
		db = new DBManage(this);

		CrashHandler handler = CrashHandler.getInstance();
		handler.init(getApplicationContext());
	}

	public DBManage getDBManage() {
		return db;
	}

	public void addActivity(Activity activity) {
		activities.add(activity);
	}

	public void removeActivity(Activity activity) {
		activities.remove(activity);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		for (Activity activity : activities) {
			activity.finish();
		}
		db.closeDB();
		System.exit(1);
	}
}
