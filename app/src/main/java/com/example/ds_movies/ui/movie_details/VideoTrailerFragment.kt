package com.example.ds_movies.ui.movie_details

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ds_movies.R
import com.example.ds_movies.databinding.FragmentVideoTrailerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem


class VideoTrailerFragment : Fragment() {

    lateinit var dataBinding : FragmentVideoTrailerBinding

    //val url = "http://d3rlna7iyyu8wu.cloudfront.net/skip_armstrong/skip_armstrong_stereo_subs.m3u8"
    //val url = "https://www.youtube.com/watch?v=nMAzEZchKdk&t=764s"

    //live stream
    val url = "https://cph-p2p-msl.akamaized.net/hls/live/2000341/test/master.m3u8"

    //val url = "rtsp://zephyr.rtsp.stream/movie?streamKey=5b7de40fb4169e6e4de287d234ba34d4"

    //val url = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mp4"

     var player :ExoPlayer? = null
    var playWhenReady = true
    var currentItem = 0
    var playBackPosition =0L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentVideoTrailerBinding.inflate(inflater,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this){
            if(activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE){
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
            findNavController().navigate(R.id.action_videoTrailerFragment_to_moviesPopularFragment)
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("here","on attach")
    }

    override fun onStart() {
        Log.e("here","on start")
        super.onStart()
            intiPlayer()
    }

    override fun onResume() {
        super.onResume()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        Log.e("here","on resume")
        if (player==null) {
            intiPlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        Log.e("here","on pause")
        releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        Log.e("here","on stop")
        releasePlayer()
    }

    private fun intiPlayer() {

            player = ExoPlayer.Builder(requireContext())
                .build()
                .also { exoPlayer ->

                    Log.e("play video","on play video")
                dataBinding.playerView.player = exoPlayer
              //  dataBinding.playerView.useController = false

//            val dataSourceFactory = DefaultHttpDataSource.Factory()
//                    val mediaSource = DashMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(MediaItem.fromUri(url))

//            val mediaItem = MediaItem.Builder()
//                .setUri(url)
//                .setMimeType(MimeTypes.VIDEO_MPEG)
//                .build()

                val mediaItem = MediaItem.fromUri(url)
                    exoPlayer.setMediaItem(mediaItem)
                    exoPlayer.playWhenReady = playWhenReady
                    exoPlayer.seekTo(currentItem,playBackPosition)
                    exoPlayer.prepare()
                    exoPlayer.play()
            }
    }

    private fun releasePlayer(){
        player?.let { exoPlayer ->
        playWhenReady = exoPlayer.playWhenReady
        currentItem = exoPlayer.currentMediaItemIndex
        playBackPosition = exoPlayer.currentPosition
        exoPlayer.release()
        }
        player = null
    }

}