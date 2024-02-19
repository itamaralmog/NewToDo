package com.example.newtodo;

import android.util.Log;

import java.io.Serializable;

public class TaskData implements Serializable {
    String taskTitle, taskDes;

    int year,month,day,hour,minutes;
    boolean active;
    public TaskData(){
        this.taskTitle = " ";
        this.taskDes = " ";
        this.year = 2024;
        this.month = 1;
        this.day = 22;
        this.hour = 12;
        this.minutes = 10;
        this.active = true;

    }

    public TaskData(String taskTitle, String taskDes){
        this.taskTitle = taskTitle;
        this.taskDes = taskDes;
        this.year = 2023;
        this.month = 7;
        this.day = 16;
        this.hour = 12;
        this.minutes = 10;
        this.active = true;
    }

    public TaskData(int y,int m, int d){
        this.taskTitle = " ";
        this.taskDes = " ";
        this.year = y;
        this.month = m;
        this.day = d;
        this.hour = 12;
        this.minutes = 10;
        this.active = true;
    }
    public TaskData(int h,int m){
        this.taskTitle = " ";
        this.taskDes = " ";
        this.year = 2024;
        this.month = 1;
        this.day = 22;
        this.hour = h;
        this.minutes = m;
        this.active = true;
    }

    public TaskData(String taskTitle, String taskDes,int y,int m,int d,int h, int mi){
        this.taskTitle = taskTitle;
        this.taskDes = taskDes;
        this.year = y;
        this.month = m;
        this.day = d;
        this.hour = h;
        this.minutes = mi;
        this.active = true;
    }
    public TaskData(String taskTitle, String tadkDes, String date ,boolean active){
        this.taskTitle = taskTitle;
        this.taskDes = tadkDes;
        Log.d("TaskData",date + "TaskData constructor");
        String [] space = date.split(" ");
        Log.d("TaskData",date + "TaskData constructor "+ space.length);
        Log.d("TaskData space", "TaskData space" + space[0] + " " + space[1]);
        String[] dateint = space[0].split("\\.");
        Log.d("TaskData", "TaskData yaer " + dateint.length);
        String[] time = space[1].split(":");
        this.year = Integer.parseInt(dateint[2]);
        this.month = Integer.parseInt(dateint[1]);
        this.day = Integer.parseInt(dateint[0]);
        this.hour = Integer.parseInt(time[0]);
        this.minutes = Integer.parseInt(time[1]);
        this.active = active;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskDes() {
        return taskDes;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }
    public String getDate(){
        String date = getDay() +"." + getMonth() + "." + getYear();
        String time = getHour() + ":" + getMinutes();
        return date + " " + time;
    }

    public boolean getActive(){return active;}

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void setTaskDes(String taskDes) {
        this.taskDes = taskDes;
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

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
