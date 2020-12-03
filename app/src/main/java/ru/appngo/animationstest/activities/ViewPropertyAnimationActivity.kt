package ru.appngo.animationstest.activities

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_property.*
import ru.appngo.animationstest.R

class ViewPropertyAnimationActivity:Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_property)
        view_property_button.animate()
                .scaleY(2f)
                .scaleX(2f)
                .setDuration(10000)
                .start()
    }
}
