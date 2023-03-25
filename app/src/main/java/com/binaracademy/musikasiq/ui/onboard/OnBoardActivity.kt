package com.binaracademy.musikasiq.ui.onboard

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.binaracademy.musikasiq.databinding.ActivityOnboardBinding
import com.binaracademy.musikasiq.ui.main.MainActivity
import com.binaracademy.musikasiq.utils.helpers.intentTo

class OnBoardActivity : AppCompatActivity() {
    private val binding: ActivityOnboardBinding by lazy {
        ActivityOnboardBinding.inflate(layoutInflater)
    }

    var Videoview: VideoView? = null
    var mediaControls: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.binaracademy.musikasiq.R.layout.activity_onboard)

        Videoview = findViewById<VideoView>(com.binaracademy.musikasiq.R.id.videoView) as VideoView

        if (mediaControls == null) {
            mediaControls = MediaController(this)

            mediaControls!!.setAnchorView(this.Videoview)
        }

        Videoview!!.setMediaController(mediaControls)
        Videoview!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + com.binaracademy.musikasiq.R.raw.onboarding))
        Videoview!!.requestFocus()
        Videoview!!.start()

        setVideoViewToLoop(Videoview!!)

        binding.apply {
            btnGetstarted.setOnClickListener{
                intentTo(MainActivity::class.java)
            }
        }
    }

    private fun setVideoViewToLoop(Videoview : VideoView){
        Videoview.setOnCompletionListener {
            Videoview.start()
        }
    }
}