package ru.appngo.animationstest.views

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.animation.ValueAnimator.REVERSE
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.content.ContextCompat
import ru.appngo.animationstest.R

class AttractAttentionMarkerRippleEffect(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    private val circlePaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
        strokeWidth = 10f
    }

    private val firstStrokePaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
        style = Paint.Style.STROKE
    }

    private val secondStrokePaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
        style = Paint.Style.STROKE
    }

    private val circleRect = RectF(150f, 150f, 200f, 200f)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawOval(circleRect, circlePaint)
        canvas.drawOval(circleRect, firstStrokePaint)
        canvas.drawOval(circleRect, secondStrokePaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            increaseStroke()
        }
        return true
    }

    private fun increaseStroke() {
        val strokeName = "increaseStroke"
        val alphaName = "alpha"
        val property = PropertyValuesHolder.ofFloat(strokeName, 100f, 1500f)
        val propertyAlpha = PropertyValuesHolder.ofInt(alphaName, 50, 0)
        val animator = ValueAnimator()
        animator.duration = 1000
        animator.setValues(property, propertyAlpha)
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.repeatMode = REVERSE
        animator.repeatCount = ValueAnimator.INFINITE
        animator.addUpdateListener { animation ->
            val resizeStrokeValue = animation.getAnimatedValue(strokeName) as Float
            val alphaValue = animation.getAnimatedValue(alphaName) as Int
            firstStrokePaint.strokeWidth = resizeStrokeValue
            firstStrokePaint.alpha = alphaValue
            secondStrokePaint.strokeWidth = resizeStrokeValue + 100
            val secondAlpha = if (alphaValue - 20 <= 0) {
                0
            } else {
                alphaValue - 20
            }
            secondStrokePaint.alpha = secondAlpha
            invalidate()
        }
        animator.start()
    }
}
