package com.singpaulee.tiketbioskop.Service;

import com.singpaulee.tiketbioskop.Model.ListTrailerModel;
import com.singpaulee.tiketbioskop.Model.ListmovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ASUS on 09/12/2017.
 */

public interface ApiServices {
	//API v3 bfcc1f88effce12d8b802ed55c126aec
	//API v4 eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiZmNjMWY4OGVmZmNlMTJkOGI4MDJlZDU1YzEyNmFlYyIsInN1YiI6IjVhMmI5MmRmOTI1MTQxMDMzNjEzNmQ1OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.5YpivKlumWx48c59F9afgeWK0OX4X2Lk2dqSiB7ykgI
	//API https://api.themoviedb.org/3/movie/550?api_key=bfcc1f88effce12d8b802ed55c126aec
	//ttps://api.themoviedb.org/3/movie/popular?api_key=bfcc1f88effce12d8b802ed55c126aec&language=en-US&page=1

	//Get Now Playing
	@GET("movie/now_playing?api_key=bfcc1f88effce12d8b802ed55c126aec&language=en-US&page=1")
	Call<ListmovieModel> ambilFilmPopuler();

	//Get Coming Soon
	@GET("movie/upcoming?api_key=bfcc1f88effce12d8b802ed55c126aec&language=en-US&page=1")
	Call<ListmovieModel> ambilFilmTopRate();

	@GET("movie/{id}/videos?api_key=bfcc1f88effce12d8b802ed55c126aec&language=en-US")
	Call<ListTrailerModel> ambilLink(
	@Path("id") int id);

}
