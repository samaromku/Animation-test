package ru.appngo.animationstest.activities

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_draw_path_view.*
import ru.appngo.animationstest.R


class GraduallyDrawPathViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_path_view)
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
                snow_flake.updatePartialPath(percent)
                snow_flake.invalidate()
            }
        }.start()
    }
}
