package com.example.actorsearchapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.ActivityClickListener;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorModel;

import java.util.List;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewHolder> {

    private ActivityClickListener activityClickListener;
    private List<ActorModel> searchedActors;

    public SearchRecyclerViewAdapter(ActivityClickListener activityClickListener){
        this.activityClickListener = activityClickListener;
    }

    @NonNull
    @Override
    public SearchRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_holder_search,parent,false);
        return new SearchRecyclerViewHolder(view,activityClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecyclerViewHolder holder, int position) {
        holder.onBind(searchedActors.get(position));
    }

    @Override
    public int getItemCount() {
        if(searchedActors != null){
            return searchedActors.size();
        }
        return 0;
    }

    public void setSearchedActors(List<ActorModel> searchedActors){
        this.searchedActors = searchedActors;
    }
}
