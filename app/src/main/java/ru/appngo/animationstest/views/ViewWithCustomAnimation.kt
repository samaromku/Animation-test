package ru.appngo.animationstest.views

import android.animation.PropertyValuesHolder.ofFloat
import android.animation.PropertyValuesHolder.ofInt
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.content.ContextCompat
import ru.appngo.animationstest.R
import ru.appngo.animationstest.views.Coordinates.Companion.getCoordinatesWithCenterOnTap


class ViewWithCustomAnimation(
    context: Context,
    attributes: AttributeSet? = null
) : @JvmOverloads View(context, attributes) {

    private val paint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
        strokeWidth = 10f
        textSize = 80f
    }
    private val viewCoordinates = mutableListOf<Coordinates>()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        viewCoordinates.forEach {
            drawOnView(it, canvas)
        }
    }


    private fun drawOnView(coordinates: Coordinates, canvas: Canvas) {
        paint.apply {
            alpha = coordinates.alpha
        }
        val oval = RectF(coordinates.topLeftX, coordinates.topLeftY, coordinates.bottomRightX, coordinates.bottomRightY)
        canvas.drawRoundRect(oval, coordinates.radius, coordinates.radius, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == ACTION_DOWN) {
            val coordinates = getCoordinatesWithCenterOnTap(event.x, event.y, 300f)
            viewCoordinates.add(coordinates)
            fadeInAnimation(coordinates)
            reduceAnimation(coordinates)
        }
        return true
    }

    /**
     * fadeInAnimation(coordinates)
     * reduceAnimation(coordinates)
     * bound looks amazing
     */
    private fun fadeInAnimation(coordinates: Coordinates) {
        val fadeInName = "fadeIn"
        val maxValue = 255
        val property = ofInt(fadeInName, 0, maxValue)
        val animator = ValueAnimator()
        animator.duration = 1500
        animator.setValues(property)
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addUpdateListener { animation ->
            with(viewCoordinates[viewCoordinates.indexOf(coordinates)]) {
                val currentValue = animation.getAnimatedValue(fadeInName) as Int
                alpha = currentValue
            }
            invalidate()
        }
        animator.start()
    }

    private fun reduceAnimation(coordinates: Coordinates) {
        val changeTopLeftX = "changeTopLeftX"
        val changeTopLeftY = "changeTopLeftY"
        val changeBottomRightX = "changeBottomRightX"
        val changeBottomRightY = "changeBottomRightY"
        val propertyTopLeftX = ofFloat(changeTopLeftX, coordinates.topLeftX, coordinates.centerX)
        val propertyTopLeftY = ofFloat(changeTopLeftY, coordinates.topLeftY, coordinates.centerY)
        val propertyBottomRightX = ofFloat(changeBottomRightX, coordinates.bottomRightX, coordinates.centerX)
        val propertyBottomRightY = ofFloat(changeBottomRightY, coordinates.bottomRightY, coordinates.centerY)
        val animator = ValueAnimator()
        animator.setValues(propertyTopLeftX, propertyTopLeftY, propertyBottomRightX, propertyBottomRightY)
        animator.duration = 1500
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addUpdateListener { animation ->
            with(viewCoordinates[viewCoordinates.indexOf(coordinates)]) {
                topLeftX = animation.getAnimatedValue(changeTopLeftX) as Float
                topLeftY = animation.getAnimatedValue(changeTopLeftY) as Float
                bottomRightX = animation.getAnimatedValue(changeBottomRightX) as Float
                bottomRightY = animation.getAnimatedValue(changeBottomRightY) as Float
            }
            invalidate()
        }
        animator.start()
    }

    // TODO: move into separate view, make it work -> . Savchenko 23.11.2020
    private fun rippleEffectAnimation(coordinates: Coordinates) {
        val rippleName = "ripple"
        val property = ofFloat(rippleName, 0f, 100f)
        val animator = ValueAnimator()
        animator.duration = 1500
        animator.setValues(property)
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addUpdateListener { animation ->
            with(viewCoordinates[viewCoordinates.indexOf(coordinates)]) {
                val currentValue = animation.getAnimatedValue(rippleName) as Float
                topLeftX -= currentValue
                topLeftY -= currentValue
                bottomRightX += currentValue
                bottomRightY += currentValue
                radius += currentValue
            }
            invalidate()
        }
        animator.start()
    }

    // TODO: move into separate view, make it work -> . Savchenko 23.11.2020
    private fun squareToCircle(coordinates: Coordinates) {
        val radiusName = "radius"
        val propertyRadius = ofFloat(radiusName, 150f, 0f)

        val animator = ValueAnimator()
        animator.setValues(propertyRadius)
        animator.duration = 1500
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addUpdateListener { animation ->
            with(viewCoordinates[viewCoordinates.indexOf(coordinates)]) {
                radius = animation.getAnimatedValue(radiusName) as Float
            }

            invalidate()
        }
        animator.start()
    }

    // TODO: move into separate view, make it work -> . Savchenko 23.11.2020
    private fun flyAway(coordinates: Coordinates) {
        val flyAwayName = "flyAway"
        val propertyFlyAway = ofFloat(flyAwayName, 0f, 150f)
        val animator = ValueAnimator()
        animator.setValues(propertyFlyAway)
        animator.duration = 2500
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addUpdateListener { animation ->
            with(viewCoordinates[viewCoordinates.indexOf(coordinates)]) {
                topLeftX += animation.getAnimatedValue(flyAwayName) as Float
                topLeftY += animation.getAnimatedValue(flyAwayName) as Float
                bottomRightX += animation.getAnimatedValue(flyAwayName) as Float
                bottomRightY += animation.getAnimatedValue(flyAwayName) as Float
            }

            invalidate()
        }
        animator.start()
    }
}


data class Coordinates(
    var topLeftX: Float,
    var topLeftY: Float,
    var bottomRightX: Float,
    var bottomRightY: Float,
    val centerX: Float,
    val centerY: Float,
    var radius: Float,
    var alpha: Int
) {
    companion object {
        fun getCoordinatesWithCenterOnTap(topLeftX: Float, topLeftY: Float, size: Float): Coordinates {
            val newTopLeftX = topLeftX - size / 2
            val newTopLeftY = topLeftY - size / 2
            return Coordinates(
                    topLeftX = newTopLeftX,
                    topLeftY = newTopLeftY,
                    bottomRightX = newTopLeftX + size,
                    bottomRightY = newTopLeftY + size,
                    radius = 150f,
                    centerX = topLeftX,
                    centerY = topLeftY,
                    alpha = 0)
        }
    }
}
