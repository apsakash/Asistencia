package com.example.akashpratapsingh.attendance;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.security.auth.Subject;

public class Subjectdetail extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<date> data;
    TextView date;
    String currentsubject;
    databasehelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectdetail);
        recyclerView=(RecyclerView)findViewById(R.id.recyclersubjectwize);
        layoutManager=new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        currentsubject=getIntent().getStringExtra("Subject");
        mydb=databasehelper.getInstance(Subjectdetail.this);
        date=(TextView)findViewById(R.id.currentsubject);
        date.setText(currentsubject);
        getData();
        RecyclerAdaptor r=new RecyclerAdaptor(data);
        recyclerView.setAdapter(r);
    }
    public void getData()
    {
        Cursor cursor=mydb.getdatabasedondate(currentsubject);
        String subject;
        while(cursor.moveToNext())
        {
            subject=cursor.getString(0);
            date a=new date(subject,Integer.parseInt(cursor.getString(1)));
            data.add(a);
        }

    }
}
