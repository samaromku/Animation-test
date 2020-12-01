package ru.appngo.animationstest.activities.withfragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_with_fragments.*
import ru.appngo.animationstest.R


class FragmentsAnimationActivity : AppCompatActivity() {

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_fragments)

        animate_to_next_fragment.setOnClickListener {
            if (counter % 2 == 0) {
                supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.animator.first_fragment_anim_enter, R.animator.first_fragment_anim_exit)
                        .replace(R.id.fragment_container, SecondFragment())
                        .commit()
                animate_to_next_fragment.text = "navigate to first"
            } else {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FirstFragment())
                        .commit()
                animate_to_next_fragment.text = "navigate to second"
            }
            counter++
        }
    }
}
