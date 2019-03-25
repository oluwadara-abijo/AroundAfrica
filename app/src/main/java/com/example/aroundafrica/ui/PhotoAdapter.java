package com.example.aroundafrica.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aroundafrica.R;
import com.example.aroundafrica.data.Photo;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {

    private List<Photo> mPhotos;
    private Context mContext;

    PhotoAdapter(List<Photo> photos, Context context) {
        mPhotos = photos;
        mContext = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        int layout = R.layout.list_item_photo;
        View view = LayoutInflater.from(mContext).inflate(layout, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewholder, int i) {
        Photo currentPhoto = mPhotos.get(i);
        myViewholder.textView.setText(currentPhoto.getTitle());
        Glide.with(mContext).load(currentPhoto.getThumbnailUrl()).into(myViewholder.imageView);
    }

    void setPhotos(List<Photo> mPhotos) {
        this.mPhotos = mPhotos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mPhotos == null ) return 0;
        return mPhotos.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView_title);
        }
    }

}
