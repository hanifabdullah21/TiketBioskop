package com.singpaulee.tiketbioskop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.singpaulee.tiketbioskop.DetailActivity;
import com.singpaulee.tiketbioskop.DetailComingSoonActivity;
import com.singpaulee.tiketbioskop.Model.MovieModel;
import com.singpaulee.tiketbioskop.R;

import java.util.ArrayList;

/**
 * Created by ASUS on 09/12/2017.
 */

public class RecyclerViewPopularAdapter extends RecyclerView.Adapter<RecyclerViewPopularAdapter.ViewHolder> {
	Context context;
	ArrayList<MovieModel> models;
	int index;

	public RecyclerViewPopularAdapter(Context context, ArrayList<MovieModel> models) {
		this.context = context;
		this.models = models;

	}

	public RecyclerViewPopularAdapter(Context context, ArrayList<MovieModel> models, int index) {
		this.context = context;
		this.models = models;
		this.index = index;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		holder.tvJudul.setText(models.get(position).getTitle());
		Glide.with(context)
				.load("https://image.tmdb.org/t/p/w500"+models.get(position).getPosterPath())
				.into(holder.image);

		holder.card.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(index ==1 ){
					Intent intent = new Intent(context, DetailActivity.class);
					intent.putParcelableArrayListExtra("LIST",models);
					intent.putExtra("POSITION",position);
					context.startActivity(intent);
				}else if(index==2){
					Intent intent = new Intent(context, DetailComingSoonActivity.class);
					intent.putParcelableArrayListExtra("LIST",models);
					intent.putExtra("POSITION",position);
					context.startActivity(intent);
				}

			}
		});
	}

	@Override
	public int getItemCount() {
		return models.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder{
		ImageView image;
		TextView tvJudul;
		CardView card;
		public ViewHolder(View itemView) {
			super(itemView);
			image = itemView.findViewById(R.id.list_gambar);
			tvJudul = itemView.findViewById(R.id.list_textview);
			card = itemView.findViewById(R.id.card);
		}
	}
}

