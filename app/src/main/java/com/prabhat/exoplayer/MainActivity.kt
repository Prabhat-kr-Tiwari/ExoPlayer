package com.prabhat.exoplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import com.prabhat.exoplayer.ui.theme.ExoPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExoPlayerTheme {
                /* Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                 }*/
                Player()
            }
        }
    }

    /*@OptIn(UnstableApi::class)
    @Composable
    fun Player() {

        var lifecycle by remember {

            mutableStateOf(Lifecycle.Event.ON_CREATE)
        }
        val context = LocalContext.current
*//*        val mediaItem =
            MediaItem.fromUri("android.resource://${context.packageName}/${R.raw.sample_video}")*//*

*//*        val mediaItem =
            MediaItem.fromUri("https://file-examples.com/storage/fe793dd9be65a9b389251ea/2017/04/file_example_MP4_480_1_5MG.mp4")*//*
                val mediaItem =
            MediaItem.fromUri("https://cdn.pixabay.com/video/2024/02/21/201308-915375262_tiny.mp4")
        val mediaSource:MediaSource=ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
            .createMediaSource(mediaItem)
        val exoplayer = remember {
            ExoPlayer.Builder(context).build().apply {
//                setMediaItem(mediaItem)
                setMediaSource(mediaSource)
                prepare()
                playWhenReady = true
            }
        }
        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(key1 = lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                lifecycle = event
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {

                exoplayer.release()
                lifecycleOwner.lifecycle.removeObserver(observer)
            }

        }
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f), factory = {
                PlayerView(context).also { playerView ->
                    playerView.player = exoplayer

                }

            },
            update = {
                when (lifecycle) {

                    Lifecycle.Event.ON_RESUME -> {
                        it.onPause()
                        it.player?.pause()
                    }

                    Lifecycle.Event.ON_PAUSE -> {
                        it.onResume()
                    }

                    else -> Unit
                }

            }
        )

    }*/
    @OptIn(UnstableApi::class)
    @Composable
    fun Player() {

        var lifecycle by remember {

            mutableStateOf(Lifecycle.Event.ON_CREATE)
        }
        val context = LocalContext.current
        /*        val mediaItem =
                    MediaItem.fromUri("android.resource://${context.packageName}/${R.raw.sample_video}")*/

        /*        val mediaItem =
                    MediaItem.fromUri("https://file-examples.com/storage/fe793dd9be65a9b389251ea/2017/04/file_example_MP4_480_1_5MG.mp4")*/
        val mediaItem =
            MediaItem.fromUri("https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_ts/master.m3u8")
        val mediaSource:MediaSource=HlsMediaSource.Factory(DefaultHttpDataSource.Factory())
            .createMediaSource(mediaItem)
        val exoplayer = remember {
            ExoPlayer.Builder(context).build().apply {
//                setMediaItem(mediaItem)
                setMediaSource(mediaSource)
                prepare()
                playWhenReady = true
            }
        }
        val lifecycleOwner = LocalLifecycleOwner.current
        DisposableEffect(key1 = lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                lifecycle = event
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {

                exoplayer.release()
                lifecycleOwner.lifecycle.removeObserver(observer)
            }

        }
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f), factory = {
                PlayerView(context).also { playerView ->
                    playerView.player = exoplayer

                }

            },
            update = {
                when (lifecycle) {

                    Lifecycle.Event.ON_RESUME -> {
                        it.onPause()
                        it.player?.pause()
                    }

                    Lifecycle.Event.ON_PAUSE -> {
                        it.onResume()
                    }

                    else -> Unit
                }

            }
        )

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExoPlayerTheme {
        Greeting("Android")
    }
}
// local: "android.resource://${context.packageName}/${R.raw.sample_video}"

// online:
//       "https://file-examples.com/storage/fe793dd9be65a9b389251ea/2017/04/file_example_MP4_480_1_5MG.mp4"

// live playback:
//        "https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_ts/master.m3u8"