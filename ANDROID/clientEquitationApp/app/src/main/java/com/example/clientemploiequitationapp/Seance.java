package com.example.clientemploiequitationapp;

import java.io.Serializable;
import java.text.DateFormatSymbols;

public class Seance implements Serializable {
    private String startDate;
    private int duration, isDone, id;

    public Seance() {

    }

    public Seance(String startDate, int duration, int isDone, int id) {
        this.startDate = startDate;
        this.duration = duration;
        this.isDone = isDone;
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int isDone() {
        return isDone;
    }

    public void setDone(int done) {
        isDone = done;
    }

    public int getYear(){
        String[] dateParts = getStartDate().split("-",3);
        int year = Integer.parseInt(dateParts[0]);
        return  year;
    }
    public int getMonth(){
        String[] dateParts = getStartDate().split("-",3);
        int month = Integer.parseInt(dateParts[1]);
        return  month;
    }
    public String getMonthName(){
        return new DateFormatSymbols().getMonths()[getMonth()-1];
    }

    public int getDay(){
        String[] dateParts = getStartDate().split("-",3);
        int month = Integer.parseInt(dateParts[2].substring(0,2));
        return  month;
    }
    public String getTime(){
        String[] dateParts = getStartDate().split(" ");
        return  dateParts[1];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
