package com.example.akashpratapsingh.attendance;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class updateinfo extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> data=new ArrayList<>();
    databasehelper mydb=databasehelper.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateinfo);
        recyclerView=(RecyclerView)findViewById(R.id.updateinfo);
        DividerItemDecoration div=new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(div);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        getdata();
        updateinfoadapter adap=new updateinfoadapter(data,updateinfo.this);
        recyclerView.setAdapter(adap);
    }
    public void getdata()
    {
        Cursor cursor=mydb.getalldatasubject();
        while(cursor.moveToNext())
        {
            String s=cursor.getString(0);
            data.add(s);
        }
    }
}
