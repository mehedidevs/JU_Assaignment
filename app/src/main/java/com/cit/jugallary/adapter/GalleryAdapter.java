package com.cit.jugallary.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cit.jugallary.DetailsActivity;
import com.cit.jugallary.R;
import com.cit.jugallary.entities.Gallery;
import com.cit.jugallary.viewHolder.GalleryViewHolder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder> {
    Context context;
    List<Gallery> galleryList;

    public GalleryAdapter(Context context, List<Gallery> galleryList) {
        this.context = context;
        this.galleryList = galleryList;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gallery,parent,false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        Gallery gallery = galleryList.get(position);

        Uri imageUri = Uri.parse(gallery.getImageUri());
        holder.img.setImageURI(imageUri);

        holder.title.setText(gallery.getImageName());

        Date date = new Date(gallery.getDateMillis());
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yy");
        String dateStr = dateFormat.format(date);
        holder.date.setText(dateStr);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("uri", gallery.getImageUri());
            intent.putExtra("title", gallery.getImageName());
            intent.putExtra("desc", gallery.getImageDetails());
            intent.putExtra("date", dateStr);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }
}
