package com.simplestudio.simplevideo;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class FolderAdaptor extends RecyclerView.Adapter<FolderAdaptor.FolderViewHolder> {

    private Context context;
    private ArrayList<String> folderName;
    private ArrayList<VideoFiles> videoFiles;

    public FolderAdaptor(Context context, ArrayList<String> folderName, ArrayList<VideoFiles> videoFiles) {
        this.context = context;
        this.folderName = folderName;
        this.videoFiles = videoFiles;
    }

    @NonNull
    @Override
    public FolderAdaptor.FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.folder_item, parent, false);
        return new FolderViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FolderAdaptor.FolderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class FolderViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public FolderViewHolder(@NonNull  View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.folder_image);
            textView = itemView.findViewById(R.id.folder_name);
        }
    }

    public void getFolders()
    {
        String path = Environment.getExternalStorageDirectory().toString();

    }
}
