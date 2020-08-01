package com.gmail.wyatt.james.snyder.simplecountdowntimer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private ArrayList<Event> events;
    private final View.OnClickListener onClickListener = new EventOnClickListener();

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView eventName;
        public TextView eventDate;

        public EventViewHolder(View itemView){
            super(itemView);
            eventName = itemView.findViewById(R.id.eventName);
            eventDate = itemView.findViewById(R.id.eventDate);
        }
    }

    public EventAdapter(ArrayList<Event> events){
        this.events = events;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event, parent, false);
        v.setOnClickListener(onClickListener);
        EventViewHolder evh = new EventViewHolder(v);

        return evh;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Event currentEvent = events.get(position);

        holder.eventName.setText(currentEvent.getName());
        holder.eventDate.setText(currentEvent.getDateString());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
