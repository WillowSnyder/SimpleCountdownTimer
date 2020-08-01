package com.gmail.wyatt.james.snyder.simplecountdowntimer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class EventOnClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        Context mainContext = v.getContext();
        RecyclerView recyclerView = (RecyclerView) ((Activity) mainContext).findViewById(R.id.recyclerview);
        int itemPosition = recyclerView.getChildLayoutPosition(v);
        Intent intent = new Intent(mainContext, editDeleteEvent.class);
        intent.putExtra("itemPosition", itemPosition);

        mainContext.startActivity(intent);
    }
}
