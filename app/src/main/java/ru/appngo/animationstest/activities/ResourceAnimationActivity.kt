package ru.appngo.animationstest.activities

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import ru.appngo.animationstest.R


class ResourceAnimationActivity : Activity() {

    private lateinit var rocketAnimation: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource_animation)
        findViewById<Button>(R.id.test_view).setOnClickListener {
            rocketAnimation.start()
        }
        findViewById<ImageView>(R.id.human_to_android).apply {
            setBackgroundResource(R.drawable.from_human_to_android)
            rocketAnimation = background as AnimationDrawable
        }
    }
}
