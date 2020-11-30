package ru.appngo.animationstest.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import ru.appngo.animationstest.R
import ru.appngo.animationstest.domain.Volume

class AudioView(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    var volumes = listOf<Volume>()
        set(value) {
            field = value
            updateRects(field)
        }
    var rects = listOf<RectF>()
    private val paint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        rects.forEach {
            canvas?.drawRect(it, paint)
        }
    }

    private fun updateRects(volumes: List<Volume>) {
        val tempRects = mutableListOf<RectF>()
        var tempRight = 0f
        volumes.forEach {
            tempRects.add(RectF(tempRight, 0f, tempRight + 20f, it.volume.toFloat()))
            tempRight += 30f
        }
        rects = tempRects
    }
}
