package com.example.akashpratapsingh.attendance;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Field;

public class DateWize extends AppCompatActivity {
    databasehelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_wize);
        CalendarView calendarView=(CalendarView)findViewById(R.id.calendar);
        System.out.println(" abcd"+calendarView.getDate());

        mydb=databasehelper.getInstance(DateWize.this);
        try
        {
            //CalendarView cv = (CalendarView) this.findViewById(R.id.calendarView1);
            Class<?> cvClass = calendarView.getClass();
            Field field = cvClass.getDeclaredField("mMonthName");
            field.setAccessible(true);

            try
            {
                TextView tv = (TextView) field.get(calendarView);
                tv.setTextColor(Color.BLACK);
            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Intent i=new Intent(DateWize.this,displaylist.class);
                if(dayOfMonth<10)
                    i.putExtra("CurrentDate","0"+Integer.toString(dayOfMonth)+"/"+(month+1)+"/"+year);
                else
                i.putExtra("CurrentDate",Integer.toString(dayOfMonth)+"/"+(month+1)+"/"+year);
                startActivity(i);

            }
        });

    }
}
