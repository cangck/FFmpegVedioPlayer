package com.leagoo.ffmpegvedioplayer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leagoo.ffmpegvedioplayer.media.Repository;
import com.leagoo.ffmpegvedioplayer.media.Video;

import java.io.File;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RecyclerView mRecycleView;
    private List<Video> videos;
    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    private void initView() {
        mRecycleView = (RecyclerView) findViewById(R.id.list);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            videos = Repository.getInstance().getVideo();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }

        videoAdapter = new VideoAdapter(MainActivity.this, videos);
        mRecycleView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecycleView.setAdapter(videoAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 100) {
                videos = Repository.getInstance().getVideo();
                videoAdapter.notifyDataSetChanged();
            }
        }
    }


    public class VideoAdapter extends RecyclerView.Adapter<ViewdioHolder> implements View.OnClickListener {
        private List<Video> video;
        private Context context;

        public VideoAdapter(Context context, List<Video> videos) {
            this.video = videos;
            this.context = context;
        }

        @Override
        public ViewdioHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
            return new ViewdioHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewdioHolder holder, int position) {
            Video video = this.video.get(position);
            Glide.with(context).load(new File(video.getPath()))
                    .centerCrop()
                    .into(holder.imageView);

            holder.imageView.setOnClickListener(this);
            holder.imageView.setTag(video);
            holder.title.setText(video.getTitle());
        }

        @Override
        public int getItemCount() {
            return video == null ? 0 : video.size();
        }


        @Override
        public void onClick(View view) {
            Video video = (Video) view.getTag();
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("path",video.getPath());
            startActivity(intent);

        }
    }


    private class ViewdioHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

        public ViewdioHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.video_view);
            title = itemView.findViewById(R.id.video_title);
        }


    }
}
