package com.example.akashpratapsingh.attendance;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;




/**
 * Created by Akash Pratap Singh on 20-Feb-17.
 */

public class Adaptor extends RecyclerView.Adapter<Adaptor.RecyclerViewHolder2>{
    ArrayList<subinfo> data=new ArrayList<>();
    Context context;
    databasehelper mydb;
    @Override
    public RecyclerViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.subrow,parent,false);//layout inflater used to say that rows will be of type defined in xml file
        RecyclerViewHolder2 recyclerViewHolder2=new RecyclerViewHolder2(view);//sending custom view to holder
        return recyclerViewHolder2;
    }
    Adaptor(ArrayList<subinfo> data,Context context)
    {
        this.data=data;
        this.context=context;
        this.mydb=databasehelper.getInstance(context);
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolder2 holder, int position) {
        final subinfo b=data.get(position);

        holder.subject=b.subject;
        holder.button.setText(b.subject);
        holder.percentage.setText(b.percent+"%");

        holder.bunkable.setText("Total="+b.total+" Present="+b.present+" Bunkable="+b.bunkable);
        holder.percentage.setTextColor(b.color);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class RecyclerViewHolder2 extends RecyclerView.ViewHolder {

        TextView button;
        String subject;
        TextView percentage;
        TextView bunkable;


        RecyclerViewHolder2(View view)
        {
            super(view);
            button=(TextView) view.findViewById(R.id.subinfo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, displaysubjectlist.class);
                    intent.putExtra("CurrentSubject",subject);
                    context.startActivity(intent);
                }
            });

            percentage=(TextView)view.findViewById(R.id.percentage);
            bunkable=(TextView)view.findViewById(R.id.bunkable);
        }


    }
}
