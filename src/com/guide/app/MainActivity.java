package com.guide.app;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	private ViewPager mGuide;
	private LinearLayout point;
	private int[] mImagesid;
	private ArrayList<ImageView> mimage;
	private int dx;
	private View point_red;
	private int width;
	private int height;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// 存放图片id的数组
		mImagesid = new int[] { R.drawable.guide_1, R.drawable.guide_2,
				R.drawable.guide_3 };
		// 存放图片的list集合
		mimage = new ArrayList<ImageView>();

		mGuide = (ViewPager) findViewById(R.id.vp_guide);
		point = (LinearLayout) findViewById(R.id.ll_point_group);

		point_red = findViewById(R.id.view_point_red);

		initViews();

		mGuide.setAdapter(new madapter());
		mGuide.setOnPageChangeListener(new mChangeListener());

	}

	// 内部类 实现接口
	// viewpage的滑动监听
	class mChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// position 当前位置 ，positionOffset 百分比 ， positionOffsetPixels 移动的距离

			point_red.setBackgroundResource(R.drawable.guide_point_hong);

			RelativeLayout.LayoutParams R_params = new RelativeLayout.LayoutParams(
					width, height);

			// 给小红点设置左边距 来动态移动小红点的位置
			R_params.leftMargin = (int) (dx * positionOffset + position * dx);
			// System.out.println(position+"////"+positionOffset+"/////"+positionOffsetPixels);

			point_red.setLayoutParams(R_params);

		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub

		}

	}

	// 初始化
	private void initViews() {

		// 初始化图片
		for (int i = 0; i < mImagesid.length; i++) {

			ImageView mImageView = new ImageView(this);

			mImageView.setBackgroundResource(mImagesid[i]);
			mimage.add(mImageView);
		}

		// 初始换 灰色小圆点
		for (int i = 0; i < mImagesid.length; i++) {

			View guide_point = new View(this);

			guide_point.setBackgroundResource(R.drawable.guide_point_hui);

			width = 10;
			height = 10;

			LayoutParams params = new LinearLayout.LayoutParams(width, height);

			int leftmargin = 10;// 小圆点之间的距离
			if (i > 0) {
				params.leftMargin = leftmargin;

			}
			dx = width + leftmargin;

			guide_point.setLayoutParams(params);// 设置灰色小圆点的大小
			point.addView(guide_point);
		}

	}

	class madapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mImagesid.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		// 初始化item
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(mimage.get(position));
			return mimage.get(position);
		}

		@Override
		// 销毁item
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}

	}

}
