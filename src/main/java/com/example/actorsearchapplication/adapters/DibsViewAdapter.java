package com.example.actorsearchapplication.adapters;

import android.view.View;

import com.example.actorsearchapplication.MainActivity;

public class DibsViewAdapter {

    private View view;
    private DibsViewHolder dibsViewHolder;

    public DibsViewAdapter(View view, MainActivity mainActivity){
        this.view = view;
        this.dibsViewHolder = new DibsViewHolder(view,mainActivity);
    }

    public void refresh(){
        dibsViewHolder.refresh();
    }
}
