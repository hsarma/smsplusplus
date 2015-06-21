package com.soham.android.libraries.smsplusplus.receiver;

import com.soham.android.libraries.smsplusplus.receiver.sap.ConnectTask;

import android.content.Context;

public class UtilTaskBuilder {
	
	

	public UtilTaskBuilder() {

		
	}

	/* This method must be overriden to create ConnectTask    */
	public ConnectTask buildTask(Context context) {
		
		// connectTask=....
		return null;
	}

}
