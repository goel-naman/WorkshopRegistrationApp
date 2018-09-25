package com.example.android.registration;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WorkshopAdaptor extends RecyclerView.Adapter<WorkshopAdaptor.WorkshopViewHolder> {

    private ArrayList<WorkshopItem> items;

    WorkshopAdaptor(ArrayList<WorkshopItem> items){
        this.items=items;
    }
    @NonNull
    @Override
    public WorkshopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workshop_cardview,parent,false);
        return new WorkshopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkshopViewHolder holder, int position) {
        WorkshopItem data=items.get(position);
        holder.title.setText(data.getTitle());
        holder.description.setText(data.getDescription());
    }

    @Override
    public int getItemCount() {
        return items!=null? items.size():0;
    }

    // view holder for the data
    class WorkshopViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;

        WorkshopViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            //itemView.setOnClickListener();
        }
    }
}
