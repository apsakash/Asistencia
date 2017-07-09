package com.example.akashpratapsingh.attendance;

import android.database.Cursor;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class displaysubject extends AppCompatActivity {//same comments as in display list
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    databasehelper mydb;
    ArrayList<subinfo> data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaysubject);
        recyclerView=(RecyclerView)findViewById(R.id.recyclersubject) ;
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mydb=databasehelper.getInstance(displaysubject.this);
        getData();
        System.out.println(" Hello World");
        Adaptor r=new Adaptor(data,displaysubject.this);
        recyclerView.setAdapter(r);
    }
    public void getData()
    {
        Cursor cursor=null;
        if(mydb!=null)
            cursor=mydb.getalldatasubject();
        String subject;
        int present;
        int total;
        int bunkable;
        int k=cursor.getCount();
        while(cursor.moveToNext())
        {

            subject=cursor.getString(0);
            System.out.println(subject);
            present=Integer.parseInt(cursor.getString(1));
            total=Integer.parseInt(cursor.getString(2));
            bunkable=(total-present>total/4)?0:total/4-total+present;
            subinfo sub=new subinfo();
            sub.total=total;
            total=100*present/total;

            sub.present=present;
            sub.bunkable=bunkable;
            sub.percent=total;
            sub.subject=subject;
            if(bunkable!=0)
            {
                sub.color=0xff00ff7f;
            }
            else
                sub.color=0xffff0000;
            data.add(sub);
            System.out.println("soks");
        }
    }
}
