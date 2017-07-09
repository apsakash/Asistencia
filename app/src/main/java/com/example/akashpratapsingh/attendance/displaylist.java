package com.example.akashpratapsingh.attendance;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;


public class displaylist extends AppCompatActivity {

    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;
    ArrayList<date> data=new ArrayList<>();
    TextView date;
    String currentdate;
    databasehelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaylist);
        recyclerView=(RecyclerView)findViewById(R.id.recycler);//finding recycler view
        layoutManager=new LinearLayoutManager(this);//defining layout manager in this case it is list but can be grid and staggered grid too
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);//ensuring the recycler view has fixed size always
        currentdate=(String)getIntent().getStringExtra("CurrentDate");
        mydb=databasehelper.getInstance(displaylist.this);
        date=(TextView)findViewById(R.id.currentdate);
        date.setText(currentdate);
        getData();
        RecyclerAdaptor r=new RecyclerAdaptor(data);//calling adaptor to inflate the data
        recyclerView.setAdapter(r);
    }
    public void getData()
    {
        Cursor cursor=mydb.getdatabasedondate(currentdate);//cursor is a abstract classs
        String subject;
        while(cursor.moveToNext())
        {
            subject=cursor.getString(1);
            date a=new date(subject,Integer.parseInt(cursor.getString(2)));
            data.add(a);
        }
    }
}
