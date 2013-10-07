package com.example.stockquote;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class StockInfoActivity extends Activity{
	
	private static final String TAG = "STOCKQUOTE";
	
	TextView companyNameTextView;
	TextView yearLowTextView;
	TextView yearHighTextView;
	TextView daysLowTextView;
	TextView daysHighTextView;
	TextView lastTradePriceOnlyTextView;
	TextView changeTextView;
	TextView daysRangeTextView;
	
	static final String KEY_ITEM = "quote";
	static final String KEY_NAME = "Name";
	static final String KEY_YEAR_LOW = "YearLow";
	static final String KEY_YEAR_HIGH = "YearHigh";
	static final String KEY_DAYS_LOW = "DaysLow";
	static final String KEY_DAYS_HIGH = "DaysHigh";
	static final String KEY_LAST_TRADE_PRICE = "LastTradePriceOnly";
	static final String KEY_CHANGE = "Change";
	static final String KEY_DAYS_RANGE = "DaysRange";
	
	String name = "";
	String yearLow = "";
	String yearHigh = "";
	String daysLow = "";
	String daysHigh = "";
	String lastTradePriceOnly = "";
	String change = "";
	String daysRange = "";

	String yahooURLFirst = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22";
	String yahooURLSecond = "%22)&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
	
	String[][] xmlPullParserArray = {{"AverageDailyVolume", "0"},{"Change", "0"},
			{"DaysLow", "0"},{"DaysHigh", "0"},{"YearLow", "0"},
			{"YearHigh", "0"},{"MarketCapitalization", "0"},{"LastTradePriceOnly", "0"},
			{"DaysRange", "0"},{"Name", "0"},{"Symbol", "0"},
			{"Volume", "0"},{"StockExchange", "0"}};
	
	int parserArrayIncrement = 0;
	
	protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock_info);
		
		Intent intent = getIntent();
		String stockSymbol = intent.getStringExtra(MainActivity.STOCK_SYMBOL);
		
		companyNameTextView = (TextView) findViewById(R.id.companyNameTextView);
		yearLowTextView = (TextView) findViewById(R.id.yearLowTextView);
		yearHighTextView = (TextView) findViewById(R.id.yearHighTextView);
		daysLowTextView = (TextView) findViewById(R.id.daysLowTextView);
		daysHighTextView = (TextView) findViewById(R.id.daysHighTextView);
		lastTradePriceOnlyTextView = (TextView) findViewById(R.id.lastTradePriceOnlyTextView);
		changeTextView = (TextView) findViewById(R.id.changeTextView);
		daysRangeTextView = (TextView) findViewById(R.id.daysRangeTextView);
		
		Log.d(TAG, "Before URL Creation " + stockSymbol);
		
		final String yqlURL = yahooURLFirst + stockSymbol + yahooURLSecond;
		
		new MyAsyncTask().execute(yqlURL);
		
	}
	
	private class MyAsyncTask extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... args) {
			
			
			
			return null;
		}
		
		protected void onPostExecute(String result) {
			
			companyNameTextView.setText(name);
			yearLowTextView.setText("Year Low: " + yearLow);
			yearHighTextView.setText("Year High: " + yearHigh);
			daysLowTextView.setText("Days Low: " + daysLow);
			daysHighTextView.setText("Days High: " + daysHigh);
			lastTradePriceOnlyTextView.setText("Last Price: " + lastTradePriceOnly);
			changeTextView.setText("Change: " + change);
			daysRangeTextView.setText("Daily Price Range: " + daysRange);
			
		}
		
		
	}
	
}
