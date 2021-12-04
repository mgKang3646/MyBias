package com.example.actorsearchapplication;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.util.ContentLengthInputStream;
import com.example.actorsearchapplication.adapters.DibsRecyclerViewAdapter;
import com.example.actorsearchapplication.utils.MVVMFactory;
import com.example.actorsearchapplication.viewutil.ButtonClickHandler;
import com.example.actorsearchapplication.viewutil.RecyclerViewUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DibsDialog extends Dialog {

    private RecyclerView dibsRecyclerView;
    private ImageButton closeDibsButton;

    public DibsDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.layout_dibs_dialog);
        getWindow().setGravity(Gravity.CENTER);

        dibsRecyclerView = findViewById(R.id.recyclerView_dibs);
        closeDibsButton = findViewById(R.id.btn_dibs_dialog_close);

        closeDibsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        RecyclerViewUtil recyclerViewUtil = new RecyclerViewUtil(dibsRecyclerView,new DibsRecyclerViewAdapter());
        recyclerViewUtil.setLayoutManagerVertical(getContext());
        ((DibsRecyclerViewAdapter)recyclerViewUtil.getRecyclerViewAdapter()).setDibs(MVVMFactory.getRoomUtil(getContext()).getDibs());
        recyclerViewUtil.getRecyclerViewAdapter().notifyDataSetChanged();





    }
}
