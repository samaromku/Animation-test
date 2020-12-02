package ru.appngo.animationstest.views

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import ru.appngo.animationstest.R

class ViewWithRepoScaleAnimation(
    context: Context,
    attributeSet: AttributeSet? = null
) : FrameLayout(context, attributeSet) {

    private val circlePaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
    }
    private var coordinates: Coordinates? = null
        /*
        Set method used to avoid creating instances in onDraw().
         */
        set(value) {
            field = value
            if (value != null) {
                this.rectF = RectF(value.topLeftX, value.topLeftY, value.bottomRightX, value.bottomRightY)
            } else {
                rectF = null
            }
        }
    private var rectF: RectF? = null

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        rectF?.let {
            canvas?.drawOval(it, circlePaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            //create view
            val view = LayoutInflater.from(context).inflate(R.layout.pixel_view, null).apply {
                layoutParams = LayoutParams(1, 1).apply {
                    x = event.x
                    y = event.y
                }
            }
            //add to container
            this.addView(view)
            //animate proper scale
            AnimatorSet().apply {
                duration = 1000
                doOnEnd {
                    removeView(view)
                }
            }
                    .run {
                        playTogether(
                                ObjectAnimator.ofFloat(view, View.SCALE_X, 300f),
                                ObjectAnimator.ofFloat(view, View.SCALE_Y, 300f))
                        start()
                    }
        }
        return true
    }
}
