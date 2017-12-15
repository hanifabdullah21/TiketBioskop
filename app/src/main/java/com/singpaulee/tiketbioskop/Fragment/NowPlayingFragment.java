package com.singpaulee.tiketbioskop.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.singpaulee.tiketbioskop.Model.ListmovieModel;
import com.singpaulee.tiketbioskop.Model.MovieModel;
import com.singpaulee.tiketbioskop.R;
import com.singpaulee.tiketbioskop.Adapter.RecyclerViewPopularAdapter;
import com.singpaulee.tiketbioskop.Service.ApiServices;
import com.singpaulee.tiketbioskop.Service.Config;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment {


	private RecyclerView mRvPopular;
	ArrayList<MovieModel> dataMovie = new ArrayList<>();
	RecyclerViewPopularAdapter adapter;

	public NowPlayingFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_now_playing, container, false);
		initView(v);

//		MovieModel movieModel = new MovieModel(
//				"Judul Film",
//				"http://grobmart.com/image/cache/data/00seller00/Divergent-500x500.jpg");
//		dataMovie.add(movieModel);

//		for(int i = 0; i<16;i++){
////			MovieModel movieModel = new MovieModel(
////					"Judul Film",
////					"http://grobmart.com/image/cache/data/00seller00/Divergent-500x500.jpg");
////			dataMovie.add(movieModel);
//		}

		adapter = new RecyclerViewPopularAdapter(getActivity(), dataMovie);
		mRvPopular.setHasFixedSize(true);
		mRvPopular.setLayoutManager(new GridLayoutManager(getActivity(),2));
		mRvPopular.setAdapter(adapter);

		ambildata();

		return v;
	}

	private void initView(View v) {
		mRvPopular = v.findViewById(R.id.rv_popular);
	}

	public void ambildata() {
		Toast.makeText(getActivity(), "Ambil Data", Toast.LENGTH_SHORT).show();
		final ProgressDialog progressDialog =  new ProgressDialog(getActivity());
		progressDialog.setTitle("Loading ...");
		progressDialog.setMessage("Lagi proses nih, sabar ya :)");
		progressDialog.show();

		ApiServices services = Config.getApiServices();
		Call<ListmovieModel> filmPopuler = services.ambilFilmPopuler();

		filmPopuler.enqueue(new Callback<ListmovieModel>() {

			//Ketika ada response
			@Override
			public void onResponse(Call<ListmovieModel> call, Response<ListmovieModel> response) {
				progressDialog.dismiss();
				//Cek Response sukses atau tidak
				if(response.isSuccessful()){
					dataMovie = response.body().getResults();
					RecyclerViewPopularAdapter adapter2 = new RecyclerViewPopularAdapter(getActivity(), dataMovie, 1);
					mRvPopular.setAdapter(adapter2);
					//Toast.makeText(getActivity(), ""+dataMovie.get(1).getTitle(), Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(getActivity(), "Response Gagal", Toast.LENGTH_SHORT).show();
				}
			}

			//Ketika tidak ada respon
			@Override
			public void onFailure(Call<ListmovieModel> call, Throwable t) {
				Toast.makeText(getActivity(), "Failure!!!", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
