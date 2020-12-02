package ru.appngo.animationstest.activities

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.app.Activity
import android.graphics.Matrix
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.view.animation.PathInterpolator
import androidx.core.content.ContextCompat
import androidx.core.graphics.PathParser
import kotlinx.android.synthetic.main.activity_animators.*
import ru.appngo.animationstest.R

class AnimatorActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animators)
        test_button.setOnClickListener {
            changeViewBackgroundColor(test_view)
        }
    }

    private val TRANSLATION_X = "TRANSLATION_X"
    private val TRANSLATION_Y = "TRANSLATION_Y"

    private fun valueAnimatorAnimate() {
        ValueAnimator().apply {
            setValues(
                    PropertyValuesHolder.ofFloat(TRANSLATION_X, 1000f),
                    PropertyValuesHolder.ofFloat(TRANSLATION_Y, 100f)
            )
            duration = 2000
            addUpdateListener {
                test_view.translationX = it.getAnimatedValue(TRANSLATION_X) as Float
                test_view.translationY = it.getAnimatedValue(TRANSLATION_Y) as Float
            }
        }.start()
    }

    private fun changeViewBackgroundColor(view: View) {
        val firstColor = ContextCompat.getColor(this, R.color.colorAccent)
        val secondColor = ContextCompat.getColor(this, R.color.purple_700)
        ObjectAnimator.ofArgb(view, "backgroundColor", firstColor, secondColor).apply {
            duration = 3000
        }.start()
    }

    private fun rotateY(view: View) {
        ObjectAnimator.ofFloat(view, View.ROTATION_Y, 1000f).apply {
            duration = 2000
        }.start()
    }

    /**
     * В PathInterpolator можно использовать только особый Path, который начинается в 0, 0 и заканчивается в 1, 1
     */
    private fun animatePathWithInterpolator(view: View) {
        val path = Path()
        path.lineTo(0.25f, 0.25f)
        path.moveTo(0.25f, 0.5f)
        path.lineTo(1f, 1f)
        val pathInterpolator = PathInterpolator(path)
        ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 300f).apply {
            interpolator = pathInterpolator
            duration = 3000
            start()
        }
    }

    private fun animatePath(view: View) {
        ObjectAnimator.ofFloat(view, View.TRANSLATION_X, View.TRANSLATION_Y, getPathFromPathData())
                .apply {
                    duration = 10000
                    start()
                }
    }

    private fun getPathFromPathData(): Path {
        val path = PathParser.createPathFromPathData(
                "M22,11h-4.17l3.24,-3.24 -1.41,-1.42L15,11h-2V9l4.66,-4.66 -1.42,-1.41L13,6.17V2h-2v4.17L7.76,2.93 6.34,4.34 11,9v2H9L4.34,6.34 2.93,7.76 6.17,11H2v2h4.17l-3.24,3.24 1.41,1.42L9,13h2v2l-4.66,4.66 1.42,1.41L11,17.83V22h2v-4.17l3.24,3.24 1.42,-1.41L13,15v-2h2l4.66,4.66 1.41,-1.42L17.83,13H22z")
        val scaleMatrix = Matrix()
        scaleMatrix.setScale(10f, 10f)
        path.transform(scaleMatrix)
        return path
    }
}
