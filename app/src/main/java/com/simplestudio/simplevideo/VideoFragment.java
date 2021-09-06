package com.simplestudio.simplevideo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.simplestudio.simplevideo.MainActivity.videoFiles;


public class VideoFragment extends Fragment {

    RecyclerView recyclerVieww;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        recyclerVieww = view.findViewById(R.id.recyclerView);

        if (videoFiles != null)
        {
            VideoAdaptor adaptor = new VideoAdaptor(getContext(),videoFiles);
            recyclerVieww.setAdapter(adaptor);
        }


        return view;
    }
}