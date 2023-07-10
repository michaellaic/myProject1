package com.example.myapplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {
    private String name , location;
    private LocalDate date;
    private LocalTime time;
    public static ArrayList<Event> eventsList = new ArrayList<>();

    public Event(String name,String location, LocalDate date, LocalTime time) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
    }
    public static ArrayList<Event> eventsForDate(LocalDate date){
        ArrayList<Event> events= new ArrayList<>();

        for (Event event : eventsList){
            if (event.getDate().equals(date))
                events.add(event);
        }
        return events;
    }

    public static ArrayList<Event> eventsForDateAndTime(LocalDate date, LocalTime time){
        ArrayList<Event> events= new ArrayList<>();

        for (Event event : eventsList){
            int eventHour = event.time.getHour();
            int cellHour = time.getHour();
            if (event.getDate().equals(date) && eventHour == cellHour)
                events.add(event);
        }
        return events;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
