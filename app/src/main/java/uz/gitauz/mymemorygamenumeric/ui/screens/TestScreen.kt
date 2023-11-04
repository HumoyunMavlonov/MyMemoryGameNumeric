package uz.gitauz.mymemorygamenumeric.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gitauz.mymemorygamenumeric.R
import uz.gitauz.mymemorygamenumeric.databinding.ScreenTestBinding

class TestScreen : Fragment(R.layout.screen_test) {
    private val binding by viewBinding(ScreenTestBinding::bind)
    private var isOpen = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonTest.setOnClickListener {
            if (isOpen) {
                // 0
                binding.buttonTest.animate().rotationY(89f).setDuration(1000)
                    .withEndAction {
                        binding.buttonTest.rotationY = -91f
                        binding.buttonTest.setImageResource(R.drawable.image_1)
                        binding.buttonTest.animate().rotationY(0f).setDuration(500)
                            .withEndAction { isOpen = false }
                            .start()
                    }.start()
            } else {
                // 0
                binding.buttonTest.animate().rotationY(-91f).setDuration(500)
                    .withEndAction {
                        binding.buttonTest.rotationY = 89f
                        binding.buttonTest.setImageResource(R.drawable.image_back)
                        binding.buttonTest.animate().rotationY(0f).setDuration(500)
                            .withEndAction { isOpen = true }
                            .start()
                    }.start()
            }
        }
    }
}