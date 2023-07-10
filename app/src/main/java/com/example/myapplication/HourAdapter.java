package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HourAdapter extends ArrayAdapter<HourEvent> {

    public HourAdapter(@NonNull Context context, List<HourEvent> hourEvents) {
            super(context, R.layout.hour_cell, hourEvents);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            HourEvent event = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.hour_cell, parent, false);
            }
            setHour(convertView, event.time);
            setEvents(convertView, event.events);


            return convertView;
        }

    private void setEvents(View convertView, ArrayList<Event> events) {
        Button event1 = convertView.findViewById(R.id.event1);
        Button event2 = convertView.findViewById(R.id.event2);
        Button event3 = convertView.findViewById(R.id.event3);

        if (events.size() == 0){
            hideEvent(event1);
            hideEvent(event2);
            hideEvent(event3);
        }
        else if (events.size() == 1){
            setEvent(event1, events.get(0));
            hideEvent(event2);
            hideEvent(event3);
        }
        else if (events.size() == 2){
            setEvent(event1, events.get(0));
            setEvent(event2, events.get(1));
            hideEvent(event3);
        }
        else if (events.size() == 3){
            setEvent(event1, events.get(0));
            setEvent(event2, events.get(1));
            setEvent(event3, events.get(2));
        }
        else {
            setEvent(event1, events.get(0));
            setEvent(event2, events.get(1));
            event3.setVisibility(View.VISIBLE);
            String eventsNotShown = String.valueOf(events.size() - 2);
            eventsNotShown += "more events";
            event3.setText(eventsNotShown);
        }
    }

    private void setEvent(Button b, Event event) {
        b.setText(event.getName());
        b.setVisibility(View.VISIBLE);
    }

    private void hideEvent(Button b) {
        b.setVisibility(View.INVISIBLE);
    }

    private void setHour(View convertView, LocalTime time) {
        TextView timeTV = convertView.findViewById(R.id.timeTV);
        timeTV.setText(CalendarUtils.formattedShortTime(time));
    }
    public interface  OnItemListener
    {
        void onItemClick(int position, Event event);
    }
}
