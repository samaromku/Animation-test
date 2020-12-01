package ru.appngo.animationstest.activities.withfragments

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet
import kotlinx.android.synthetic.main.fragment_first.*
import ru.appngo.animationstest.R

class FirstFragment : Fragment(
        R.layout.fragment_first
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTransitionName(shared_image, getString(R.string.shared_image))
        shared_image.setOnClickListener {
            val thirdFragment = ThirdFragment().apply {
                val set = TransitionSet()
                        .addTransition(ChangeBounds())
                        .addTransition(ChangeTransform())
                        .addTransition(ChangeImageTransform())
                sharedElementEnterTransition = set
            }
            requireActivity().supportFragmentManager
                    .beginTransaction()
                    .addSharedElement(shared_image, getString(R.string.shared_image))
                    .replace(R.id.fragment_container, thirdFragment)
                    .commit()
        }
    }
}
