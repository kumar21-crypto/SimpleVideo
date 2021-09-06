    package com.simplestudio.simplevideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    static ArrayList<VideoFiles> videoFiles = new ArrayList<>();
    static ArrayList<String> folderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, new VideoFragment()).commit();

        Dexter.withContext(MainActivity.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        Toast.makeText(MainActivity.this, "permission given", Toast.LENGTH_SHORT).show();
                        videoFiles = getAllVideos(MainActivity.this);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                        permissionToken.continuePermissionRequest();

                    }
                })
                .check();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId())
                {
                    case R.id.videoId:
                        fragment = new VideoFragment();
                        break;

                    case R.id.folderId:
                        fragment = new Folder();
                        break;

                    case R.id.filterId:
                        fragment = new Filter();
                        break;

                }

                loadFragment(fragment);

                return true;
            }
        });


    }

    public void loadFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainContainer, fragment).commit();
    }

    public ArrayList<VideoFiles> getAllVideos(Context context)
    {
        ArrayList<VideoFiles> tempVideoFiles = new ArrayList<>();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] proj = {
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.DATE_ADDED,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DISPLAY_NAME

        };

        Cursor videoCursor = getContentResolver().query(uri,proj,null,null,null);

        if (videoCursor != null)
        {
            while (videoCursor.moveToNext())
            {
                String id = videoCursor.getString(0);
                String path = videoCursor.getString(1);
                String title = videoCursor.getString(2);
                String size = videoCursor.getString(3);
                String duration = videoCursor.getString(4);
                String date = videoCursor.getString(5);
                String filename = videoCursor.getString(6);

                VideoFiles videoFiles = new VideoFiles(id, path,title, filename, size, date, duration);

                tempVideoFiles.add(videoFiles);

            }
            videoCursor.close();

        }
        return tempVideoFiles;
    }

}