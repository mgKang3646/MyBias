package com.example.actorsearchapplication.adapters;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.ActorDetailActivity;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.WebViewActivity;
import com.example.actorsearchapplication.models.RecyclerHolderClickModel;
import com.example.actorsearchapplication.models.SNSModel;
import com.example.actorsearchapplication.utils.SNSUtil;
import com.example.actorsearchapplication.viewutil.IntentUtil;

public class SNSRecyclerViewHolder extends RecyclerView.ViewHolder {

    private ImageView snsImageView;
    private SNSModel snsModel;

    public SNSRecyclerViewHolder(@NonNull View itemView, ActorDetailActivity actorDetailActivity) {
        super(itemView);
        snsImageView = itemView.findViewById(R.id.iv_sns);
        IntentUtil intentUtil = new IntentUtil(actorDetailActivity);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentUtil.moveToWebViewActivity(snsModel);
            }
        });
    }

    public void onBind(SNSModel snsModel){
        this.snsModel = snsModel;
        SNSUtil.setSNSIconInImageView(snsImageView,snsModel.getSort());
    }




}
