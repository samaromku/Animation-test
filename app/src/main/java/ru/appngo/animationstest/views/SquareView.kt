package ru.appngo.animationstest.views

import android.content.Context
import android.util.AttributeSet
import android.view.View

class SquareView(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    /*
    Draw square view with minimal side.
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (widthMeasureSpec > heightMeasureSpec) {
            super.onMeasure(heightMeasureSpec, heightMeasureSpec)
        } else {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec)
        }
        println("width ${MeasureSpec.toString(widthMeasureSpec)}")
        println("height ${MeasureSpec.toString(heightMeasureSpec)}")
    }
}
