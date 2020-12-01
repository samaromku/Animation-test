package ru.appngo.animationstest.activities

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_animator_set.*
import ru.appngo.animationstest.R

class AnimatorSetActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator_set)
        val view = addView()
        animatorBuilderPattern(view)
    }

    /*
    Creates view 1*1 pixel.
     */
    private fun addView(): View {
        val view = View(this).apply {
            layoutParams = FrameLayout.LayoutParams(1, 1).apply {
                setMargins(200, 200, 200, 200)
            }
            setBackgroundColor(ContextCompat.getColor(this.context, R.color.teal_200))
        }
        container.addView(view)
        return view
    }

    private fun animatorBuilderPattern(view: View) {
        val set = AnimatorSet()
        set.duration = 3000
        set.interpolator = BounceInterpolator()
        set.play(ObjectAnimator.ofFloat(view, View.SCALE_X, 200f))
            .after(ObjectAnimator.ofFloat(view, View.SCALE_Y, 200f))
        set.start()
    }

    private fun animatorSetSequentially(view: View) {
        AnimatorSet().apply {
            duration = 3000
            interpolator = BounceInterpolator()
        }.run {
            playSequentially(
                ObjectAnimator.ofFloat(view, View.SCALE_X, 200f).apply {
                    //delay before each animation if required
                    startDelay = 1000
                },
                ObjectAnimator.ofFloat(view, View.SCALE_Y, 200f)
            )
            start()
        }
    }

    private fun animateNumberOfAnimators(view: View) {
        ObjectAnimator.ofFloat(view, View.SCALE_X, 200f).apply {
            duration = 3000
            interpolator = BounceInterpolator()
        }.start()
        ObjectAnimator.ofFloat(view, View.SCALE_Y, 200f).apply {
            duration = 3000
            interpolator = BounceInterpolator()
        }.start()
    }
}
