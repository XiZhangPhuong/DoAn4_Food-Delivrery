package com.example.fastfooddelivery.Adapter;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fastfooddelivery.*;
import com.example.fastfooddelivery.Model.PhotoIndicator;

import java.util.List;
import static android.os.Build.VERSION_CODES.R;

public class PhotoViewPager2Adapter extends RecyclerView.Adapter<PhotoViewPager2Adapter.PhotoViewHolder> {

    private List<PhotoIndicator> mListPhoto;

    public PhotoViewPager2Adapter(List<PhotoIndicator> mListPhoto) {
        this.mListPhoto = mListPhoto;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.example.fastfooddelivery.R.layout.item_photo, parent, false);

        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewPager2Adapter.PhotoViewHolder holder, int position) {
        PhotoIndicator photo = mListPhoto.get(position);
        if (photo == null)
            return;
        holder.img_photo.setImageResource(photo.getResourceId());
    }

    @Override
    public int getItemCount() {
        if (mListPhoto != null)
            return mListPhoto.size();
        return 0;
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_photo;
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            img_photo = itemView.findViewById(com.example.fastfooddelivery.R.id.img_photo);
        }
    }
}
