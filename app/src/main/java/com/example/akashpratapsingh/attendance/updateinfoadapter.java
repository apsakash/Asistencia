package com.example.akashpratapsingh.attendance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Akash Pratap Singh on 22-Feb-17.
 */

public class updateinfoadapter extends RecyclerView.Adapter<updateinfoadapter.Adaptorviewholder> {
    ArrayList<String> data;
    Context context;


    @Override
    public Adaptorviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.updateinforow,parent,false);//layout inflater used to say that rows will be of type defined in xml file
        Adaptorviewholder adaptorview=new Adaptorviewholder(view);//sending custom view to holder
        return adaptorview;
    }
    updateinfoadapter(ArrayList<String> data,Context context)
    {
        this.data=data;
        this.context=context;
    }
    @Override
    public void onBindViewHolder(Adaptorviewholder holder, int position) {
        final String s=data.get(position);
        Log.d("hjk",s);
        holder.subject.setText(s.toUpperCase());

        holder.present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update a=new update(s,"present",context);
                a.process();
            }
        });
        holder.absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update a=new update(s,"bunk",context);
                a.process();
            }
        });
    }



    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class Adaptorviewholder extends RecyclerView.ViewHolder  {

        ImageButton present;
        ImageButton absent;
        TextView subject;

        Adaptorviewholder(View view) {
            super(view);
            present = (ImageButton) view.findViewById(R.id.present);
            absent = (ImageButton) view.findViewById(R.id.absent);
            subject = (TextView) view.findViewById(R.id.subjectdetail);

        }

    }

}
