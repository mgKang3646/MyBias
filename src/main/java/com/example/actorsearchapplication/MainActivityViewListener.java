package com.example.actorsearchapplication;

import android.content.Context;
import android.widget.Button;
import com.example.actorsearchapplication.viewmodels.ListViewModel;

public interface MainActivityViewListener {

    void requestSwitchSelected(int mode, int position);
    void moveDetailPage(Class className, int id);


}
