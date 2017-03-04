package com.amb.wallpaper.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amb.wallpaper.R;
import com.amb.wallpaper.databinding.ItemPhotoViewBinding;
import com.amb.wallpaper.model.Photo;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by abarsode on 3/4/17.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder> {

    private List<Photo> mPhotoList;

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemPhotoViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_photo_view, parent, false);
        PhotoViewHolder photoViewHolder = new PhotoViewHolder(binding);
        return photoViewHolder;
    }

    @Override
    public int getItemCount() {
        if (mPhotoList == null) {
            return 0;
        } else {
            return mPhotoList.size();
        }

    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {

        if (mPhotoList != null && position < mPhotoList.size()) {
            ImageView imageView = holder.mPhotoViewBinding.photoImageview;
            String url = getPhotoUrl(mPhotoList.get(position));
            Glide
                    .with(imageView.getContext())
                    .load(url)
                    .centerCrop()
//                    .placeholder(android.R.color.darker_gray)
                    .crossFade()
                    .into(imageView);
        }

    }

    //        https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg
    static final String flickrUrl = "https://farm%1$s.staticflickr.com/%2$s/%3$s_%4$s_n.jpg";

    private String getPhotoUrl(Photo photo) {
        if (photo != null) {
            return String.format(flickrUrl, photo.getFarm(), photo.getServer(), photo.getId(), photo.getSecret());
        } else {
            return null;
        }
    }

    public void setPhotoList(List<Photo> photoList) {
        mPhotoList = photoList;
        notifyDataSetChanged();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {

        ItemPhotoViewBinding mPhotoViewBinding;

        public PhotoViewHolder(ItemPhotoViewBinding photoViewBinding) {
            super(photoViewBinding.getRoot());
            mPhotoViewBinding = photoViewBinding;
        }

        public PhotoViewHolder(View itemView) {
            super(itemView);
        }
    }

}


