package com.binaracademy.musikasiq.ui.mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.SeekBar
import androidx.activity.viewModels
import com.binaracademy.musikasiq.R
import com.binaracademy.musikasiq.databinding.ActivityMediaPlayerBinding
import com.binaracademy.musikasiq.ui.listsong.ListSongFragment
import com.binaracademy.musikasiq.utils.helpers.intentTo
import com.binaracademy.musikasiq.viewmodel.MediaPlayerViewModel

class MediaPlayerActivity : AppCompatActivity() {
    private val binding: ActivityMediaPlayerBinding by lazy {
        ActivityMediaPlayerBinding.inflate(layoutInflater)
    }

    lateinit var runnable: Runnable
    private var handler = Handler()
    private val viewModel: MediaPlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.loadPopularTracks(null)

        viewModel.getPopularTracks().observe(this) {
            it.onSuccess { response ->


            }
        }

        setUpAction()

    }

    private fun setUpAction() {
        val mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.mahalini_sial)

        mediaPlayer.setOnPreparedListener {
            val maxTime = createTimeLabel(mediaPlayer.duration)
            binding.apply {
                totalTime.text = maxTime
                seekBar.max = mediaPlayer.duration
                mediaPlayer.start()
                fab.setImageResource(R.drawable.ic_pause)
            }
        }

        binding.apply {
            fab.setOnClickListener {
                if (!mediaPlayer.isPlaying) {
                    mediaPlayer.start()
                    fab.setImageResource(R.drawable.ic_pause)
                } else {
                    mediaPlayer.pause()
                    fab.setImageResource(R.drawable.ic_play)
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
            val curTime = createTimeLabel(mediaPlayer.currentPosition)
            binding.apply {
                currentTime.text = curTime
                handler.postDelayed(runnable, 1000)
                seekBar.progress = mediaPlayer.currentPosition
            }
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
        val min = duration / 1000 / 60
        val sec = duration / 1000 % 60

        if (sec < 10) {
            timeLabel = "0$sec"
        } else {
            timeLabel += sec
        }

        timeLabel = "$min:$timeLabel"

        return timeLabel
    }
}