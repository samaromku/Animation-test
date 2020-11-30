package ru.appngo.animationstest.activities

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_audio_animation.*
import ru.appngo.animationstest.R
import ru.appngo.animationstest.domain.Volume

class AudioAnimationActivity : Activity() {

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
                val allList = getFullList()
                val partOfList =  allList.size * percent / 100.0f
                audio_view.volumes = allList.take(partOfList.toInt())
                audio_view.invalidate()
            }
        }.start()
    }

    private fun getFullList(): List<Volume> {
        return listOf(
                Volume(123),
                Volume(12),
                Volume(1203),
                Volume(200),
                Volume(400),
                Volume(300),
                Volume(107),
                Volume(50),
                Volume(123),
                Volume(120),
                Volume(103),
                Volume(250),
                Volume(400),
                Volume(500),
                Volume(10),
                Volume(50),
                Volume(123),
                Volume(12),
                Volume(203),
                Volume(200),
                Volume(700),
                Volume(330),
                Volume(100),
                Volume(50),
                Volume(400),
                Volume(500),
                Volume(10),
                Volume(50),
                Volume(123),
                Volume(12),
                Volume(203),
                Volume(200),
                Volume(700),
                Volume(330),
                Volume(100),
        )
    }
}
