package com.binaracademy.musikasiq.ui.mediaplayer

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import com.binaracademy.musikasiq.R
import com.binaracademy.musikasiq.databinding.ActivityMediaPlayerBinding
import com.binaracademy.musikasiq.ui.listsong.ListSongFragment

class MediaPlayerActivity : AppCompatActivity() {
    private val binding: ActivityMediaPlayerBinding by lazy {
        ActivityMediaPlayerBinding.inflate(layoutInflater)
    }

    lateinit var runnable: Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.mahalini_sial)

        binding.apply {
            seekBar.progress = 0
            seekBar.max = mediaPlayer.duration

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
                val intentTo = Intent(this@MediaPlayerActivity, ListSongFragment::class.java)
                startActivity(intentTo)
            }

        }

        runnable = Runnable {
            binding.seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
        mediaPlayer.setOnCompletionListener {
            binding.apply {
                fab.setImageResource(R.drawable.ic_play)
                seekBar.progress = 0
            }
        }
    }
}