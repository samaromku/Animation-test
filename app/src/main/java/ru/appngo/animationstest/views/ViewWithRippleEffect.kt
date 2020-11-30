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

class ViewWithRippleEffect(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    private val circlePaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
        strokeWidth = 10f
    }

    private val ripplePaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
    }

    private val secondRipplePaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
    }

    private val circleRect = RectF(500f, 500f, 550f, 550f)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawOval(circleRect, circlePaint)
        canvas.drawOval(circleRect, ripplePaint)
        canvas.drawOval(circleRect, secondRipplePaint)
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
            ripplePaint.style = Paint.Style.STROKE
            ripplePaint.strokeWidth = resizeStrokeValue
            ripplePaint.alpha = alphaValue
            secondRipplePaint.style = Paint.Style.STROKE
            secondRipplePaint.strokeWidth = resizeStrokeValue + 100
            val secondAlpha = if (alphaValue - 20 <= 0) {
                0
            } else {
                alphaValue - 20
            }
            secondRipplePaint.alpha = secondAlpha
            invalidate()
        }
        animator.start()
    }
}
