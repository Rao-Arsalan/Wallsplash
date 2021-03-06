package com.media.wallpapers.wallsplash.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.media.wallpapers.wallsplash.R;
import com.media.wallpapers.wallsplash.holder.LatestWallpaperHolder;
import com.media.wallpapers.wallsplash.model.Photo;
import com.media.wallpapers.wallsplash.utill.GlideApp;

import java.util.List;

public class LatestWallpaperAdapter extends PagedListAdapter<Photo, LatestWallpaperHolder> {
    private static final String TAG = LatestWallpaperAdapter.class.getSimpleName();
    private static final DiffUtil.ItemCallback<Photo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Photo>() {
        @Override
        public boolean areItemsTheSame(Photo oldItem, Photo newItem) {
            return oldItem.getPhotoId().equals(newItem.getPhotoId());
        }

        @Override
        public boolean areContentsTheSame(Photo oldItem, Photo newItem) {
            return oldItem.equals(newItem);
        }

    };
    private RequestManager glide;
    // private List<Photo> mWallpaperList;
    private Context mContext;
    public LatestWallpaperAdapter(Context context, List<Photo> mWallpaperList, RequestManager glide) {
        super(DIFF_CALLBACK);
        this.mContext = context;
        //this.mWallpaperList = mWallpaperList;
        this.glide = glide;
    }
    @NonNull
    @Override
    public LatestWallpaperHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_latest, parent, false);
        return new LatestWallpaperHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LatestWallpaperHolder holder, int position) {
        Photo photo = getItem(position);
        String paletteRGBColor = photo.getPhotoColor() != null ? photo.getPhotoColor() : mContext.getString(R.string.default_palette_color);
        // Log.i(TAG, "color-->" + paletteRGBColor);
        int color = Color.parseColor(paletteRGBColor);
        int height = photo.getPhotoHeight();
        int width = photo.getPhotoWidth();
        /*Log.i("MYAPP", MyApp.getInstance() + "");
        Log.i("Height", height + "");
        Log.i("width", width + "");
*/

        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        //Log.i("DiplayMatrics-->", displayMetrics.widthPixels + "");

        float finalHeight = displayMetrics.widthPixels / ((float) width / (float) height);
        // Log.i("Display final Height", finalHeight + "");
       /*holder.getmLatestWallpaperIv().getLayoutParams().height = height;
       holder.getmLatestWallpaperIv().getLayoutParams().width = width;
       holder.getmLatestWallpaperIv().requestLayout();*/
       /*holder.getmLatestWallpaperCv().setCardBackgroundColor(color);
       holder.getmLatestWallpaperIv().setMaxWidth(photo.getPhotoWidth());
       holder.getmLatestWallpaperIv().setMaxHeight(photo.getPhotoHeight());*/

        //.asBitmap()
        GlideApp.with(mContext).load(photo.getPhotoUrl().getRegular())
                .placeholder(new ColorDrawable(color))
                .fallback(R.mipmap.ic_launcher_round)

                .into(holder.getmLatestWallpaperIv());
        holder.getmLatestWallpaperIv().setMinimumHeight((int) finalHeight);
        Log.i(TAG, "photo id :" + photo.getPhotoId());
        Log.i(TAG, "photo url :" + photo.getPhotoUrl().getSmall());

    }


}
