package com.soham.android.libraries.smsplusplus.receiver.sms;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;

public class UtilSms {
	public static boolean sendSMS(Context c, String phone, String body) {
		SmsManager tm = SmsManager.getDefault();
		Log.d("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD", phone + "    Body=" + body);
		tm.sendTextMessage(phone, null, body, null, null);
		return true;

	}

	
}
