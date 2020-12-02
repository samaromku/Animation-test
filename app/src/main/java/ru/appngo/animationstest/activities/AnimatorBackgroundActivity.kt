package ru.appngo.animationstest.activities

import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_animator_background.*
import ru.appngo.animationstest.R

class AnimatorBackgroundActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator_background)
        test_button.setOnClickListener {
            changeViewBackgroundColor(test_view)
        }
    }

    private fun changeViewBackgroundColor(view: View) {
        val firstColor = ContextCompat.getColor(this, R.color.colorAccent)
        val secondColor = ContextCompat.getColor(this, R.color.purple_700)
        ObjectAnimator.ofArgb(view, "backgroundColor", firstColor, secondColor).apply {
            duration = 3000
        }.start()
    }
}
