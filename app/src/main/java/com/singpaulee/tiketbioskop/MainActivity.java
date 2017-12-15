package com.singpaulee.tiketbioskop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.singpaulee.tiketbioskop.Fragment.NowPlayingFragment;
import com.singpaulee.tiketbioskop.Fragment.ComingSoonFragment;

public class MainActivity extends AppCompatActivity {
	//private TextView mTextMessage;
	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener() {
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			switch (item.getItemId()) {
				case R.id.navigation_nowplaying:
					//mTextMessage.setText(R.string.title_home);
					//TODO 1 ganti dengan fragment
					getSupportFragmentManager()
							.beginTransaction()
							.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
							.replace(R.id.frame_layout,new NowPlayingFragment())
							.commit();
					return true;
				case R.id.navigation_comingsoon:
					//mTextMessage.setText(R.string.title_dashboard);
					getSupportFragmentManager()
							.beginTransaction()
							.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
							.replace(R.id.frame_layout,new ComingSoonFragment())
							.commit();
					return true;
			}
			return false;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//mTextMessage = (TextView) findViewById(R.id.message);
		BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

		//TODO 2 Default tampilan ketika dibuka
		getSupportFragmentManager()
				.beginTransaction()
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
				.replace(R.id.frame_layout,new NowPlayingFragment())
				.commit();
	}

}
