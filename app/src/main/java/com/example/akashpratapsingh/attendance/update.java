package com.example.akashpratapsingh.attendance;

import android.content.Context;
import android.icu.util.Calendar;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Akash Pratap Singh on 21-Feb-17.
 */

public class update {
    databasehelper mydb;
    String subject;
    String status;
    Context context;
    static ArrayList<String> subjects=new ArrayList<>();
    update(String s,String s1,Context context)
    {
        this.subject=s;
        this.status=s1;
        mydb=databasehelper.getInstance(context);
        this.context=context;
    }
    public static ArrayList<String> getsubjects()
    {
        System.out.println(subjects.size());
        return subjects;
    }
    public void process()
    {
        subject=subject.toLowerCase();
        java.util.Calendar calendar= java.util.Calendar.getInstance();
        String s=calendar.getTime().toString();
        s=s.substring(s.indexOf(' ')+1);
        String s1=s.substring(0,s.indexOf(' '));
        switch(s1)
        {
            case "Jan":s1="1";break;
            case "Feb":s1="2";break;
            case "Mar":s1="3";break;
            case "Apr":s1="4";break;
            case "May":s1="5";break;
            case "Jun":s1="6";break;
            case "Jul":s1="7";break;
            case "Aug":s1="8";break;
            case "Sep":s1="9";break;
            case "Oct":s1="10";break;
            case "Nov":s1="11";break;
            case "Dec":s1="12";break;
        }
        s=s.substring(s.indexOf(' ')+1);
        String s2=s.substring(0,s.indexOf(' '));
        String s3=s.substring(s.lastIndexOf(' ')+1);
        mydb.insert_data(s2+"/"+s1+"/"+s3,subject,status);
        Toast.makeText(context,"Successfully Updated "+subject,Toast.LENGTH_SHORT).show();
    }
}
