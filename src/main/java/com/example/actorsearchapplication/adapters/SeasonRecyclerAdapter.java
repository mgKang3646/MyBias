package com.example.actorsearchapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.SeasonModel;

import java.util.List;

public class SeasonRecyclerAdapter extends RecyclerView.Adapter<SeasonRecyclerHolder>{

    private List<SeasonModel> seasons;
    @NonNull
    @Override
    public SeasonRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_holder_season,parent,false);
        return new SeasonRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonRecyclerHolder holder, int position) {
        holder.onBind(seasons.get(position));
    }

    @Override
    public int getItemCount() {
        if(seasons != null){
            return seasons.size();
        }
        return 0;
    }
    public void setSeasons(List<SeasonModel> seasons){
        this.seasons = seasons;
    }
}
