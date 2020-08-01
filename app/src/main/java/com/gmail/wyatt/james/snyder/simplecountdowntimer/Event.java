package com.gmail.wyatt.james.snyder.simplecountdowntimer;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class Event {
    private int year, month, day;
    private String name;

    public Event(String name, int year, int month, int day){
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getDateString(){
        LocalDate today = LocalDate.now();
        LocalDate eventDate = LocalDate.of(year, month + 1, day);

        long daysBetween = ChronoUnit.DAYS.between(today, eventDate);
        String currentDateString;

        if(daysBetween > 0){
            currentDateString = Long.toString(daysBetween) + " days until";
        } else {
            currentDateString = Long.toString(-daysBetween) + " days since";
        }

        return currentDateString;
    }

    public String getName(){
        return this.name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setName(String name) {
        this.name = name;
    }
}
