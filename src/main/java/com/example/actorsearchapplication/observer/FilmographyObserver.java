package com.example.actorsearchapplication.observer;

import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.adapters.FilmographyRecyclerAdapter;
import com.example.actorsearchapplication.models.FilmographyModel;

import java.util.List;

public class FilmographyObserver implements Observer<List<FilmographyModel>>   {

    private FilmographyRecyclerAdapter filmographyRecyclerAdapter;

    public FilmographyObserver(RecyclerView.Adapter recyclerViewAdapter){
        this.filmographyRecyclerAdapter = (FilmographyRecyclerAdapter)recyclerViewAdapter;
    }

    @Override
    public void onChanged(List<FilmographyModel> filmography) {
        if(filmography != null){
            filmographyRecyclerAdapter.setFilmography(filmography);
            filmographyRecyclerAdapter.notifyDataSetChanged();
        }else{
            Log.v("Tag","FilmographyObserver : List<FilmographyModel> is Null");
        }
    }
}
