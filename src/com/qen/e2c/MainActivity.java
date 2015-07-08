package com.qen.e2c;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baidu.appx.BDInterstitialAd;
import com.baidu.appx.BDInterstitialAd.InterstitialAdListener;
import com.example.excel2.R;

import jxl.Sheet;
import jxl.Workbook;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private static String TAG = "AppX_Interstitial";
	private BDInterstitialAd appxInterstitialAdView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		appxInterstitialAdView = new BDInterstitialAd(this,
				"kr6KikubAM7uDSMElfEGc4qG", "QphHC2IhSRCreEmmTIgdlqn7");
		// 设置插屏广告行为监听器
		appxInterstitialAdView.setAdListener(new InterstitialAdListener() {

			@Override
			public void onAdvertisementDataDidLoadFailure() {
				Log.e(TAG, "load failure");
			}

			@Override
			public void onAdvertisementDataDidLoadSuccess() {
				Log.e(TAG, "load success");
			}

			@Override
			public void onAdvertisementViewDidClick() {
				Log.e(TAG, "on click");
			}

			@Override
			public void onAdvertisementViewDidHide() {
				Log.e(TAG, "on hide");
			}

			@Override
			public void onAdvertisementViewDidShow() {
				Log.e(TAG, "on show");
			}

			@Override
			public void onAdvertisementViewWillStartNewIntent() {
				Log.e(TAG, "leave");
			}

		});

		// 加载广告
		appxInterstitialAdView.loadAd();
		// 展示插屏广告前先请先检查下广告是否加载完毕
		if (appxInterstitialAdView.isLoaded()) {
			appxInterstitialAdView.showAd();
		} else {
			Log.i(TAG, "AppX Interstitial Ad is not ready");
			appxInterstitialAdView.loadAd();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void readExcelFile(View v) {
		Intent intent = new Intent(this, First.class);
		startActivity(intent);
	}
}
