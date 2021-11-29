package com.example.actorsearchapplication.viewutil;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewUtil {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;

    public RecyclerViewUtil(RecyclerView recyclerView, RecyclerView.Adapter recyclerViewAdapter){
        this.recyclerView = recyclerView;
        this.recyclerViewAdapter = recyclerViewAdapter;
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void setLayoutManagerHorizontal(Context context){
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
    }

    public void setLayoutManagerVertical(Context context){
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
    }

    public RecyclerView.Adapter getRecyclerViewAdapter(){
        return recyclerViewAdapter;
    }



}
