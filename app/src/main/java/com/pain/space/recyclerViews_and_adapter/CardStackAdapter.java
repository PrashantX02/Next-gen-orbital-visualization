package com.pain.space.recyclerViews_and_adapter;

import static android.view.View.GONE;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pain.space.R;
import com.pain.space.model.swipe_card_model;

import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    public List<swipe_card_model> list;
    private Context context;
    public CardStackAdapter(List<swipe_card_model> list, Context context){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.space_card_swap,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        swipe_card_model model = list.get(position);
//        if(model.getHeaders() == "") {
//            holder.header.setText("Prashant");
//        }

        if(model.getHeaders().isEmpty()){

            holder.header.setVisibility(GONE);
            holder.photo.setVisibility(GONE);
            holder.description.setVisibility(GONE);

            holder.lines.setVisibility(View.VISIBLE);
            holder.lines.setText(model.getLines());
        }else{
            holder.lines.setVisibility(GONE);

            holder.header.setVisibility(View.VISIBLE);
            holder.photo.setVisibility(View.VISIBLE);
            holder.description.setVisibility(View.VISIBLE);

            holder.header.setText(model.getHeaders());
            holder.photo.setImageResource(model.getImage());
            holder.description.setText(model.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void  setItems(List<swipe_card_model> newItems) {
        this.list = newItems;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView header,description,lines;
        ImageView photo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            header = itemView.findViewById(R.id.header_title);
            lines = itemView.findViewById(R.id.lines);
            description = itemView.findViewById(R.id.tag_state_description);
            photo = itemView.findViewById(R.id.imageView30);
        }
    }

}