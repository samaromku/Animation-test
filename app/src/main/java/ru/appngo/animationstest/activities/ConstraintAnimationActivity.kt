package ru.appngo.animationstest.activities

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import ru.appngo.animationstest.R

class ConstraintAnimationActivity : Activity() {

    lateinit var root: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_animation_1)
        root = findViewById(R.id.root_1)
        addAnimationOperations()
    }

    private fun addAnimationOperations() {
        var set = false
        val constraint1 = ConstraintSet()
        constraint1.clone(this, R.layout.activity_constraint_animation_1)
        val constraint2 = ConstraintSet()
        constraint2.clone(this, R.layout.activity_constraint_animation_2)

        findViewById<Button>(R.id.animated_image).setOnClickListener {
            TransitionManager.beginDelayedTransition(root)
            val constraint = if (set) {
                constraint1
            } else {
                constraint2
            }
            constraint.applyTo(root)
            set = !set
        }
    }
}
