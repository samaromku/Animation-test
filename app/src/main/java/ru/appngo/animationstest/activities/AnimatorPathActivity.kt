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
import androidx.core.graphics.PathParser
import kotlinx.android.synthetic.main.activity_animators_path.*
import ru.appngo.animationstest.R

class AnimatorPathActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animators_path)
        test_button.setOnClickListener {
            animatePath(test_view)
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
        val path = PathParser.createPathFromPathData("M19,13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z")
        val scaleMatrix = Matrix()
        scaleMatrix.setScale(10f, 10f)
        path.transform(scaleMatrix)
        return path
    }
}
