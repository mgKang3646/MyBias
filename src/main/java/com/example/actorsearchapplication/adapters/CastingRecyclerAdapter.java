package com.example.actorsearchapplication.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.ActivityClickListener;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorModel;

import java.util.List;

public class CastingRecyclerAdapter extends RecyclerView.Adapter<CastingRecyclerHolder> {

    private List<ActorModel> casting;
    private ActivityClickListener activityClickListener;

    public CastingRecyclerAdapter(ActivityClickListener activityClickListener){
        this.activityClickListener = activityClickListener;
    }

    @NonNull
    @Override
    public CastingRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_holder,parent,false);
        return new CastingRecyclerHolder(view, activityClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CastingRecyclerHolder holder, int position) {
        holder.onBind(casting.get(position));
    }

    @Override
    public int getItemCount() {
        if(casting != null){
            return casting.size();
        }
        return 0;
    }

    public void setCasting(List<ActorModel> casting){
        this.casting = casting;
    }
}
