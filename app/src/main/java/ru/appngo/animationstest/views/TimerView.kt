package ru.appngo.animationstest.views

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.content.ContextCompat
import ru.appngo.animationstest.R
import kotlin.math.cos
import kotlin.math.sin

class TimerView(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    private val centerX = 400f
    private val centerY = 400f
    private val radius = 300f
    private val rectangleHeight = 100
    private var paths = mutableListOf<Path>()
    private var time:String = ""

    private val paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
        strokeWidth = 16f
        style = Paint.Style.STROKE
    }

    private val textPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
        textSize = 54f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paths.forEach {
            canvas?.drawPath(it, paint)
        }
        canvas?.drawText(time, 350f, 420f, textPaint)
    }

    fun animateView() {
        val angle = "angle"
        val propertyAngle = PropertyValuesHolder.ofFloat(angle, 0f, 360f)
        ValueAnimator().apply {
            duration = 10000
            setValues(propertyAngle)
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { animation ->
                val angleValue = animation.getAnimatedValue(angle) as Float
                paths.add(getRectangleOnCircleShapePath(angleValue.toInt()))
                time = "${duration - currentPlayTime}"
                invalidate()
            }
        }.start()
    }

    private fun getRectangleOnCircleShapePath(angel: Int): Path {
        val p = Path()
        val dX = (radius * cos(Math.toRadians(angel.toDouble()))).toInt()
        val dY = (radius * sin(Math.toRadians(angel.toDouble()))).toInt()
        val dhx = (rectangleHeight / 2 * cos(Math.toRadians(angel.toDouble()))).toInt()
        val dhy = (rectangleHeight / 2 * sin(Math.toRadians(angel.toDouble()))).toInt()
        p.moveTo((centerX + dX - dhx), (centerY - dY + dhy))
        p.lineTo((centerX + dX + dhx), (centerY - dY - dhy))
        return p
    }
}
