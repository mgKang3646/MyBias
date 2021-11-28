package com.example.actorsearchapplication.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.MainActivityViewListener;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.MovieModel;
import com.example.actorsearchapplication.models.TvModel;

import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewHolder> {
    // 어댑터가 다양한 뷰 홀더를 처리하려면 제네릭을 RecyclerView.ViewHolder 추상클래스를 넣는다. ( 제어가 필요 )
    // 일단은 ActorRecyclerViewHolder 전용 어댑터로 구현한다.

    public static final int MODE_POPULAR_ACTORS = 0;
    public static final int MODE_MOVIE = 1;
    public static final int MODE_TV = 2;


    private List<ActorModel> actors;
    private List<MovieModel> movies;
    private List<TvModel> tvs;

    private MainActivityViewListener mainActivityViewListener;

    private int mode = 0;

    public MainRecyclerViewAdapter(MainActivityViewListener mainActivityViewListener){
        this.mainActivityViewListener = mainActivityViewListener;
    }

    @NonNull
    @Override
    public MainRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_holder,parent,false);
        return new MainRecyclerViewHolder(view, mainActivityViewListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerViewHolder holder, int position) {
        holder.setMode(mode); // 다른 관심사
        if(mode == MODE_POPULAR_ACTORS){
            holder.onBind(actors.get(position));
        }
        else if(mode == MODE_MOVIE) {
            holder.onBind(movies.get(position));
        }
        else if(mode == MODE_TV){
            holder.onBind(tvs.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(actors != null){
            return actors.size();
        }
        return 0;
    }

    public void setActors(List<ActorModel> actors) {
        this.actors = actors;
    }
    public void setMovies(List<MovieModel> movies) { this.movies = movies; }
    public void setTvs(List<TvModel> tvs) { this.tvs = tvs; }
    public void setMode(int mode){ this.mode = mode; }

    public void requestSwitchSelected(){
        mainActivityViewListener.requestSwitchSelected(mode,0);
    }
}
