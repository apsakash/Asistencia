package com.example.akashpratapsingh.attendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by Akash Pratap Singh on 19-Feb-17.
 */

public class databasehelper extends SQLiteOpenHelper implements Serializable{//implement serializable when object is to be sent to different intents
    //generally serializable gives error like in this case it gave null pointer
    //It meant that it is not suitable for cases where one object has to be shared by different intents
    // to overcome this we use singleton
    public static databasehelper sInstance;//creating singleton
    public static final String database_name="attendance.db";
    public static final String table_name="datewize";
    public static final String date="date_of";
    public static final String subject="subject";
    public static final String table2="subjectwize";
    public Context context;
    SQLiteDatabase db=this.getWritableDatabase();



    // function to set static instance
    public static synchronized databasehelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new databasehelper(context.getApplicationContext());
        }
        return sInstance;
    }


    //constructor
    public databasehelper(Context context)
    {
        super(context,database_name,null,1);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//   to create the database
        db.execSQL("Create table "+table_name+"(date_of text,subject text, bunked integer);");
        db.execSQL("Create table "+table2+"(subject text primary key,present integer,total integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+table_name);
        onCreate(db);
    }
    //functions for datewise


    public void insert_data(String date,String subject,String bunked)
    {

        ContentValues contentValues=new ContentValues();
        contentValues.put(this.date,date);
        contentValues.put(this.subject,subject);
        boolean bool=true;
        if(bunked.equalsIgnoreCase("bunk")) {
            contentValues.put("bunked", 0);
            bool = false;
        }
        else
        contentValues.put("bunked",1);
        long rest=db.insert(table_name,null,contentValues);
        if(rest==-1)
        {

            return;
        }
        update_data(subject,bool);

    }

    public Cursor getdatabasedondate(String date)
    {
        System.out.println("Dr APS");

        Cursor cursor=db.rawQuery("Select * from datewize where date_of=\""+date+"\";",null);
        return cursor;
    }



    // functions for subject wize
    private Cursor getdatasubject(String subject)
    {

        Cursor cursor=db.rawQuery("Select * from subjectwize where subject=\""+subject+"\"",null);
        System.out.print("ds");

        return cursor;
    }


    public Cursor getalldatasubject()
    {
        Cursor cursor=db.rawQuery("Select * from "+table2+";",null);
        return cursor;
    }

    public Cursor getdatabasedonsubject(String subject)
    {
        Cursor cursor=db.rawQuery("Select * from datewize where subject=\""+subject+"\";",null);
        return cursor;
    }
    //update subject data when new date added
    public void update_data(String subject,boolean status)
    {

        ContentValues contentValues=new ContentValues();
        Cursor cursor=getdatasubject(subject);
        int k=cursor.getCount();
        contentValues.put(this.subject,subject);
        boolean s=cursor.isAfterLast();

        while(cursor.moveToNext()){
            if(status)
            contentValues.put("present",Integer.parseInt(cursor.getString(1))+1);
            else
                contentValues.put("present",Integer.parseInt(cursor.getString(1)));
        contentValues.put("total",Integer.parseInt(cursor.getString(2))+1);
        db.update(table2,contentValues,"subject=?",new String[]{subject});
        break;}

    }

    //insert data by preset button
    public void insert_data(String subject,int present,int total)
    {
        System.out.println("Came here");

        ContentValues contentValues=new ContentValues();
        contentValues.put(this.subject,subject);
        contentValues.put("present",present);
        contentValues.put("total",total);
        long rest=db.insert(table2,null,contentValues);
        if(rest==-1)
        {
            Toast.makeText(this.context,"Insertion UnSuccessful",Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this.context,"Insertion Successful",Toast.LENGTH_LONG).show();
    }
}