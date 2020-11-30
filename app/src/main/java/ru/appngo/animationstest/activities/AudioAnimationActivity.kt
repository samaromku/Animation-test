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

    private val fullList: List<AudioFrameInfo> =
            generateSequence {
                AudioFrameInfo(Random.nextInt(1000))
            }
                    .take(50)
                    .toList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_animation)
        drawPathGradually()
    }

    private fun drawPathGradually() {
        val percentsToDraw = "percentToDraw"
        val percentToDrawProperty = PropertyValuesHolder.ofFloat(percentsToDraw, 0f, 100f)
        ValueAnimator().apply {
            duration = 5000
            setValues(percentToDrawProperty)
            addUpdateListener {
                val percent = it.getAnimatedValue(percentsToDraw) as Float
                val allList = fullList
                val partOfList = allList.size * percent / 100.0f
                audio_view.volumes = allList.take(partOfList.toInt())
                audio_view.invalidate()
            }
        }.start()
    }
}
