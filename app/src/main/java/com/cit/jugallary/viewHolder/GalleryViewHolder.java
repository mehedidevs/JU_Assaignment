package com.cit.jugallary.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cit.jugallary.R;

public class GalleryViewHolder extends RecyclerView.ViewHolder {
    public ImageView img;
    public TextView title,date;
    public GalleryViewHolder(@NonNull View itemView) {
        super(itemView);

        img = itemView.findViewById(R.id.img);
        title = itemView.findViewById(R.id.title);
        date = itemView.findViewById(R.id.date);
    }
}
