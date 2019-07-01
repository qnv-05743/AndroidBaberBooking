package com.applicando.androidbaberbooking.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applicando.androidbaberbooking.Model.Salon;
import com.applicando.androidbaberbooking.R;

import java.util.List;

public class MySalonAdapter extends RecyclerView.Adapter<MySalonAdapter.MyViewHolder> {

    Context context;
    List<Salon> salonList;

    public MySalonAdapter(Context context, List<Salon> salonList) {
        this.context = context;
        this.salonList = salonList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_salon, viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.txt_salon_name.setText(salonList.get(i).getName());
        myViewHolder.txt_salon_address.setText(salonList.get(i).getAddress());

    }

    @Override
    public int getItemCount() {

        return salonList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt_salon_name, txt_salon_address;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_salon_address = (TextView) itemView.findViewById(R.id.txt_salon_address);
            txt_salon_name = (TextView) itemView.findViewById(R.id.txt_salon_name);
        }
    }
}
