package ru.appngo.animationstest.activities

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_timer.*
import ru.appngo.animationstest.R

class TimerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        timer_button.setOnClickListener {
            timer_image.animateView()
        }
    }
}
