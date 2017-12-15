package com.singpaulee.tiketbioskop;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.singpaulee.tiketbioskop.Model.ListTrailerModel;
import com.singpaulee.tiketbioskop.Model.MovieModel;
import com.singpaulee.tiketbioskop.Model.TrailerModel;
import com.singpaulee.tiketbioskop.Service.ApiServices;
import com.singpaulee.tiketbioskop.Service.Config;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailComingSoonActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {


	private AppBarLayout mAppBar;
	private CollapsingToolbarLayout mToolbarLayout;
	private ImageView mIvDetail;
	private Toolbar mToolbar;
	private SliderLayout mSlider;
	private PagerIndicator mCustomIndicator;
	private PagerIndicator mCustomIndicator2;
	private TextView mTvRating;
	private TextView mTvRelease;
	private TextView mTvOverview;
	private ShineButton mBtnFavorite;

	ArrayList<MovieModel> list = new ArrayList<>();
	ArrayList<TrailerModel> trailer = new ArrayList<>();
	int posisi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_coming_soon);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		initView();

		list = getIntent().getParcelableArrayListExtra("LIST");
		posisi = getIntent().getIntExtra("POSITION", 0);

		//get key from movie db
		goToTrailer();

		getSupportActionBar().setTitle(list.get(posisi).getTitle());
		mTvOverview.setText(list.get(posisi).getOverview());
		Glide.with(this).load("https://image.tmdb.org/t/p/w500" + list.get(posisi).getPosterPath()).into(mIvDetail);
		mTvRating.setText("" + list.get(posisi).getVoteAverage());
		mTvRelease.setText("" + list.get(posisi).getReleaseDate());

		/*Setting Slider*/
		ImageView slider = new ImageView(this);
		HashMap<String, String> file_maps = new HashMap<String, String>();
		file_maps.put("Trailer", "https://image.tmdb.org/t/p/w500" + list.get(posisi).getPosterPath());
		file_maps.put("Clip", "https://image.tmdb.org/t/p/w500" + list.get(posisi).getBackdropPath());

		for (String name : file_maps.keySet()) {
			TextSliderView textSliderView = new TextSliderView(this);
			// initialize a SliderLayout
			textSliderView
					.description(name)
					.image(file_maps.get(name))
					.setScaleType(BaseSliderView.ScaleType.Fit)
					.setOnSliderClickListener(this);

			//add your extra information
			textSliderView.bundle(new Bundle());
			textSliderView.getBundle()
					.putString("extra", name);

			mSlider.addSlider(textSliderView);
		}
		mSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
		mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
		mSlider.setCustomAnimation(new DescriptionAnimation());
		mSlider.setDuration(4000);
		mSlider.addOnPageChangeListener(this);
		/*Setting Slider*/

	}

	private void goToTrailer() {
		ApiServices services = Config.getApiServices();
		Call<ListTrailerModel> filmPopuler = services.ambilLink(list.get(posisi).getId());

		filmPopuler.enqueue(new Callback<ListTrailerModel>() {
			@Override
			public void onResponse(Call<ListTrailerModel> call, Response<ListTrailerModel> response) {
				//Cek Response sukses atau tidak
				if (response.isSuccessful()) {
					trailer = response.body().getResults();
				} else {
					Toast.makeText(DetailComingSoonActivity.this, "Response Gagal", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(Call<ListTrailerModel> call, Throwable t) {

			}
		});
	}

	@Override
	public void onSliderClick(BaseSliderView slider) {

	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {

	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}


	private void initView() {
		mAppBar = findViewById(R.id.app_bar);
		mToolbarLayout = findViewById(R.id.toolbar_layout);
		mIvDetail = findViewById(R.id.iv_detail);
		mToolbar = findViewById(R.id.toolbar);
		mSlider = findViewById(R.id.slider);
		mCustomIndicator = findViewById(R.id.custom_indicator);
		mCustomIndicator2 = findViewById(R.id.custom_indicator2);
		mTvRating = findViewById(R.id.tv_rating);
		mTvRelease = findViewById(R.id.tv_release);
		mTvOverview = findViewById(R.id.tv_overview);
		mBtnFavorite = findViewById(R.id.btn_favorite);
	}
}
