package ru.appngo.animationstest.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.PathParser
import ru.appngo.animationstest.R

class SnowFlakePathView(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    var currentPath = Path()

    private val paint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
        strokeWidth = 10f
        textSize = 80f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(currentPath, paint)
    }

    /**
     * The logic of calculating partial path is taken from SOF and could be improved someday.
     */
    fun updatePartialPath(percent: Float) {
        val measure = PathMeasure(getSnowflakeFullPath(), false)
        val length = measure.length
        val partialPath = Path()
        measure.getSegment(0f, length * percent / 100.0f, partialPath, true)
        currentPath = partialPath
    }

    private fun getSnowflakeFullPath(): Path {
        val path = PathParser.createPathFromPathData(
                "M22,11h-4.17l3.24,-3.24 -1.41,-1.42L15,11h-2V9l4.66,-4.66 -1.42,-1.41L13,6.17V2h-2v4.17L7.76,2.93 6.34,4.34 11,9v2H9L4.34,6.34 2.93,7.76 6.17,11H2v2h4.17l-3.24,3.24 1.41,1.42L9,13h2v2l-4.66,4.66 1.42,1.41L11,17.83V22h2v-4.17l3.24,3.24 1.42,-1.41L13,15v-2h2l4.66,4.66 1.41,-1.42L17.83,13H22z")
        val scaleMatrix = Matrix()
        scaleMatrix.setScale(10f, 10f)
        path.transform(scaleMatrix)
        return path
    }
}
