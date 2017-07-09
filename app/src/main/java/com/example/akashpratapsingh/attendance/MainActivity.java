package com.example.akashpratapsingh.attendance;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    databasehelper mydb;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mydb=databasehelper.getInstance(MainActivity.this);
        ImageButton changetodate=(ImageButton)findViewById(R.id.datewize);
        ImageButton changetosubject=(ImageButton)findViewById(R.id.subjectwize);
        ImageButton preset=(ImageButton)findViewById(R.id.preset);
        ImageButton record=(ImageButton)findViewById(R.id.input);

        changetodate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,DateWize.class);
                startActivity(i);

            }
        });
        changetosubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,displaysubject.class);
                startActivity(i);

            }
        });
        preset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,predefine.class);
                startActivity(i);
            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//on click listenner repeats if it does not get data on first try...this gave error bexause it was throwin null pointer when it cam for second call
                Intent i=new Intent(MainActivity.this,updateinfo.class);
                startActivity(i);

            }
        });
    }

}
