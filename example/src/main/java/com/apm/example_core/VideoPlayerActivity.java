package com.apm.example_core;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apm.core.enums.VideoType;
import com.apm.core.utils.VideoUtils;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;

public class VideoPlayerActivity extends AppCompatActivity {

    private PlayerView mPlayerView;
    private Player mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        mPlayerView = findViewById(R.id.player_view);

        mPlayer = VideoUtils.LoadVideo(VideoPlayerActivity.this, mPlayerView, VideoType.URL, true,
                "https://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_2mb.mp4",
                "https://www.sample-videos.com/video/mkv/720/big_buck_bunny_720p_2mb.mkv",
                "https://www.sample-videos.com/video/3gp/144/big_buck_bunny_144p_2mb.3gp");

    }

    @Override
    protected void onDestroy() {
        if (mPlayer != null){
            mPlayer.release();
        }
        super.onDestroy();
    }
}
