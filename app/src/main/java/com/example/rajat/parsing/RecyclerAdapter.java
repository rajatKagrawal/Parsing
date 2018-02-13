package com.example.rajat.parsing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by rajat on 13/2/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Myholder> {

    List<Country> list;
    ImageLoader imageLoader;
    public RecyclerAdapter(List<Country> list, ImageLoader imageLoader) {
        this.list = list;
        this.imageLoader=imageLoader;
    }


    @Override
    public RecyclerAdapter.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show,parent,false);
        Myholder myHolder = new Myholder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.Myholder holder, int position) {

        Country p = list.get(position);
        holder.rank.setText(p.getRank());
        holder.country.setText(p.getCountry());
        String image1 = p.getImageurl();
        imageLoader.displayImage(image1, holder.flag);
        holder.population.setText(p.getPopulation());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myholder extends RecyclerView.ViewHolder{
        ImageView flag;
        TextView rank,country,population;

        public Myholder(View itemView) {
            super(itemView);
            flag = (ImageView) itemView.findViewById(R.id.flag);
            rank = (TextView) itemView.findViewById(R.id.rank);
            country = (TextView) itemView.findViewById(R.id.country);
            population = (TextView) itemView.findViewById(R.id.population);
        }
    }
}
