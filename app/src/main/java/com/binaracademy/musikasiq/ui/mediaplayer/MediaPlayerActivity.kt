package com.binaracademy.musikasiq.ui.mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import androidx.activity.viewModels
import com.binaracademy.musikasiq.R
import com.binaracademy.musikasiq.data.model.TrackItem
import com.binaracademy.musikasiq.databinding.ActivityMediaPlayerBinding
import com.binaracademy.musikasiq.ui.home.HomeFragment
import com.binaracademy.musikasiq.ui.listsong.ListSongFragment
import com.binaracademy.musikasiq.utils.helpers.intentTo
import com.binaracademy.musikasiq.utils.load
import com.binaracademy.musikasiq.utils.showSnackbar
import com.binaracademy.musikasiq.viewmodel.MediaPlayerViewModel

class MediaPlayerActivity : AppCompatActivity() {
    private val binding: ActivityMediaPlayerBinding by lazy {
        ActivityMediaPlayerBinding.inflate(layoutInflater)
    }

    lateinit var runnable: Runnable

    private var handler = Handler()

    private lateinit var mediaPlayer: MediaPlayer

    private val viewModel: MediaPlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val track = intent.getParcelableExtra<TrackItem>(HomeFragment.TRACK_ITEM)
        viewModel.loadTrackMetaData(track?.id.toString())

        viewModel.getUrlToPlay().observe(this) {
            it.onSuccess { meta ->
                binding.ivSong.load(meta.artworkUrl)
                binding.songTitle.text = meta.title
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(meta.audio.first().url)
                    prepare()
                    start()
                }

                setUpAction()
            }

            it.onFailure { error ->
                binding.root.showSnackbar(error.message.toString())
            }
        }

    }

    private fun setUpAction() {
        mediaPlayer.setOnPreparedListener {
            val totTime = createTimeLabel(mediaPlayer.duration)
            binding.apply {
                totalTime.text = totTime
                seekBar.max = mediaPlayer.duration
                mediaPlayer.start()
                fab.setImageResource(R.drawable.ic_pause)
            }
        }

        binding.apply {
            fab.setOnClickListener {
                if (!mediaPlayer.isPlaying) {
                    mediaPlayer.start()
                    binding.fab.setImageResource(R.drawable.ic_pause)
                } else {
                    mediaPlayer.pause()
                    binding.fab.setImageResource(R.drawable.ic_play)
                }
            }

            seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(p0: SeekBar?, position: Int, changed: Boolean) {
                    if (changed) {
                        mediaPlayer.seekTo(position)
                    }
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {
                }
                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            })

            btnBack.setOnClickListener {
                intentTo(ListSongFragment::class.java)
            }

            btnBackward.setOnClickListener {

            }

            btnForward.setOnClickListener {

            }

            btnLoopSong.setOnClickListener {

            }

            btnLoopSongWithCertainCount.setOnClickListener {

            }


        }

        runnable = Runnable {
            binding.seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        mediaPlayer.setOnCompletionListener {
            binding.apply {
                seekBar.progress = 0
                fab.setImageResource(R.drawable.ic_play)
            }
        }
    }

    private fun createTimeLabel(duration: Int): String {
        var timeLabel = ""
        val min = duration/1000/60
        val sec = duration/1000%60

        timeLabel = "$timeLabel : $min"
        if (sec < 10) {
            timeLabel += "0"
        } else {
            timeLabel += sec
        }

        return timeLabel
    }
}