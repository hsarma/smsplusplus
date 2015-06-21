package com.soham.android.libraries.smsplusplus.receiver.sap;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public abstract class ConnectTask extends AsyncTask<String, Void, String> {

	private Context context;

	private String http_webapp_url;

	public void setHttp_webapp_url(String http_webapp_url) {
		this.http_webapp_url = http_webapp_url;
	}

	public abstract void setURL();

	public ConnectTask(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected String doInBackground(String... x) {
		// TODO Auto-generated method stub
		String retString = null;
		setURL();
		try {

			if (isInvalidSms(x)) {
				retString = processInvalidSms(x);

			} else {

				retString = processValidSms(x);
			}

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retString;
	}

	private String processValidSms(String... x)
			throws UnsupportedEncodingException, ClientProtocolException,
			IOException {
		// TODO Auto-generated method stub
		String r = processPC_Connection(x);
		processMoreWorksHere(x,r);
		return r;
	}

	public abstract void processMoreWorksHere(String[] x,String z);

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		postProcessingSms(result);

	}

	/**
	 * @param x
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public String processPC_Connection(String... x)
			throws UnsupportedEncodingException, IOException,
			ClientProtocolException {
		String retString;
		HttpClient client = new DefaultHttpClient();
		HttpPost requestHttpPost = new HttpPost(http_webapp_url);
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("phone", x[0]));
		urlParameters.add(new BasicNameValuePair("message", x[1]));

		requestHttpPost.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(requestHttpPost);

		byte[] msgChunk = new byte[512];
		int ch = response.getEntity().getContent().read(msgChunk);
		retString = new String(msgChunk, 0, ch);
		msgChunk = null;
		Log.d("Received", retString);
		return retString;
	}

	public abstract String processInvalidSms(String[] x);

	public abstract boolean isInvalidSms(String[] x);

	public abstract void postProcessingSms(String result);

}
