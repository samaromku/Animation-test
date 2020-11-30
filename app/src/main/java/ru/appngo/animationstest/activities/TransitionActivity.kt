package ru.appngo.animationstest.activities

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.transition.*
import kotlinx.android.synthetic.main.activity_transition.*
import ru.appngo.animationstest.R

class TransitionActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition)
        animateTransition(animated_view)
    }

    private fun animateTransition(view: View) {
        val transitionSet: TransitionSet = TransitionSet().addTransition(Slide())
        TransitionManager.beginDelayedTransition(container, transitionSet)
        view.visibility = View.VISIBLE
    }
}
