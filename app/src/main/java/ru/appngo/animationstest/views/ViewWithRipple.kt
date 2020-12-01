package ru.appngo.animationstest.views

import android.animation.PropertyValuesHolder.ofFloat
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import ru.appngo.animationstest.R

/*
On tap create circle and increase it. After animation ends, remove the circle.
 */
class ViewWithRipple(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

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
            increaseAnimation(Coordinates.getCoordinatesWithCenterOnTap(
                    topLeftX = event.x,
                    topLeftY = event.y,
                    size = 300f
            ))
        }
        return true
    }

    private fun increaseAnimation(coordinates: Coordinates) {
        val topLeftX = "topLeftX"
        val topLeftY = "topLeftY"
        val bottomRightX = "bottomRightX"
        val bottomRightY = "bottomRightY"
        val propertyTopLeftX = ofFloat(topLeftX, coordinates.centerX, coordinates.topLeftX)
        val propertyTopLeftY = ofFloat(topLeftY, coordinates.centerY, coordinates.topLeftY)
        val propertyBottomRightX = ofFloat(bottomRightX, coordinates.centerX, coordinates.bottomRightX)
        val propertyBottomRightY = ofFloat(bottomRightY, coordinates.centerY, coordinates.bottomRightY)
        ValueAnimator().apply {
            setValues(propertyTopLeftX, propertyTopLeftY, propertyBottomRightX, propertyBottomRightY)
            duration = 500
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { animation ->
                this@ViewWithRipple.coordinates = coordinates.copy(
                        topLeftX = animation.getAnimatedValue(topLeftX) as Float,
                        topLeftY = animation.getAnimatedValue(topLeftY) as Float,
                        bottomRightX = animation.getAnimatedValue(bottomRightX) as Float,
                        bottomRightY = animation.getAnimatedValue(bottomRightY) as Float,
                )
                invalidate()
            }
            doOnEnd {
                this@ViewWithRipple.coordinates = null
                invalidate()
            }
        }.start()
    }
}
