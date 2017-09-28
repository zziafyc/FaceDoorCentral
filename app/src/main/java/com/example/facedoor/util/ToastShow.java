package com.example.facedoor.util;

import android.widget.Toast;

public class ToastShow {

	public static void showTip(final Toast toast, final String tip) {
		if (toast != null) {
			toast.setText(tip);
			toast.show();
		}
	}

	public static void showTip(final Toast toast, final int resId) {
		if (toast != null) {
			toast.setText(resId);
			toast.show();
		}
	}
//	public static void showTipLong(){
//		
//	}
}
