package ru.appngo.animationstest.activities

import android.app.Activity
import android.os.Bundle
import android.view.animation.*
import kotlinx.android.synthetic.main.activity_animation.*
import ru.appngo.animationstest.R

class AnimationActivity : Activity() {

    lateinit var animation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
    }

    private fun animateView() {
        //нет методов resume(), pause()
        animation = AnimationSet(true).apply {
            duration = 5000
            //важный флаг. Если его нет, вью возвращается к исходному положению в конце
            fillAfter = true
            interpolator = AccelerateInterpolator()
            addAnimation(TranslateAnimation(0f, 200f, 0f, 200f).apply {
                //можно задать отложенный запуск для каждой анимации
                startOffset = 2000
            })
            addAnimation(AlphaAnimation(0.0f, 1.0f))
            addAnimation(ScaleAnimation(2f, 1f, 2f, 1f))
            //поворачивает вью только в одной плоскости(не в z)
            addAnimation(RotateAnimation(0f, 3f))
        }
        square_view.startAnimation(animation)
    }

    override fun onStop() {
        super.onStop()
        animation.cancel()
    }

    override fun onStart() {
        super.onStart()
        animateView()
    }
}
