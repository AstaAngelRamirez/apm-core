package com.apm.core.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;

import com.apm.core.enums.VideoType;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.File;

/**
 * Created by Ing. Oscar G. Medina Cruz on 18/07/18.
 */
public class VideoUtils {

    /**
     * Load a video from File or URL
     * @param context       Application context
     * @param playerView    {@link PlayerView} to render video
     * @param videoType     {@link VideoType} of the current video
     * @param autoPlay      If the video plays automatically after loading
     * @param videoSources  Array of videos of {@link VideoType}
     * @return              {@link SimpleExoPlayer} instance
     */
    public static SimpleExoPlayer LoadVideo(Context context, PlayerView playerView, VideoType videoType,
                                            boolean autoPlay, Object... videoSources) {
        // 1. Create a default TrackSelector
        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        // 2. Create the player
        SimpleExoPlayer player =
                ExoPlayerFactory.newSimpleInstance(context, trackSelector);
        player.setPlayWhenReady(autoPlay);

        // Bind the player to the view.
        playerView.setPlayer(player);

        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, StringUtils.GetApplicationName(context)));

        if (videoSources.length == 1){
            // This is the MediaSource representing the media to be played.
            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(videoType == VideoType.FILE
                            ? Uri.fromFile((File) videoSources[0])
                            : Uri.parse(String.valueOf(videoSources[0])));
            // Prepare the player with the source.
            player.prepare(videoSource);
        } else {
            MediaSource[] videoSourceList = new MediaSource[videoSources.length];
            for (int i = 0; i < videoSources.length; i++){
                videoSourceList[i] = new ExtractorMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(videoType == VideoType.FILE
                                ? Uri.fromFile((File) videoSources[i])
                                : Uri.parse(String.valueOf(videoSources[i])));
            }
            ConcatenatingMediaSource concatenatedSource =
                    new ConcatenatingMediaSource(videoSourceList);
            player.prepare(concatenatedSource);
        }

        return player;
    }
}
