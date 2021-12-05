package com.example.actorsearchapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.ActorDetailActivity;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.SNSIdModel;
import com.example.actorsearchapplication.models.SNSModel;

import java.util.List;

public class SNSRecyclerViewAdapter extends RecyclerView.Adapter<SNSRecyclerViewHolder> {


    List<SNSModel> snsModels;
    ActorDetailActivity actorDetailActivity;

    public SNSRecyclerViewAdapter(ActorDetailActivity actorDetailActivity){
        this.actorDetailActivity = actorDetailActivity;
    }

    @NonNull
    @Override
    public SNSRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_holder_sns,parent,false);
        return new SNSRecyclerViewHolder(view,actorDetailActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull SNSRecyclerViewHolder holder, int position) {
        holder.onBind(snsModels.get(position));
    }

    @Override
    public int getItemCount() {
        if(snsModels != null) return snsModels.size();
        return 0;
    }

    public void setSnsModels(SNSIdModel snsIdModel){
        this.snsModels = snsIdModel.getSnsModel();
    }
}
