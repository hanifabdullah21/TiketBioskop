package com.singpaulee.tiketbioskop.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ASUS on 09/12/2017.
 */

public class Config {
	public final static String BASE_URL = "https://api.themoviedb.org/3/";

	private static Retrofit getRetrofit(){
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		return retrofit;
	}

	public static ApiServices getApiServices(){
		return getRetrofit().create(ApiServices.class);
	}
}
