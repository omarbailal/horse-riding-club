package com.example.adminequitationapp;

import java.text.DateFormatSymbols;

public class Task {
    private String startDate, title, detail;
    private int duration, isDone, id;
    public Task(){

    }

    public Task(String startDate, String title, String detail, int duration, int isDone, int id) {
        this.startDate = startDate;
        this.title = title;
        this.detail = detail;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public int getDay(){
        String[] dateParts = getStartDate().split("-",3);
        int month = Integer.parseInt(dateParts[2].substring(0,2));
        return  month;
    }
    public String getTime(){
        String[] dateParts = getStartDate().split(" ");
        return  dateParts[1];
    }
    public String getMonthName(){
        return new DateFormatSymbols().getMonths()[getMonth()-1];
    }

    @Override
    public String toString() {
        return "Task{" +
                "startDate='" + startDate + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", duration=" + duration +
                ", isDone=" + isDone +
                ", id=" + id +
                '}';
    }
}
