package ru.appngo.animationstest.activities

import android.animation.Animator
import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import ru.appngo.animationstest.R

class InterpolatorActivity : Activity() {

    private val animatorsList = mutableListOf<Animator>()

    private fun Animator.handleLifeCycle(): Animator {
        animatorsList.add(this)
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interpolator)
        linearAnimation()
        bounceAnimation()
        accelerateAnimation()
        decelerateAnimation()
        overshootAnimation()
    }

    override fun onPause() {
        super.onPause()
        animatorsList.forEach {
            it.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        animatorsList.forEach {
            it.resume()
        }
    }

    private fun linearAnimation() {
        ObjectAnimator.ofFloat(findViewById(R.id.linear), View.TRANSLATION_X, 500f).apply {
            duration = 3000
            interpolator = LinearInterpolator()
            repeatCount = ObjectAnimator.INFINITE
        }
                .handleLifeCycle()
                .start()

    }

    private fun bounceAnimation() {
        ObjectAnimator.ofFloat(findViewById(R.id.bounce), View.TRANSLATION_X, 500f).apply {
            duration = 3000
            interpolator = BounceInterpolator()
            repeatCount = ObjectAnimator.INFINITE
        }
                .handleLifeCycle()
                .start()
    }

    private fun accelerateAnimation() {
        ObjectAnimator.ofFloat(findViewById(R.id.accelerate), View.TRANSLATION_X, 500f).apply {
            duration = 3000
            interpolator = AccelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
        }
                .handleLifeCycle()
                .start()
    }

    private fun decelerateAnimation() {
        ObjectAnimator.ofFloat(findViewById(R.id.decelerate), View.TRANSLATION_X, 500f).apply {
            duration = 3000
            interpolator = DecelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
        }
                .handleLifeCycle()
                .start()
    }

    private fun overshootAnimation() {
        ObjectAnimator.ofFloat(findViewById(R.id.overshoot), View.TRANSLATION_X, 500f).apply {
            duration = 3000
            interpolator = OvershootInterpolator()
            repeatCount = ObjectAnimator.INFINITE
        }
                .handleLifeCycle()
                .start()
    }
}
