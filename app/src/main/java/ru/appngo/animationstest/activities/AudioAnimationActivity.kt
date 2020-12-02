package ru.appngo.animationstest.activities

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_audio_animation.*
import ru.appngo.animationstest.R
import ru.appngo.animationstest.domain.AudioFrameInfo
import kotlin.random.Random

class AudioAnimationActivity : Activity() {

    private var animator: ValueAnimator? = null

    private val fullList: List<AudioFrameInfo> =
            generateSequence {
                AudioFrameInfo(Random.nextInt(300))
            }
                    .take(1050)
                    .toList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_animation)
        play_button.setOnClickListener {
            if (animator == null) {
                drawAudioGradually()
            } else {
                animator?.resume()
            }
        }
        pause_button.setOnClickListener {
            animator?.pause()
        }
    }

    private fun drawAudioGradually() {
        val percentsToDraw = "percentToDraw"
        val percentToDrawProperty = PropertyValuesHolder.ofFloat(percentsToDraw, 0f, 100f)
        animator = ValueAnimator().apply {
            duration = 15000
            setValues(percentToDrawProperty)
            addUpdateListener {
                val percent = it.getAnimatedValue(percentsToDraw) as Float
                val allList = fullList
                val partOfList = allList.size * percent / 100.0f
                audio_view.volumes = allList.take(partOfList.toInt())
                audio_view.invalidate()
            }
        }
        animator?.start()
    }
}
