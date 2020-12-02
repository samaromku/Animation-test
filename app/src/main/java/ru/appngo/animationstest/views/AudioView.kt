package ru.appngo.animationstest.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import ru.appngo.animationstest.R
import ru.appngo.animationstest.domain.AudioFrameInfo

class AudioView(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    var volumes = listOf<AudioFrameInfo>()
        /*
        Add this method because we shouldn't create objects in onDraw()
         */
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

    /*
    Create and update the list of rectangles which will be drawn in the next manner:
    * - rect (10f)
    - space  (10f)
    **-**-**-**-**
    ** ** ** **
    **    **
          **
     */
    private fun updateRects(audioFrameInfos: List<AudioFrameInfo>) {
        val tempRects = mutableListOf<RectF>()
        var tempRight = 0f
        audioFrameInfos.forEach {
            tempRects.add(RectF(tempRight, 0f, tempRight + 4f, it.volume.toFloat()))
            tempRight += 6f
        }
        rects = tempRects
    }
}
