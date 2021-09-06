package com.simplestudio.simplevideo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class VideoAdaptor extends RecyclerView.Adapter<VideoAdaptor.videoViewHolder> {

    private Context context;
    static ArrayList<VideoFiles> videoFiles;

    public VideoAdaptor(Context context, ArrayList<VideoFiles> videoFiles) {
        this.context = context;
        this.videoFiles = videoFiles;
    }

    @NonNull
    @Override
    public videoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_layout, parent, false);
        return new videoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdaptor.videoViewHolder holder, int position) {

        holder.videoName.setText(videoFiles.get(position).getTitle());
        String path = videoFiles.get(position).getPath();

        try {
            Glide.with(context)
                    .load(path)
                    .placeholder(R.drawable.logo)
                    .centerCrop()
                    .into(holder.videoImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoPlayer.class);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoFiles.size();
    }


    public class videoViewHolder extends RecyclerView.ViewHolder {

        ImageView videoImage,moreImage;
        TextView videoName,videoDuration;
        CardView cardView;

        public videoViewHolder(@NonNull View itemView) {
            super(itemView);

            videoImage = itemView.findViewById(R.id.videoImage);
            moreImage = itemView.findViewById(R.id.more);
            videoName = itemView.findViewById(R.id.videoName);
            videoDuration = itemView.findViewById(R.id.videoDuration);
            cardView = itemView.findViewById(R.id.mainCardView);

        }
    }
}
