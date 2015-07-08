package com.qen.e2c;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.baidu.appx.BDBannerAd;
import com.baidu.appx.BDBannerAd.BannerAdListener;
import com.example.excel2.R;

public class BannerAdActivity extends Activity {

	private static String TAG = "AppX_BannerAd";

	private RelativeLayout appxBannerContainer;
	private static BDBannerAd bannerAdView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.banner);

		// ���������ͼ
		// ����ʱ��ʹ����ȷ��ApiKey�͹��λID
		// �˴�ApiKey���ƹ�λID���ǲ����õ�
		// ������ʽ�ύӦ�õ�ʱ����ȷ�ϴ������Ѿ�����Ϊ��Ӧ�ö�Ӧ��Key��ID
		// �����ȡ��������ġ��ٶȿ��������Ľ��滻����Ʒ����.pdf��
		bannerAdView = new BDBannerAd(this, "kr6KikubAM7uDSMElfEGc4qG",
				"e2MIzmjeNE2tk8ocngBmfrK0");

		// ���ú�����չʾ�ߴ磬�粻���ã�Ĭ��ΪSIZE_FLEXIBLE;
		bannerAdView.setAdSize(BDBannerAd.SIZE_320X50);

		// ���ú�������Ϊ������
		bannerAdView.setAdListener(new BannerAdListener() {

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
			public void onAdvertisementViewDidShow() {
				Log.e(TAG, "on show");
			}

			@Override
			public void onAdvertisementViewWillStartNewIntent() {
				Log.e(TAG, "leave app");
			}
		});

		// �����������
		appxBannerContainer = (RelativeLayout) findViewById(R.id.appx_banner_container);

		// ��ʾ�����ͼ
		appxBannerContainer.addView(bannerAdView);
	}
}
