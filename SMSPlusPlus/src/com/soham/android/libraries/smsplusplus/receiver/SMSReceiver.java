package com.soham.android.libraries.smsplusplus.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.soham.android.libraries.smsplusplus.receiver.sap.ConnectTask;

public abstract class SMSReceiver extends BroadcastReceiver {

	public SMSReceiver() {

	}

	@Override
	public void onReceive(Context context, Intent intent) {
		final Bundle bundle = intent.getExtras();
		if (bundle != null) {

			final Object[] pdusObj = (Object[]) bundle.get("pdus");

			for (int i = 0; i < pdusObj.length; i++) {

				SmsMessage currentMessage = SmsMessage
						.createFromPdu((byte[]) pdusObj[i]);
				String phoneNumber = currentMessage
						.getDisplayOriginatingAddress();

				String senderNum = phoneNumber;
				String message = currentMessage.getDisplayMessageBody();

				onSms(senderNum, message, context);

			}
		}
	}

	public abstract void onSms(String senderNum, String message, Context context);

}
