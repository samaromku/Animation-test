package ru.appngo.animationstest.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import ru.appngo.animationstest.R

class ViewWithShadow(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    val rect = RectF(100f, 100f, 200f, 200f)
    val paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
        setShadowLayer(32f, 8f, 8f, R.color.colorAccentShadow)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(rect, paint)
    }
}
