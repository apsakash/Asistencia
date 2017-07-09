package com.example.akashpratapsingh.attendance;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Akash Pratap Singh on 20-Feb-17.
 */

public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.RecyclerViewHolder> {
    ArrayList<date> data=new ArrayList<>();
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.daterow,parent,false);
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }
    RecyclerAdaptor(ArrayList<date> input)
    {
        data=input;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        date d=data.get(position);
        holder.textView.setText(d.getSubject()+"  ");
        if(d.bunked==0)
        {
            holder.presence.setImageResource(R.drawable.absent);
        }
        else
            holder.presence.setImageResource(R.drawable.present2);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{//static class so that each row has class created once...not every time for each new one

        TextView textView;
        ImageView presence;
        RecyclerViewHolder(View view)
        {
            super(view);
            textView=(TextView) view.findViewById(R.id.daterow);
            presence=(ImageView)view.findViewById(R.id.presence);
        }
    }
}
