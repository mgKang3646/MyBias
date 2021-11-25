package com.example.actorsearchapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.ActivityClickListener;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.FilmographyModel;

import java.util.List;

public class FilmographyRecyclerAdapter extends RecyclerView.Adapter<FilmographyRecyclerHolder> {

    private List<FilmographyModel> filmography;

    private ActivityClickListener activityClickListener;

    public FilmographyRecyclerAdapter(ActivityClickListener activityClickListener){
        this.activityClickListener = activityClickListener;
    }

    @NonNull
    @Override
    public FilmographyRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_holder,parent,false);
        return new FilmographyRecyclerHolder(view,activityClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmographyRecyclerHolder holder, int position) {
        holder.onBind(filmography.get(position));
    }

    @Override
    public int getItemCount() {
        if( filmography != null) return filmography.size();
        return 0;
    }

    public void setFilmography(List<FilmographyModel> filmography){
        this.filmography = filmography;
    }
}
