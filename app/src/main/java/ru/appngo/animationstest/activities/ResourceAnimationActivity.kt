package ru.appngo.animationstest.activities

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_resource_animation.*
import ru.appngo.animationstest.R


class ResourceAnimationActivity : Activity() {

    private lateinit var rocketAnimation: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource_animation)
        test_view.setOnClickListener {
            rocketAnimation.start()
        }
        human_to_android.apply {
            setBackgroundResource(R.drawable.from_human_to_android)
            rocketAnimation = background as AnimationDrawable
        }
    }
}
